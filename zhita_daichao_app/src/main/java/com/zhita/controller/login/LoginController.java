package com.zhita.controller.login;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import com.zhita.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.User;
import com.zhita.service.login.IntLoginService;
import com.zhita.service.merchant.IntMerchantService;
import com.zhita.service.sourcedadson.SourceDadSonService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/app_login")
public class LoginController {
    @Autowired
    IntLoginService loginService;

    @Autowired
    IntMerchantService intMerchantService;

    @Autowired
    SourceDadSonService sourceDadSonService;

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    // 发送验证码
    @RequestMapping("/sendSMS")
    @ResponseBody
    public Map<String, String> sendSMS(String phone, String company, HttpServletRequest request) {
        String currentIp = getIpAddress(request);
        RedisClientUtil redis = new RedisClientUtil();
        Map<String, String> map = new HashMap<>();
        if (null == redis.getSourceClick(currentIp)) {
            SMSUtil smsUtil = new SMSUtil();
            String state = smsUtil.sendSMS(phone, "json", company);
            if (state.equals("提交成功")) {
                redis.set(currentIp, "1", 3600);
            }
            map.put("msg", state);
            return map;
        } else {
            map.put("msg", "发送失败");
            return map;
        }

    }

    @RequestMapping("/sendShortMessage")
    @ResponseBody
    public Map<String, String> sendShortMessage(String phone, String company, String appNumber, String code) {
        Map<String, String> map = new HashMap<>();
        DateFormat format = new SimpleDateFormat("yyyy/M/dd");
        if (MD5Utils.getMD5(phone + appNumber + format.format(new Date()) + "rong51@dai").equals(code)) {
            SMSUtil smsUtil = new SMSUtil();
            String state = smsUtil.sendSMS(phone, "json", company);
            map.put("msg", state);
            return map;
        } else {
            map.put("msg", "发送失败");
            return map;
        }

    }

    // APP注册

    /**
     * @param phone            手机号
     * @param pwd              密码
     * @param sourceId         渠道名字（本来是渠道id，但是为了审核，前端不能改）
     * @param code             验证码
     * @param registrationType 软件类型
     * @return
     */
    @RequestMapping("/registered")
    @ResponseBody
    @Transactional
    public Map<String, Object> registered(String phone, String pwd, String sourceId, String code, String company, String registrationType, String sonSourceName) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(pwd) || StringUtils.isEmpty(sourceId)
                || StringUtils.isEmpty(code)) {
            map.put("msg", "phone,pwd,sourceId或code不能为空");
            map.put("SCode", "401");
            return map;
        } else {
//			int num = sourceDadSonService.getSourceDadSon(sourceId,sonSourceName,company);
//			if (num == 0) {
//			sourceDadSonService.setSourceDadSon(sourceId,sonSourceName,company);
//			}
            PhoneDeal phoneDeal = new PhoneDeal();
            String newPhone = phoneDeal.encryption(phone);
            int merchantId = intMerchantService.getsourceId(sourceId);
            RedisClientUtil redisClientUtil = new RedisClientUtil();
            String key = phone + "Key";
            String redisCode = redisClientUtil.get(key);
            if (redisCode == null) {
                map.put("msg", "验证码已过期，请重新发送");
                map.put("SCode", "402");
                return map;
            }
            if (redisCode.equals(code)) {
                redisClientUtil.delkey(key);// 验证码正确就从redis里删除这个key
                String loginStatus = "1";
                MD5Util md5Util = new MD5Util();
                String md5Pwd = md5Util.EncoderByMd5(pwd);

                User user = loginService.findphone(newPhone, company); // 判断该用户是否存在
                if (user == null) {
                    String registrationTime = System.currentTimeMillis() + ""; // 获取当前时间戳
                    int number = loginService.setAPPUser1(newPhone, md5Pwd, merchantId, registrationTime, loginStatus, registrationType, company, sonSourceName);
                    if (number == 1) {
                        int id = loginService.getId(newPhone, company); // 获取该用户的id
                        map.put("msg", "用户注册成功，数据插入成功");
                        map.put("loginStatus", loginStatus);
                        map.put("userId", id);
                        map.put("phone", phone);
                        map.put("SCode", "200");
                    } else {
                        map.put("msg", "用户注册失败，用户数据插入失败");
                        map.put("SCode", "405");
                    }
                } else {
                    map.put("msg", "该手机号已被注册");
                    map.put("SCode", "406");
                }
            } else {
                map.put("msg", "验证码输入错误");
                map.put("SCode", "403");
            }

        }
        return map;
    }

    // 忘记密码

    /**
     * @param phone 手机号
     * @param pwd   密码
     * @param code  验证码
     * @return
     */
    @RequestMapping("/forgotpwd")
    @ResponseBody
    @Transactional
    public Map<String, Object> forgotpwd(String phone, String pwd, String code, String company, String oneSourceName, String twoSourceName) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(pwd) || StringUtils.isEmpty(code)) {
            map.put("msg", "phone,pwd或code不能为空");
            map.put("SCode", "401");
            return map;
        } else {
            PhoneDeal phoneDeal = new PhoneDeal();
            String newPhone = phoneDeal.encryption(phone);
            RedisClientUtil redisClientUtil = new RedisClientUtil();
            String key = phone + "Key";
            String redisCode = redisClientUtil.get(key);
            if (redisCode == null) {
                map.put("msg", "验证码已过期，请重新发送");
                map.put("SCode", "402");
                return map;
            }
            if (redisCode.equals(code)) {
                redisClientUtil.delkey(key);// 验证码正确就从redis里删除这个key
                MD5Util md5Util = new MD5Util();
                String md5Pwd = md5Util.EncoderByMd5(pwd);
                int number = loginService.updatePwd(newPhone, md5Pwd, company);
                if (number == 1) {
                    int id = loginService.getId(newPhone, company); // 获取该用户的id
                    map.put("msg", "密码修改成功");
                    map.put("userId", id);
                    map.put("phone", phone);
                    map.put("SCode", "200");
                } else {
                    map.put("msg", "密码修改失败");
                    map.put("SCode", "405");
                }
            } else {
                map.put("msg", "验证码输入错误");
                map.put("SCode", "403");
            }
        }

        return map;

    }

    /**
     * @param phone 手机号
     * @param pwd   密码
     * @return
     */
    @RequestMapping("/pwdlogin")
    @ResponseBody
    @Transactional
    public Map<String, Object> pwdLogin(String phone, String pwd, String company, String oneSourceName, String twoSourceName) {
        Map<String, Object> map = new HashMap<String, Object>();
        String loginStatus = "1";
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(pwd)) {
            map.put("msg", "phone或pwd不能为空");
            return map;
        } else {
            PhoneDeal phoneDeal = new PhoneDeal();
            String newPhone = phoneDeal.encryption(phone);
            User user = loginService.findphone(newPhone, company); // 判断该用户是否存在
            if (user == null) {
                map.put("msg", "用户名不存在,请先注册");
                map.put("SCode", "405");
                return map;
            } else {
                MD5Util md5Util = new MD5Util();
                String dataMd5Pwd = loginService.getMd5pwd(newPhone, company);
                String Md5Pwd = md5Util.EncoderByMd5(pwd); // md5加密
                if (Md5Pwd.equals(dataMd5Pwd)) {
                    String loginTime = System.currentTimeMillis() + "";
                    int num = loginService.updateStatus(loginStatus, newPhone, company, loginTime);
                    if (num == 1) {
                        int id = loginService.getId(newPhone, company); // 获取该用户的id
                        map.put("msg", "用户登录成功，登录状态修改成功");
                        map.put("SCode", "200");
                        map.put("loginStatus", loginStatus);
                        map.put("userId", id);
                        map.put("phone", phone);
                    } else {
                        map.put("msg", "用户登录失败，登录状态修改失败");
                        map.put("SCode", "406");
                    }
                } else {
                    map.put("msg", "密码错误");
                    map.put("SCode", "403");
                    return map;
                }
            }

        }

        return map;

    }

    // 验证码登陆

    /**
     * @param phone            手机号
     * @param code             验证码
     * @param company          公司名
     * @param registrationType 软件类型
     * @return
     */
    @RequestMapping("/codelogin")
    @ResponseBody
    @Transactional
    public Map<String, Object> codeLogin(String phone, String code, String company, String registrationType, String sourceId, String sonSourceName) {
        Map<String, Object> map = new HashMap<String, Object>();
        String loginStatus = "1";
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)) {
            map.put("msg", "phone或code不能为空");
            return map;
        } else {
//			int num1 = sourceDadSonService.getSourceDadSon(sourceId,sonSourceName,company);
//			if (num1 == 0) {
//			sourceDadSonService.setSourceDadSon(sourceId,sonSourceName,company);
//			}
            PhoneDeal phoneDeal = new PhoneDeal();
            String newPhone = phoneDeal.encryption(phone);
            RedisClientUtil redisClientUtil = new RedisClientUtil();
            String key = phone + "Key";
            String redisCode = redisClientUtil.get(key);
            if (redisCode == null) {
                map.put("msg", "验证码已过期，请重新发送");
                map.put("SCode", "402");
                return map;
            }
            if (redisCode.equals(code)) {
                redisClientUtil.delkey(key);// 验证码正确就从redis里删除这个key
                String registrationTime = System.currentTimeMillis() + "";  //获取当前时间戳
                User user = loginService.findphone(newPhone, company); // 判断该用户是否存在
                if (user == null) {
                    int merchantId = intMerchantService.getsourceId(sourceId);
                    int number = loginService.insertUser1(newPhone, loginStatus, company, registrationType, registrationTime, merchantId, sonSourceName);
                    if (number == 1) {
                        int id = loginService.getId(newPhone, company); //获取该用户的id
                        map.put("msg", "用户登录成功，数据插入成功，让用户添加密码");
                        map.put("SCode", "201");
                        map.put("loginStatus", loginStatus);
                        map.put("userId", id);
                        map.put("phone", phone);
                    } else {
                        map.put("msg", "用户登录失败，用户数据插入失败");
                        map.put("SCode", "405");
                    }
                } else {
                    String loginTime = System.currentTimeMillis() + "";
                    int num = loginService.updateStatus(loginStatus, newPhone, company, loginTime);
                    if (num == 1) {
                        int id = loginService.getId(newPhone, company); // 获取该用户的id
                        String pwd = loginService.getPwd(id);
                        if (pwd == null) {
                            map.put("msg", "用户登录成功，登录状态修改成功，让用户添加密码");
                            map.put("SCode", "201");
                            map.put("loginStatus", loginStatus);
                            map.put("userId", id);
                            map.put("phone", phone);
                        } else {
                            map.put("msg", "用户登录成功，登录状态修改成功");
                            map.put("SCode", "200");
                            map.put("loginStatus", loginStatus);
                            map.put("userId", id);
                            map.put("phone", phone);
                        }
                    } else {
                        map.put("msg", "用户登录失败，登录状态修改失败");
                        map.put("SCode", "406");
                    }
                }
            } else {
                map.put("msg", "验证码错误");
                map.put("SCode", "403");
                return map;
            }

            return map;
        }

    }

    //用户登录之后，发现该账号没密码，让他添加密码
    @RequestMapping("/setpwd")
    @ResponseBody
    @Transactional
    public Map<String, Object> setPwd(String pwd, int userId, String oneSourceName, String twoSourceName) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(pwd) || StringUtils.isEmpty(userId)) {
            map.put("msg", "pwd或userId不能为空");
            return map;
        } else {
            MD5Util md5Util = new MD5Util();
            String md5Pwd = md5Util.EncoderByMd5(pwd);
            int number = loginService.setPwd(userId, md5Pwd);
            if (number == 1) {
                map.put("msg", "密码添加成功");
                map.put("SCode", "200");
            } else {
                map.put("msg", "密码添加失败");
                map.put("SCode", "400");
            }

        }

        return map;

    }

    //用户找回密码的时候，判断是否存在该用户
    @RequestMapping("/getuser")
    @ResponseBody
    @Transactional
    public Map<String, Object> getuser(String phone, String company, String oneSourceName, String twoSourceName) {
        Map<String, Object> map = new HashMap<String, Object>();
        PhoneDeal phoneDeal = new PhoneDeal();
        String newPhone = phoneDeal.encryption(phone);
        User user = loginService.findphone(newPhone, company); // 判断该用户是否存在
        if (user == null) {
            map.put("msg", "用户名不存在,请先注册");
            map.put("SCode", "405");
        } else {
            map.put("msg", "用户存在");
            map.put("SCode", "201");
        }
        return map;

    }


    // 退出登录

    /**
     * @return
     */
    @RequestMapping("/logOut")
    @ResponseBody
    @Transactional
    public Map<String, String> appLogOut(int userId, String company, String oneSourceName, String twoSourceName) {
        Map<String, String> map = new HashMap<>();
        if (StringUtils.isEmpty(userId)) {
            map.put("msg", "userId不能为空");
            return map;
        } else {
            String loginStatus = "0";
            int number = loginService.updatelogOutStatus(loginStatus, userId, company);
            if (number == 1) {
                map.put("msg", "用户退出成功，登录状态修改成功");
                map.put("SCode", "200");
                map.put("loginStatus", loginStatus);
            } else {
                map.put("msg", "用户退出失败，登录状态修改失败");
                map.put("SCode", "400");
            }
        }

        return map;

    }

}

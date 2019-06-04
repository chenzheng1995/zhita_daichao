package com.zhita.controller.login;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.zhita.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.User;
import com.zhita.service.login.IntLoginService;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/app_login")
public class LoginController {
    @Autowired
    IntLoginService loginService;

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
   /* @RequestMapping("/sendSMS")
    @ResponseBody
    public Map<String, String> sendSMS(String phone, String company,HttpServletRequest request) {
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
    }*/

    // 发送验证码
    @RequestMapping("/sendH5ShortMessage")
    @ResponseBody
    public Map<String, String> sendH5ShortMessage(String phone, String company, String sessionId, String code) {
        Map<String, String> map = new HashMap<>();
        RedisClientUtil redis = new RedisClientUtil();
        String serviceCode = redis.get(sessionId);
        if (StringUtils.isEmpty(serviceCode)) {
            map.put("msg", "会话过期请刷新页面");
        } else if (!serviceCode.equals(code)) {
            map.put("msg", "验证码错误");
        } else {
            SMSUtil smsUtil = new SMSUtil();
            String state = smsUtil.sendSMS(phone, "json", company);
            map.put("msg", state);
        }
        return map;
    }

    // APP注册

    /**
     * @param phone            手机号
     * @param pwd              密码
     * @param sourceId         渠道id
     * @param code             验证码
     * @param registrationType 软件类型
     * @return
     */
    @RequestMapping("/registered")
    @ResponseBody
    @Transactional
    public Map<String, Object> registered(String phone, String pwd, int sourceId, String code, String company, String registrationType) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(pwd) || StringUtils.isEmpty(sourceId)
                || StringUtils.isEmpty(code)) {
            map.put("msg", "phone,pwd,sourceId或code不能为空");
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
                String loginStatus = "1";
                MD5Util md5Util = new MD5Util();
                String md5Pwd = md5Util.EncoderByMd5(pwd);
                User user = loginService.findphone(newPhone, company); // 判断该用户是否存在
                if (user == null) {
                    String registrationTime = System.currentTimeMillis() + ""; // 获取当前时间戳
                    int number = loginService.setAPPUser(newPhone, md5Pwd, sourceId, registrationTime, loginStatus, registrationType, company);
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
    public Map<String, Object> forgotpwd(String phone, String pwd, String code, String company) {
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
    public Map<String, Object> pwdLogin(String phone, String pwd, String company) {
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
    public Map<String, Object> codeLogin(String phone, String code, String company, String registrationType, int sourceId) {
        Map<String, Object> map = new HashMap<String, Object>();
        String loginStatus = "1";
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)) {
            map.put("msg", "phone或code不能为空");
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
                String registrationTime = System.currentTimeMillis() + "";  //获取当前时间戳
                User user = loginService.findphone(newPhone, company); // 判断该用户是否存在
                if (user == null) {
                    int number = loginService.insertUser(newPhone, loginStatus, company, registrationType, registrationTime, sourceId);
                    if (number == 1) {
                        int id = loginService.getId(newPhone, company); //获取该用户的id
                        map.put("msg", "用户登录成功，数据插入成功，让用户添加密码");
                        map.put("SCode", "201");
                        map.put("loginStatus", loginStatus);
                        map.put("userId", id);
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
                        } else {
                            map.put("msg", "用户登录成功，登录状态修改成功");
                            map.put("SCode", "200");
                            map.put("loginStatus", loginStatus);
                            map.put("userId", id);
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
    public Map<String, Object> setPwd(String pwd, int userId) {
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
    public Map<String, Object> getuser(String phone, String company) {
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
    public Map<String, String> appLogOut(int userId, String company) {
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

    private int getRandomCode() {
        return (int) ((Math.random() * 9 + 1) * 1000);
    }

    public InputStream bufferedImageToInputStream(BufferedImage image) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", os);
            InputStream input = new ByteArrayInputStream(os.toByteArray());
            return input;
        } catch (IOException e) {
            System.out.println("图片流转换异常");
            e.getStackTrace();
        }
        return null;
    }

    @RequestMapping("/initializationH5")
    @ResponseBody
    public Map initializationH5() {
        Map<String, String> codeMap = applyCodeImage();
        Map<String, String> resultMap = new HashMap<>();
        String sessionId = java.util.UUID.randomUUID().toString();
        RedisClientUtil.set(sessionId, codeMap.get("photoCode"), 3600);
        resultMap.put("sessionId", sessionId);
        resultMap.put("photoUrl", codeMap.get("photoUrl"));
        return resultMap;
    }

    @RequestMapping("/getH5Code")
    @ResponseBody
    public String getH5Code(String sessionId) {
        Map<String, String> codeMap = applyCodeImage();
        RedisClientUtil.set(sessionId, codeMap.get("photoCode"), 3600);
        return codeMap.get("photoUrl");
    }

    private Map<String, String> applyCodeImage() {
        int width = 90;// 定义图片的width
        int height = 20;// 定义图片的height
        int codeCount = 4;// 定义图片上显示验证码的个数
        int xx = 15;
        int fontHeight = 18;
        int codeY = 16;
        Map<String, String> result = new HashMap<>();
        char[] codeSequence = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics gd = buffImg.getGraphics();
        //随机操作对象
        Random random = new Random();
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
        // 设置字体。
        gd.setFont(font);
        // 画边框。
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, width - 1, height - 1);
        // 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
        gd.setColor(Color.BLACK);
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;
        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。
            String code = String.valueOf(codeSequence[random.nextInt(10)]);
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            // 用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, (i + 1) * xx, codeY);
            // 将产生的四个随机数组合在一起。
            randomCode.append(code);
        }
        /*Map<String, Object> map = new HashMap<String, Object>();
        //存放验证码
        map.put("code", randomCode);
        //存放生成的验证码BufferedImage对象
        map.put("codePic", buffImg);
        return map;*/
        result.put("photoUrl",uploadImage(bufferedImageToInputStream(buffImg)));
        result.put("photoCode", randomCode.toString());
        return result;
    }


    private String uploadImage(InputStream inputStream) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = format.format(new Date()) + UUID.randomUUID().toString() + ".JPG";// 文件原名称
        // 判断文件类型
        String path = "D://nginx-1.14.2/html/dist/H5Code/" + fileName;
        FolderUtil folderUtil = new FolderUtil();
        try {
            folderUtil.uploadImage(inputStream, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "http://tg.mis8888.com/H5Code/" + fileName;
    }
}

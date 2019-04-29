package com.zhita.controller.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.User;
import com.zhita.service.login.IntLoginService;
import com.zhita.util.MD5Util;
import com.zhita.util.PhoneDeal;
import com.zhita.util.RedisClientUtil;
import com.zhita.util.SMSUtil;

@Controller
@RequestMapping("/app_login")
public class LoginController {
	@Autowired
	IntLoginService loginService;

	// 发送验证码
	@RequestMapping("/sendSMS")
	@ResponseBody
	public Map<String, String> sendSMS(String phone,String company) {
		Map<String, String> map = new HashMap<>();
		SMSUtil smsUtil = new SMSUtil();
		String state = smsUtil.sendSMS(phone, "json",company);
		map.put("msg", state);
		return map;
	}

	// APP注册
	/**
	 * @param phone    手机号
	 * @param pwd      密码
	 * @param sourceId 渠道id
	 * @param code     验证码
	 * @param registrationType  软件类型
	 * @return
	 */
	@RequestMapping("/registered")
	@ResponseBody
	@Transactional
	public Map<String, Object> registered(String phone, String pwd, int sourceId, String code,String company,String registrationType) {
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
				User user = loginService.findphone(newPhone,company); // 判断该用户是否存在
				if (user == null) {
					String registrationTime = System.currentTimeMillis() + ""; // 获取当前时间戳
					int number = loginService.setAPPUser(newPhone, md5Pwd, sourceId, registrationTime, loginStatus,registrationType,company);
					if (number == 1) {
						int id = loginService.getId(newPhone,company); // 获取该用户的id
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
	public Map<String, Object> forgotpwd(String phone, String pwd, String code,String company) {
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
				int number = loginService.updatePwd(newPhone, md5Pwd,company);
				if (number == 1) {
					int id = loginService.getId(newPhone,company); // 获取该用户的id
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
	public Map<String, Object> pwdLogin(String phone, String pwd,String company) {
		Map<String, Object> map = new HashMap<String, Object>();
		String loginStatus = "1";
		if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(pwd)) {
			map.put("msg", "phone或pwd不能为空");
			return map;
		} else {
			PhoneDeal phoneDeal = new PhoneDeal();			
			String newPhone = phoneDeal.encryption(phone);
			User user = loginService.findphone(newPhone,company); // 判断该用户是否存在
			if (user == null) {
				map.put("msg", "用户名不存在,请先注册");
				map.put("SCode", "405");
				return map;
			} else {
				MD5Util md5Util = new MD5Util();
				String dataMd5Pwd = loginService.getMd5pwd(newPhone,company);
				String Md5Pwd = md5Util.EncoderByMd5(pwd); // md5加密
				if (Md5Pwd.equals(dataMd5Pwd)) {
					String loginTime = System.currentTimeMillis()+"";
					int num = loginService.updateStatus(loginStatus, newPhone,company,loginTime);
					if (num == 1) {
						int id = loginService.getId(newPhone,company); // 获取该用户的id
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
	 * @param phone 手机号
	 * @param code  验证码
	 * @param company  公司名
	 * @param registrationType 软件类型
	 * @return
	 */
	@RequestMapping("/codelogin")
	@ResponseBody
	@Transactional
	public Map<String, Object> codeLogin(String phone, String code,String company,String registrationType,int sourceId) {
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
				String registrationTime = System.currentTimeMillis()+"";  //获取当前时间戳
				User user = loginService.findphone(newPhone,company); // 判断该用户是否存在
				if (user == null) {
					int number = loginService.insertUser(newPhone,loginStatus,company,registrationType,registrationTime,sourceId);
					if (number == 1) {								
						int id = loginService.getId(newPhone,company); //获取该用户的id					
							map.put("msg", "用户登录成功，数据插入成功，让用户添加密码");
							map.put("SCode", "201");
							map.put("loginStatus", loginStatus);
							map.put("userId", id);				
					} else {
						map.put("msg", "用户登录失败，用户数据插入失败");
						map.put("SCode", "405");
					}
				} else {
					String loginTime = System.currentTimeMillis()+"";
					int num = loginService.updateStatus(loginStatus, newPhone,company,loginTime);
					if (num == 1) {
						int id = loginService.getId(newPhone,company); // 获取该用户的id
						String pwd = loginService.getPwd(id);
						if(pwd==null) {
						map.put("msg","用户登录成功，登录状态修改成功，让用户添加密码");
						map.put("SCode","201");
						map.put("loginStatus", loginStatus);
						map.put("userId", id);
						}else {
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
		}else {
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
		User user = loginService.findphone(newPhone,company); // 判断该用户是否存在
		if (user == null) {
			map.put("msg", "用户名不存在,请先注册");
			map.put("SCode", "405");
			}else {
				map.put("msg", "用户存在");
				map.put("SCode", "201");	
			}
		return map;
		
	}	
	
	
	// 退出登录
	/**
	 * @param phone 手机号
	 * @param code  验证码
	 * @return
	 */
	@RequestMapping("/logOut")
	@ResponseBody
	@Transactional
	public Map<String, String> appLogOut(int userId,String company) {
		Map<String, String> map = new HashMap<>();
		if (StringUtils.isEmpty(userId)) {
			map.put("msg", "userId不能为空");
			return map;
			}else {
				String loginStatus = "0";
				int number = loginService.updatelogOutStatus(loginStatus,userId,company);
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

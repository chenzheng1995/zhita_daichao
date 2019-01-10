package com.zhita.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.ManageLogin;
import com.zhita.model.manage.User;
import com.zhita.service.login.IntLoginService;
import com.zhita.service.login.LoginServiceImp;
import com.zhita.util.MD5Util;
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
	public Map<String, String> sendSMS(String phone) {
		Map<String, String> map = new HashMap<>();
		SMSUtil smsUtil = new SMSUtil();
		String state = smsUtil.sendSMS(phone, "json");
		map.put("msg", state);
		return map;
	}

	// APP注册
	/**
	 * @param phone    手机号
	 * @param pwd      密码
	 * @param sourceId 渠道id
	 * @param code     验证码
	 * @return
	 */
	@RequestMapping("/registered")
	@ResponseBody
	public Map<String, Object> registered(String phone, String pwd, int sourceId, String code) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(pwd) || StringUtils.isEmpty(sourceId) || StringUtils.isEmpty(code)) {
			map.put("msg", "phone,pwd,sourceId或code不能为空");
			return map;
		} else {
			RedisClientUtil redisClientUtil = new RedisClientUtil();
			String key = phone+"Key";
			String redisCode = redisClientUtil.get(key);
			if(redisCode==null) {
				map.put("msg", "验证码已过期，请重新发送");
				return map;
			}
			if(redisCode.equals(code)) {
				String loginStatus = "1";
				MD5Util md5Util = new MD5Util();
				String md5Pwd = md5Util.EncoderByMd5(pwd);
				User user = loginService.findphone(phone); // 判断该用户是否存在
				if(user==null) {
					String registrationTime = System.currentTimeMillis()+"";  //获取当前时间戳
					int number = loginService.setAPPUser(phone, md5Pwd, sourceId,registrationTime,loginStatus);
					if (number == 1) {		
						int id = loginService.getId(phone); //获取该用户的id
						map.put("msg", "用户注册成功，数据插入成功");
						map.put("loginStatus", loginStatus);
						map.put("userId", id);
						map.put("phone", phone);
					} else {
						map.put("msg", "用户注册失败，用户数据插入失败");
					}
				}else {
					map.put("msg","该手机号已被注册");					
				}
			}else {
				map.put("msg", "验证码输入错误");
			}

		}
		return map;
	}
	
	@RequestMapping("/llll")
	@ResponseBody
	public Map<String, Object> ac(String phone, String pwd, int sourceId, String code) {
		Map<String, Object> map = new HashMap<String, Object>();
					String registrationTime = System.currentTimeMillis()+"";  //获取当前时间戳
					String loginStatus = "1";
					String md5Pwd = pwd;
					int number = loginService.setAPPUser(phone, md5Pwd, sourceId,registrationTime,loginStatus);
					if (number == 1) {		
						int id = loginService.getId(phone); //获取该用户的id
						map.put("msg", "用户注册成功，数据插入成功");
						map.put("loginStatus", loginStatus);
						map.put("userId", id);
						map.put("phone", phone);					
					} else {
						map.put("msg", "用户注册失败，用户数据插入失败");
					}
					code.length();
		return map;
	}
	
	

//	@RequestMapping("/login")
//	@ResponseBody
//	public Map<String, String> login(String userName,String pwd,int sourceId) {
//		Map<String, String> map = new HashMap<String, String>();
//		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(pwd) || StringUtils.isEmpty(sourceId)) {
//			map.put("msg", "userName,pwd或sourceId不能为空");
//			return map;
//		} else {
//			ManageLogin manageLogin = loginService.findFormatByLoginName(userName); // 判断用户名是否存在
//			if (manageLogin == null) {
//				map.put("msg", "用户名不存在");
//				return map;
//			}
//			MD5Util md5Util = new MD5Util();
//			String dataMd5Pwd = manageLogin.getMd5pwd();
//			String Md5Pwd = md5Util.EncoderByMd5(Pwd); // md5加密
//			if (!Md5Pwd.equals(dataMd5Pwd)) {
//				map.put("msg", "密码错误");
//				return map;
//			}else {
//				map.put("msg", "200");
//			}
//		}
//
//		return map;
//
//	}

}

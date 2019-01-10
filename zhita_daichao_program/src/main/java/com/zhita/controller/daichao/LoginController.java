package com.zhita.controller.daichao;

import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zhita.model.manage.User;
import com.zhita.service.login.IntLoginService;
import com.zhita.util.RedisClientUtil;
import com.zhita.util.SMSUtil;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	@Resource(name = "loginServiceImp")
	private IntLoginService intLoginService;

	public IntLoginService getIntLoginService() {
		return intLoginService;
	}

	public void setIntLoginService(IntLoginService intLoginService) {
		this.intLoginService = intLoginService;
	}

	// 微信登录
	@RequestMapping("/WXlogin")
	@ResponseBody
	public Object getPhoneNumber(String encryptedData, String iv, String session_key) {
		// 被加密的数据
		byte[] dataByte = Base64.decode(encryptedData);
		// 加密秘钥
		byte[] keyByte = Base64.decode(session_key);
		// 偏移量
		byte[] ivByte = Base64.decode(iv);
		try {
			// 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
			int base = 16;
			if (keyByte.length % base != 0) {
				int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
				byte[] temp = new byte[groups * base];
				Arrays.fill(temp, (byte) 0);
				System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
				keyByte = temp;
			}
			// 初始化
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
			AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
			parameters.init(new IvParameterSpec(ivByte));
			cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
			byte[] resultByte = cipher.doFinal(dataByte);
			if (null != resultByte && resultByte.length > 0) {
				String result = new String(resultByte, "UTF-8");
				return JSONObject.parseObject(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//发送验证码
	@RequestMapping("/sendSMS")
	@ResponseBody
	public Map<String, String> sendSMS(String phone){
		Map<String, String> map = new HashMap<>();
		SMSUtil smsUtil = new SMSUtil();
		String state = smsUtil.sendSMS(phone, "json");
        map.put("msg",state);		
		return map;
		
	}

	// 验证码登录
	@RequestMapping("/codelogin")
	@ResponseBody
	public Map<String, Object> codeLogin(String verificationCode, String phone,String nickName,String openId) {//verificationCode是验证码，phone是手机号，nickName是昵称
		int num = 0;
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isEmpty(verificationCode) || StringUtils.isEmpty(phone) || StringUtils.isEmpty(nickName) || StringUtils.isEmpty(openId)) {
			map.put("msg", "verificationCode,phone,nickName或openId不能为空");
			map.put("SCode", "401");
			return map;
			}else {
				RedisClientUtil redisClientUtil = new RedisClientUtil();
				String key = phone+"Key";
				String redisCode = redisClientUtil.get(key);
				if(redisCode==null) {
					map.put("msg", "验证码已过期，请重新发送");
					map.put("SCode", "402");
					return map;
				}
				if(redisCode.equals(verificationCode)) {
					String registrationTime = System.currentTimeMillis()+"";  //获取当前时间戳
					User user = intLoginService.findphone(phone); // 判断该用户是否存在
					String loginStatus = "1";
					if (user == null) {			//如果用户不存在			
						int number = intLoginService.insertfootprint(phone, nickName, openId,registrationTime,loginStatus);
						if (number == 1) {		
							int id = intLoginService.getId(phone); //获取该用户的id
							map.put("msg", "用户登录成功，数据插入成功");
							map.put("SCode", "200");
							map.put("loginStatus", loginStatus);
							map.put("userId", id);
						} else {
							map.put("msg", "用户登录失败，用户数据插入失败");
							map.put("SCode", "405");
						}
					}else {
						if(user.getOpenId()==null) {
						    num = intLoginService.updateloginStatus(loginStatus,openId,phone); //当openId为null时，通过phone更新loginStatus和openId
						}else {
						    num = intLoginService.updateStatus(loginStatus,phone);//当openId不为null时，通过phone更新loginStatus
						}
						
						if (num == 1) {	
							int id = intLoginService.getId(phone); //获取该用户的id
							map.put("msg", "用户登录成功，登录状态修改成功");
							map.put("SCode", "200");
							map.put("loginStatus", loginStatus);
							map.put("userId", id);
						} else {
							map.put("msg", "用户登录失败，登录状态修改失败");
							map.put("SCode", "405");
						}
					}

				}else {
					map.put("msg", "验证码输入错误");
					map.put("SCode", "403");
				}
			}

		return map;

	}
	
	// 退出登录
	@RequestMapping("/logOut")
	@ResponseBody
	public Map<String, String> logOut(int userId) {
		Map<String, String> map = new HashMap<>();
		if (StringUtils.isEmpty(userId)) {
			map.put("msg", "userId不能为空");
			return map;
			}else {
				String loginStatus = "0";
				int number = intLoginService.updatelogOutStatus(loginStatus,userId);
				if (number == 1) {														
					map.put("msg", "用户退出成功，登录状态修改成功");
					map.put("SCode", "200");
					map.put("loginStatus", loginStatus);
				} else {
					map.put("msg", "用户退出失败，登录状态修改失败");
					map.put("SCode", "408");
				}
			}

		return map;

	}
	
	
	// 判断用户是否登录
	@RequestMapping("/islogin")
	@ResponseBody
	public Map<String, String> islogin(String openId) {
		Map<String, String> map = new HashMap<>();
		if (StringUtils.isEmpty(openId)) {
			map.put("msg", "openId不能为空");
			return map;
			}else {
				String loginStatus = intLoginService.getLoginStatus(openId);
				if(loginStatus==null) {
					map.put("msg","该用户还未注册");
					map.put("code","2");
				}
				if("0".equals(loginStatus)) {
					map.put("msg","该用户未登录");
					map.put("code","0");
				}				
				if("1".equals(loginStatus)) {
					String userId = intLoginService.getUserId(openId);
					String phone = intLoginService.getPhone(openId);
					map.put("msg","该用户已登录");
					map.put("code","1");
					map.put("userId", userId);
					map.put("phone", phone);
				}
			}

		return map;

	}
	

}

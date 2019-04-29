package com.zhita.controller.daichao;

import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhita.model.manage.User;
import com.zhita.service.login.IntLoginService;
import com.zhita.util.AESUtil;
import com.zhita.util.PhoneDeal;
import com.zhita.util.PostAndGet;
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
	public Map<String, Object> getPhoneNumber(String encryptedData, String iv, String code , String company , Integer fatherId,String nickName,String registrationType) {
		int num = 0;
		int number = 0;
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isEmpty(encryptedData) || StringUtils.isEmpty(iv) || StringUtils.isEmpty(code) || StringUtils.isEmpty(company)
				|| StringUtils.isEmpty(nickName) || StringUtils.isEmpty(registrationType)) {
			map.put("msg", "encryptedData,iv,code,company,nickName和registrationType不能为空");
			map.put("SCode", "401");
			return map;
			}else {
	
				PostAndGet pGet = new PostAndGet();
				String string = pGet.sendGet("https://api.weixin.qq.com/sns/jscode2session?js_code=" + code + "&appid=wx95e29f36ef71f6a2" +
				"&secret=db67bd09f1ebed05dbd0d5d384b14a84" + "&grant_type=authorization_code");
				JSONObject jsonObject = JSON.parseObject(string);
				String openId =  (String) jsonObject.get("openid");   //获取openid
				String session_key =  (String) jsonObject.get("session_key");   //获取session_key	
				

			    try {
			        byte[] resultByte = AESUtil.instance.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(session_key), Base64.decodeBase64(iv));
			        if(null != resultByte && resultByte.length > 0){
			            String userInfo = new String(resultByte, "UTF-8");
			            System.out.println(userInfo);
			            JSONObject json = JSONObject.parseObject(userInfo); //将字符串{“id”：1}
			            String phoneNumber = json.getString("phoneNumber");
						PhoneDeal phoneDeal = new PhoneDeal();			
						String newPhone = phoneDeal.encryption(phoneNumber);
			            
			            String registrationTime = System.currentTimeMillis()+"";  //获取当前时间戳
						User user = intLoginService.findphone(newPhone,company); // 判断该用户是否存在
						String loginStatus = "1";
						if (user == null) {			//如果用户不存在	
							if(fatherId==null) { //如果用户是通过扫二维码登录进来的，fatherId不为null，否则fatherId为null
						        number = intLoginService.insertfootprint(newPhone, nickName, openId,registrationTime,loginStatus,company,registrationType);
							}else {
								number = intLoginService.insertfootprint1(newPhone, nickName, openId,registrationTime,loginStatus,company,registrationType,fatherId);
							}
							if (number == 1) {								
								int id = intLoginService.getId(newPhone,company); //获取该用户的id
								map.put("msg", "用户登录成功，数据插入成功");
								map.put("SCode", "200");
								map.put("loginStatus", loginStatus);
								map.put("userId", id);
								map.put("phone", phoneNumber);
							} else {
								map.put("msg", "用户登录失败，用户数据插入失败");
								map.put("SCode", "405");
							}
						}else {
							String loginTime = System.currentTimeMillis()+"";
							if(user.getOpenId()==null) {  //当openId为null时，通过phone更新loginStatus和openId
								if(fatherId==null) {   //如果用户是通过扫二维码登录进来的，fatherId不为null，否则fatherId为null
									num = intLoginService.updateloginStatus(loginStatus,openId,newPhone,company,loginTime); 
								}else {
									 num = intLoginService.updateloginStatus1(loginStatus,openId,newPhone,company,loginTime,fatherId); 
								}						   
							}else {  //当openId不为null时，通过phone更新loginStatus
								if(fatherId==null) {   //如果用户是通过扫二维码登录进来的，fatherId不为null，否则fatherId为null
									num = intLoginService.updateStatus(loginStatus,newPhone,company,loginTime);
								}else {
									num = intLoginService.updateStatus1(loginStatus,newPhone,company,loginTime,fatherId); 
								}	
							    
							}
							if (num == 1) {	
								int id = intLoginService.getId(newPhone,company); //获取该用户的id
								map.put("msg", "用户登录成功，登录状态修改成功");
								map.put("SCode", "200");
								map.put("loginStatus", loginStatus);
								map.put("userId", id);
								map.put("phone", phoneNumber);
							} else {
								map.put("msg", "用户登录失败，登录状态修改失败");
								map.put("SCode", "405");
							}
						}
			        }
			    } catch (InvalidAlgorithmParameterException e) {
			        e.printStackTrace();
			    } catch (UnsupportedEncodingException e) {
			        e.printStackTrace();
			    }

		return map;
	}
	}
	
	//发送验证码
	@RequestMapping("/sendSMS")
	@ResponseBody
	public Map<String, String> sendSMS(String phone,String company){
		Map<String, String> map = new HashMap<>();
		SMSUtil smsUtil = new SMSUtil();
		String state = smsUtil.sendSMS(phone, "json",company);
        map.put("msg",state);		
		return map;
		
	}

	// 验证码登录
	@RequestMapping("/codelogin")
	@ResponseBody
	@Transactional
	public Map<String, Object> codeLogin(String verificationCode, String phone,String nickName,String code,String company,
			String registrationType,Integer fatherId) {//verificationCode是验证码，phone是手机号，nickName是昵称
		int num = 0;
		int number = 0;
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isEmpty(verificationCode) || StringUtils.isEmpty(phone) || StringUtils.isEmpty(nickName) 
				|| StringUtils.isEmpty(code) || StringUtils.isEmpty(company)) {
			map.put("msg", "verificationCode,phone,nickName,code或company不能为空");
			map.put("SCode", "401");
			return map;
			}else {
				PhoneDeal phoneDeal = new PhoneDeal();			
				String newPhone = phoneDeal.encryption(phone);
				PostAndGet pGet = new PostAndGet();
				String string = pGet.sendGet("https://api.weixin.qq.com/sns/jscode2session?js_code=" + code + "&appid=wx95e29f36ef71f6a2" +
				"&secret=db67bd09f1ebed05dbd0d5d384b14a84" + "&grant_type=authorization_code");
				JSONObject jsonObject = JSON.parseObject(string);
				String openId =  (String) jsonObject.get("openid");   //获取openid
				RedisClientUtil redisClientUtil = new RedisClientUtil();
				String key = phone+"Key";
				String redisCode = redisClientUtil.get(key);
				if(redisCode==null) {
					map.put("msg", "验证码已过期，请重新发送");
					map.put("SCode", "402");
					return map;
				}
				if(redisCode.equals(verificationCode)) {
					redisClientUtil.delkey(key);//验证码正确就从redis里删除这个key
					String registrationTime = System.currentTimeMillis()+"";  //获取当前时间戳
					User user = intLoginService.findphone(newPhone,company); // 判断该用户是否存在
					String loginStatus = "1";
					if (user == null) {			//如果用户不存在	
						if(fatherId==null) { //如果用户是通过扫二维码登录进来的，fatherId不为null，否则fatherId为null
					        number = intLoginService.insertfootprint(newPhone, nickName, openId,registrationTime,loginStatus,company,registrationType);
						}else {
							number = intLoginService.insertfootprint1(newPhone, nickName, openId,registrationTime,loginStatus,company,registrationType,fatherId);
						}
						if (number == 1) {								
							int id = intLoginService.getId(newPhone,company); //获取该用户的id
							map.put("msg", "用户登录成功，数据插入成功");
							map.put("SCode", "200");
							map.put("loginStatus", loginStatus);
							map.put("userId", id);
						} else {
							map.put("msg", "用户登录失败，用户数据插入失败");
							map.put("SCode", "405");
						}
					}else {
						String loginTime = System.currentTimeMillis()+"";
						if(user.getOpenId()==null) {  //当openId为null时，通过phone更新loginStatus和openId
							if(fatherId==null) {   //如果用户是通过扫二维码登录进来的，fatherId不为null，否则fatherId为null
								num = intLoginService.updateloginStatus(loginStatus,openId,newPhone,company,loginTime); 
							}else {
								 num = intLoginService.updateloginStatus1(loginStatus,openId,newPhone,company,loginTime,fatherId); 
							}						   
						}else {  //当openId不为null时，通过phone更新loginStatus
							if(fatherId==null) {   //如果用户是通过扫二维码登录进来的，fatherId不为null，否则fatherId为null
								num = intLoginService.updateStatus(loginStatus,newPhone,company,loginTime);
							}else {
								num = intLoginService.updateStatus1(loginStatus,newPhone,company,loginTime,fatherId); 
							}	
						    
						}
						if (num == 1) {	
							int id = intLoginService.getId(newPhone,company); //获取该用户的id
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
	@Transactional
	public Map<String, String> logOut(int userId,String company) {
		Map<String, String> map = new HashMap<>();
		if (StringUtils.isEmpty(userId)) {
			map.put("msg", "userId不能为空");
			return map;
			}else {
				String loginStatus = "0";
				int number = intLoginService.updatelogOutStatus(loginStatus,userId,company);
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
	public Map<String, String> islogin(String openId, String company) {
		Map<String, String> map = new HashMap<>();
		if (StringUtils.isEmpty(openId)) {
			map.put("msg", "openId不能为空");
			return map;
			}else {
				String loginStatus = intLoginService.getLoginStatus(openId,company);
				if(loginStatus==null) {
					map.put("msg","该用户还未注册");
					map.put("code","2");
				}
				if("0".equals(loginStatus)) {
					map.put("msg","该用户未登录");
					map.put("code","0");
				}				
				if("1".equals(loginStatus)) {
					String userId = intLoginService.getUserId(openId,company);
					String phone = intLoginService.getPhone(openId,company);
					PhoneDeal phoneDeal = new PhoneDeal();			
					String newPhone = phoneDeal.decryption(phone);
					map.put("msg","该用户已登录");
					map.put("code","1");
					map.put("userId", userId);
					map.put("phone", newPhone);
				}
			}

		return map;

	}
	

}

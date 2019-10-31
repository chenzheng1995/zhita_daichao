package com.zhita.controller.joint_login;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zhita.model.manage.User;
import com.zhita.service.login.IntLoginService;
import com.zhita.service.merchant.IntMerchantService;
import com.zhita.util.PhoneDeal;


@Controller
@RequestMapping("/joint_login")
public class JointLoginController {

    @Autowired
    IntLoginService loginService;
    
    @Autowired
    IntMerchantService intMerchantService;

	//联合登录
	@RequestMapping(value="/login")
	@ResponseBody	
	public Map<String, Object> login(@RequestBody String jsonstring) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		JSONObject json = JSONObject.parseObject(jsonstring);
    	String channel_code = json.getString("channel_code");
    	String mobile = json.getString("mobile");
    	String platform = json.getString("platform");
    	String sign = json.getString("sign");
    	String timestamp = json.getString("timestamp");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> map1 = new HashMap<>();
		String company = "借吧";
		String sourceName = "XGLD001";
		String registrationType = "APP";
		String sonSourceName = "XGLD001";

		if (StringUtils.isEmpty(channel_code) || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(platform)
				|| StringUtils.isEmpty(sign) || StringUtils.isEmpty(timestamp)) {
			map.put("msg", "channel_code,mobile,platform,sign,timestamp不能为空");
			map.put("status",405);
			map.put("data", map1);
			return map;
		} else {
		String channel_secret = channel_code;
		String string = "channel_code="+channel_code+"&mobile="+mobile+"&platform="+platform+"&timestamp="+timestamp+channel_secret;
//        string = string.toLowerCase();

        // 生成一个MD5加密计算摘要
        MessageDigest md = MessageDigest.getInstance("MD5");
        // 计算md5函数
        md.update(string.getBytes("UTF-8"));
        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
        String Md5 = new BigInteger(1, md.digest()).toString(16); 
        if(sign.equals(Md5)) {
        	PhoneDeal phoneDeal = new PhoneDeal();
        	String newPhone = phoneDeal.encryption(mobile);
        	 User user = loginService.findphone(newPhone, company); // 判断该用户是否存在
             if (user == null) {
            	 String registrationTime = System.currentTimeMillis() + ""; // 获取当前时间戳
            	 String loginStatus = "0";
            	 int merchantId = intMerchantService.getsourceId(sourceName);
        	int number = loginService.setAPPUser2(newPhone, merchantId, registrationTime, loginStatus, registrationType, company,sonSourceName);
        	if (number == 1) {
        		map1.put("url","http://tg.mis8888.com/fenfa/fenfa.html");
				map.put("msg", "success");
				map.put("status", 0);
				map.put("data", map1);
				
			} else {
				map.put("msg", "其他异常");
				map.put("status", 407);
				map.put("data", map1);
			}
             }else {
         		map1.put("url","http://tg.mis8888.com/fenfa/fenfa.html");
 				map.put("msg", "success");
 				map.put("status", 0);
 				map.put("data", map1);
             }
        }else {
			map.put("status",406);
			map.put("msg", "sign错误");
			map.put("data", map1);
			return map;
        }
		
		return map;	
	}
	}
	
	
	//撞库
	@RequestMapping(value="/hitLibrary")
	@ResponseBody	
	public Map<String, Object> hitLibrary(@RequestBody String jsonstring) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		JSONObject json = JSONObject.parseObject(jsonstring);
    	String channel_code = json.getString("channel_code");
    	String mobile_hash = json.getString("mobile_hash");
    	String sign = json.getString("sign");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> map1 = new HashMap<>();

		if (StringUtils.isEmpty(channel_code) || StringUtils.isEmpty(sign) || StringUtils.isEmpty(mobile_hash)) {
			map.put("msg", "channel_code,mobile_hash,sign不能为空");
			map.put("status",405);
			map.put("data", map1);
			return map;
		} else {
			String channel_secret = channel_code;
			String string = "channel_code="+channel_code+"&mobile_hash="+mobile_hash+channel_secret;
//	        string = string.toLowerCase();

	        // 生成一个MD5加密计算摘要
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        // 计算md5函数
	        md.update(string.getBytes("UTF-8"));
	        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
	        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
	        String Md5 = new BigInteger(1, md.digest()).toString(16); 
	        if(sign.equals(Md5)) {
				map.put("msg", "success");
				map.put("status", 0);
				map.put("data", map1);
				return map;
		}else {
			map.put("status",406);
			map.put("msg", "sign错误");
			map.put("data", map1);
			return map;
        }

		}
	}
}

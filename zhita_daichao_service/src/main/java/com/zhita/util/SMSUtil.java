package com.zhita.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;


public class SMSUtil {
	
	// 互亿无线-短信验证码的APIID
	private static final String APIID = "C50513591";
	
	// 互亿无线-短信验证码的APIKEY
	private static final String APIKEY = "27a9f11b32046fdc16b371d847f7f395";
	

//	public static void main(String[] args) {
//        sendSMS("13486070402","json");
//	}
	/**
	 * 
	 * @param APIID
	 * @param APIKEY
	 * @param mobile 接收手机号码，只能提交 1 个号码 
	 * @param content 短信内容
	 * @param format 返回格式（可选值为：xml 或 json，系统默认为 xml）
	 * @return
	 */
	public String sendSMS (String mobile,String format) {
		String state = null;
	    PostAndGet postAndGet = new PostAndGet();	   
	    String code =((int)((Math.random()*9+1)*100000))+"";
	    String content = "您的验证码是："+code+"。请不要把验证码泄露给其他人。";
	    Map<String, Object> map = JSON.parseObject(postAndGet.sendGet("http://106.ihuyi.com/webservice/sms.php?method=Submit&account="+APIID+"&password="+APIKEY+"&mobile="+mobile+"&content="+content+"&format="+format));
	    String mapCode = map.get("code")+"";
        if("2".equals(mapCode)) {
        	RedisClientUtil redisClientUtil = new RedisClientUtil();
        	redisClientUtil.set(mobile+"Key", code);
        	state = "200";
        }else {
        	state = "0";
		}
		return state;
		
	}

}

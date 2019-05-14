package com.zhita.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;


public class SMSUtil {
	
//	// 互亿无线-短信验证码的APIID
//	private static final String APIID = "C50513591";
//	
//	// 互亿无线-短信验证码的APIKEY
//	private static final String APIKEY = "27a9f11b32046fdc16b371d847f7f395";
	

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
	 * @param company 公司名称
	 * @return
	 */
	public String sendSMS (String mobile,String format,String company) {
		
		String APIID = "";
		String APIKEY = "";
//		if("多米记".equals(company)) {
//			APIID = "C50513591";    // 互亿无线-短信验证码的APIID
//			 APIKEY = "27a9f11b32046fdc16b371d847f7f395";  // 互亿无线-短信验证码的APIKEY
//		}
//		if("借吧".equals(company)) {
//			APIID = "C50513591";    // 互亿无线-短信验证码的APIID
//			 APIKEY = "27a9f11b32046fdc16b371d847f7f395";  // 互亿无线-短信验证码的APIKEY
//		}
		
		if("多米记".equals(company)) {
			APIID = "C50513591";    // 互亿无线-短信验证码的APIID
			 APIKEY = "27a9f11b32046fdc16b371d847f7f395";  // 互亿无线-短信验证码的APIKEY
		}
		if("借吧".equals(company)) {
			APIID = "C48965934";    // 互亿无线-短信验证码的APIID
			 APIKEY = "d0ff0876b9539387b27c7ed900c466ff";  // 互亿无线-短信验证码的APIKEY
		}
		
		Map<String,Object> cmap =new HashMap<String, Object>();
		cmap.put("0", "提交失败");
		cmap.put("2", "提交成功");
		cmap.put("400", "非法ip访问");
		cmap.put("401", "账号不能为空");
		cmap.put("402", "密码不能为空");
		cmap.put("403", "手机号码不能为空");
		cmap.put("4030", "手机号码已被列入黑名单");
		cmap.put("404", "短信内容不能为空");
		cmap.put("405", "API ID或API KEY不正确");
		cmap.put("4050", "账号被冻结");
		cmap.put("40501", "动态密码已过期");
		cmap.put("40502", "动态密码校验失败");
		cmap.put("4051", "剩余条数不足");
		cmap.put("4052", "访问ip与备案ip不符");
		cmap.put("406", "手机号码格式不正确");
		cmap.put("407", "短信内容含有敏感字符");
		cmap.put("4070", "签名格式不正确");
		cmap.put("4071", "没有提交备案模板");
		cmap.put("4072", "提交的短信内容与审核通过的模板内容不匹配");
		cmap.put("40722", "变量内容超过指定的长度【8】");
		cmap.put("4073", "短信内容超出长度限制");
		cmap.put("4074", "短信内容包含emoji符号");
		cmap.put("4075", "签名未通过审核");
		cmap.put("408", "发送超限（【20】条），已加入黑名单，可登入平台解除");
		cmap.put("4080", "同一手机号码同一秒钟之内发送频率不能超过1条");
		cmap.put("4082", "超出同一手机号一天之内【5】条短信限制");
		cmap.put("4085", "同一手机号验证码短信发送超出【5】条");
		cmap.put("4086", "手机操作过过于频繁");
		String state = "未知问题";
	    PostAndGet postAndGet = new PostAndGet();	   
	    String code =((int)((Math.random()*9+1)*1000))+"";
	    String content = "您的验证码是："+code+"。请不要把验证码泄露给其他人。";
	    Map<String, Object> map = JSON.parseObject(postAndGet.sendGet("http://106.ihuyi.com/webservice/sms.php?method=Submit&account="+APIID+"&password="+APIKEY+"&mobile="+mobile+"&content="+content+"&format="+format));
	    String mapCode = map.get("code")+"";
	    
	  //遍历map中的键 
	    for (String key : cmap.keySet()) { 
	        if(key.equals(mapCode)) {
	        	RedisClientUtil redisClientUtil = new RedisClientUtil();
	        	redisClientUtil.set(mobile+"Key", code);
	        	state =(String) cmap.get(key) ;
	        }
	    }

		return state;
		
	}

}

package com.zhita.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.functors.IfClosure;
import org.springframework.web.context.request.NativeWebRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Test {
	

	public static void main(String[] args) {
//	RedisClientUtil redisClientUtil = new RedisClientUtil();
//	redisClientUtil.set("13586485199Key", "123456");
//	System.out.println(redisClientUtil.get("13586485199Key"));
//	System.out.println(redisClientUtil.getkeyAll());
//		String creationTime = System.currentTimeMillis()+"";
//		System.out.println(creationTime);
//		Map<String,Object> map = new HashMap<String,Object>();
//		Map<String,Object> map1 = new HashMap<String,Object>();
//		map1.put("String1", "String1");
//		map1.put("String2", "String2");
//		Map<String,Object> map2 = new HashMap<String,Object>();
//		map2.put("String3", "String3");
//		map2.put("String4", "String4");
//		map.put("map1", map1);
//		map.put("map2", map2);
//		map.put("String5", "String5");
//		map.put("String6", "String6");
		
		PostAndGet pGet = new PostAndGet();
		String code = "023zT3wB1P5bbd0w5quB1exowB1zT3wi";
		 
		String string = pGet.sendGet("https://api.weixin.qq.com/sns/jscode2session?js_code=" + code + "&appid=wxdea525a189135ccf" + "&secret=2810bb3053465c58e68125f41d3ca9b9" + "&grant_type=authorization_code");
		JSONObject jsonObject = JSON.parseObject(string);
		String openid =  (String) jsonObject.get("openid");
		System.out.println(openid);
		
	}

}

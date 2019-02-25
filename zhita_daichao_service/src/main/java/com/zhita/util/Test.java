package com.zhita.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.functors.IfClosure;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
		
//		PostAndGet pGet = new PostAndGet();
//		String code = "023zT3wB1P5bbd0w5quB1exowB1zT3wi";
//		 
//		String string = pGet.sendGet("https://api.weixin.qq.com/sns/jscode2session?js_code=" + code + "&appid=wxdea525a189135ccf" + "&secret=2810bb3053465c58e68125f41d3ca9b9" + "&grant_type=authorization_code");
//		JSONObject jsonObject = JSON.parseObject(string);
//		String openid =  (String) jsonObject.get("openid");
//		System.out.println(openid);
		
//		System.out.println(stampToDate("1548052959000"));
//		System.out.println("111");
		
//		Thread threads[]=new Thread[100];
//		for(int i=0;i<100;i++){
//			threads[i]=new Thread();
//			threads[i].start();
//			}
		
//		System.out.println(mobileEncrypt("13486070402"));
//		System.out.println(idEncrypt("330225199507155112"));
//	}
//
//    // 手机号码前三后四脱敏
//    public static String mobileEncrypt(String mobile) {
//        if (StringUtils.isEmpty(mobile) || (mobile.length() != 11)) {
//            return mobile;
//        }
//        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
//    }
//
//    //身份证前三后四脱敏
//    public static String idEncrypt(String id) {
//        if (StringUtils.isEmpty(id) || (id.length() < 8)) {
//            return id;
//        }
//        return id.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*");
//    }
	 
//	 public static String stampToDate(String s){
//	        String res;
//	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	        long lt = new Long(s);
//	        Date date = new Date(lt);
//	        res = simpleDateFormat.format(date);
//	        return res;
//	    }
//		String [] aStrings = {"多米记","xx1","xx2"};
//        String JSON_ARRAY_STR = "[\"多米记\",\"xx1\",\"xx2\"]";
//		JSONArray jsonArray = JSON.parseArray(JSON_ARRAY_STR);
//        int size = jsonArray.size();
//        for (int i = 0; i < size; i++){
//            JSONObject jsonObject = jsonArray.getJSONObject(i);
//            System.out.println(jsonObject.getString("studentName")+":"+jsonObject.getInteger("studentAge"));
//        }
		
//		StringBuffer stringBuffer = new StringBuffer().append("a").append("b");
//		System.out.println(stringBuffer);
		
//		String string = "[\"多米记\",\"xx1\",\"xx2\"]";
//		string = string.replaceAll("\"", "").replace("[","").replace("]","");
//		System.out.println(string);
//		String [] stringArr= string.split(",");
//		for(int i=0;i<stringArr.length;i++){
//			System.out.println(stringArr[i]);
//			}
		
//		RedisClientUtil redisClientUtil = new RedisClientUtil();
//		redisClientUtil.set("czkey", "2019/2/25");
//		System.out.println(redisClientUtil.getkeyAll());
//		String SourceClick =redisClientUtil.get("123");
//		if(SourceClick==null) {
//			System.out.println(redisClientUtil.getkeyAll());
//			redisClientUtil.set("啦啦拉key","0");
//			System.out.println(redisClientUtil.getkeyAll());
//			System.out.println(redisClientUtil.getSourceClick("啦啦拉key"));
//			redisClientUtil.set("啦啦拉key","1");
//			System.out.println(redisClientUtil.getSourceClick("啦啦拉key"));
//		}else {
//			System.out.println(2);
//		}
//		
//		System.out.println(redisClientUtil.getkeyAll());
	
		TuoMinUtil tuoMinUtil = new TuoMinUtil();
		System.out.println(tuoMinUtil.nameEncrypt("陈峥"));
		
	}
}

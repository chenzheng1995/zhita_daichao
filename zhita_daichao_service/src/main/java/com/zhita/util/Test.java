package com.zhita.util;

public class Test {

	public static void main(String[] args) {
	RedisClientUtil redisClientUtil = new RedisClientUtil();
//	redisClientUtil.set("codeKey2", "4444");
//	System.out.println(redisClientUtil.get("codeKey2"));
	System.out.println(redisClientUtil.getkeyAll());
	}

}

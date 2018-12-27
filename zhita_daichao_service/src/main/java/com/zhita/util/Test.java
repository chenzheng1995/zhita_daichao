package com.zhita.util;

public class Test {

	public static void main(String[] args) {
	RedisClientUtil redisClientUtil = new RedisClientUtil();
	redisClientUtil.set("13586485199Key", "123456");
	System.out.println(redisClientUtil.get("13586485199Key"));
	System.out.println(redisClientUtil.getkeyAll());
	}

}

package com.zhita.controller.daichao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.zhita.util.RedisClientUtil;

public class Test {
	public static void main(String[] args) {
//		List<String> list=new ArrayList<>();
//		list.add("2018-11-1");
//		list.add("2018-11-2");
//		list.add("2018-11-3");
//		list.add("2018-11-15");
//		list.add("2018-11-21");
//		list.add("2018-11-1");
//		
//	    HashSet h = new HashSet(list);   
//	    list.clear();   
//	    list.addAll(h);   
//	    for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i)+"----list");
//		}
//	   
//		
//		List<String> list1=new ArrayList<>();
//		list1.add("2018-11-2");
//		list1.add("2018-11-8");
//		list1.add("2018-11-28");
//		list1.add("2018-11-8");
//		
//	    HashSet h1 = new HashSet(list1);   
//	    list1.clear();   
//	    list1.addAll(h1);  
//	    for (int i = 0; i <list1.size(); i++) {
//			System.out.println(list1.get(i)+"----list1");
//		}
//		
//		//list.retainAll(list1);
//		
//		list1.removeAll(list);
//		list.addAll(list1);
//		
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
		
		RedisClientUtil redisClientUtil = new RedisClientUtil();
		redisClientUtil.set("cz", "123456");
		System.out.println(redisClientUtil.getkeyAll());
		redisClientUtil.delkey("cz");
		System.out.println(redisClientUtil.get("cz"));
		System.out.println(redisClientUtil.getkeyAll());
		
	}
}

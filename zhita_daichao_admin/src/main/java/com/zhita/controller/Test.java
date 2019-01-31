package com.zhita.controller;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
/*	try {
		int a=10/0;
		System.out.println("没有异常");
		} catch (Exception e) {
			System.out.println("有异常");
		}*/
		
/*		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		  String sDate = simpleDateFormat.format(new Date());  
		System.out.println(sDate);*/
		List<String> list=new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		List<String> list1=new ArrayList<>();
		list1.add("张三");
		list1.add("李四");
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list1.size(); j++) {
				System.out.println(list.get(i)+"---"+list1.get(j));
			}
		}
		  
	}
}

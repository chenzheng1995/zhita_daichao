package com.zhita.controller;

public class Test {
	public static void main(String[] args) {
	try {
		int a=10/0;
		System.out.println("没有异常");
		} catch (Exception e) {
			System.out.println("有异常");
		}
		
	}
}

package com.zhita.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.zhita.model.manage.JiaFangTongji;
import com.zhita.util.DateListUtil;

public class Test {
	public static void main(String[] args) throws ParseException {
	/*	Timestamps time=new Timestamps();
		String a=time.stampToDate("1552025570000");//年 月 日 时 分 秒
		System.out.println(a);
		String b=time.stampToDate1("1552025570000");//年 月 日
		System.out.println(b);*/
		//List<String> dayslist=DateListUtil.getDays("2019-03-01", "2019-03-07");
		
		JiaFangTongji jia=new JiaFangTongji();
		jia.setDate("2019-03-07");
		jia.setAmount(0);
		
		JiaFangTongji jia1=new JiaFangTongji();
		jia1.setDate("2019-03-04");
		jia1.setAmount(0);
		
		JiaFangTongji jia2=new JiaFangTongji();
		jia2.setDate("2019-03-02");
		jia2.setAmount(0);
		
		JiaFangTongji jia3=new JiaFangTongji();
		jia3.setDate("2019-03-03");
		jia3.setAmount(0);
		
		JiaFangTongji jia4=new JiaFangTongji();
		jia4.setDate("2019-03-01");
		jia4.setAmount(0);
		
		JiaFangTongji jia5=new JiaFangTongji();
		jia5.setDate("2019-03-06");
		jia5.setAmount(0);
		
		JiaFangTongji jia6=new JiaFangTongji();
		jia6.setDate("2019-03-05");
		jia6.setAmount(0);
		
		List<JiaFangTongji> list=new ArrayList<>();
		list.add(0, jia);
		list.add(1, jia1);
		list.add(2, jia2);
		list.add(3, jia3);
		list.add(4, jia4);
		list.add(5, jia5);
		list.add(6, jia6);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		System.out.println("----------------------");
		DateListUtil.ListSort(list);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		//System.out.println(DateListUtil.getDiffrent2(dayslist,list));
		
	}
}

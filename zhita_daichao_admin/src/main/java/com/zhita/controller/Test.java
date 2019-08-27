package com.zhita.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.zhita.model.manage.User;
import com.zhita.util.Timestamps;

public class Test {
	public static void main(String[] args) throws ParseException {
	/*	Timestamps time=new Timestamps();
		String a=time.stampToDate("1552025570000");//年 月 日 时 分 秒
		System.out.println(a);
		String b=time.stampToDate1("1552025570000");//年 月 日
		System.out.println(b);*/
		//List<String> dayslist=DateListUtil.getDays("2019-03-01", "2019-03-07");
		
/*		JiaFangTongji jia=new JiaFangTongji();
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
		}*/

		//System.out.println(DateListUtil.getDiffrent2(dayslist,list));
		/*for (TestEnum e : TestEnum.values()) {
		    System.out.println(e);
		}*/
	/*	TuoMinUtil t=new TuoMinUtil();
		System.out.println(t.mobileEncrypt("18871552652"));;*/
		Timestamps timestamps=new Timestamps();
		String startTime = "2019-06-04";
		String startTimestamps = timestamps.dateToStamp(startTime);
		String endTime = "2019-06-04";
		String endTimestamps = (Long.parseLong(timestamps.dateToStamp(endTime))+86400000)+"";
		System.out.println(startTimestamps+"---------------"+endTimestamps);
		
		
	/*	float appnum=28;
		int discount1=80;
		float disAppnum=0;
		if (appnum >= 30) {
			int overtop=(int)appnum-30;//overtop是当前申请数超过30的那部分数量
			System.out.println(overtop);
			disAppnum=(int)Math.ceil((overtop * discount1 *1.0/ 100+30));// 申请数
		} else {
			disAppnum=appnum;// 申请数
		}
		System.out.println(disAppnum);*/
		
		 User user1=new User();
		 user1.setRegistrationtime("1566921599000");
		 user1.setLoginTime("1567180799000");
		 
		 User user2=new User();
		 user2.setRegistrationtime("1566921599000");
		 user2.setLoginTime("1567180799000");
		 
		 int activatenum=0;//激活人数
		 List<User> listact=new ArrayList<User>();
		 listact.add(user1);
		 listact.add(user2);
		 for (int j = 0; j < listact.size(); j++) {
			if(!listact.get(j).getRegistrationtime().equals(listact.get(j).getLoginTime())){
				activatenum++;
			}
		}
		 System.out.println(activatenum+"////////////");
	}
}

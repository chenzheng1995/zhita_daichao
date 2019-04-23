package com.zhita.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.zhita.util.DateListUtil;
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
		
		/*String date="2019-03-07";
		String date1=date+" "+"00:00:00";
		System.out.println(date1);
		String timeStart=Timestamps.dateToStamp1(date1);//将开始时间转换为时间戳
		System.out.println(timeStart);*/
//		System.out.println(Timestamps.dateToStamp1("2019-03-06 24:00:00"));
//		String endTime = "2019-3-18";
//		Timestamps timestamps = new Timestamps(); 
//		String endtimestamps = timestamps.dateToStamp(endTime);
//		long i = Long.parseLong(endtimestamps);
//		String s = i+"";
//System.out.println(i);
//		String num=1+(((int)(Math.random()*8998)+1000+1)+"");
//		int applications = Integer.parseInt(num);
//		System.out.println(applications);
		
//		try {
//			String ip = InetAddress.getLocalHost().getHostAddress();
//			System.out.println(ip);
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	/*	HashMap<String, Object> map = new HashMap<>();
		 ArrayList<Object> arrList = new ArrayList<Object>();
		 ArrayList<Object> arrList1 = new ArrayList<Object>();
		 arrList.add(1);
		 arrList.add(2);
		 arrList.add(3);
		 for (Object object : arrList) {
			 map.put("1key", object);
			 arrList1.add(map);
			 
		}
		 
			System.out.println(1111);	*/
		//String num=1+(((int)(Math.random()*8998)+1000+1)+"");
		//System.out.println(num);
		
		String dateStart="2019-4-20";
		String dateEnd="2019-4-21";
		
		/*String startTime = Timestamps.dateToStamp(dateStart);// 将开始时间转换为时间戳格式
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(dateEnd.replace("/", "-")));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date newDate = calendar.getTime();
		String nextDate = sdf.format(newDate);// 传进来日期的后一天
		
		String endTime = Timestamps.dateToStamp(nextDate);// 将结束时间转换为时间戳格式
		System.out.println(startTime+"start"+endTime+"end");*/
		System.out.println(DateListUtil.getDays(dateStart, dateEnd));;
	}
}

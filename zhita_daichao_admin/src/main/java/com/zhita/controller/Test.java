package com.zhita.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
		
		//String dateStart="2019-4-20";
		//String dateEnd="2019-4-21";
		
		/*String startTime = Timestamps.dateToStamp(dateStart);// 将开始时间转换为时间戳格式
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(dateEnd.replace("/", "-")));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date newDate = calendar.getTime();
		String nextDate = sdf.format(newDate);// 传进来日期的后一天
		
		String endTime = Timestamps.dateToStamp(nextDate);// 将结束时间转换为时间戳格式
		System.out.println(startTime+"start"+endTime+"end");*/
		//System.out.println(DateListUtil.getDays(dateStart, dateEnd));;
	/*	PhoneDeal phoneDeal=new PhoneDeal();//手机号解密实体类
		System.out.println(phoneDeal.encryption("18871552653"));*/
		/*String discount="80%";
		int discount1 = Integer.parseInt(discount.substring(0, discount.length() - 1));
		System.out.println(discount1+"------------");*/
		
		//System.out.println(8 * 120 / 100);
		  
		//System.out.println((int)Math.ceil(6 * 120 *1.0/ 100));
		//System.out.println((int)Math.ceil(Float.parseFloat((8 * 120 / 100)+"."+(8 * 120 % 100))));
		//System.out.println((int)Math.ceil(9.6));*/
		//System.out.println((int)Math.ceil((3 * 80 *1.0/ 100)));
		//System.out.println((int)Math.ceil(2.4*1.0));
		/*Date d=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sf.format(d);
		System.out.println(date);*/
		/*List<String> list=new ArrayList<>();
		list.add("2019-06-01");
		list.add("2019-06-02");
		list.add("2019-06-03");
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		
		System.out.println("---------------------");
		
		Date d=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sf.format(d);//date为当天时间
		
		list.remove("2019-06-04");
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}*/
		
		
		/*  	List<String> list1=new ArrayList<>();//user表去除今天的数据
	        list1.add("2019-6-01");
	        list1.add("2019-6-02");
	        list1.add("2019-6-03");
	        list1.add("2019-6-04");


	        List<String> list2=new ArrayList<>();//历史数据
	        list2.add("2019-6-01");
	        list2.add("2019-6-02");
	        list2.add("2019-6-03");
	        list2.add("2019-6-04");
	        list2.add("2019-6-05");
	        
	        
	        System.out.println("====求差集===");
	        List<String> list=list1.stream().filter(t-> !list2.contains(t)).collect(Collectors.toList());
	        for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}*/

		/*Date d=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date=sf.format(d);//date为当天时间
		System.out.println(date);
		
		String endTime = date;
		String endTimestamps = (Long.parseLong(Timestamps.dateToStamp1(endTime))+86400000)+"";
		System.out.println(endTimestamps);*/
		
		
	/*	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		System.out.println("当前日期:"+sf.format(c.getTime()));
		c.add(Calendar.DAY_OF_MONTH, 1);
		System.out.println("增加一天后日期:"+sf.format(c.getTime()));*/

		
	/*	SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 1);
		System.out.println("增加一天后日期:"+sf1.format(c.getTime()));*/
	/*	String date="2019-06-02";
		
		String startTime1 = date;
		String startTimestamps1 = ;
		String endTime1 = date;
		String endTimestamps1 = (Long.parseLong(Timestamps.dateToStamp(endTime1))+86400000)+"";
		System.out.println(startTimestamps1+"****"+endTimestamps1);*/
		//System.out.println(Timestamps.dateToStamp("2019-06-06"));
		float appnum=40;
		float disAppnum=0;
		int discount1=80;
		if (appnum >= 30) {
			int overtop=(int)appnum-30;//overtop是当前申请数超过30的那部分数量
			disAppnum=(int)Math.ceil((overtop * discount1 *1.0/ 100+30));// 申请数
		} else {
			disAppnum=appnum;// 申请数
		}
		System.out.println(disAppnum);
		
		//获取前一天的日期
		/*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String datetoday=df.format(today);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		String dateyes = df.format(calendar.getTime());
		System.out.println(datetoday+dateyes);*/
	}
}

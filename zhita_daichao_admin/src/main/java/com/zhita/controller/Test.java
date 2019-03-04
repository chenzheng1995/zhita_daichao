package com.zhita.controller;

import java.text.DecimalFormat;
import java.text.ParseException;

public class Test {
	public static void main(String[] args) throws ParseException {
/*	try {
		int a=10/0;
		System.out.println("没有异常");
		} catch (Exception e) {
			System.out.println("有异常");
		}*/
		
/*		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		  String sDate = simpleDateFormat.format(new Date());  
		System.out.println(sDate);*/
/*		List<String> list=new ArrayList<>();
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
		  */
/*		int a=0;
		int b=0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				a=a+2;
				System.out.println(a);
			}
			b=b+a;
			System.out.println(b+"----------");
			a=0;
		}*/
/*		List<String> list1=new ArrayList<>();
		list1.add("1");
		list1.add("2");
		list1.add("3");
		List<String> list2=new ArrayList<>();
		list1.add("4");
		list1.add("5");
		list1.add("6");
		list2.addAll(list1);
		for (int i = 0; i < list2.size(); i++) {
			System.out.println(list2.get(i));
		}
		*/
		
		
		
/*		List<String> list= new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");	
		list.add("6");
		list.add("7");
		list.add("8");
		list.add("9");
		list.add("10");
		list.add("11");
		list.add("12");
		list.add("13");
		list.add("14");
		list.add("15");	
		list.add("16");
		list.add("17");*/
		
		//System.out.println(list.subList(0, 10));
		//System.out.println(list.subList(10, 17));

		//ListPageUtil listPageUtil=new ListPageUtil(list,3,2);


		
/*		TestObj2 a=new TestObj2();
		a.setName("a");
		a.setAge(0);
		TestObj2 a1=new TestObj2();
		a1.setName("a1");
		a1.setAge(1);
		TestObj2 a2=new TestObj2();
		a2.setName("a2");
		a2.setAge(2);
		
		TestObj2 s1=new TestObj2();
		s1.setName("s1");
		s1.setAge(1);
		TestObj2 s2=new TestObj2();
		s2.setName("s2");
		s2.setAge(2);
		
		listtwo.add(a);
		listtwo.add(a1);
		listtwo.add(a2);
		
		listtwo1.add(s1);
		listtwo1.add(s2);
		
		TestObj1 testobj1=new TestObj1();
		testobj1.setId(1);
		testobj1.setName("百度");
		testobj1.setListobj2(listtwo);
		
		TestObj1 testobj2=new TestObj1();
		testobj2.setId(2);
		testobj2.setName("谷歌");
		testobj2.setListobj2(listtwo1);
		
		listone.add(testobj1);
		listone.add(testobj2);
		for (int i = 0; i < listone.size(); i++) {
			System.out.println(listone.get(i));
		}*/

/*		float a=(float) 0.025;
		float b=2;
		String c=(a/b)+"%";
		float d=a%b;
		System.out.println(c);
		System.out.println(d);
		*/
	/*	String date="2019/02/25";
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(date.replace("/", "-")));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date newDate = calendar.getTime();
		String nextDate=sdf.format(newDate);//传进来日期的后一天
		System.out.println(nextDate);*/
		/*SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		Date dates=sdf.parse(date);
		Calendar calendar=Calendar.getInstance();
    	calendar.setTime(dates);
       	calendar.add(Calendar.DAY_OF_MONTH, +1);//+1今天的时间加一天
       	Date nextdate = calendar.getTime();
       	System.out.println(nextdate);*/
	/*	float a=1;
		Integer b=2;
		String c=(a/b)+"%";
		System.out.println(c);*/
	/*	float a=0;
		double b=0;
		if(a<0.000001)
		{
			System.out.println("float Equal 0!\n");
		}
*/
	/*	RedisClientUtil redisClientUtil=new RedisClientUtil();
		System.out.println(redisClientUtil.getSourceClick("多米记"+"yunying1"+"2019/2/26"+"Key"));*/

		/*String str="80%";
		System.out.println(str.substring(0, str.length()-1));;*/
		/*TuoMinUtil u=new TuoMinUtil();
		System.out.println(u.nameEncrypt("张三张三"));;*/
	/*	float a=16.66666f;
		DecimalFormat df = new DecimalFormat("#.00");
		System.out.println(df.format(a));
		System.out.println(new DecimalFormat("#.00").format(a));*/
	}
}

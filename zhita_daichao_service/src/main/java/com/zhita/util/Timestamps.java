package com.zhita.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Timestamps {

	// 获取今天0点的时间戳
	public Long getTodayZeroTimestamps() {
		Long currentTimestamps = System.currentTimeMillis();
		Long oneDayTimestamps = Long.valueOf(60 * 60 * 24 * 1000);
		Long todayZeroTimestamps = currentTimestamps - (currentTimestamps + 60 * 60 * 8 * 1000) % oneDayTimestamps;
		return todayZeroTimestamps;
	}

	// 获取当前月第一天零点的时间戳
	public Long getMonthlyZeroTimestamps() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		Long first = c.getTime().getTime();
		Long oneDayTimestamps = Long.valueOf(60 * 60 * 24 * 1000);
		Long monthlyZeroTimestamps = first - (first + 60 * 60 * 8 * 1000) % oneDayTimestamps;
		return monthlyZeroTimestamps;
	}
	// 获取下个月第一天零点的时间戳
	public Long getNextMonthlyZeroTimestamps() {
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		Long last =ca.getTime().getTime();
		Long oneDayTimestamps = Long.valueOf(60 * 60 * 24 * 1000);
		Long nextMonthlyZeroTimestamps = last - (last + 60 * 60 * 8 * 1000) % oneDayTimestamps+86400000;
		return nextMonthlyZeroTimestamps;
	}
	

	//把时间戳转换成时间格式(年 月 日 时 分 秒)
	 public static String stampToDate(String s){
		 String res=null;
		 if(s==null) {
			 res="0";
		 }else {
		     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     long lt = new Long(s);
		     Date date = new Date(lt);
		     res = simpleDateFormat.format(date);
		 }
		 return res;
	 }
	 
	//把时间戳转换成时间格式(年 月 日)
	 public static String stampToDate1(String s){
		 String res=null;
		 if(s==null) {
			 res="0";
		 }else {
		     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		     long lt = new Long(s);
		     Date date = new Date(lt);
		     res = simpleDateFormat.format(date);
		 }
		 return res;
	 }
	    
	 //将时间转换为时间戳格式(年 月 日)
	  public static String dateToStamp(String s) throws ParseException{
	      String res;
	      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	      Date date = simpleDateFormat.parse(s);
	      long ts = date.getTime();
	      res = String.valueOf(ts);
	      return res;
	  }
}

package com.zhita.util;

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
	


}

package com.zhita.model.manage;

import java.math.BigDecimal;

//当前类的作用——小程序——我的账单页面——显示每天  收入   支出的一个金额总数
public class DayBill {
	private String time;//当天时间
	private BigDecimal sum;//当天金额总和
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public BigDecimal getSum() {
		return sum;
	}
	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}
	
}

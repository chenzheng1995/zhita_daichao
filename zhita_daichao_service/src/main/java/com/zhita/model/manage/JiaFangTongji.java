package com.zhita.model.manage;

//甲方统计表
public class JiaFangTongji {
	private String date;//足迹的日期
	private int amount;//足迹的数量（用户的数量）
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "JiaFangTongji [date=" + date + ", amount=" + amount + "]";
	}
	
}

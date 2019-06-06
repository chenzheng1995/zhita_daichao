package com.zhita.model.manage;

//渠道方看的统计数据
public class TongjiSorce {
	private Integer id;//将查询出来的渠道id  当做主键id
	private String date;//日期
	private String date1;//
	private String sourceName;//渠道名称
	private Integer uv;//uv
	private float appNum;//折扣后的申请数
	private float appNum1;//真实的申请数
	private String cvr;//转化率
	private Integer sumappnum;//点过甲方  用户的总数量
	private String company;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public Integer getUv() {
		return uv;
	}
	public void setUv(Integer uv) {
		this.uv = uv;
	}
	public float getAppNum() {
		return appNum;
	}
	public void setAppNum(float appNum) {
		this.appNum = appNum;
	}
	public float getAppNum1() {
		return appNum1;
	}
	public void setAppNum1(float appNum1) {
		this.appNum1 = appNum1;
	}
	public String getCvr() {
		return cvr;
	}
	public void setCvr(String cvr) {
		this.cvr = cvr;
	}
	public Integer getSumappnum() {
		return sumappnum;
	}
	public void setSumappnum(Integer sumappnum) {
		this.sumappnum = sumappnum;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	@Override
	public String toString() {
		return "TongjiSorce [id=" + id + ", date=" + date + ", date1=" + date1 + ", sourceName=" + sourceName + ", uv="
				+ uv + ", appNum=" + appNum + ", appNum1=" + appNum1 + ", cvr=" + cvr + ", sumappnum=" + sumappnum
				+ ", company=" + company + "]";
	}

}

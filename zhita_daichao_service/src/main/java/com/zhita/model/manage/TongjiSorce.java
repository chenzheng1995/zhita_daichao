package com.zhita.model.manage;

//渠道方看的统计数据
public class TongjiSorce {
	private Integer id;//将查询出来的渠道id  当做主键id
	private String sourceName;//渠道名称
	private Integer uv;//uv
	private float appNum;//申请数
	private String cvr;//转化率
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getCvr() {
		return cvr;
	}
	public void setCvr(String cvr) {
		this.cvr = cvr;
	}
	@Override
	public String toString() {
		return "TongjiSorce [id=" + id + ", sourceName=" + sourceName + ", uv=" + uv + ", appNum=" + appNum + ", cvr="
				+ cvr + ", getId()=" + getId() + ", getSourceName()=" + getSourceName() + ", getUv()=" + getUv()
				+ ", getAppNum()=" + getAppNum() + ", getCvr()=" + getCvr() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}

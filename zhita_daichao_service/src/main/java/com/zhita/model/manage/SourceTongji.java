package com.zhita.model.manage;

//渠道统计
public class SourceTongji {
	private Integer id;//商家足迹id
	private String date;//商家足迹时间
	private String businessName;//商家名称
	private String sourceName;//渠道名称
	private Integer pv;//pv
	private Integer uv;//uv
	private Integer applicationNumber;//申请数
	private String cvr;//转化率
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
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public Integer getPv() {
		return pv;
	}
	public void setPv(Integer pv) {
		this.pv = pv;
	}
	public Integer getUv() {
		return uv;
	}
	public void setUv(Integer uv) {
		this.uv = uv;
	}
	public Integer getApplicationNumber() {
		return applicationNumber;
	}
	public void setApplicationNumber(Integer applicationNumber) {
		this.applicationNumber = applicationNumber;
	}
	public String getCvr() {
		return cvr;
	}
	public void setCvr(String cvr) {
		this.cvr = cvr;
	}
	@Override
	public String toString() {
		return "SourceTongji [id=" + id + ", date=" + date + ", businessName=" + businessName + ", sourceName="
				+ sourceName + ", pv=" + pv + ", uv=" + uv + ", applicationNumber=" + applicationNumber + ", cvr=" + cvr
				+ "]";
	}
}

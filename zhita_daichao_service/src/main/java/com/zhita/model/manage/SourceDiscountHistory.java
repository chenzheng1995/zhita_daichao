package com.zhita.model.manage;

public class SourceDiscountHistory {
    private Integer id;

    private String sourcename;

    private String date;//日期
    
    private Integer uv;//uv
    
    private float appnum;//申请数
    
    private String cvr;//转化率
    
    private String deleted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSourcename() {
		return sourcename;
	}

	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getUv() {
		return uv;
	}

	public void setUv(Integer uv) {
		this.uv = uv;
	}

	public float getAppnum() {
		return appnum;
	}

	public void setAppnum(float appnum) {
		this.appnum = appnum;
	}

	public String getCvr() {
		return cvr;
	}

	public void setCvr(String cvr) {
		this.cvr = cvr;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

   
}
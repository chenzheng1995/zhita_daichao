package com.zhita.model.manage;

import java.io.Serializable;

//统计表
public class Statistical implements Serializable{
    private Integer id;//统计id

    private String time;//时间

    private Integer uv;//访问您网站的一台电脑客户端

    private Integer conversionrate;//转换率

    private Integer businessid;//商家id
    
    private Integer sourceId;//渠道id
    
    private String businessName;//贷款商家的名称
    
    private Integer applicationNumber;//贷款商家的申请次数
    
    public Statistical(Integer id, String time, Integer uv, Integer conversionrate, Integer businessid) {
        this.id = id;
        this.time = time;
        this.uv = uv;
        this.conversionrate = conversionrate;
        this.businessid = businessid;
    }

    public Statistical() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    public Integer getConversionrate() {
        return conversionrate;
    }

    public void setConversionrate(Integer conversionrate) {
        this.conversionrate = conversionrate;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }
    
	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Integer getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(Integer applicationNumber) {
		this.applicationNumber = applicationNumber;
	}
    
}
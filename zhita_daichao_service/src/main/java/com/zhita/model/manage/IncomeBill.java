package com.zhita.model.manage;

import java.math.BigDecimal;

//收入账单表
public class IncomeBill {
    private Integer id;

    private BigDecimal money;//收入金额

    private Integer bigtypeid;//大分类id
    
    private String bigtype;//大分类

    private Integer smalltypeid;//小分类id
    
    private String smalltype;//小分类
    
    private Integer userid;//用户id

    private String time;//数据产生的时间

    private String beizhu;//备注

    private String deleted;//假删除（删除：1，没删除：0）
    
    private String company;//公司名

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getBigtypeid() {
        return bigtypeid;
    }

    public void setBigtypeid(Integer bigtypeid) {
        this.bigtypeid = bigtypeid;
    }
    
    public String getBigtype() {
		return bigtype;
	}

	public void setBigtype(String bigtype) {
		this.bigtype = bigtype;
	}

	public Integer getSmalltypeid() {
        return smalltypeid;
    }

    public void setSmalltypeid(Integer smalltypeid) {
        this.smalltypeid = smalltypeid;
    }
    
	public String getSmalltype() {
		return smalltype;
	}

	public void setSmalltype(String smalltype) {
		this.smalltype = smalltype;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu == null ? null : beizhu.trim();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }
    
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}
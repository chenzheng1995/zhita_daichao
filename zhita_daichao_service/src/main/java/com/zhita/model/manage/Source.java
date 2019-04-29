package com.zhita.model.manage;

import java.io.Serializable;

//渠道表
public class Source implements Serializable{
    private Integer id;//渠道id

    private String sourcename;//渠道名称

    private String account;//账户
    
    private String pwd;//密码

    private String link;//链接

    private String state;//状态

    private String deleted;//假删除（删除：1，没删除：0）
    
    private String company;//公司名
    
    private String discount;//折扣率
    
    private Integer templateId;//模板id

    public Source(Integer id, String sourcename, String account, String link, String state, String deleted, String company, String discount,Integer templateId) {
        this.id = id;
        this.sourcename = sourcename;
        this.account = account;
        this.link = link;
        this.state = state;
        this.deleted = deleted;
        this.company = company;
        this.discount = discount;
        this.templateId = templateId;
    }

    public Source() {
        super();
    }

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
		this.sourcename = sourcename == null ? null : sourcename.trim();
	}

	public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }
    
    
    public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

	@Override
	public String toString() {
		return "Source [id=" + id + ", sourcename=" + sourcename + ", account=" + account + ", pwd=" + pwd + ", link="
				+ link + ", state=" + state + ", deleted=" + deleted + ", company=" + company + ", discount=" + discount
				+ ", templateId=" + templateId + "]";
	}
	
}
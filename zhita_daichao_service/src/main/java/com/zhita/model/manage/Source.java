package com.zhita.model.manage;

import java.io.Serializable;

//渠道表
public class Source implements Serializable{
    private Integer id;//渠道id

    private String sourceName;//渠道名称

    private String account;//账户

    private String link;//链接

    private String state;//状态

    private String deleted;//假删除（删除：1，没删除：0）
    
    private String company;//公司名

    public Source(Integer id, String sourceName, String account, String link, String state, String deleted, String company) {
        this.id = id;
        this.sourceName = sourceName;
        this.account = account;
        this.link = link;
        this.state = state;
        this.deleted = deleted;
        this.company = company;
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

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName == null ? null : sourceName.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
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
}
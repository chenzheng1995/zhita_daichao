package com.zhita.model.manage;

//渠道表
public class Source {
    private Integer id;//渠道id

    private String sourcename;//渠道名称

    private String account;//账户

    private String link;//链接

    private String state;//状态

    private String deleted;//假删除

    public Source(Integer id, String sourcename, String account, String link, String state, String deleted) {
        this.id = id;
        this.sourcename = sourcename;
        this.account = account;
        this.link = link;
        this.state = state;
        this.deleted = deleted;
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
}
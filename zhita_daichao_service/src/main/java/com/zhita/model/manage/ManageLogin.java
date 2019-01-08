package com.zhita.model.manage;

public class ManageLogin {
    private Integer id;

    private String username;

    private String phone;

    private String loginstatus;

    private String logintime;
    
    private String deleted;

    public ManageLogin(Integer id, String username, String phone, String loginstatus, String logintime, String deleted) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.loginstatus = loginstatus;
        this.logintime = logintime;
        this.deleted = deleted;
    }

    public ManageLogin() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getLoginstatus() {
        return loginstatus;
    }

    public void setLoginstatus(String loginstatus) {
        this.loginstatus = loginstatus == null ? null : loginstatus.trim();
    }

    public String getLogintime() {
        return logintime;
    }

    public void setLogintime(String logintime) {
        this.logintime = logintime == null ? null : logintime.trim();
    }
    
    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }
}
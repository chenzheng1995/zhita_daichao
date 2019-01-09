package com.zhita.model.manage;


import java.io.Serializable;
import java.util.List;

//管理登录用户表
public class ManageLogin implements Serializable{

    private Integer id;//用户id

    private String username;//用户名

    private String phone;//手机号

    private String loginstatus;//登陆状态

    private String logintime;//登陆时间
    
    private String deleted;//假删除（删除：1，没删除：0）
    
    private List<Role> listRole;//一个用户有多个角色

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

	public List<Role> getListRole() {
		return listRole;
	}

	public void setListRole(List<Role> listRole) {
		this.listRole = listRole;
	}
    
}
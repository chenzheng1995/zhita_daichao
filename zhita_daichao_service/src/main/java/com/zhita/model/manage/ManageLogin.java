package com.zhita.model.manage;


import java.io.Serializable;
import java.util.List;

//管理登录用户表
public class ManageLogin implements Serializable{

    private Integer id;//用户id

    private String username;//用户名

    private String phone;//手机号
    
    private String pwd;//密码

    private String loginstatus;//登陆状态

    private String logintime;//登陆时间
    
    private String deleted;//账号状态（ 1:已禁用 0:已启用）
    
    private String company;//公司名
    private String sourcename;//渠道名
    private List<String> listcompany;//公司名list（添加后台管理用户的时候，复选框选值的时候使用的公司名字段）
    
    private String listcompanyString;//公司名字符串
    
    private List<Role> listRole;//一个用户有多个角色
    
    private List<String> listRoleId;//做添加操作时  存角色id的集合

    private String listRoleIdString;//做添加操作时  存角色id的字符串
    
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


	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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
    
    
    public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	

	public String getSourcename() {
		return sourcename;
	}

	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}

	public List<String> getListcompany() {
		return listcompany;
	}

	public void setListcompany(List<String> listcompany) {
		this.listcompany = listcompany;
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

	public List<String> getListRoleId() {
		return listRoleId;
	}

	public void setListRoleId(List<String> listRoleId) {
		this.listRoleId = listRoleId;
	}

	public String getListRoleIdString() {
		return listRoleIdString;
	}

	public void setListRoleIdString(String listRoleIdString) {
		this.listRoleIdString = listRoleIdString;
	}

	public String getListcompanyString() {
		return listcompanyString;
	}

	public void setListcompanyString(String listcompanyString) {
		this.listcompanyString = listcompanyString;
	}

}
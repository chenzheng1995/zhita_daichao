package com.zhita.model.manage;

import java.util.List;

//角色表
public class Role {
    private Integer id;//角色id

    private String rolename;//角色名称

    private String rolemiaoshu;//角色描述

    private String deleted;//假删除（删除：1，没删除：0）
    
    private List<Functions> listFunction;//一个角色有多个功能
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getRolemiaoshu() {
        return rolemiaoshu;
    }

    public void setRolemiaoshu(String rolemiaoshu) {
        this.rolemiaoshu = rolemiaoshu == null ? null : rolemiaoshu.trim();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }

	public List<Functions> getListFunction() {
		return listFunction;
	}

	public void setListFunction(List<Functions> listFunction) {
		this.listFunction = listFunction;
	}
    
}
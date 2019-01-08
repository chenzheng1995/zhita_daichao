package com.zhita.model.manage;

//用户角色表
public class ManageloginRole {
    private Integer id;

    private Integer manageLoginId;//用户id

    private Integer roleId;//角色id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getManageLoginId() {
        return manageLoginId;
    }

    public void setManageLoginId(Integer manageLoginId) {
        this.manageLoginId = manageLoginId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
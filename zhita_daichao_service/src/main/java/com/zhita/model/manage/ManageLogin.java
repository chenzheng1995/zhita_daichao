package com.zhita.model.manage;

public class ManageLogin {
    private Integer id;

    private String username;

    private String md5pwd;

    public ManageLogin(Integer id, String username, String md5pwd) {
        this.id = id;
        this.username = username;
        this.md5pwd = md5pwd;
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

    public String getMd5pwd() {
        return md5pwd;
    }

    public void setMd5pwd(String md5pwd) {
        this.md5pwd = md5pwd == null ? null : md5pwd.trim();
    }
}
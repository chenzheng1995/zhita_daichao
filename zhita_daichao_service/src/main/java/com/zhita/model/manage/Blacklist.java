package com.zhita.model.manage;

public class Blacklist {
    private Integer id;

    private Integer userid;

    private String name;

    private String idcard;

    private String phone;

    private String creationtime;
    
    private String company;

    public Blacklist(Integer id, Integer userid, String name, String idcard, String phone, String creationtime, String company) {
        this.id = id;
        this.userid = userid;
        this.name = name;
        this.idcard = idcard;
        this.phone = phone;
        this.creationtime = creationtime;
        this.company = company;
    }

    public Blacklist() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(String creationtime) {
        this.creationtime = creationtime == null ? null : creationtime.trim();
    }
    
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}
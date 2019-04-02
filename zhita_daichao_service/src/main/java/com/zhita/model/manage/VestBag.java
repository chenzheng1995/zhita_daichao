package com.zhita.model.manage;

public class VestBag {
    private Integer id;

    private String vestbagname;

    private String deleted;

    private String company;

    public VestBag(Integer id, String vestbagname, String deleted, String company) {
        this.id = id;
        this.vestbagname = vestbagname;
        this.deleted = deleted;
        this.company = company;
    }

    public VestBag() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVestbagname() {
        return vestbagname;
    }

    public void setVestbagname(String vestbagname) {
        this.vestbagname = vestbagname == null ? null : vestbagname.trim();
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
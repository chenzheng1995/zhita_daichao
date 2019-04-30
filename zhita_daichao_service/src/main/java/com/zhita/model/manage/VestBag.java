package com.zhita.model.manage;

public class VestBag {
    private Integer id;

    private String vestbagname;

    private String deleted;

    private String company;
    
    private String oneSourceName;
    
    private String twoSourceName;

    public VestBag(Integer id, String vestbagname, String deleted, String company,String oneSourceName,String twoSourceName) {
        this.id = id;
        this.vestbagname = vestbagname;
        this.deleted = deleted;
        this.company = company;
        this.oneSourceName = oneSourceName;
        this.twoSourceName = twoSourceName;
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
    
    public String getOneSourceName() {
        return oneSourceName;
    }

    public void setOneSourceName(String oneSourceName) {
        this.oneSourceName = oneSourceName == null ? null : oneSourceName.trim();
    }
    
    public String TwoSourceName() {
        return twoSourceName;
    }

    public void setTwoSourceName(String twoSourceName) {
        this.twoSourceName = twoSourceName == null ? null : twoSourceName.trim();
    }
}
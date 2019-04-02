package com.zhita.model.manage;

public class VestBagVersion {
    private Integer id;

    private Integer vestbagid;

    private String version;

    private String company;

    private String deleted;

    public VestBagVersion(Integer id, Integer vestbagid, String version, String company, String deleted) {
        this.id = id;
        this.vestbagid = vestbagid;
        this.version = version;
        this.company = company;
        this.deleted = deleted;
    }

    public VestBagVersion() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVestbagid() {
        return vestbagid;
    }

    public void setVestbagid(Integer vestbagid) {
        this.vestbagid = vestbagid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
    	this.version = version == null ? null : version.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }
}
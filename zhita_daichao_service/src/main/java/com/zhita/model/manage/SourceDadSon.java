package com.zhita.model.manage;

public class SourceDadSon {
    private Integer id;

    private String onesourcename;

    private String twosourcename;

    private String deleted;
    
    private String company;
    
    private String tableType;


    public SourceDadSon(Integer id, String onesourcename, String twosourcename, String deleted,String company,String tableType) {
        this.id = id;
        this.onesourcename = onesourcename;
        this.twosourcename = twosourcename;
        this.deleted = deleted;
        this.company = company;
        this.tableType = tableType;
    }

    public SourceDadSon() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOnesourcename() {
        return onesourcename;
    }

    public void setOnesourcename(String onesourcename) {
        this.onesourcename = onesourcename == null ? null : onesourcename.trim();
    }

    public String getTwosourcename() {
        return twosourcename;
    }

    public void setTwosourcename(String twosourcename) {
        this.twosourcename = twosourcename == null ? null : twosourcename.trim();
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
    
    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType == null ? null : tableType.trim();
    }
    
    
}
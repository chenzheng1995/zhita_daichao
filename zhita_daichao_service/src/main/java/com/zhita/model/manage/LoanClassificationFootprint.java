package com.zhita.model.manage;

public class LoanClassificationFootprint {
    private Integer id;

    private Integer userid;

    private String footprint;

    private String footprinttime;

    private String deleted;

    public LoanClassificationFootprint(Integer id, Integer userid, String footprint, String footprinttime, String deleted) {
        this.id = id;
        this.userid = userid;
        this.footprint = footprint;
        this.footprinttime = footprinttime;
        this.deleted = deleted;
    }

    public LoanClassificationFootprint() {
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

    public String getFootprint() {
        return footprint;
    }

    public void setFootprint(String footprint) {
        this.footprint = footprint == null ? null : footprint.trim();
    }

    public String getFootprinttime() {
        return footprinttime;
    }

    public void setFootprinttime(String footprinttime) {
        this.footprinttime = footprinttime == null ? null : footprinttime.trim();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }
}
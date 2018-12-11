package com.zhita.model.manage;

public class Statistical {
    private Integer id;

    private String time;

    private Integer uv;

    private Integer conversionrate;

    private Integer businessid;

    public Statistical(Integer id, String time, Integer uv, Integer conversionrate, Integer businessid) {
        this.id = id;
        this.time = time;
        this.uv = uv;
        this.conversionrate = conversionrate;
        this.businessid = businessid;
    }

    public Statistical() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    public Integer getConversionrate() {
        return conversionrate;
    }

    public void setConversionrate(Integer conversionrate) {
        this.conversionrate = conversionrate;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }
}
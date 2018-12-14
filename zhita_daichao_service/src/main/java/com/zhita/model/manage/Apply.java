package com.zhita.model.manage;

public class Apply {
    private Integer id;

    private Integer userid;

    private Integer dailyapplications;

    private Integer dailyusers;

    private Integer monthlyapplications;

    private Integer monthlyusers;

    public Apply(Integer id, Integer userid, Integer dailyapplications, Integer dailyusers, Integer monthlyapplications, Integer monthlyusers) {
        this.id = id;
        this.userid = userid;
        this.dailyapplications = dailyapplications;
        this.dailyusers = dailyusers;
        this.monthlyapplications = monthlyapplications;
        this.monthlyusers = monthlyusers;
    }

    public Apply() {
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

    public Integer getDailyapplications() {
        return dailyapplications;
    }

    public void setDailyapplications(Integer dailyapplications) {
        this.dailyapplications = dailyapplications;
    }

    public Integer getDailyusers() {
        return dailyusers;
    }

    public void setDailyusers(Integer dailyusers) {
        this.dailyusers = dailyusers;
    }

    public Integer getMonthlyapplications() {
        return monthlyapplications;
    }

    public void setMonthlyapplications(Integer monthlyapplications) {
        this.monthlyapplications = monthlyapplications;
    }

    public Integer getMonthlyusers() {
        return monthlyusers;
    }

    public void setMonthlyusers(Integer monthlyusers) {
        this.monthlyusers = monthlyusers;
    }
}
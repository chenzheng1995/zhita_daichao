package com.zhita.model.manage;

//申请表
public class Apply {
    private Integer id;//申请id

    private Integer userid;//用户id

    private Integer dailyapplications;//日申请次数

    private Integer dailyusers;//日申请用户

    private Integer monthlyapplications;//月申请次数

    private Integer monthlyusers;//月申请用户

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
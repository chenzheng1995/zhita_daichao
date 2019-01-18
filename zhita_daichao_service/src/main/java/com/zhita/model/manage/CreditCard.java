package com.zhita.model.manage;

import java.io.Serializable;

//信用卡表
public class CreditCard implements Serializable{
    private Integer id;//信用卡id

    private String title;//标题

    private String cover;//封面

    private String intro;//简介

    private String isstick;//是否置顶(1置顶，0不置顶)

    private String state;//状态(1开启，2关闭)
    
    private String content;//内容

    private String url;//"url"

    private String deleted;//假删除（删除：1，没删除：0）
    
    private String company;//公司名

    public CreditCard(Integer id, String title, String cover, String intro, String isstick, String state,String content, String url, String deleted, String company) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.intro = intro;
        this.isstick = isstick;
        this.state = state;
        this.content=content;
        this.url = url;
        this.deleted = deleted;
        this.company = company;
    }

    public CreditCard() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getIsstick() {
        return isstick;
    }

    public void setIsstick(String isstick) {
        this.isstick = isstick == null ? null : isstick.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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
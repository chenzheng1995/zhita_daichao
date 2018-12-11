package com.zhita.model.manage;

public class CreditCard {
    private Integer id;

    private String title;

    private String cover;

    private String intro;

    private String isstick;

    private String state;

    private String url;

    private String deleted;

    public CreditCard(Integer id, String title, String cover, String intro, String isstick, String state, String url, String deleted) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.intro = intro;
        this.isstick = isstick;
        this.state = state;
        this.url = url;
        this.deleted = deleted;
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
}
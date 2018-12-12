package com.zhita.model.manage;

//广告表
public class Advertising {
    private Integer id;//广告id

    private String title;//标题

    private String cover;//封面

    private String link;//链接

    private String state;//状态

    private String advertising;//广告位

    private String deleted;//假删除

    public Advertising(Integer id, String title, String cover, String link, String state, String advertising, String deleted) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.link = link;
        this.state = state;
        this.advertising = advertising;
        this.deleted = deleted;
    }

    public Advertising() {
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getAdvertising() {
        return advertising;
    }

    public void setAdvertising(String advertising) {
        this.advertising = advertising == null ? null : advertising.trim();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }
}
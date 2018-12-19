package com.zhita.model.manage;

//攻略表
public class Strategy {
    private Integer id;//id

    private String title;//标题

    private String cover;//封面

    private String synopsis;//简介

    private String type;//类型

    private String url;//"url"

    private String isstick;//是否置顶（1：置顶0：不置顶）

    private String state;//状态（1：开启2关闭）

    private String content;//内容

    private String deleted;//假删除（删除；1没删除2）

    public Strategy(Integer id, String title, String cover, String synopsis, String type, String url, String isstick, String state, String content, String deleted) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.synopsis = synopsis;
        this.type = type;
        this.url = url;
        this.isstick = isstick;
        this.state = state;
        this.content = content;
        this.deleted = deleted;
    }

    public Strategy() {
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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis == null ? null : synopsis.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }
}
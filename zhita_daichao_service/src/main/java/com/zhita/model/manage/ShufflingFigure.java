package com.zhita.model.manage;

import java.io.Serializable;

//轮播图表
public class ShufflingFigure implements Serializable{
    private Integer id;//轮播图id

    private String title;//标题

    private String cover;//封面

    private String link;//链接

    private String state;//状态

    private String deleted;//假删除

    public ShufflingFigure(Integer id, String title, String cover, String link, String state, String deleted) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.link = link;
        this.state = state;
        this.deleted = deleted;
    }

    public ShufflingFigure() {
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

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }
}
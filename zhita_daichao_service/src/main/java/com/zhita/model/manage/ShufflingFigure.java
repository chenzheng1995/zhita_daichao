package com.zhita.model.manage;

import java.io.Serializable;

//轮播图表
public class ShufflingFigure implements Serializable{
    private Integer id;//轮播图id

    private String title;//标题

    private String cover;//封面

    private String link;//链接
    
    private String businessname;//商家名称

    private String state;//状态(1开启，2关闭)

    private String deleted;//假删除
    
    private String company;//公司名

    public ShufflingFigure(Integer id, String title, String cover, String link, String businessname,String state, String deleted, String company) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.link = link;
        this.businessname=businessname;
        this.state = state;
        this.deleted = deleted;
        this.company = company;
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
    
    public String getBusinessname() {
		return businessname;
	}

	public void setBusinessname(String businessname) {
		this.businessname = businessname;
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
    
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

	@Override
	public String toString() {
		return "ShufflingFigure [id=" + id + ", title=" + title + ", cover=" + cover + ", link=" + link
				+ ", businessname=" + businessname + ", state=" + state + ", deleted=" + deleted + ", company="
				+ company + "]";
	}
    
}
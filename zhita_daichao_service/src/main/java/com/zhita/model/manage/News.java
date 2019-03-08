package com.zhita.model.manage;

public class News {
    private Integer id;

    private String title;

    private String titleimage;

    private String date;

    private Integer viewed;

    private String author;

    private String contenturl;

    private String isstick;

    private String company;

    private String deleted;

    public News(Integer id, String title, String titleimage, String date, Integer viewed, String author, String contenturl, String isstick, String company, String deleted) {
        this.id = id;
        this.title = title;
        this.titleimage = titleimage;
        this.date = date;
        this.viewed = viewed;
        this.author = author;
        this.contenturl = contenturl;
        this.isstick = isstick;
        this.company = company;
        this.deleted = deleted;
    }

    public News() {
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

    public String getTitleimage() {
        return titleimage;
    }

    public void setTitleimage(String titleimage) {
        this.titleimage = titleimage == null ? null : titleimage.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public Integer getViewed() {
        return viewed;
    }

    public void setViewed(Integer viewed) {
        this.viewed = viewed;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getContenturl() {
        return contenturl;
    }

    public void setContenturl(String contenturl) {
        this.contenturl = contenturl == null ? null : contenturl.trim();
    }

    public String getIsstick() {
        return isstick;
    }

    public void setIsstick(String isstick) {
        this.isstick = isstick == null ? null : isstick.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }
}
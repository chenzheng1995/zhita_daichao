package com.zhita.model.manage;

public class NewsType {
    private Integer id;

    private String type;

    private String company;

    public NewsType(Integer id, String type, String company) {
        this.id = id;
        this.type = type;
        this.company = company;
    }

    public NewsType() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}
package com.zhita.model.manage;

public class Notice {
    private Integer id;

    private String name;

    private Integer money;

    private String deleted;

    public Notice(Integer id, String name, Integer money, String deleted) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.deleted = deleted;
    }

    public Notice() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }
}
package com.zhita.model.manage;

//甲方商家——自定义值表
public class LoanCustomvalue {
    private Integer id;//id

    private String fields;//自定义字段

    private String deleted;//假删除（删除：1，没删除：0）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields == null ? null : fields.trim();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }
}
package com.zhita.model.manage;

//功能表
public class Functions {
    private Integer id;//功能id

    private String functionFirst;//第一层功能

    private String functionSecond;//第二层功能

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFunctionFirst() {
        return functionFirst;
    }

    public void setFunctionFirst(String functionFirst) {
        this.functionFirst = functionFirst == null ? null : functionFirst.trim();
    }

    public String getFunctionSecond() {
        return functionSecond;
    }

    public void setFunctionSecond(String functionSecond) {
        this.functionSecond = functionSecond == null ? null : functionSecond.trim();
    }
}
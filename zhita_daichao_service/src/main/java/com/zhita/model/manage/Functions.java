package com.zhita.model.manage;

import java.util.List;

//功能表
public class Functions {
    private String id;//功能id

    private String functionFirst;//第一层功能

    private String functionSecond;//第二层功能
    
    private List<SecondFunction> secondlist;

    public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public List<SecondFunction> getSecondlist() {
		return secondlist;
	}

	public void setSecondlist(List<SecondFunction> secondlist) {
		this.secondlist = secondlist;
	}

	@Override
	public String toString() {
		return "Functions [id=" + id + ", functionFirst=" + functionFirst + ", functionSecond=" + functionSecond
				+ ", secondlist=" + secondlist + "]";
	}
}
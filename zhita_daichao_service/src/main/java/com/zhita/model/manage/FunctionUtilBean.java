package com.zhita.model.manage;

import java.util.List;

//专门存功能表里面的第一层功能和第二层功能————  一个第一层功能  对应一个第二层功能的集合
public class FunctionUtilBean {
	private Integer id;
	private String function_first;
	private List<String> function_secondlist;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFunction_first() {
		return function_first;
	}
	public void setFunction_first(String function_first) {
		this.function_first = function_first;
	}
	public List<String> getFunction_secondlist() {
		return function_secondlist;
	}
	public void setFunction_secondlist(List<String> function_secondlist) {
		this.function_secondlist = function_secondlist;
	}

	
}

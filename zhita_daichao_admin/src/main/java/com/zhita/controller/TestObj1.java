package com.zhita.controller;

import java.util.List;

public class TestObj1 {
	private Integer id;
	private String name;
	private List<TestObj2> listobj2;
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
		this.name = name;
	}
	public List<TestObj2> getListobj2() {
		return listobj2;
	}
	public void setListobj2(List<TestObj2> listobj2) {
		this.listobj2 = listobj2;
	}
	@Override
	public String toString() {
		return "TestObj1 [id=" + id + ", name=" + name + ", listobj2=" + listobj2 + "]";
	}
	

}

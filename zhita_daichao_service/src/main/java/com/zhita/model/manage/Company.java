package com.zhita.model.manage;

//公司表
public class Company {
	private int id;//公司id
	private String company;//公司名
	private String deleted;//假删除（删除：1，没删除：0）
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
}

package com.zhita.service.registe;

import java.util.List;

import com.zhita.model.manage.LoansBusinesses;

public interface IntRegisteService{
	//查询出所有贷款商家的信息
	public List<LoansBusinesses> queryAll();
	//通过商家分类查询出商家信息
	public List<LoansBusinesses> queryByLoansClass(String businessClassification);
}

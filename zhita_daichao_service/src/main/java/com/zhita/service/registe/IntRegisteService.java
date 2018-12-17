package com.zhita.service.registe;

import java.util.List;

import com.zhita.model.manage.LoansBusinesses;
import com.zhita.util.PageUtil;

public interface IntRegisteService{
	//查询出所有贷款商家的信息
	public List<LoansBusinesses> queryAll();
	//通过商家分类查询出商家信息
	public List<LoansBusinesses> queryByLoansClass(String businessClassification);
	//后台管理---查询贷款商家部分字段信息，含分页
	public List<LoansBusinesses> queryAllAdmain(Integer page) ;
  	//后台管理---查询贷款商家总条数
  	int pageCount();
    //后台管理---添加贷款商家信息
    int insert(LoansBusinesses record);
}

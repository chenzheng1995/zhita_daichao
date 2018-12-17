package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.LoansBusinesses;

public interface LoansBusinessesMapper {
    int deleteByPrimaryKey(Integer id);
    
    //后台管理---添加贷款商家信息
    int insert(LoansBusinesses record);

    int insertSelective(LoansBusinesses record);

    LoansBusinesses selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoansBusinesses record);

    int updateByPrimaryKey(LoansBusinesses record);
    //小程序---查询出所有的贷款商家信息
    List<LoansBusinesses> queryAll();
    //小程序---通过商家分类查询出商家信息
  	List<LoansBusinesses> queryByLoansClass(String businessClassification);
  	//后台管理---查询贷款商家部分字段信息，含分页
  	List<LoansBusinesses> queryAllAdmain(Integer page);
  	//后台管理---查询贷款商家总条数
  	int pageCount();
}
package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.LoansBusinesses;

public interface LoansBusinessesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoansBusinesses record);

    int insertSelective(LoansBusinesses record);

    LoansBusinesses selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoansBusinesses record);

    int updateByPrimaryKey(LoansBusinesses record);
    //查询出所有的贷款商家信息
    List<LoansBusinesses> queryAll();
    //通过商家分类查询出商家信息
  	List<LoansBusinesses> queryByLoansClass(String businessClassification);
}
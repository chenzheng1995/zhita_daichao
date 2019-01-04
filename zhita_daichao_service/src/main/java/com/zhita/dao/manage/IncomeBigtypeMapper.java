package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.IncomeBigtype;

public interface IncomeBigtypeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(IncomeBigtype record);

    int insertSelective(IncomeBigtype record);

    IncomeBigtype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IncomeBigtype record);

    int updateByPrimaryKey(IncomeBigtype record);
    
    //查询出所有的大类别
    List<IncomeBigtype> queryAllBigtype();
}
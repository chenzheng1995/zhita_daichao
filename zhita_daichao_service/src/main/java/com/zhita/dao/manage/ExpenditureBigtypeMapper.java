package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.ExpenditureBigtype;

public interface ExpenditureBigtypeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ExpenditureBigtype record);

    int insertSelective(ExpenditureBigtype record);

    ExpenditureBigtype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExpenditureBigtype record);

    int updateByPrimaryKey(ExpenditureBigtype record);
    
    //查询出所有的大类别
    List<ExpenditureBigtype> queryAllBigtype();
}
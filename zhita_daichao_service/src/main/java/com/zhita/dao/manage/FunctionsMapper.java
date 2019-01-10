package com.zhita.dao.manage;

import com.zhita.model.manage.Functions;

public interface FunctionsMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Functions record);

    int insertSelective(Functions record);

    Functions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Functions record);

    int updateByPrimaryKey(Functions record);
    
}
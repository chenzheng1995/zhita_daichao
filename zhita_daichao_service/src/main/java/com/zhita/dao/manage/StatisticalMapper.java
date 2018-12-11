package com.zhita.dao.manage;

import com.zhita.model.manage.Statistical;

public interface StatisticalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Statistical record);

    int insertSelective(Statistical record);

    Statistical selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Statistical record);

    int updateByPrimaryKey(Statistical record);
}
package com.zhita.dao.manage;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.ButtonFootprint;

public interface ButtonFootprintMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ButtonFootprint record);

    int insertSelective(ButtonFootprint record);

    ButtonFootprint selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ButtonFootprint record);

    int updateByPrimaryKey(ButtonFootprint record);

    int insertfootprint(@Param("footprintName")String footprintName,@Param("userId") String userId,@Param("currentTimestamp") long currentTimestamp);
}
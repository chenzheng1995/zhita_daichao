package com.zhita.dao.manage;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.LoanClassificationFootprint;

public interface LoanClassificationFootprintMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoanClassificationFootprint record);

    int insertSelective(LoanClassificationFootprint record);

    LoanClassificationFootprint selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoanClassificationFootprint record);

    int updateByPrimaryKey(LoanClassificationFootprint record);

    int insertfootprint(@Param("footprintName")String footprintName,@Param("userId") String userId,@Param("currentTimestamp") long currentTimestamp);
}
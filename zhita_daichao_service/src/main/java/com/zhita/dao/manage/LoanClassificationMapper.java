package com.zhita.dao.manage;

import com.zhita.model.manage.LoanClassification;

public interface LoanClassificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoanClassification record);

    int insertSelective(LoanClassification record);

    LoanClassification selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoanClassification record);

    int updateByPrimaryKey(LoanClassification record);
}
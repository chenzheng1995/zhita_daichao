package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.LoanClassification;
import com.zhita.model.manage.LoanClassificationCopy;

public interface LoanClassificationCopyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoanClassificationCopy record);

    int insertSelective(LoanClassificationCopy record);

    LoanClassificationCopy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoanClassificationCopy record);

    int updateByPrimaryKey(LoanClassificationCopy record);

	List<LoanClassification> queryLoanClass(String company);
}
package com.zhita.dao.manage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.LoanClassificationCopy;

public interface LoanClassificationCopyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoanClassificationCopy record);

    int insertSelective(LoanClassificationCopy record);

    LoanClassificationCopy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoanClassificationCopy record);

    int updateByPrimaryKey(LoanClassificationCopy record);
	
	List<LoanClassificationCopy> queryLoanClass(String company);
	
	List<LoanClassificationCopy> queryLoanClassAfter(String company);

	int pageCountByBusinessClassification1(@Param("businessClassification")String businessClassification,@Param("company") String company);


}
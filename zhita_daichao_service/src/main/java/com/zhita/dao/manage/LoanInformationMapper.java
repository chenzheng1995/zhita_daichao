package com.zhita.dao.manage;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.LoanInformation;

public interface LoanInformationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoanInformation record);

    int insertSelective(LoanInformation record);

    LoanInformation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoanInformation record);

    int updateByPrimaryKey(LoanInformation record);

	int setloanInformation(@Param("userId")int userId,@Param("name") String name,@Param("idCard") String idCard,@Param("professionalIdentity") String professionalIdentity,
			@Param("monthlyIncomeRange") String monthlyIncomeRange,@Param("educationalBackground") String educationalBackground, @Param("sesamePoints")String sesamePoints,
			@Param("cellPhoneTime") String cellPhoneTime,@Param("isCreditCard") String isCreditCard,@Param("isAccumulationFund") String isAccumulationFund,
			@Param("isSocialSecurity") String isSocialSecurity,@Param("isCar") String isCar,@Param("isHouse") String isHouse);
}
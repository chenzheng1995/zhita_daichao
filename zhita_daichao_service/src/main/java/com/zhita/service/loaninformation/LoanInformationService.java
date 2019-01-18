package com.zhita.service.loaninformation;

import com.zhita.model.manage.LoanInformation;

public interface LoanInformationService {

	int setloanInformation(int userId, String name, String idCard, String professionalIdentity, String monthlyIncomeRange, String educationalBackground,
			String sesamePoints, String cellPhoneTime, String isCreditCard, String isAccumulationFund, String isSocialSecurity, String isCar, String isHouse, String company);

	LoanInformation getloanInformation(int userId, String company);

	int updateloanInformation(int userId, String name, String idCard, String professionalIdentity,
			String monthlyIncomeRange, String educationalBackground, String sesamePoints, String cellPhoneTime,
			String isCreditCard, String isAccumulationFund, String isSocialSecurity, String isCar, String isHouse,
			String company);

}

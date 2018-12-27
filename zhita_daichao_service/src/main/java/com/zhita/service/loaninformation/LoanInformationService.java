package com.zhita.service.loaninformation;

public interface LoanInformationService {

	int setloanInformation(int userId, String name, String idCard, String professionalIdentity, String monthlyIncomeRange, String educationalBackground,
			String sesamePoints, String cellPhoneTime, String isCreditCard, String isAccumulationFund, String isSocialSecurity, String isCar, String isHouse);

}

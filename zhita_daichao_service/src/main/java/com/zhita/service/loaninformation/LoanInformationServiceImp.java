package com.zhita.service.loaninformation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.LoanInformationMapper;
import com.zhita.model.manage.LoanInformation;

@Service
public class LoanInformationServiceImp implements LoanInformationService{

    @Autowired
    LoanInformationMapper loanInformationMapper;

	@Override
	public int setloanInformation(int userId, String name, String idCard, String professionalIdentity,
			String monthlyIncomeRange, String educationalBackground, String sesamePoints, String cellPhoneTime,
			String isCreditCard, String isAccumulationFund, String isSocialSecurity, String isCar, String isHouse, String company) {
		int number = loanInformationMapper.setloanInformation(userId,name,idCard,professionalIdentity,monthlyIncomeRange,
				educationalBackground,sesamePoints,cellPhoneTime,isCreditCard,isAccumulationFund,isSocialSecurity,isCar,isHouse,company);				
		return number;
	}

	@Override
	public LoanInformation getloanInformation(int userId, String company) {
		LoanInformation loanInformation = loanInformationMapper.getloanInformation(userId,company);
		return loanInformation;
	}

	@Override
	public int updateloanInformation(int userId, String name, String idCard, String professionalIdentity,
			String monthlyIncomeRange, String educationalBackground, String sesamePoints, String cellPhoneTime,
			String isCreditCard, String isAccumulationFund, String isSocialSecurity, String isCar, String isHouse,
			String company) {
		int number = loanInformationMapper.updateloanInformation(userId,name,idCard,professionalIdentity,monthlyIncomeRange,
				educationalBackground,sesamePoints,cellPhoneTime,isCreditCard,isAccumulationFund,isSocialSecurity,isCar,isHouse,company);		
		return number;
	}

}

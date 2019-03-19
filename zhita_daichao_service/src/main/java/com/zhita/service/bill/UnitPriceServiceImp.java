package com.zhita.service.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhita.dao.manage.UnitPriceMapper;

@Service
public class UnitPriceServiceImp implements UnitPriceService{

	@Autowired
	UnitPriceMapper unitPriceMapper;

	@Override
	public int setunitprice(String firm, String firmType, String account, int price, String accountType,String registrationTime) {
		int number = unitPriceMapper.setunitprice(firm,firmType,account,price,accountType,registrationTime);
		return number;
	}

	@Override
	public int updateUnitPrice(String firm, String firmType, String account, int price, String accountType,int id,String registrationTime) {
		int number = unitPriceMapper.updateUnitPrice(firm,firmType,account,price,accountType,id,registrationTime);
		return number;
	}
}

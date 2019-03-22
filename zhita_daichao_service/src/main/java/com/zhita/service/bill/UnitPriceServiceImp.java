package com.zhita.service.bill;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhita.dao.manage.UnitPriceMapper;
import com.zhita.model.manage.UnitPrice;

@Service
public class UnitPriceServiceImp implements UnitPriceService{

	@Autowired
	UnitPriceMapper unitPriceMapper;

	@Override
	public int setunitprice(String firm, String firmType, String account, int price, String accountType,String registrationTime,String company) {
		int number = unitPriceMapper.setunitprice(firm,firmType,account,price,accountType,registrationTime,company);
		return number;
	}

	@Override
	public int updateUnitPrice(String firm, String firmType, String account, int price, String accountType,int id,String registrationTime) {
		int number = unitPriceMapper.updateUnitPrice(firm,firmType,account,price,accountType,id,registrationTime);
		return number;
	}

	@Override
	public int deleteUnitPrice(int id,String registrationTime) {
		int number = unitPriceMapper.deleteUnitPrice(id,registrationTime);
		return number;
	}

	@Override
	public int deleteFirm(String firm, String account, String registrationTime,String company) {
		int number = unitPriceMapper.deleteFirm(firm,account,registrationTime,company);
		return number;
	}

	@Override
	public List<String> getaccount(String firm, String firmType) {
		List<String> list = unitPriceMapper.getaccount(firm,firmType);
		return list;
	}

	@Override
	public List<Integer> getprice(String account) {
		List<Integer> list = unitPriceMapper.getprice(account);
		return list;
	}

	@Override
	public List<UnitPrice> getunitprice(String firm, String firmType,String company) {
		List<UnitPrice> accountList = unitPriceMapper.getunitprice(firm,firmType,company);
		return accountList;
	}

}

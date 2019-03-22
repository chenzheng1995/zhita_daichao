package com.zhita.service.bill;

import java.util.List;

import com.zhita.model.manage.UnitPrice;

public interface UnitPriceService {

	int setunitprice(String firm, String firmType, String account, int price, String accountType, String registrationTime, String company);

	int updateUnitPrice(String firm, String firmType, String account, int price, String accountType, int id, String registrationTime);

	int deleteUnitPrice(int id, String registrationTime);

	int deleteFirm(String firm, String account, String registrationTime, String company);

	List<String> getaccount(String firm, String firmType);

	List<Integer> getprice(String account);

	List<UnitPrice> getunitprice(String firm, String firmType, String company);

}

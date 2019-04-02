package com.zhita.service.bill;

import java.util.List;

import com.zhita.model.manage.UnitPrice;

public interface UnitPriceService {

	int setunitprice(int sourceId, int businessesId, String firmType, String account, int price, String accountType, String registrationTime, String company);

	int updateUnitPrice(int sourceId, int businessesId, String firmType, String account, int price, String accountType, int id, String registrationTime);

	int deleteUnitPrice(int id, String registrationTime);

	int deleteFirm(int sourceId, String account, String registrationTime, String company);

	List<String> getaccount(String firm, String firmType);

	List<Integer> getprice(String account);

	List<UnitPrice> getunitprice(int sourceId, String company);

	int getbusinessesId(String account, String company);

	List<UnitPrice> getaccountBySourceId(String company, Integer sourceId, String firmType);

	List<Object> getaccountById(int sourceId, String company);




}

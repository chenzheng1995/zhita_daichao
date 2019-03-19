package com.zhita.service.bill;

public interface UnitPriceService {

	int setunitprice(String firm, String firmType, String account, int price, String accountType, String registrationTime);

	int updateUnitPrice(String firm, String firmType, String account, int price, String accountType, int id, String registrationTime);

}

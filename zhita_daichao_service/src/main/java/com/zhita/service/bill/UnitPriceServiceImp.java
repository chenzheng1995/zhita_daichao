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
	public int setunitprice(int sourceId, int businessesId, String firmType, String account, int price, String accountType,String registrationTime,String company) {
		int number = unitPriceMapper.setunitprice(sourceId,businessesId,firmType,account,price,accountType,registrationTime,company);
		return number;
	}

	@Override
	public int updateUnitPrice(int sourceId, int businessesId, String firmType, String account, int price, String accountType,int id,String registrationTime,String company) {
		int number = unitPriceMapper.updateUnitPrice(sourceId,businessesId,firmType,account,price,accountType,id,registrationTime,company);
		return number;
	}

	@Override
	public int deleteUnitPrice(int id,String registrationTime) {
		int number = unitPriceMapper.deleteUnitPrice(id,registrationTime);
		return number;
	}

	@Override
	public int deleteFirm(int sourceId, String account, String registrationTime,String company) {
		int number = unitPriceMapper.deleteFirm(sourceId,account,registrationTime,company);
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
	public List<UnitPrice> getunitprice(int sourceId,String company,int pages,int pageSize) {
		List<UnitPrice> accountList = unitPriceMapper.getunitprice(sourceId,company,pages,pageSize);
		return accountList;
	}

	@Override
	public int getbusinessesId(String account, String company) {
		int Id = unitPriceMapper.getbusinessesId(account,company);
		return Id;
	}

	@Override
	public List<UnitPrice> getaccountBySourceId(String company, Integer sourceId, String firmType,int pages, int pageSize) {
		List<UnitPrice> accountList = unitPriceMapper.getaccountBySourceId(company,sourceId,firmType,pages,pageSize);
		return accountList;
	}

	@Override
	public List<Object> getaccountById(int sourceId, String company) {
		List<Object> firmList = unitPriceMapper.getaccountById(sourceId,company);
		return firmList;
	}

	@Override
	public int setunitprice1(UnitPrice unitPrice) {
		int number = unitPriceMapper.setunitprice1(unitPrice);
		return number;
	}

	@Override
	public int pageCountUnitprice(int sourceId, String company) {
		int totalCount=unitPriceMapper.pageCountUnitprice(sourceId,company);
		return totalCount;
	}

	@Override
	public String getaccountType(String company, String account) {
		String accountType = unitPriceMapper.getaccountType(company, account);
		return accountType;
	}

	@Override
	public List<Object> getPrice(String company, String account) {
		List<Object> list = unitPriceMapper.getPrice(company,account);
		return list;
	}

	@Override
	public Integer getSourceTo(String company, String account) {
		Integer SourceTo = unitPriceMapper.getSourceTo(company,account);
		return SourceTo;
	}

	@Override
	public int pageCountUnitprice1(Integer businessesId, String company) {
		int totalCount=unitPriceMapper.pageCountUnitprice1(businessesId,company);
		return totalCount;
	}

	@Override
	public List<UnitPrice> getunitprice1(Integer businessesId, String company, int pages, int pageSize) {
		List<UnitPrice> accountList = unitPriceMapper.getunitprice1(businessesId,company,pages,pageSize);
		return accountList;
	}

}

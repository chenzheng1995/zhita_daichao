package com.zhita.service.top_up_amount;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.TopUpAmountMapper;
import com.zhita.model.manage.TopUpAmount;

@Service
public class AmountServiceImp implements AmountService{
	@Autowired
	TopUpAmountMapper topUpAmountMapper;

	@Override
	public Integer setTUamount(String billingDate, String firm, int topUpAmount, String cashReceipts, String paymentAccount,
			String contact, String note, String company, String firmType, String registrationTime,String otimestamps) {
		Integer number = topUpAmountMapper.setTUamount(billingDate,firm,topUpAmount,cashReceipts,paymentAccount,contact,note,company,firmType,registrationTime,otimestamps);
		return number;
	}

	@Override
	public int updateTUamount(String billingDate, String firm, int topUpAmount, String cashReceipts,
			String paymentAccount, String contact, String note, String company, String firmType,
			String registrationTime,int id,String otimestamps) {
		int number = topUpAmountMapper.updateTUamount(billingDate,firm,topUpAmount,cashReceipts,paymentAccount,contact,note,company,firmType,registrationTime,id,otimestamps);
		return number;
	}

	@Override
	public int deleteAmountById(int id,String registrationTime) {
		int number = topUpAmountMapper.deleteAmountById(id,registrationTime);
		return number;
	}

	@Override
	public int deleteAmountByOperationDate(String otimestamps, String registrationTime,String company,
			String firmtype) {
		int number = topUpAmountMapper.deleteAmountByOperationDate(otimestamps,registrationTime,company,firmtype);
		return number;
	}

	@Override
	public int pageCountByAmount(String firmType, String company) {
		int totalCount=topUpAmountMapper.pageCountByAmount(firmType,company);
		return totalCount;
	}

	@Override
	public List<TopUpAmount> getAmount(String firmType, String company, int page,int pagesize) {
		List<TopUpAmount> amountList = topUpAmountMapper.getAmount(firmType,company,page,pagesize);
		return amountList;
	}

	@Override
	public Integer gettopUpAmount(String sourceName, String date, String company) {
		Integer topUpAmount = topUpAmountMapper.gettopUpAmount(sourceName,date,company);
		return topUpAmount;
	}

	@Override
	public Integer getAmountbyfirm(String startDate, String endDate, String company,String sourceName) {
		Integer topUpAmount = topUpAmountMapper.getAmountbyfirm(startDate,endDate,company,sourceName);
		return topUpAmount;
	}

	@Override
	public Integer setTUamount1(TopUpAmount topupamount) {
		Integer id = topUpAmountMapper.setTUamount1(topupamount);
		return id;
	}

	@Override
	public int pageCountByAmountNoFirm(String firmType, String company, String firm) {
		int totalCount=topUpAmountMapper.pageCountByAmountNoFirm(firmType,company,firm);
		return totalCount;
	}

	@Override
	public List<TopUpAmount> getAmountNoFirm(String firmType, String company, int page, int pageSize, String firm) {
		List<TopUpAmount> amountList = topUpAmountMapper.getAmountNoFirm(firmType,company,page,pageSize,firm);
		return amountList;
	}


}
package com.zhita.service.top_up_amount;

import java.util.List;

import com.zhita.model.manage.TopUpAmount;

public interface AmountService {

	int setTUamount(String billingDate, String firm, int topUpAmount, String cashReceipts, String paymentAccount,
			String contact, String note, String company, String firmType, String registrationTime, String otimestamps);

	int updateTUamount(String billingDate, String firm, int topUpAmount, String cashReceipts, String paymentAccount,
			String contact, String note, String company, String firmType, String registrationTime, int id, String otimestamps);

	int deleteAmountById(int id, String registrationTime);

	int deleteAmountByOperationDate(String otimestamps, String registrationTime);

	int pageCountByAmount(String firmType, String company);

	List<TopUpAmount> getAmount(String firmType, String company, int page, int pagesize);

	Integer gettopUpAmount(String sourceName, String date, String company);

}

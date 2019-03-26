package com.zhita.service.editbill;

public interface EditBillService {

	int setEditBill(String operationDate, String account, int registrationNumber, int price, int realPay, String note,
			String accountType, String firmType, String modifyTime, String registrationTime,
			String company,int sourceId, int businessesId);

}

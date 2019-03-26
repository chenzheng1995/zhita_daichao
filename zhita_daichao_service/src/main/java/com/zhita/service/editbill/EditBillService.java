package com.zhita.service.editbill;

public interface EditBillService {

	int setTUamount(String operationDate, String account, int registrationNumber, int price, int realPay, String note,
			String note2, String accountType, String firmType, String modifyTime, String registrationTime,
			String company);

}

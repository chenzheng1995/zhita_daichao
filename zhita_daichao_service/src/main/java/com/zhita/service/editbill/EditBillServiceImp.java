package com.zhita.service.editbill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.EditBillMapper;

@Service
public class EditBillServiceImp implements EditBillService{

	@Autowired
	EditBillMapper editBillMapper;

	@Override
	public int setEditBill(String operationDate, String account, int registrationNumber, int price, int realPay,
			String note, String accountType, String firmType, String modifyTime, String registrationTime,
			String company,int sourceId, int businessesId) {
		int number = editBillMapper.setEditBill(operationDate, account, registrationNumber, price, realPay,
				note, accountType, firmType, modifyTime, registrationTime, company,sourceId,businessesId);
		return number;
	}

}

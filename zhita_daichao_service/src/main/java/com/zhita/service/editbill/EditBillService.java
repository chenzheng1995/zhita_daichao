package com.zhita.service.editbill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zhita.model.manage.EditBill;

public interface EditBillService {

	int setEditBill(String operationDate, String account, int registrationNumber, int price, int realPay, String note,
			String accountType, String firmType, String modifyTime, String registrationTime,
			String company,int sourceId, int businessesId, String sourceTo);

	int updateEditBill(String oDatetimestamps, String account, Integer registrationNumber, Integer price, int realPay,
			String note, String accountType, String firmType, String modifyTimestamps, String registrationTimestamps,
			Integer sourceId, Integer businessesId, String sourceTo, int id);

	int deleteEditBillById(int id, String modifyTimestamps);


	int pageCountByEditBill2(Integer businessesId);

//	List<EditBill> getEditBill1(ArrayList<String> accountList, String company, int page, int pageSize, String firmType);

	List<EditBill> getEditBill1(HashMap<String, Object> map1);

	int pageCountByEditBill1(HashMap<String, Object> map2);

	List<EditBill> getEditBill2(Integer businessesId, String company, int page, int pageSize);

	int deleteEditBillByOperationDate(HashMap<String, Object> map1);

	int deleteEditBillByOperationDate1(Integer businessesId, String company, String oDatetimestamps, String modifyTimestamps);

	List<EditBill> getEditBillCount1(HashMap<String, Object> map1);

	List<EditBill> getEditBillCount2(Integer businessesId, String company, String startDate, String endDate,
			String accountType);

	List<String> getdate(HashMap<String, Object> map1);



}

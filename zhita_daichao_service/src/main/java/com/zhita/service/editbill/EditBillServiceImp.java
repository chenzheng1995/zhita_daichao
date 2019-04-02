package com.zhita.service.editbill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.EditBillMapper;
import com.zhita.model.manage.EditBill;

@Service
public class EditBillServiceImp implements EditBillService{

	@Autowired
	EditBillMapper editBillMapper;

	@Override
	public int setEditBill(String operationDate, String account, int registrationNumber, int price, int realPay,
			String note, String accountType, String firmType, String modifyTime, String registrationTime,
			String company,int sourceId, int businessesId, String sourceTo) {
		int number = editBillMapper.setEditBill(operationDate, account, registrationNumber, price, realPay,
				note, accountType, firmType, modifyTime, registrationTime, company,sourceId,businessesId,sourceTo);
		return number;
	}

	@Override
	public int updateEditBill(String oDatetimestamps, String account, Integer registrationNumber, Integer price,
			int realPay, String note, String accountType, String firmType, String modifyTimestamps,
			String registrationTimestamps, Integer sourceId, Integer businessesId, String sourceTo,
			int id) {
		int number = editBillMapper.updateEditBill(oDatetimestamps, account, registrationNumber, price, realPay,
				note, accountType, firmType, modifyTimestamps, registrationTimestamps,sourceId,businessesId,sourceTo,id);
		return number;
	}

	@Override
	public int deleteEditBillById(int id,String modifyTimestamps) {
		int number = editBillMapper.deleteEditBillById(id,modifyTimestamps);
		return number;
	}


	@Override
	public int pageCountByEditBill2(Integer businessesId) {
		int totalCount=editBillMapper.pageCountByEditBill2(businessesId);
		return totalCount;
	}

//	@Override
//	public List<EditBill> getEditBill1(ArrayList<String> accountList, String company, int page, int pageSize,
//			String firmType) {
//		List<EditBill> EditBillList = editBillMapper.getEditBill1(accountList,company,page,pageSize,firmType);
//		return EditBillList;
//	}

	@Override
	public List<EditBill> getEditBill1(HashMap<String, Object> map1) {
		List<EditBill> EditBillList = editBillMapper.getEditBill1(map1);
		return EditBillList;
	}

	@Override
	public int pageCountByEditBill1(HashMap<String, Object> map2) {
		int totalCount=editBillMapper.pageCountByEditBill1(map2);
		return totalCount;
	}

	@Override
	public List<EditBill> getEditBill2(Integer businessesId, String company,int page ,int pageSize) {
		List<EditBill> EditBillList = editBillMapper.getEditBill2(businessesId,company,page,pageSize);
		return EditBillList;
	}


	@Override
	public int deleteEditBillByOperationDate(HashMap<String, Object> map1) {
		int number = editBillMapper.deleteEditBillByOperationDate(map1);
		return number;
	}

	@Override
	public int deleteEditBillByOperationDate1(Integer businessesId, String company, String oDatetimestamps,String modifyTimestamps) {
		int number = editBillMapper.deleteEditBillByOperationDate1(businessesId,company,oDatetimestamps,modifyTimestamps);
		return number;
	}

	@Override
	public List<EditBill> getEditBillCount1(HashMap<String, Object> map1) {
		List<EditBill> EditBillList = editBillMapper.getEditBillCount1(map1);
		return EditBillList;
	}

	@Override
	public List<EditBill> getEditBillCount2(Integer businessesId, String company, String startDate, String endDate,
			String accountType) {
		List<EditBill> List = editBillMapper.getEditBillCount2(businessesId,company,startDate,endDate,accountType);
		return List;
	}

	@Override
	public List<String> getdate(HashMap<String, Object> map1) {
		List<String> dateList = editBillMapper.getdate(map1);
		return dateList;
	}

	@Override
	public List<String> getdate2(String startDate, String endDate, Integer businessesId, String company,int page, int pageSize) {
		List<String> dateList = editBillMapper.getdate2(startDate,endDate,businessesId,company,page,pageSize);
		return dateList;
	}

	@Override
	public int pageCountByCThrough1(String startDate, String endDate, Integer sourceId, String company) {
		int totalCount=editBillMapper.pageCountByCThrough1(startDate,endDate,sourceId,company);
		return totalCount;
	}

	@Override
	public int pageCountByCThrough2(String startDate, String endDate, Integer businessesId, String company) {
		int totalCount=editBillMapper.pageCountByCThrough2(startDate,endDate,businessesId,company);
		return totalCount;
	}

	@Override
	public int pageCountByPThrough1(String startDate, String endDate, String company,String firmType) {
		int totalCount=editBillMapper.pageCountByPThrough1(startDate,endDate,company,firmType);
		return totalCount;
	}

	@Override
	public List<Integer> getPThroughSourceId(String startDate, String endDate, String company,String firmType,int page,int pageSize) {
		List<Integer> List = editBillMapper.getPThroughSourceId(startDate,endDate,company,firmType,page,pageSize);
		return List;
	}

	@Override
	public ArrayList<EditBill> getPThrough(Integer sourceId, String startDate, String endDate, String company) {
		ArrayList<EditBill> List3 = editBillMapper.getPThrough(sourceId,startDate,endDate,company);
		return List3;
	}

	@Override
	public int pageCountByPThrough2(String startDate, String endDate, String company, String firmType) {
		int totalCount=editBillMapper.pageCountByPThrough2(startDate,endDate,company,firmType);
		return totalCount;
	}

	@Override
	public List<Integer> getPThroughBusinessesId(String startDate, String endDate, String company, String firmType) {
		List<Integer> List = editBillMapper.getPThroughBusinessesId(startDate,endDate,company,firmType);
		return List;
	}

	@Override
	public ArrayList<EditBill> getPThrough1(Integer businessesId , String startDate, String endDate, String company) {
		ArrayList<EditBill> List3 = editBillMapper.getPThrough1(businessesId,startDate,endDate,company);
		return List3;
	}


}

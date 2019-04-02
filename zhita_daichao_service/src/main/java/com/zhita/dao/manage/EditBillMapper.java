package com.zhita.dao.manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.EditBill;

public interface EditBillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EditBill record);

    int insertSelective(EditBill record);

    EditBill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EditBill record);

    int updateByPrimaryKey(EditBill record);

	int setEditBill(@Param("operationDate") String operationDate,@Param("account") String account,@Param("registrationNumber") int registrationNumber,
			@Param("price")int price,@Param("realPay") int realPay,@Param("note") String note,@Param("accountType") String accountType,
			@Param("firmType") String firmType,@Param("modifyTime")String modifyTime,@Param("registrationTime") String registrationTime,
			@Param("company")String company,@Param("sourceId") int sourceId,@Param("businessesId") int businessesId,@Param("sourceTo") String sourceTo);

	int updateEditBill(@Param("oDatetimestamps")String oDatetimestamps,@Param("account") String account,@Param("registrationNumber") Integer registrationNumber,@Param("price") Integer price,@Param("realPay") int realPay,
			@Param("note")String note,@Param("accountType") String accountType,@Param("firmType") String firmType,@Param("modifyTimestamps") String modifyTimestamps,@Param("registrationTimestamps") String registrationTimestamps,
			@Param("sourceId") Integer sourceId,@Param("businessesId") Integer businessesId,@Param("sourceTo") String sourceTo,@Param("id") int id);

	int deleteEditBillById(@Param("id")int id,@Param("modifyTimestamps") String modifyTimestamps);

	int pageCountByEditBill2(Integer businessesId);

//	List<EditBill> getEditBill1(@Param("accountList")ArrayList<String> accountList,@Param("company") String company,@Param("page") int page,@Param("pageSize") int pageSize,@Param("firmType") String firmType);

	List<EditBill> getEditBill1(HashMap<String, Object> map1);

	int pageCountByEditBill1(HashMap<String, Object> map2);

	List<EditBill> getEditBill2(@Param("businessesId")Integer businessesId,@Param("company") String company,@Param("page") int page,@Param("pageSize") int pageSize);

	int deleteEditBillByOperationDate(HashMap<String, Object> map1);

	int deleteEditBillByOperationDate1(@Param("businessesId")Integer businessesId,@Param("company") String company,@Param("oDatetimestamps") String oDatetimestamps,@Param("modifyTimestamps") String modifyTimestamps);

	List<EditBill> getEditBillCount1(HashMap<String, Object> map1);

	List<EditBill> getEditBillCount2(@Param("businessesId")Integer businessesId,@Param("company") String company,@Param("startDate") String startDate,@Param("endDate") String endDate,
			@Param("accountType")String accountType);

	List<String> getdate(HashMap<String, Object> map1);

	List<String> getdate2(@Param("startDate")String startDate,@Param("endDate") String endDate, @Param("businessesId")Integer businessesId, @Param("company")String company,@Param("page") int page,@Param("pageSize") int pageSize);

	int pageCountByCThrough1(@Param("startDate")String startDate,@Param("endDate") String endDate,@Param("sourceId") Integer sourceId,@Param("company") String company);

	int pageCountByCThrough2(@Param("startDate")String startDate,@Param("endDate") String endDate,@Param("businessesId") Integer businessesId,@Param("company") String company);

	int pageCountByPThrough1(@Param("startDate")String startDate,@Param("endDate") String endDate,@Param("company") String company,@Param("firmType") String firmType);

	List<Integer> getPThroughSourceId(@Param("startDate")String startDate,@Param("endDate") String endDate,@Param("company") String company,@Param("firmType") String firmType,@Param("page") int page,@Param("pageSize") int pageSize);

	ArrayList<EditBill> getPThrough(@Param("sourceId")Integer sourceId, @Param("startDate") String startDate,@Param("endDate") String endDate,@Param("company") String company);

	int pageCountByPThrough2(@Param("startDate")String startDate,@Param("endDate") String endDate,@Param("company") String company,@Param("firmType") String firmType);

	List<Integer> getPThroughBusinessesId(@Param("startDate")String startDate,@Param("endDate") String endDate,@Param("company") String company,@Param("firmType") String firmType);

	ArrayList<EditBill> getPThrough1(@Param("businessesId")Integer businessesId, @Param("startDate") String startDate,@Param("endDate") String endDate,@Param("company") String company);







}
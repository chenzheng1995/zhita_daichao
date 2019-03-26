package com.zhita.dao.manage;

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
			@Param("company")String company,@Param("sourceId") int sourceId,@Param("businessesId") int businessesId);



}
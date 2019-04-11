package com.zhita.dao.manage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.UnitPrice;

public interface UnitPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UnitPrice record);

    int insertSelective(UnitPrice record);

    UnitPrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UnitPrice record);

    int updateByPrimaryKey(UnitPrice record);

	int setunitprice(@Param("sourceId")int sourceId,@Param("businessesId")int businessesId,@Param("firmType") String firmType,@Param("account") String account,@Param("price") int price,@Param("accountType") String accountType,@Param("registrationTime") String registrationTime,@Param("company") String company);

	int updateUnitPrice(@Param("sourceId")int sourceId,@Param("businessesId")int businessesId,@Param("firmType") String firmType,@Param("account") String account,@Param("price") int price,@Param("accountType") String accountType,@Param("id") int id,@Param("registrationTime") String registrationTime,@Param("company") String company);

	int deleteUnitPrice(@Param("id")int id,@Param("registrationTime") String registrationTime);

	int deleteFirm(@Param("sourceId")int sourceId,@Param("account") String account,@Param("registrationTime") String registrationTime,@Param("company") String company);

	List<String> getaccount(@Param("firm")String firm,@Param("firmType") String firmType);

	List<Integer> getprice(String account);

	List<UnitPrice> getunitprice(@Param("sourceId") int sourceId,@Param("company") String company,@Param("pages") int pages,@Param("pageSize") int pageSize);

	int getbusinessesId(@Param("account")String account,@Param("company") String company);

	List<UnitPrice> getaccountBySourceId(@Param("company")String company,@Param("sourceId") Integer sourceId,@Param("firmType") String firmType,@Param("pages") int pages,@Param("pageSize") int pageSize);

	List<Object> getaccountById(@Param("sourceId")int sourceId,@Param("company") String company);

	int setunitprice1(UnitPrice unitPrice);

	int pageCountUnitprice(@Param("sourceId")int sourceId,@Param("company") String company);

	String getaccountType(@Param("company")String company,@Param("account") String account);

	List<Object> getPrice(@Param("company")String company,@Param("account") String account);

	Integer getSourceTo(@Param("company")String company,@Param("account") String account);

	int pageCountUnitprice1(@Param("businessesId")Integer businessesId,@Param("company") String company);

	List<UnitPrice> getunitprice1(@Param("businessesId")Integer businessesId,@Param("company") String company,@Param("pages") int pages,@Param("pageSize") int pageSize);




}
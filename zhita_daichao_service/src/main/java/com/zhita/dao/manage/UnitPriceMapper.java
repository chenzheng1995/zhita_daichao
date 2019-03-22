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

	int setunitprice(@Param("firm")String firm,@Param("firmType") String firmType,@Param("account") String account,@Param("price") int price,@Param("accountType") String accountType,@Param("registrationTime") String registrationTime,@Param("company") String company);

	int updateUnitPrice(@Param("firm")String firm,@Param("firmType") String firmType,@Param("account") String account,@Param("price") int price,@Param("accountType") String accountType,@Param("id") int id,@Param("registrationTime") String registrationTime);

	int deleteUnitPrice(@Param("id")int id,@Param("registrationTime") String registrationTime);

	int deleteFirm(@Param("firm")String firm,@Param("account") String account,@Param("registrationTime") String registrationTime,@Param("company") String company);

	List<String> getaccount(@Param("firm")String firm,@Param("firmType") String firmType);

	List<Integer> getprice(String account);

	List<UnitPrice> getunitprice(@Param("firm")String firm,@Param("firmType") String firmType,@Param("company") String company);
}
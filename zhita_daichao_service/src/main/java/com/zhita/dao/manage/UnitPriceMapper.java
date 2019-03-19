package com.zhita.dao.manage;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.UnitPrice;

public interface UnitPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UnitPrice record);

    int insertSelective(UnitPrice record);

    UnitPrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UnitPrice record);

    int updateByPrimaryKey(UnitPrice record);

	int setunitprice(@Param("firm")String firm,@Param("firmType") String firmType,@Param("account") String account,@Param("price") int price,@Param("accountType") String accountType,@Param("registrationTime") String registrationTime);

	int updateUnitPrice(@Param("firm")String firm,@Param("firmType") String firmType,@Param("account") String account,@Param("price") int price,@Param("accountType") String accountType,@Param("id") int id,@Param("registrationTime") String registrationTime);
}
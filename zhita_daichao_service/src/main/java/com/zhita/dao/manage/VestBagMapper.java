package com.zhita.dao.manage;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.VestBag;

public interface VestBagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VestBag record);

    int insertSelective(VestBag record);

    VestBag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VestBag record);

    int updateByPrimaryKey(VestBag record);

	Integer getVestBag(@Param("vestBagName")String vestBagName,@Param("company") String company);

	void setVestBag(@Param("vestBagName")String vestBagName,@Param("company") String company);




}
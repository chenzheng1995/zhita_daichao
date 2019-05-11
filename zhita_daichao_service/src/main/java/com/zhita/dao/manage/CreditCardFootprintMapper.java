package com.zhita.dao.manage;

import org.apache.ibatis.annotations.Param;

//完全复制商品足迹表mapper.java
public interface CreditCardFootprintMapper {
	
	//后台管理---根据传过来的足迹名称，查询出足迹的个数
	int queryCount(String businessName,String company);
	
	//小程序
	long getApplications(@Param("businessName")String businessName,@Param("company") String company);

}
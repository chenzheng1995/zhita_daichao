package com.zhita.service.card;

import org.apache.ibatis.annotations.Param;

//完全复制CommodityFootprintService.java
public interface IntCreditCardFootprintService {

	//后台管理---根据传过来的足迹名称，查询出足迹的个数
	public int queryCount(String businessName,String company);
	//小程序
	long getApplications(@Param("businessName")String businessName,@Param("company") String company);
}

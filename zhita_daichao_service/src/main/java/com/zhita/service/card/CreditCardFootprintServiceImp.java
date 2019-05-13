package com.zhita.service.card;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.CreditCardFootprintMapper;

//完全复制CommodityFootprintServiceImp.java
@Service
public class CreditCardFootprintServiceImp implements IntCreditCardFootprintService{
	
	@Autowired
	private CreditCardFootprintMapper creditCardFootprintMapper;
	

	//后台管理---根据传过来的足迹名称，查询出足迹的个数
	public int queryCount(String businessName,String company) {
		int count=creditCardFootprintMapper.queryCount(businessName,company);
		return count;
	}

	//小程序
	public long getApplications(@Param("businessName")String businessName,@Param("company") String company){
		long applications =creditCardFootprintMapper.getApplications(businessName, company);
		return applications;
	}

	@Override
	public int insertfootprint(String footprintName, String userId, long currentTimestamp, String company) {
		int number = creditCardFootprintMapper.insertfootprint(footprintName, userId, currentTimestamp,company);
		return number;
	}
}

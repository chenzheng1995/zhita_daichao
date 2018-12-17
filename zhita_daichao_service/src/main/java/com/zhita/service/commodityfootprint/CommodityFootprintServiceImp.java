package com.zhita.service.commodityfootprint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.CommodityFootprintMapper;



@Service
public class CommodityFootprintServiceImp implements CommodityFootprintService{

	@Autowired
	CommodityFootprintMapper commodityFootprintMapper;
	
	@Override
	public long getDailyApplications(long todayZeroTimestamps, long tomorrowZeroTimestamps) {
		long dailyApplications = commodityFootprintMapper.getDailyApplications(todayZeroTimestamps,tomorrowZeroTimestamps);
		return dailyApplications;
	}

	@Override
	public long getdailyApplicationsUsers(long todayZeroTimestamps, long tomorrowZeroTimestamps) {
		long dailyApplicationsUsers = commodityFootprintMapper.getdailyApplicationsUsers(todayZeroTimestamps,tomorrowZeroTimestamps);
		return dailyApplicationsUsers;
	}

	@Override
	public long getmonthlyApplications(long monthlyZeroTimestamps, long nextMonthlyZeroTimestamps) {
		long monthlyApplications = commodityFootprintMapper.getmonthlyApplications(monthlyZeroTimestamps,nextMonthlyZeroTimestamps);
		return monthlyApplications;
	}

	@Override
	public long getmonthlyApplicationsUsers(long monthlyZeroTimestamps, long nextMonthlyZeroTimestamps) {
		long monthlyApplicationsUsers = commodityFootprintMapper.getmonthlyApplicationsUsers(monthlyZeroTimestamps,nextMonthlyZeroTimestamps);
		return monthlyApplicationsUsers;
	}

	@Override
	public int insertfootprint(String footprintName, String userId, long currentTimestamp) {
		int number = commodityFootprintMapper.insertfootprint(footprintName,userId,currentTimestamp);
		return number;
	}




}

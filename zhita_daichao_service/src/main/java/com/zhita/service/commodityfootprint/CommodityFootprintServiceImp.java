package com.zhita.service.commodityfootprint;



import java.util.List;

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

	@Override
	public List<String> getbusinessName(String userId,int pageSize, int startRow) {
		List<String> list =	(List<String>) commodityFootprintMapper.getbusinessName(userId,pageSize,startRow);
		return list;
	}

	@Override
	public long getApplications(String businessName) {
		long applications = commodityFootprintMapper.getApplications(businessName);
		return applications;
	}




}

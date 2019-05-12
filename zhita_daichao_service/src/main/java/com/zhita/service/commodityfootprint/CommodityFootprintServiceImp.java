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
	public int insertfootprint(String footprintName, String userId, long currentTimestamp, String company) {
		int number = commodityFootprintMapper.insertfootprint(footprintName,userId,currentTimestamp,company);
		return number;
	}


	@Override
	public List<String> getbusinessName(String userId,int pageSize, int startRow,String company) {
		List<String> list =	(List<String>) commodityFootprintMapper.getbusinessName(userId,pageSize,startRow,company);
		return list;
	}

	@Override
	public long getApplications(String businessName,String company) {
		long applications = commodityFootprintMapper.getApplications(businessName,company);
		return applications;
	}


	//后台管理---根据传过来的足迹名称，查询出足迹的个数
	public int queryCount(String businessName,String company) {
		int count=commodityFootprintMapper.queryCount(businessName,company);
		return count;
	}

	@Override
	public long getRecordNumber(String userId,String company) {
		long recordNumber = commodityFootprintMapper.getRecordNumber(userId,company);
		return recordNumber;
	}





}

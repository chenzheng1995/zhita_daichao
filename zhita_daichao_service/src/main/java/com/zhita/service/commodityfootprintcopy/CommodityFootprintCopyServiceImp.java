package com.zhita.service.commodityfootprintcopy;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.CommodityFootprintCopyMapper;
import com.zhita.dao.manage.CommodityFootprintMapper;



@Service
public class CommodityFootprintCopyServiceImp implements CommodityFootprintCopyService{

	@Autowired
	CommodityFootprintCopyMapper commodityFootprintCopyMapper;
	
	@Override
	public long getDailyApplications1(long todayZeroTimestamps, long tomorrowZeroTimestamps) {
		long dailyApplications = commodityFootprintCopyMapper.getDailyApplications1(todayZeroTimestamps,tomorrowZeroTimestamps);
		return dailyApplications;
	}

	@Override
	public long getdailyApplicationsUsers1(long todayZeroTimestamps, long tomorrowZeroTimestamps) {
		long dailyApplicationsUsers = commodityFootprintCopyMapper.getdailyApplicationsUsers1(todayZeroTimestamps,tomorrowZeroTimestamps);
		return dailyApplicationsUsers;
	}

	@Override
	public long getmonthlyApplications1(long monthlyZeroTimestamps, long nextMonthlyZeroTimestamps) {
		long monthlyApplications = commodityFootprintCopyMapper.getmonthlyApplications1(monthlyZeroTimestamps,nextMonthlyZeroTimestamps);
		return monthlyApplications;
	}

	@Override
	public long getmonthlyApplicationsUsers1(long monthlyZeroTimestamps, long nextMonthlyZeroTimestamps) {
		long monthlyApplicationsUsers = commodityFootprintCopyMapper.getmonthlyApplicationsUsers1(monthlyZeroTimestamps,nextMonthlyZeroTimestamps);
		return monthlyApplicationsUsers;
	}

	@Override
	public int insertfootprint1(String footprintName, String userId, long currentTimestamp, String company) {
		int number = commodityFootprintCopyMapper.insertfootprint1(footprintName,userId,currentTimestamp,company);
		return number;
	}


	@Override
	public List<String> getbusinessName1(String userId,int pageSize, int startRow,String company) {
		List<String> list =	(List<String>) commodityFootprintCopyMapper.getbusinessName1(userId,pageSize,startRow,company);
		return list;
	}

	@Override
	public long getApplications1(String businessName,String company) {
		long applications = commodityFootprintCopyMapper.getApplications1(businessName,company);
		return applications;
	}


	//后台管理---根据传过来的足迹名称，查询出足迹的个数
	public int queryCount1(String businessName,String company) {
		int count=commodityFootprintCopyMapper.queryCount1(businessName,company);
		return count;
	}

	@Override
	public long getRecordNumber1(String userId,String company) {
		long recordNumber = commodityFootprintCopyMapper.getRecordNumber1(userId,company);
		return recordNumber;
	}

	@Override
	public int insertfootprintAppCopy(String footprintName, String userId, long currentTimestamp, String company,
			String oneSourceName, String twoSourceName) {
		int number = commodityFootprintCopyMapper.insertfootprintAppCopy(footprintName, userId, currentTimestamp,company,oneSourceName,twoSourceName);
		return number;
	}

	@Override
	public long getRecordNumberAppCopy(String userId, String company, String oneSourceName, String twoSourceName) {
		 long recordNumber = commodityFootprintCopyMapper.getRecordNumberAppCopy(userId,company,oneSourceName,twoSourceName);
		return recordNumber;
	}

	@Override
	public List<String> getbusinessNameAppCopy(String userId, int pageSize, int startRow, String company,
			String oneSourceName, String twoSourceName) {
		List<String> list =	(List<String>) commodityFootprintCopyMapper.getbusinessNameAppCopy(userId,pageSize,startRow,company,oneSourceName,twoSourceName);
		return list;
	}

	@Override
	public long getApplicationsAppCopy(String businessName, String company, String oneSourceName, String twoSourceName) {
		long applications = commodityFootprintCopyMapper.getApplicationsAppCopy(businessName,company,oneSourceName,twoSourceName);
		return applications;
	}



}

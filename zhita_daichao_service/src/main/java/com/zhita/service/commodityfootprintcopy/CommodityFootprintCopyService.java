package com.zhita.service.commodityfootprintcopy;

import java.util.List;

public interface CommodityFootprintCopyService {

	long getDailyApplications1(long todayZeroTimestamps, long tomorrowZeroTimestamps);

	long getdailyApplicationsUsers1(long todayZeroTimestamps, long tomorrowZeroTimestamps);

	long getmonthlyApplications1(long monthlyZeroTimestamps, long nextMonthlyZeroTimestamps);

	long getmonthlyApplicationsUsers1(long monthlyZeroTimestamps, long nextMonthlyZeroTimestamps);

	int insertfootprint1(String footprintName, String userId, long currentTimestamp, String company);
	
	//后台管理---根据传过来的足迹名称，查询出足迹的个数
	public int queryCount1(String businessName,String company);

	List<String> getbusinessName1(String userId, int pageSize, int startRow, String company);

	long getApplications1(String businessName, String company);

	long getRecordNumber1(String userId, String company);

}

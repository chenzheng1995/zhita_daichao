package com.zhita.service.commodityfootprint;

import java.util.List;

public interface CommodityFootprintService {

	long getDailyApplications(long todayZeroTimestamps, long tomorrowZeroTimestamps);

	long getdailyApplicationsUsers(long todayZeroTimestamps, long tomorrowZeroTimestamps);

	long getmonthlyApplications(long monthlyZeroTimestamps, long nextMonthlyZeroTimestamps);

	long getmonthlyApplicationsUsers(long monthlyZeroTimestamps, long nextMonthlyZeroTimestamps);

	int insertfootprint(String footprintName, String userId, long currentTimestamp);

	List<String> getbusinessName(String userId, int pageSize, int startRow);

	long getApplications(String businessName);

}

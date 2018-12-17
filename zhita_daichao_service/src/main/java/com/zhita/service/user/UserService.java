package com.zhita.service.user;

public interface UserService {

	Long getregistered();

	Long getdailyUsers(long todayZeroTimestamps, long tomorrowZeroTimestamps);

	Long getmonthlyUsers(long monthlyZeroTimestamps, long nextMonthlyZeroTimestamps);

}

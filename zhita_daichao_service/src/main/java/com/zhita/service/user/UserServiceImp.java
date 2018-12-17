package com.zhita.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.UserMapper;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserMapper userMapper;

	public Long getregistered() {
		Long registered = userMapper.getregistered();
		return registered;
	}

	@Override
	public Long getdailyUsers(long todayZeroTimestamps, long tomorrowZeroTimestamps) {
		Long dailyUsers = userMapper.getdailyUsers(todayZeroTimestamps, tomorrowZeroTimestamps);
		return dailyUsers;
	}

	@Override
	public Long getmonthlyUsers(long monthlyZeroTimestamps, long nextMonthlyZeroTimestamps) {
		Long monthlyUsers = userMapper.getmonthlyUsers(monthlyZeroTimestamps, nextMonthlyZeroTimestamps);
		return monthlyUsers;
	}

}

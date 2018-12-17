package com.zhita.controller.home;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.service.commodityfootprint.CommodityFootprintService;
import com.zhita.service.user.UserService;
import com.zhita.util.Timestamps;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	UserService userService;
	
	@Autowired
	CommodityFootprintService commodityFootprintService;

	@RequestMapping("/gethome")
	@ResponseBody
	public Map<String, Long> getHome(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Long> map = new HashMap<>();
		long registered = userService.getregistered(); // 获取总注册数
		Timestamps timestamps = new Timestamps();
		long todayZeroTimestamps = timestamps.getTodayZeroTimestamps(); //今天0点的时间戳
		long tomorrowZeroTimestamps = todayZeroTimestamps+86400000; //明天0点的时间戳
		long monthlyZeroTimestamps = timestamps.getMonthlyZeroTimestamps();// 获取当前月第一天零点的时间戳
		long nextMonthlyZeroTimestamps = timestamps.getNextMonthlyZeroTimestamps();// 获取下个月第一天零点的时间戳
		long dailyUsers = userService.getdailyUsers(todayZeroTimestamps,tomorrowZeroTimestamps); //获取日新增用户数
		long monthlyUsers = userService.getmonthlyUsers(monthlyZeroTimestamps,nextMonthlyZeroTimestamps);//获取月新增用户数
		long dailyApplications = commodityFootprintService.getDailyApplications(todayZeroTimestamps,tomorrowZeroTimestamps); // 获取日申请次数
		long dailyApplicationsUsers = commodityFootprintService.getdailyApplicationsUsers(todayZeroTimestamps,tomorrowZeroTimestamps); // 获取日申请用户
		long monthlyApplications = commodityFootprintService.getmonthlyApplications(monthlyZeroTimestamps,nextMonthlyZeroTimestamps); // 获取月申请次数
		long monthlyApplicationsUsers = commodityFootprintService.getmonthlyApplicationsUsers(monthlyZeroTimestamps,nextMonthlyZeroTimestamps); // 获取月申请用户
		map.put("registered", registered);
		map.put("dailyUsers", dailyUsers);
		map.put("monthlyUsers", monthlyUsers);
		map.put("dailyApplications", dailyApplications);
		map.put("dailyApplicationsUsers", dailyApplicationsUsers);
		map.put("monthlyApplications", monthlyApplications);
		map.put("monthlyApplicationsUsers", monthlyApplicationsUsers);
		return map;

	}
}

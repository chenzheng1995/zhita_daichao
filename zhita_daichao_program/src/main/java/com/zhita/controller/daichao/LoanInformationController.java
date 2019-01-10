package com.zhita.controller.daichao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.service.loaninformation.LoanInformationService;


@Controller
@RequestMapping("/loaninformation")
public class LoanInformationController {
	
	@Autowired
	LoanInformationService loanInformationService;
	
	//添加贷款信息
	@RequestMapping("/setloaninformation")
	@ResponseBody
	/*
	 * userId  用户id
	 * name  用户名字
	 * idCard 身份证
	 * professionalIdentity 职业身份
	 * monthlyIncomeRange 月收入范围
	 * educationalBackground  学历
	 * sesamePoints  芝麻分
	 * cellPhoneTime  手机号使用时长
	 * isCreditCard  是否有信用卡
	 * isAccumulationFund 是否缴纳公积金
	 * isSocialSecurity 是否缴纳社保
	 * isCar 名下有无车辆
	 * isHouse 名下有无房产
	 */
	public Map<String, Object> setloanInformation(int userId,String name,String idCard,String professionalIdentity,String monthlyIncomeRange
			,String educationalBackground,String sesamePoints,String cellPhoneTime,String isCreditCard,String isAccumulationFund
			,String isSocialSecurity,String isCar,String isHouse) {
		Map<String, Object> map = new HashMap<String, Object>();
		int number = loanInformationService.setloanInformation(userId,name,idCard,professionalIdentity,monthlyIncomeRange,educationalBackground,
				sesamePoints,cellPhoneTime,isCreditCard,isAccumulationFund,isSocialSecurity,isCar,isHouse);
		if(number == 1) {
			map.put("msg", "插入成功");
			map.put("SCode", "200");
		}else {
			map.put("msg", "插入失败");
			map.put("SCode", "405");
		}
		return map; 
		
	}
}

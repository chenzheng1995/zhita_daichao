package com.zhita.controller.daichao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.LoanInformation;
import com.zhita.service.loaninformation.LoanInformationService;


@Controller
@RequestMapping("/loaninformation")
public class LoanInformationController {
	
	@Autowired
	LoanInformationService loanInformationService;
	
	//添加贷款信息
	@RequestMapping("/setloaninformation")
	@ResponseBody
	@Transactional
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
	 * company 公司名称
	 */
	public Map<String, Object> setloanInformation(int userId,String name,String idCard,String professionalIdentity,String monthlyIncomeRange
			,String educationalBackground,String sesamePoints,String cellPhoneTime,String isCreditCard,String isAccumulationFund
			,String isSocialSecurity,String isCar,String isHouse,String company) {
		Map<String, Object> map = new HashMap<String, Object>();
		int number = loanInformationService.setloanInformation(userId,name,idCard,professionalIdentity,monthlyIncomeRange,educationalBackground,
				sesamePoints,cellPhoneTime,isCreditCard,isAccumulationFund,isSocialSecurity,isCar,isHouse,company);
		if(number == 1) {
			map.put("msg", "更新成功");
			map.put("SCode", "200");
		}else {
			map.put("msg", "更新失败");
			map.put("SCode", "405");
		}
		return map; 
		
	}
	
	//查询贷款信息
	@RequestMapping("/getloaninformation")
	@ResponseBody
	public Map<String, Object> getloanInformation(int userId,String company){
		Map<String, Object> map = new HashMap<String, Object>();
		LoanInformation loanInformation = loanInformationService.getloanInformation(userId,company); //查找该用户的贷款信息
		if (loanInformation == null) {	
			map.put("msg","不存在该用户，请检查userId和company是否传对");
		}else {
			map.put("loanInformation", loanInformation);
		}
		return map;
		
	}
}

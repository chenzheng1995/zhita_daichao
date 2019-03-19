package com.zhita.controller.bill;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.service.bill.UnitPriceService;


@Controller
@RequestMapping("/bill")
public class BillController {

	@Autowired
	UnitPriceService UnitPriceService;
	
    //单价设置里的添加
	/**
	 * 
	 * @param firm  公司名
	 * @param firmType 公司类型（渠道方为1，甲方为2）
	 * @param account 账号
	 * @param price 价格
	 * @param accountType 账号类型
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/setunitprice")
    public Map<String,Object> setUnitPrice (String firm, String firmType, String account,int price,String accountType){
		HashMap<String,Object> map=new HashMap<>();
		String registrationTime = System.currentTimeMillis() + ""; // 获取当前时间戳
		int number = UnitPriceService.setunitprice(firm,firmType,account,price,accountType,registrationTime);
		if (number == 1) {								
			map.put("msg", "数据插入成功");
			map.put("SCode", "200");
		} else {
			map.put("msg", "数据插入失败");
			map.put("SCode", "405");
		}
		return map;
	}
	
    //单价设置里的更新
	/**
	 * 
	 * @param firm  公司名
	 * @param firmType 公司类型（渠道方为1，甲方为2）
	 * @param account 账号
	 * @param price 价格
	 * @param accountType 账号类型
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateunitprice")
    public Map<String,Object> updateUnitPrice (String firm, String firmType, String account,int price,String accountType,int id){
		HashMap<String,Object> map=new HashMap<>();
		String registrationTime = System.currentTimeMillis() + ""; // 获取当前时间戳
		int number = UnitPriceService.updateUnitPrice(firm,firmType,account,price,accountType,id,registrationTime);
		if (number == 1) {								
			map.put("msg", "数据修改成功");
			map.put("SCode", "200");
		} else {
			map.put("msg", "数据修改失败");
			map.put("SCode", "405");
		}
		return map;
	}
	
    //单价设置里的删除
	/**
	 * 
	 * @param firm  公司名
	 * @param firmType 公司类型（渠道方为1，甲方为2）
	 * @param account 账号
	 * @param price 价格
	 * @param accountType 账号类型
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping("/deleteunitprice")
//    public Map<String,Object> deleteUnitPrice (int id){
//		HashMap<String,Object> map=new HashMap<>();
//		int number = UnitPriceService.deleteUnitPrice(id);
//		if (number == 1) {								
//			map.put("msg", "数据修改成功");
//			map.put("SCode", "200");
//		} else {
//			map.put("msg", "数据修改失败");
//			map.put("SCode", "405");
//		}
//		return map;
//	}
	}
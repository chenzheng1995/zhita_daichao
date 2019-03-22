package com.zhita.controller.bill;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.UnitPrice;
import com.zhita.service.bill.UnitPriceService;
import com.zhita.service.merchant.IntMerchantService;
import com.zhita.service.registe.IntRegisteService;


@Controller
@RequestMapping("/bill")
public class BillController {

	@Autowired
	UnitPriceService UnitPriceService;
	
	@Autowired
	IntMerchantService intMerchantService;
	
	@Autowired
	IntRegisteService intRegisteService;
	
	
	
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
    public Map<String,Object> setUnitPrice (String firm, String firmType, String account,int price,String accountType,String company){
		HashMap<String,Object> map=new HashMap<>();
		String registrationTime = System.currentTimeMillis() + ""; // 获取当前时间戳
		int number = UnitPriceService.setunitprice(firm,firmType,account,price,accountType,registrationTime,company);
		if (number != 0) {								
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
		if (number != 0) {								
			map.put("msg", "数据修改成功");
			map.put("SCode", "200");
		} else {
			map.put("msg", "数据修改失败");
			map.put("SCode", "405");
		}
		return map;
	}
	
    //单价设置里的单条记录删除
	@ResponseBody
	@RequestMapping("/deleteunitprice")
    public Map<String,Object> deleteUnitPrice (int id){
		HashMap<String,Object> map=new HashMap<>();
		String registrationTime = System.currentTimeMillis() + ""; // 获取当前时间戳
		int number = UnitPriceService.deleteUnitPrice(id,registrationTime);
		if (number != 0) {								
			map.put("msg", "数据删除成功");
			map.put("SCode", "200");
		} else {
			map.put("msg", "数据删除失败");
			map.put("SCode", "405");
		}
		return map;
	}
	
    //单价设置里的删除某个账号的所有数据
	/**
	 * 
	 * @param firm  公司名
	 * @param account 账号
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deletefirm")
    public Map<String,Object> deleteFirm (String firm,String account,String company){
		HashMap<String,Object> map=new HashMap<>();
		String registrationTime = System.currentTimeMillis() + ""; // 获取当前时间戳
		int number = UnitPriceService.deleteFirm(firm,account,registrationTime,company);
		if (number != 0) {								
			map.put("msg", "数据删除成功");
			map.put("SCode", "200");
		} else {
			map.put("msg", "数据删除失败");
			map.put("SCode", "405");
		}
		return map;
	}
	
//    //单价设置里查询所有数据
//	/**
//	 * 
//	 * @param firm  公司名
//	 * @param firmType 公司类型（渠道方为1，甲方为2）
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/getunitprice")
//    public Map<String,Object> getunitprice (String firm,String firmType){
//		HashMap<String,Object> map=new HashMap<>();
//		List<String> accountList = UnitPriceService.getaccount(firm,firmType);
//		for (String account : accountList) {
//			List<Integer> priceList = UnitPriceService.getprice(account);
//			map.put(account, priceList);
//		}
//		map.put("list", accountList);
//		return map;
//	}
	
    //单价设置里查询所有数据
	/**
	 * 
	 * @param firm  公司名
	 * @param firmType 公司类型（渠道方为1，甲方为2）
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getunitprice")
    public Map<String,Object> getunitprice (String firm,String firmType,String company){
		HashMap<String,Object> map=new HashMap<>();
		List<UnitPrice> accountList = UnitPriceService.getunitprice(firm,firmType,company);
		map.put("list", accountList);
		return map;
	}
	
    //根据传过来的平台类型，返回平台名
	@ResponseBody
	@RequestMapping("/getfirm")
    public Map<String,Object> getFirm (String firmType,String company){   //firmType（渠道方为1，甲方为2）
		HashMap<String,Object> map=new HashMap<>();
		List<String> firmList = null;
		if(firmType.equals("1")) {
			firmList = intMerchantService.getOneFirm(company);
		}
		if(firmType.equals("2")) {
			firmList = intRegisteService.getTwoFirm(company);
		}
		map.put("firmList", firmList);
		return map;
	}
	}
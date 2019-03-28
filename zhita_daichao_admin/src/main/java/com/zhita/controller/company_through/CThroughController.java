package com.zhita.controller.company_through;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.EditBill;
import com.zhita.model.manage.UnitPrice;
import com.zhita.service.bill.UnitPriceService;
import com.zhita.service.editbill.EditBillService;
import com.zhita.service.top_up_amount.AmountService;
import com.zhita.util.Timestamps;

@Controller
@RequestMapping("/CThrough")
public class CThroughController {
	@Autowired
	EditBillService editBillService;
	
	@Autowired
	UnitPriceService unitPriceService;
	
	@Autowired
	AmountService amountService;
	
	/**
	 * 
	 * @param firmType 平台类型
	 * @param company 公司名
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param sourceId 渠道来源
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("/getCThrough")
	@Transactional
	public ArrayList<Object> getCThrough(String firmType,String company,String startDate,String endDate,Integer sourceId,String sourceName) throws ParseException {
		HashMap<String, Object> map = new HashMap<>();
		HashMap<String, Object> map1 = new HashMap<>();
		
		ArrayList<String> accountList = new ArrayList<>();
		ArrayList<Object> List2 = new ArrayList<>();
		Timestamps timestamps = new Timestamps();
		startDate = timestamps.dateToStamp(startDate);//转换成时间戳
		endDate = (Long.parseLong(timestamps.dateToStamp(endDate))+86400000)+"";//转换成时间戳
		if(firmType.equals("1")) {
		List<UnitPrice> List = unitPriceService.getaccountBySourceId(company,sourceId,firmType);
		for (UnitPrice unitPrice : List) {
			accountList.add( unitPrice.getAccount());
		}
		map1.put("accountList", accountList);
		map1.put("company", company);
		map1.put("startDate", startDate);
		map1.put("endDate", endDate);
		
		List<String> dateList = editBillService.getdate(map1);
		for (String date : dateList) {
			HashMap<String, Object> map2 = new HashMap<>();	
			startDate = date;
			endDate = (Long.parseLong(startDate)+86400000)+"";//转换成时间戳
			map1.put("startDate", startDate);
			map1.put("endDate", endDate);
			map1.put("accountType", "CPA");
			List<EditBill> CPAList = editBillService.getEditBillCount1(map1);
			map1.put("accountType", "CPS");
			List<EditBill> CPSList = editBillService.getEditBillCount1(map1);
			map1.put("accountType", "UV");
			List<EditBill> UVList = editBillService.getEditBillCount1(map1);
			Integer CPARealpay = CPAList.get(0).getRealpay();
			Integer CPSRealpay = CPSList.get(0).getRealpay();
			Integer UVRealpay = UVList.get(0).getRealpay();
			if(CPARealpay==null) {
				CPARealpay = 0;
			}
			if(CPSRealpay==null) {
				CPSRealpay = 0;
			}
			if(UVRealpay==null) {
				UVRealpay = 0;
			}
			int realpaySum = CPARealpay+CPSRealpay+UVRealpay;//实付金额
			Integer topUpAmount = amountService.gettopUpAmount(sourceName,date,company);//获取充值金额
			if(topUpAmount==null) {
				topUpAmount=0;
			}
			int remainingAmount = topUpAmount-realpaySum;//剩余金额
			if(realpaySum>0) {
				realpaySum = realpaySum*-1;
			}
			map2.put("CPAList", CPAList);
			map2.put("CPSList", CPSList);
			map2.put("UVList", UVList);
			map2.put("realpaySum", realpaySum);
			map2.put("topUpAmount", topUpAmount);
			map2.put("remainingAmount", remainingAmount);
			List2.add(map2);
		}
		
		}
		
		if(firmType.equals("2")) {
			List<UnitPrice> List = unitPriceService.getaccountBySourceId(company,sourceId,firmType);
			for (UnitPrice unitPrice : List) {
				accountList.add( unitPrice.getAccount());
			}
			map1.put("accountList", accountList);
			map1.put("company", company);
			map1.put("startDate", startDate);
			map1.put("endDate", endDate);
			
			List<String> dateList = editBillService.getdate(map1);
			for (String date : dateList) {
				HashMap<String, Object> map2 = new HashMap<>();	
				startDate = date;
				endDate = (Long.parseLong(startDate)+86400000)+"";//转换成时间戳
				map1.put("startDate", startDate);
				map1.put("endDate", endDate);
				map1.put("accountType", "CPA");
				List<EditBill> CPAList = editBillService.getEditBillCount1(map1);
				map1.put("accountType", "CPS");
				List<EditBill> CPSList = editBillService.getEditBillCount1(map1);
				map1.put("accountType", "UV");
				List<EditBill> UVList = editBillService.getEditBillCount1(map1);
				Integer CPARealpay = CPAList.get(0).getRealpay();
				Integer CPSRealpay = CPSList.get(0).getRealpay();
				Integer UVRealpay = UVList.get(0).getRealpay();
				if(CPARealpay==null) {
					CPARealpay = 0;
				}
				if(CPSRealpay==null) {
					CPSRealpay = 0;
				}
				if(UVRealpay==null) {
					UVRealpay = 0;
				}
				int realpaySum = CPARealpay+CPSRealpay+UVRealpay;//实付金额
				Integer topUpAmount = amountService.gettopUpAmount(sourceName,date,company);//获取充值金额
				if(topUpAmount==null) {
					topUpAmount=0;
				}
				int remainingAmount = topUpAmount-realpaySum;//剩余金额
				if(realpaySum>0) {
					realpaySum = realpaySum*-1;
				}
				map2.put("CPAList", CPAList);
				map2.put("CPSList", CPSList);
				map2.put("UVList", UVList);
				map2.put("realpaySum", realpaySum);
				map2.put("topUpAmount", topUpAmount);
				map2.put("remainingAmount", remainingAmount);
				List2.add(map2);
			}
			
			}
		
		
		return List2;
	
	}

}

package com.zhita.controller.platform_through;

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
import com.zhita.service.bill.UnitPriceService;
import com.zhita.service.editbill.EditBillService;
import com.zhita.service.merchant.IntMerchantService;
import com.zhita.service.registe.IntRegisteService;
import com.zhita.service.top_up_amount.AmountService;
import com.zhita.util.PageUtil;
import com.zhita.util.Timestamps;

@Controller
@RequestMapping("/PThrough")
public class PThroughController {
	@Autowired
	EditBillService editBillService;
	
	@Autowired
	UnitPriceService unitPriceService;
	
	@Autowired
	AmountService amountService;
	
	@Autowired
	IntMerchantService intMerchantService;
	
	@Autowired
	IntRegisteService intRegisteService;
	


	@ResponseBody
	@RequestMapping("/getPThrough")
	@Transactional
	public Map<String,Object> getPThrough(String firmType,String company,String startDate,String endDate,Integer page) throws ParseException {
		HashMap<String, Object> map = new HashMap<>();
		ArrayList<EditBill> List2 = new ArrayList<>();
		Timestamps timestamps = new Timestamps();
		startDate = timestamps.dateToStamp(startDate);//转换成时间戳
		endDate = (Long.parseLong(timestamps.dateToStamp(endDate))+86400000)+"";//转换成时间戳
		if(firmType.equals("1")) {
			int totalCount=editBillService.pageCountByPThrough1(startDate,endDate,company,firmType);
	    	PageUtil pageUtil=new PageUtil(page,10,totalCount);
	    	if(page<1) {
	    		page=1;
	    	}else if(page>pageUtil.getTotalPageCount()) {
	      		if(totalCount==0) {
	    			page=pageUtil.getTotalPageCount()+1;
	    		}else {
	    			page=pageUtil.getTotalPageCount();
	    		}
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil.setPage(pages);
	    	
	    	
			List<Integer> List = editBillService.getPThroughSourceId(startDate,endDate,company,firmType,pageUtil.getPage(),pageUtil.getPageSize());
			pageUtil=new PageUtil(page,10,totalCount);
			for (Integer sourceId : List) {
				if(sourceId==null) {
					continue;
				}
				String sourceName = intMerchantService.getSourceName(sourceId, company);
				Integer topUpAmount = amountService.getAmountbyfirm(startDate,endDate,company,sourceName);
				if(topUpAmount==null) {
					topUpAmount=0;
				}
				List2 = editBillService.getPThrough(sourceId,startDate,endDate,company);
                for (EditBill list2 : List2) {
    				if(list2==null) {
    					continue;
    				}
                	list2.setRemainingAmount(topUpAmount-list2.getRealpay());
					list2.setRealpay(list2.getRealpay()*-1);
					list2.setTopUpAmount(topUpAmount);
				}
			}
			map.put("list2",List2);
			map.put("pageUtil",pageUtil);
		}
		
		if(firmType.equals("2")) {
			int totalCount=editBillService.pageCountByPThrough2(startDate,endDate,company,firmType);
	    	PageUtil pageUtil=new PageUtil(page,10,totalCount);
	    	if(page<1) {
	    		page=1;
	    	}else if(page>pageUtil.getTotalPageCount()) {
	      		if(totalCount==0) {
	    			page=pageUtil.getTotalPageCount()+1;
	    		}else {
	    			page=pageUtil.getTotalPageCount();
	    		}
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil.setPage(pages);
	    	
			List<Integer> List = editBillService.getPThroughBusinessesId(startDate,endDate,company,firmType);
			pageUtil=new PageUtil(page,10,totalCount);
			for (Integer businessesId : List) {
				if(businessesId==null) {
					continue;
				}
				String businessesName = intRegisteService.getBusinessesName(businessesId,company);
				Integer topUpAmount = amountService.getAmountbyfirm(startDate,endDate,company,businessesName);
				if(topUpAmount==null) {
					topUpAmount=0;
				}
				List2 = editBillService.getPThrough1(businessesId,startDate,endDate,company);
                for (EditBill list2 : List2) {
    				if(list2==null) {
    					continue;
    				}
                	list2.setRemainingAmount(topUpAmount-list2.getRealpay());
					list2.setRealpay(list2.getRealpay()*-1);
					list2.setTopUpAmount(topUpAmount);
				}
			}
			map.put("list2",List2);
			map.put("pageUtil",pageUtil);
		}
		
		
		return map;
	}
}

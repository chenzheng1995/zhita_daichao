package com.zhita.controller.daichao;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.service.commodityfootprint.CommodityFootprintService;
import com.zhita.service.registe.IntRegisteService;

@Controller
@RequestMapping("/browsing")
public class BrowsingHistoryController {
	
	@Autowired
	CommodityFootprintService cFootprintService;
	
	@Autowired
	IntRegisteService intRegisteService;
	
	//查询用户的浏览记录
	@RequestMapping("/getbrowsing")
	@ResponseBody

	public List<Map<String, Object>> insertFootprint(String userId,int curPage) { //userId是用户的id，curPage是页码
		 int pageSize = 10; //每页的条数，暂时写死，后续可以让前端传
		 int startRow = (curPage - 1) * pageSize; //起始条数
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list1=new ArrayList<Map<String, Object>>();
		List<String> list =	(List<String>) cFootprintService.getbusinessName(userId,pageSize,startRow);//获取商品名称
	    for (String businessName : list) {	
	    	long applications = cFootprintService.getApplications(businessName); //获取申请人数	    	
	    	map = intRegisteService.getLoansBusinesses(businessName); //获取商品的所有信息
	    	map.put("applications", applications);
	    	list1.add(map);
		}	    
	    return list1;
		
	}
}

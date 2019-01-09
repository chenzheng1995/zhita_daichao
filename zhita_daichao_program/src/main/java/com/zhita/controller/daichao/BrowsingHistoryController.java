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

	public Map<String,Object> insertFootprint(String userId,int curPage) { //userId是用户的id，curPage是页码
		 Map<String,Object> map1 = new HashMap<String,Object>();
		 int pageSize = 10; //每页的条数，暂时写死，后续可以让前端传
		 if(curPage<1){
		   curPage = 1;
		 }
		 long recordNumber = cFootprintService.getRecordNumber(userId);//根据用户id，查询出他有多少条足迹
		 int largestPages = (int) (recordNumber/10)+1;//最大页数
		 if(largestPages<curPage) {
			 curPage = largestPages;
		 }
		 int startRow = (curPage - 1) * pageSize; //起始条数
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> goodslist=new ArrayList<Map<String, Object>>();
		List<String> list =	(List<String>) cFootprintService.getbusinessName(userId,pageSize,startRow);//获取商品名称
	    for (String businessName : list) {	
	    	long applications = cFootprintService.getApplications(businessName); //获取申请人数	    	
	    	map = intRegisteService.getLoansBusinesses(businessName); //获取商品的所有信息
	    	if(map==null) {
            continue;
	    	}
	    	map.put("applications", applications);
	    	goodslist.add(map);
		}	
	    map1.put("goodslist", goodslist);
	    map1.put("largestPages", largestPages);
	    return map1;
		
	}
}

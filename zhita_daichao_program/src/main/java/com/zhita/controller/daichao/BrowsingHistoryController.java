package com.zhita.controller.daichao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.service.commodityfootprint.CommodityFootprintService;
import com.zhita.service.commodityfootprintcopy.CommodityFootprintCopyService;
import com.zhita.service.registe.IntRegisteCopyService;
import com.zhita.service.registe.IntRegisteService;

@Controller
@RequestMapping("/browsing")
public class BrowsingHistoryController {
	
	@Autowired
	CommodityFootprintService cFootprintService;
	
	@Autowired
	IntRegisteService intRegisteService;
	
	@Autowired
	CommodityFootprintCopyService commodityFootprintCopyService;
	
	@Autowired
	IntRegisteCopyService intRegisteCopyService;
	
//	//查询用户的浏览记录
//	@RequestMapping("/getbrowsing")
//	@ResponseBody
//
//	public Map<String,Object> insertFootprint(String userId,int curPage,String company) { //userId是用户的id，curPage是页码
//		 Map<String,Object> map1 = new HashMap<String,Object>();
//		if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(curPage) || StringUtils.isEmpty(company)) {
//			map1.put("msg", "userId,curPage或company不能为空");
//			map1.put("SCode", "401");
//			return map1;
//			}else {			
//		 int pageSize = 10; //每页的条数，暂时写死，后续可以让前端传
//		 if(curPage<1){
//		   curPage = 1;
//		 }
//		 long recordNumber = cFootprintService.getRecordNumber(userId,company);//根据用户id和公司名称，查询出他有多少条唯一商品名称的足迹
//		 int largestPages = (int) (recordNumber/10)+1;//最大页数
//		 if(largestPages<curPage) {
//			 curPage = largestPages;
//		 }
//		 int startRow = (curPage - 1) * pageSize; //起始条数
//		Map<String, Object> map = new HashMap<String, Object>();
//		List<Map<String, Object>> goodslist=new ArrayList<Map<String, Object>>();
//		List<String> list =	(List<String>) cFootprintService.getbusinessName(userId,pageSize,startRow,company);//获取商品名称
//	    for (String businessName : list) {	  	
//	map = intRegisteService.getLoansBusinesses(businessName,company); //获取商品的所有信息
// 	if(map!=null) {
//         fakeApplications = (int) map.get("applications"); //假的申请人数
//	}
//	long applications = cFootprintService.getApplications(businessName,company)+fakeApplications; //获取申请人数	 
//	if(map==null) {
//    continue;
//	}
//	map.put("applications", applications);
//	goodslist.add(map);
//}	
//    List<String> fixedWordList =new ArrayList<String>();
//    fixedWordList.add("%日");
//    fixedWordList.add("可贷额度");
//    fixedWordList.add("参考利率");
//    fixedWordList.add("人已申请");
//    fixedWordList.add("立即申请");
//	    map1.put("goodslist", goodslist);
//	    map1.put("largestPages", largestPages);
//	map1.put("fixedWordList", fixedWordList);
//	    return map1;
//			}
//	}
	
	//查询用户的浏览记录(为了审核)
	@RequestMapping("/getbrowsing")
	@ResponseBody

	public Map<String,Object> insertFootprint(String userId,int curPage,String company) { //userId是用户的id，curPage是页码
		 Map<String,Object> map1 = new HashMap<String,Object>();
		 int fakeApplications = 0;
		if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(curPage) || StringUtils.isEmpty(company)) {
			map1.put("msg", "userId,curPage或company不能为空");
			map1.put("SCode", "401");
			return map1;
			}else {			
		 int pageSize = 10; //每页的条数，暂时写死，后续可以让前端传
		 if(curPage<1){
		   curPage = 1;
		 }
		 long recordNumber = commodityFootprintCopyService.getRecordNumber1(userId,company);//根据用户id和公司名称，查询出他有多少条唯一商品名称的足迹
		 int largestPages = (int) (recordNumber/10)+1;//最大页数
		 if(largestPages<curPage) {
			 curPage = largestPages;
		 }
		 int startRow = (curPage - 1) * pageSize; //起始条数
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> goodslist=new ArrayList<Map<String, Object>>();
		List<String> list =	(List<String>) commodityFootprintCopyService.getbusinessName1(userId,pageSize,startRow,company);//获取商品名称
		for (String businessName : list) {	  	
	    	map = intRegisteService.getLoansBusinesses(businessName,company); //获取商品的所有信息
 	    	if(map!=null) {
		         fakeApplications = (int) map.get("applications"); //假的申请人数
	    	}
	    	long applications = cFootprintService.getApplications(businessName,company)+fakeApplications; //获取申请人数	 
	    	if(map==null) {
            continue;
	    	}
	    	map.put("applications", applications);
	    	goodslist.add(map);
		}	
	    
	     List<String> fixedWordList =new ArrayList<String>();
	     fixedWordList.add("%次");
	     fixedWordList.add("人均消费");
	     fixedWordList.add("折扣率");
	     fixedWordList.add("人已领取");
	     fixedWordList.add("立即领取");
	    map1.put("goodslist", goodslist);
	    map1.put("largestPages", largestPages);
	    map1.put("fixedWordList", fixedWordList);
	    return map1;
			}
	}
}

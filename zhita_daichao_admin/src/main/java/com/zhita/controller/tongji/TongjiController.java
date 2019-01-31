package com.zhita.controller.tongji;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.Source;
import com.zhita.model.manage.Statistical;
import com.zhita.service.commodityfootprint.CommodityFootprintService;
import com.zhita.service.merchant.IntMerchantService;
import com.zhita.service.registe.IntRegisteService;
import com.zhita.service.tongji.IntTongjiService;
import com.zhita.util.PageUtil;

@Controller
@RequestMapping("/tongji")
public class TongjiController {
	@Autowired
	private IntTongjiService intTongjiService;
	@Autowired
	private IntRegisteService intRegisteService;
	@Autowired
	private CommodityFootprintService commodityFootprintService;
	@Autowired
	private IntMerchantService IntMerchantService;
	
	//后台管理---查询统计表所有信息，含分页
    @ResponseBody
    @RequestMapping("/queryAllPage")
    public Map<String,Object> queryAllPage(Integer page,String company){
    	List<Source> listSource=IntMerchantService.queryAll(company);//查询出所有的渠道信息
    	List<String> list1=intTongjiService.queryAllLoansName();//查询出所有贷款商家的商家名称，存入lis1t集合
    	for (int i = 0; i < list1.size(); i++) {
    		//intRegisteService.upaApplicationNumber(commodityFootprintService.queryCount(list1.get(i)),list1.get(i));//将商家的被申请人数字段进行修改
		}
    	int totalCount=intTongjiService.pageCount();//该方法是查询贷款商家总条数
    	PageUtil pageUtil=new PageUtil(page,2,totalCount);
    	if(page<1) {
    		page=1;
    	}
    	else if(page>pageUtil.getTotalPageCount()) {
    		if(totalCount==0) {
    			page=pageUtil.getTotalPageCount()+1;
    		}else {
    			page=pageUtil.getTotalPageCount();
    		}
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil.setPage(pages);
    	
    	List<Statistical> list=intTongjiService.queryAllPage(pageUtil.getPage(),pageUtil.getPageSize());
    	for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getBusinessName()+"***"+list.get(i).getApplicationNumber());
		}
    	
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listSource", listSource);
    	map.put("listStatistical",list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
	//后台管理---通过渠道名称   查询统计表所有信息，含分页
    @ResponseBody
    @RequestMapping("/queryAllPageBySourceName")
    public Map<String,Object> queryAllPageBySourceName(String sourceName,Integer page,String company){
    	List<String> list1=null;
    	List<Statistical> list=null;
    	PageUtil pageUtil=null;
    	HashMap<String,Object> map=null;
    	if(sourceName==null||"".equals(sourceName)) {
    	   	List<Source> listSource=IntMerchantService.queryAll(company);
        	list1=intTongjiService.queryAllLoansName();//查询出所有贷款商家的商家名称，存入lis1t集合
        	for (int i = 0; i < list1.size(); i++) {
        		//intRegisteService.upaApplicationNumber(commodityFootprintService.queryCount(list1.get(i)),list1.get(i));//将商家的被申请人数字段进行修改
    		}
        	int totalCount=intTongjiService.pageCount();//该方法是查询贷款商家总条数
        	pageUtil=new PageUtil(page,2,totalCount);
        	if(page<1) {
        		page=1;
        	}
        	else if(page>pageUtil.getTotalPageCount()) {
        		if(totalCount==0) {
        			page=pageUtil.getTotalPageCount()+1;
        		}else {
        			page=pageUtil.getTotalPageCount();
        		}
        	}
        	int pages=(page-1)*pageUtil.getPageSize();
        	pageUtil.setPage(pages);
        	
            list=intTongjiService.queryAllPage(pageUtil.getPage(),pageUtil.getPageSize());
        	for (int i = 0; i < list.size(); i++) {
    			System.out.println(list.get(i).getBusinessName()+"***"+list.get(i).getApplicationNumber());
    		}
        	
        	map=new HashMap<>();
        	map.put("listSource", listSource);
        	map.put("listStatistical",list);
        	map.put("pageutil", pageUtil);
    	}
    	else {
    		list1=intTongjiService.quereyAllLoansNameBySourceName(sourceName);//通过渠道名称   查询出所有贷款商家的商家名称，存入lis1t集合
        	for (int i = 0; i < list1.size(); i++) {
        		//intRegisteService.upaApplicationNumber(commodityFootprintService.queryCount(list1.get(i)),list1.get(i));//将商家的被申请人数字段进行修改
    		}
        	int totalCount=intTongjiService.pageCountBySourceName(sourceName);
        	pageUtil=new PageUtil(page,2,totalCount);
        	if(page<1) {
        		page=1;
        	}
        	else if(page>pageUtil.getTotalPageCount()) {
        		if(totalCount==0) {
        			page=pageUtil.getTotalPageCount()+1;
        		}else {
        			page=pageUtil.getTotalPageCount();
        		}
        	}
        	int pages=(page-1)*pageUtil.getPageSize();
        	pageUtil.setPage(pages);
        	
        	list=intTongjiService.queryAllPageBySourceName(sourceName, pageUtil.getPage(), pageUtil.getPageSize());
        	for (int i = 0; i < list.size(); i++) {
    			System.out.println(list.get(i).getBusinessName()+"***"+list.get(i).getApplicationNumber());
    		}
          	map=new HashMap<>();
        	map.put("listStatistical",list);
        	map.put("pageutil", pageUtil);
    	}
    		return map;
    }
    	
}

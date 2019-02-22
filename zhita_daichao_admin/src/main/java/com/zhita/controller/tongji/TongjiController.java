package com.zhita.controller.tongji;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.Source;
import com.zhita.model.manage.SourceTongji;
import com.zhita.service.commodityfootprint.CommodityFootprintService;
import com.zhita.service.merchant.IntMerchantService;
import com.zhita.service.registe.IntRegisteService;
import com.zhita.service.tongji.IntTongjiService;
import com.zhita.util.ListPageUtil;
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
	private IntMerchantService intMerchantService;
	
	//后台管理----渠道方看的渠道统计，含分页
	
	//后台管理---关联查询统计所有信息，含分页    
    @ResponseBody
    @RequestMapping("/queryAllPage")
    public Map<String,Object> queryAllPage(Integer page,String[] company){
    	PageUtil pageUtil=null;
    	List<Source> listsource=new ArrayList<>();
    	List<SourceTongji> listsourceTongji=new ArrayList<>();//刚开始查询出来的数据
    	List<SourceTongji> listsourceTongjifor=new ArrayList<>();//经过封装后的数据
    	List<SourceTongji> listsourceTongjitoListPage=new ArrayList<>();//最终的数据
    	if(company.length==1) {
    		
    		System.out.println("company.length==1");
    		
    		listsource=intMerchantService.queryAll(company[0]);//查询出所有的渠道信息
    		int totalCount=intTongjiService.pageCount(company[0]);//该方法是查询统计总条数
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
    		
	    	listsourceTongji=intTongjiService.queryAllPage(company[0],pageUtil.getPage(),pageUtil.getPageSize());
    		for (int i = 0; i < listsourceTongji.size(); i++) {
				int pv=intTongjiService.queryPV(company[0], listsourceTongji.get(i).getSourceName());
				int uv=intTongjiService.queryUV(company[0], listsourceTongji.get(i).getSourceName());
				int appnum=intTongjiService.queryApplicationNumber(company[0], listsourceTongji.get(i).getSourceName());
				SourceTongji sourceTongji=new SourceTongji();
				sourceTongji.setId(listsourceTongji.get(i).getId());
				sourceTongji.setDate(listsourceTongji.get(i).getDate());
				sourceTongji.setBusinessName(listsourceTongji.get(i).getBusinessName());
				sourceTongji.setSourceName(listsourceTongji.get(i).getSourceName());
				sourceTongji.setPv(pv);
				sourceTongji.setUv(uv);
				sourceTongji.setApplicationNumber(appnum);
				sourceTongji.setCvr(appnum/uv+"%");
				listsourceTongjitoListPage.add(sourceTongji);
			}
    		pageUtil=new PageUtil(page,2,totalCount);
    	}
    	else if(company.length>1) {
    		
    		System.out.println("company.length>1");
    		
    		List<Source> listsourcefor=null;
    		for (int i = 0; i < company.length; i++) {
    			listsourcefor=intMerchantService.queryAll(company[i]);//查询出所有的渠道信息 
    			listsource.addAll(listsourcefor);
    			
    			listsourceTongji=intTongjiService.queryAllPage1(company[i]);
    			for (int j = 0; j < listsourceTongji.size(); j++) {
    				int pv=intTongjiService.queryPV(company[i], listsourceTongji.get(j).getSourceName());
    				int uv=intTongjiService.queryUV(company[i], listsourceTongji.get(j).getSourceName());
    				int appnum=intTongjiService.queryApplicationNumber(company[i], listsourceTongji.get(j).getSourceName());
    				SourceTongji sourceTongji=new SourceTongji();
    				sourceTongji.setId(listsourceTongji.get(j).getId());
    				sourceTongji.setDate(listsourceTongji.get(j).getDate());
    				sourceTongji.setBusinessName(listsourceTongji.get(j).getBusinessName());
    				sourceTongji.setSourceName(listsourceTongji.get(j).getSourceName());
    				sourceTongji.setPv(pv);
    				sourceTongji.setUv(uv);
    				sourceTongji.setApplicationNumber(appnum);
    				sourceTongji.setCvr(appnum/uv+"%");
    				listsourceTongjifor.add(sourceTongji);
				}
			}
    		
			for (int i = 0; i < listsourceTongjifor.size(); i++) {
				System.out.println(listsourceTongjifor.get(i)+"整合后的集合");
			}
			
			System.out.println("传进工具类的page"+page);
			
			ListPageUtil listPageUtil=new ListPageUtil(listsourceTongjifor,page,2);
			listsourceTongjitoListPage.addAll(listPageUtil.getData());
			
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
    	}
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listsource", listsource);
    	map.put("listsourceTongjitoListPage",listsourceTongjitoListPage);
    	map.put("pageutil", pageUtil);
    	map.put("company", company);
    	return map;
    }
	//后台管理---通过渠道名称   查询统计表所有信息，含分页
    @ResponseBody
    @RequestMapping("/queryAllPageBySourceName")
    public Map<String,Object> queryAllPageBySourceName(Integer page,String[] company,String[] sourceName){
    	PageUtil pageUtil=null;
    	List<SourceTongji> listSourceTongjione=new ArrayList<>();
    	List<SourceTongji> listSourceTongjitwo=new ArrayList<>();
    	List<SourceTongji> listone=new ArrayList<>();
    	List<SourceTongji> listtwo=new ArrayList<>();//对集合进行处理后的集合   最终集合
       //	List<SourceTongji> listoneto=new ArrayList<>();
    	//公司名选择的是全部  渠道选择的是全部 
    	if((company.length>1)&&(sourceName.length>1)) {
    		
    		System.out.println("第一个if："+"company.length>1-----sourceName.length>1");
    		
    		for (int i = 0; i < company.length; i++) {
    			for (int j = 0; j < sourceName.length; j++) {
    				listone=intTongjiService.queryAllPageBySourceName1(company[i],sourceName[j]);
    				for (int k = 0; k < listone.size(); k++) {
        				int pv=intTongjiService.queryPV(company[i], listone.get(k).getSourceName());
        				int uv=intTongjiService.queryUV(company[i], listone.get(k).getSourceName());
        				int appnum=intTongjiService.queryApplicationNumber(company[i], listone.get(k).getSourceName());
        				SourceTongji sourceTongji=new SourceTongji();
        				sourceTongji.setId(listone.get(k).getId());
        				sourceTongji.setDate(listone.get(k).getDate());
        				sourceTongji.setBusinessName(listone.get(k).getBusinessName());
        				sourceTongji.setSourceName(listone.get(k).getSourceName());
        				sourceTongji.setPv(pv);
        				sourceTongji.setUv(uv);
        				sourceTongji.setApplicationNumber(appnum);
        				sourceTongji.setCvr(appnum/uv+"%");
        				listSourceTongjione.add(sourceTongji);
					}
				}
    			listSourceTongjitwo.addAll(listSourceTongjione);
    		}	
    		
			for (int i = 0; i < listSourceTongjitwo.size(); i++) {
				System.out.println(listSourceTongjitwo.get(i)+"整合后的集合");
			}
			
			System.out.println("传进工具类的page"+page);
			
			ListPageUtil listPageUtil=new ListPageUtil(listSourceTongjitwo,page,2);
			listtwo.addAll(listPageUtil.getData());
			
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
    		
    	}
    	//公司名选择的是全部   渠道选择的不是全部  
    	else if((company.length>1)&&(sourceName.length==1)) {
    	
    		System.out.println("第二个if："+"company.length>1-----sourceName.length==1");
    		
    		for (int i = 0; i < company.length; i++) {
				listone=intTongjiService.queryAllPageBySourceName1(company[i],sourceName[0]);
				
    			for (int j = 0; j < listone.size(); j++) {
    				int pv=intTongjiService.queryPV(company[i], listone.get(j).getSourceName());
    				int uv=intTongjiService.queryUV(company[i], listone.get(j).getSourceName());
    				int appnum=intTongjiService.queryApplicationNumber(company[i], listone.get(j).getSourceName());
    				SourceTongji sourceTongji=new SourceTongji();
    				sourceTongji.setId(listone.get(j).getId());
    				sourceTongji.setDate(listone.get(j).getDate());
    				sourceTongji.setBusinessName(listone.get(j).getBusinessName());
    				sourceTongji.setSourceName(listone.get(j).getSourceName());
    				sourceTongji.setPv(pv);
    				sourceTongji.setUv(uv);
    				sourceTongji.setApplicationNumber(appnum);
    				sourceTongji.setCvr(appnum/uv+"%");
    				listSourceTongjione.add(sourceTongji);
				}
			}
    		
			for (int i = 0; i < listSourceTongjione.size(); i++) {
				System.out.println(listSourceTongjione.get(i)+"整合后的集合");
			}
			
			System.out.println("传进工具类的page"+page);
			
			ListPageUtil listPageUtil=new ListPageUtil(listSourceTongjione,page,2);
			listtwo.addAll(listPageUtil.getData());
			
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
    		
    	}
    	//公司名选择的不是全部   渠道选择的是全部  
    	else if((company.length==1)&&(sourceName.length>1)) {
    		
    		System.out.println("第三个if："+"company.length==1-----sourceName.length>1");
    		
    		for (int i = 0; i < sourceName.length; i++) {
				listone=intTongjiService.queryAllPageBySourceName1(company[0],sourceName[i]);
				
    			for (int j = 0; j < listone.size(); j++) {
    				int pv=intTongjiService.queryPV(company[0], listone.get(j).getSourceName());
    				int uv=intTongjiService.queryUV(company[0], listone.get(j).getSourceName());
    				int appnum=intTongjiService.queryApplicationNumber(company[0], listone.get(j).getSourceName());
    				SourceTongji sourceTongji=new SourceTongji();
    				sourceTongji.setId(listone.get(j).getId());
    				sourceTongji.setDate(listone.get(j).getDate());
    				sourceTongji.setBusinessName(listone.get(j).getBusinessName());
    				sourceTongji.setSourceName(listone.get(j).getSourceName());
    				sourceTongji.setPv(pv);
    				sourceTongji.setUv(uv);
    				sourceTongji.setApplicationNumber(appnum);
    				sourceTongji.setCvr(appnum/uv+"%");
    				listSourceTongjione.add(sourceTongji);
				}
			}
    		
			for (int i = 0; i < listSourceTongjione.size(); i++) {
				System.out.println(listSourceTongjione.get(i)+"整合后的集合");
			}
			
			System.out.println("传进工具类的page"+page);
			
			ListPageUtil listPageUtil=new ListPageUtil(listSourceTongjione,page,2);
			listtwo.addAll(listPageUtil.getData());
			
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
    		
    	}
    	//公司名选择的不是全部   渠道选择的不是全部  
    	else if((company.length==1)&&(sourceName.length==1)) {
    		
    		System.out.println("第四个if："+"company.length==1-----sourceName.length==1");
    		
    		int totalCount=intTongjiService.pageCountBySourceName(company[0],sourceName[0]);//该方法是查询统计总条数
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
    		
	    	listone=intTongjiService.queryAllPageBySourceName(company[0],sourceName[0],pageUtil.getPage(),pageUtil.getPageSize());
    		for (int i = 0; i < listone.size(); i++) {
				int pv=intTongjiService.queryPV(company[0], listone.get(i).getSourceName());
				int uv=intTongjiService.queryUV(company[0], listone.get(i).getSourceName());
				int appnum=intTongjiService.queryApplicationNumber(company[0], listone.get(i).getSourceName());
				SourceTongji sourceTongji=new SourceTongji();
				sourceTongji.setId(listone.get(i).getId());
				sourceTongji.setDate(listone.get(i).getDate());
				sourceTongji.setBusinessName(listone.get(i).getBusinessName());
				sourceTongji.setSourceName(listone.get(i).getSourceName());
				sourceTongji.setPv(pv);
				sourceTongji.setUv(uv);
				sourceTongji.setApplicationNumber(appnum);
				sourceTongji.setCvr(appnum/uv+"%");
				listtwo.add(sourceTongji);
			}
    		pageUtil=new PageUtil(page,2,totalCount);
    	}
    	
    	HashMap<String, Object> map=new HashMap<>();
        map.put("listSourceTongjitwo",listtwo);
        map.put("pageutil", pageUtil);
    	return map;
    }
}

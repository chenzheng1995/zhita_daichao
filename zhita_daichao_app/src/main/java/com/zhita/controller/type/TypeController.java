package com.zhita.controller.type;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.LoanClassification;
import com.zhita.model.manage.LoanClassificationCopy;
import com.zhita.model.manage.LoansBusinesses;
import com.zhita.model.manage.LoansBusinessesCopy;
import com.zhita.service.commodityfootprint.CommodityFootprintService;
import com.zhita.service.sourcedadson.SourceDadSonService;
import com.zhita.service.type.IntTypeService;
import com.zhita.util.PageUtil;

@Controller
@RequestMapping(value="/type")
public class TypeController {
	@Resource(name="typeServiceImp")
	private IntTypeService intTypeService;

	public IntTypeService getIntTypeService() {
		return intTypeService;
	}

	public void setIntTypeService(IntTypeService intTypeService) {
		this.intTypeService = intTypeService;
	}
	
	@Autowired
	CommodityFootprintService cFootprintService;
	
	@Autowired
	SourceDadSonService sourceDadSonService;
	
	//小程序，获取前4个贷款分类	
    @ResponseBody
    @RequestMapping("/queryLoanClassBefore")
    @Transactional
    public List<LoanClassification> queryLoanClass1(String company,String oneSourceName,String twoSourceName){
    	List<LoanClassification> list = intTypeService.queryLoanClass1(company);
		return list;
   	
    }

	//小程序，获取后3个贷款分类	
    @ResponseBody
    @RequestMapping("/queryLoanClassAfter")
    @Transactional
    public List<LoanClassification> queryLoanClassAfter1(String company,String oneSourceName,String twoSourceName){
    	List<LoanClassification> list = intTypeService.queryLoanClassAfter1(company);
		return list;
   	
    }
	
	//小程序---通过贷款分类的名称，查询出当前贷款分类下的所有贷款商家的信息
    @ResponseBody
    @RequestMapping("/queryLoanbusinByLoanClass")
    public Map<String,Object> queryLoanbusinByLoanClass(String businessClassification,Integer page,String company,String oneSourceName,String twoSourceName){
    	HashMap<String,Object> map=new HashMap<>();
    	String  tableType = sourceDadSonService.getTableType(oneSourceName,twoSourceName,company);	
    	if(tableType.equals("1")) {
    		int totalCount=intTypeService.pageCountByBusinessClassification(businessClassification,company);
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
        	List<LoansBusinesses> list=intTypeService.queryLoanbusinByLoanClass(businessClassification,pages,pageUtil.getPageSize(),company);
        	pageUtil=new PageUtil(page,10,totalCount);
        	 for (LoansBusinesses loansBusinesses : list) {
        	        String businessName = loansBusinesses.getBusinessname();
        	        int fakeApplications = loansBusinesses.getApplications(); //假的申请人数
        	        int applications = (int)cFootprintService.getApplications(businessName,company)+fakeApplications;//获取申请人数	  
        	        loansBusinesses.setApplications(applications);
        	        String loanlimitbig = loansBusinesses.getLoanlimitbig().setScale(0)+"";
        	        String loanlimitsmall = loansBusinesses.getLoanlimitsmall().setScale(0)+"";
        	        String loanlimit = loanlimitsmall+"~"+loanlimitbig;
        	        loansBusinesses.setLoanlimit(loanlimit);
        			}
        	map.put("listLoansBusinByLike",list);
        	map.put("pageutil", pageUtil);
    	}
    	
    	if(tableType.equals("2")) {
    		int totalCount=intTypeService.pageCountByBusinessClassificationAppCopy(businessClassification,company,oneSourceName,twoSourceName);
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
        	List<LoansBusinessesCopy> list=intTypeService.queryLoanbusinByLoanClassAppCopy(businessClassification,pages,pageUtil.getPageSize(),company,oneSourceName,twoSourceName);
        	pageUtil=new PageUtil(page,10,totalCount);
        	 for (LoansBusinessesCopy loansBusinesses : list) {
        	        String businessName = loansBusinesses.getBusinessname();
        	        int fakeApplications = loansBusinesses.getApplications(); //假的申请人数
        	        int applications = (int)cFootprintService.getApplications(businessName,company)+fakeApplications;//获取申请人数	  
        	        loansBusinesses.setApplications(applications);
        	        String loanlimitbig = loansBusinesses.getLoanlimitbig().setScale(0)+"";
        	        String loanlimitsmall = loansBusinesses.getLoanlimitsmall().setScale(0)+"";
        	        String loanlimit = loanlimitsmall+"~"+loanlimitbig;
        	        loansBusinesses.setLoanlimit(loanlimit);
        			}
        	map.put("listLoansBusinByLike",list);
        	map.put("pageutil", pageUtil);
    	}
    	
    	return map;
    }   

}

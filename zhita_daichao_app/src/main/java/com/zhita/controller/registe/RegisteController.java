package com.zhita.controller.registe;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.LoansBusinesses;
import com.zhita.service.commodityfootprint.CommodityFootprintService;
import com.zhita.service.registe.IntRegisteCopyService;
import com.zhita.service.registe.IntRegisteService;
import com.zhita.service.sourcedadson.SourceDadSonService;
import com.zhita.util.PageUtil;


@Controller
@RequestMapping(value="/registe")
public class RegisteController {
	
	@Autowired
	IntRegisteService intRegisteService;
	
	@Autowired
	CommodityFootprintService cFootprintService;
	
	@Autowired
	SourceDadSonService sourceDadSonService;
	
	@Autowired
	IntRegisteCopyService intRegisteCopyService;
	
	
	
	//小程序---查询所有贷款商家信息,含分页
    @ResponseBody
    @RequestMapping("/queryAll")
    @Transactional
    public Map<String,Object> queryAll(Integer page,String company,String oneSourceName,String twoSourceName){        	
    	HashMap<String,Object> map=new HashMap<>();
    	String  tableType = sourceDadSonService.getTableType(oneSourceName,twoSourceName,company);
    	PageUtil pageUtil=null;
    	int totalCount = 0;
    	int pages = 0;
    	List<LoansBusinesses> list = null;
    	if(tableType.equals("1")) {        	
        	totalCount=intRegisteService.pageCount2(company);//该方法是查询贷款商家总条数
        	pageUtil=new PageUtil(page,10,totalCount);
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
        	pages=(page-1)*pageUtil.getPageSize();
        	pageUtil.setPage(pages); 	
        	list=intRegisteService.queryAllAdmainpro(pageUtil.getPage(),pageUtil.getPageSize(),company);
        	pageUtil=new PageUtil(page,10,totalCount);
        	if(list!=null) {
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
        	}
        	map.put("listLoansBusin",list);
        	map.put("pageutil", pageUtil);	
    	}
            
        	if(tableType.equals("2")) {
            	totalCount=intRegisteCopyService.pageCountAppCopy(company,oneSourceName,twoSourceName);//该方法是查询贷款商家总条数
            	pageUtil=new PageUtil(page,10,totalCount);
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
            	pages=(page-1)*pageUtil.getPageSize();
            	pageUtil.setPage(pages); 	
            	list=intRegisteCopyService.queryAllAdmainproAppCopy(pageUtil.getPage(),pageUtil.getPageSize(),company,oneSourceName,twoSourceName);
            	pageUtil=new PageUtil(page,10,totalCount);
            	if(list!=null) {
                    for (LoansBusinesses loansBusinesses : list) {
            	        int fakeApplications = loansBusinesses.getApplications(); //假的申请人数
            	        int applications = fakeApplications;
            	        loansBusinesses.setApplications(applications);
            	        String loanlimitbig = loansBusinesses.getLoanlimitbig().setScale(0)+"";
            	        String loanlimitsmall = loansBusinesses.getLoanlimitsmall().setScale(0)+"";
            	        String loanlimit = loanlimitsmall+"~"+loanlimitbig;
            	        loansBusinesses.setLoanlimit(loanlimit);          
            		}
            	}

        	map.put("listLoansBusin",list);
        	map.put("pageutil", pageUtil);	
    	}

    	return map;
    }
    
    @ResponseBody
    @RequestMapping("/dynamicData")
    @Transactional
    public HashMap<String,Object> queryAll(){
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("left", "可贷额度");
    	map.put("centerTop", "%");
    	map.put("centerBottom", "成功率");
    	map.put("button", "立即放款");
    	map.put("right", "人已放款");
		return map;    
    	
    }

    
}
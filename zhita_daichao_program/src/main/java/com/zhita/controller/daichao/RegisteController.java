package com.zhita.controller.daichao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.LoansBusinesses;
import com.zhita.model.manage.LoansBusinessesCopy;
import com.zhita.service.commodityfootprint.CommodityFootprintService;
import com.zhita.service.registe.IntRegisteCopyService;
import com.zhita.service.registe.IntRegisteService;
import com.zhita.util.PageUtil;


@Controller
@RequestMapping(value="/registe")
public class RegisteController {
	
	@Autowired
	IntRegisteService intRegisteService;
	
	@Autowired
	CommodityFootprintService cFootprintService;
	
	@Autowired
	IntRegisteCopyService intRegisteCopyService;
	
	//小程序---查询所有贷款商家信息,含分页
//    @ResponseBody
//    @RequestMapping("/queryAll")
//    public Map<String,Object> queryAll(Integer page,String company){    	
//
//    	int totalCount=intRegisteService.pageCount(company);//该方法是查询贷款商家总条数
//    	PageUtil pageUtil=new PageUtil(page,10,totalCount);
//    	if(page<1) {
//    		page=1;
//    	}
//    	else if(page>pageUtil.getTotalPageCount()) {
//    		if(totalCount==0) {
//    			page=pageUtil.getTotalPageCount()+1;
//    		}else {
//    			page=pageUtil.getTotalPageCount();
//    		}
//    	}
//    	int pages=(page-1)*pageUtil.getPageSize();
//    	pageUtil.setPage(pages); 	
//    	List<LoansBusinesses> list=intRegisteService.queryAllAdmainpro(pageUtil.getPage(),pageUtil.getPageSize(),company);
//        for (LoansBusinesses loansBusinesses : list) {
//	        String businessName = loansBusinesses.getBusinessname();
//	        int fakeApplications = loansBusinesses.getApplications(); //假的申请人数
//	        int applications = (int)cFootprintService.getApplications(businessName,company)+fakeApplications;//获取申请人数	  
//	        loansBusinesses.setApplications(applications);
//	        String loanlimitbig = loansBusinesses.getLoanlimitbig().setScale(0)+"";
//	        String loanlimitsmall = loansBusinesses.getLoanlimitsmall().setScale(0)+"";
//	        String loanlimit = loanlimitsmall+"~"+loanlimitbig;
//	        loansBusinesses.setLoanlimit(loanlimit);
//        
//		}	
//    List<String> fixedWordList =new ArrayList<String>();
//    fixedWordList.add("%日");
//    fixedWordList.add("可贷额度");
//    fixedWordList.add("参考利率");
//    fixedWordList.add("人已申请");
//	  fixedWordList.add("立即申请");
// 	
//    	HashMap<String,Object> map=new HashMap<>();
//    	map.put("listLoansBusin",list);
//    	map.put("pageutil", pageUtil);
//	    map.put("fixedWordList", fixedWordList);
//    	return map;
//    }
	//小程序---查询所有贷款商家信息,含分页(为了审核用的)
  @ResponseBody
  @RequestMapping("/queryAll")
  public Map<String,Object> queryAll(Integer page,String company){    	
  	int totalCount=intRegisteCopyService.pageCount1(company);//该方法是查询贷款商家总条数
  	PageUtil pageUtil=new PageUtil(page,10,totalCount);
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
  	List<LoansBusinessesCopy> list=intRegisteCopyService.queryAllAdmainpro1(pageUtil.getPage(),pageUtil.getPageSize(),company);
      for (LoansBusinessesCopy loansBusinesses : list) {
	        int fakeApplications = loansBusinesses.getApplications(); //假的申请人数
	        int applications =fakeApplications;//获取申请人数	  
	        loansBusinesses.setApplications(applications);
	        String loanlimitbig = loansBusinesses.getLoanlimitbig().setScale(0)+"";
	        String loanlimitsmall = loansBusinesses.getLoanlimitsmall().setScale(0)+"";
	        String loanlimit = loanlimitsmall+"~"+loanlimitbig;
	        loansBusinesses.setLoanlimit(loanlimit);
      
		}
     List<String> fixedWordList =new ArrayList<String>();
     fixedWordList.add("%次");
     fixedWordList.add("人均消费");
     fixedWordList.add("折扣率");
     fixedWordList.add("人已领取");
     fixedWordList.add("立即领取");
	
  	HashMap<String,Object> map=new HashMap<>();
  	map.put("listLoansBusin",list);
  	map.put("pageutil", pageUtil);
  	map.put("fixedWordList", fixedWordList);
  	return map;
  }
	
	
}
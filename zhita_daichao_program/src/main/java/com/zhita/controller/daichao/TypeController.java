package com.zhita.controller.daichao;


import java.util.ArrayList;
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
import com.zhita.service.loanclassificationcopy.LCFicationCopyService;
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
	LCFicationCopyService lCFicationCopyService;
	
	//小程序（为了审核），获取前4个贷款分类	
    @ResponseBody
    @RequestMapping("/queryLoanClassBefore")
    @Transactional
    public List<LoanClassificationCopy> queryLoanClass(String company){
    	List<LoanClassificationCopy> list = intTypeService.queryLoanClass(company);
		return list;
   	
    }

	//小程序（为了审核），获取后3个贷款分类	
    @ResponseBody
    @RequestMapping("/queryLoanClassAfter")
    @Transactional
    public List<LoanClassificationCopy> queryLoanClassAfter(String company){
    	List<LoanClassificationCopy> list = intTypeService.queryLoanClassAfter(company);
		return list;
   	
    }
	
	//小程序---通过贷款分类的名称，查询出当前贷款分类下的所有贷款商家的信息
//    @ResponseBody
//    @RequestMapping("/queryLoanbusinByLoanClass")
//    @Transactional
//    public Map<String,Object> queryLoanbusinByLoanClass(String businessClassification,Integer page,String company){
//    	int totalCount=intTypeService.pageCountByBusinessClassification(businessClassification,company);
//    	PageUtil pageUtil=new PageUtil(page,10,totalCount);
//    	if(page<1) {
//    		page=1;
//    	}else if(page>pageUtil.getTotalPageCount()) {
//      		if(totalCount==0) {
//    			page=pageUtil.getTotalPageCount()+1;
//    		}else {
//    			page=pageUtil.getTotalPageCount();
//    		}
//    	}
//    	int pages=(page-1)*pageUtil.getPageSize();
//    	List<LoansBusinesses> list=intTypeService.queryLoanbusinByLoanClass(businessClassification,pages,pageUtil.getPageSize(),company);
//    	 for (LoansBusinesses loansBusinesses : list) {
//    	        String businessName = loansBusinesses.getBusinessname();
//    	        int fakeApplications = loansBusinesses.getApplications(); //假的申请人数
//    	        int applications = (int)cFootprintService.getApplications(businessName,company)+fakeApplications;//获取申请人数	  
//    	        loansBusinesses.setApplications(applications);
//    	        String loanlimitbig = loansBusinesses.getLoanlimitbig().setScale(0)+"";
//    	        String loanlimitsmall = loansBusinesses.getLoanlimitsmall().setScale(0)+"";
//    	        String loanlimit = loanlimitsmall+"~"+loanlimitbig;
//    	        loansBusinesses.setLoanlimit(loanlimit);
//    			}   
//	 List<String> fixedWordList =new ArrayList<String>();
//     fixedWordList.add("%日");
//     fixedWordList.add("可贷额度");
//     fixedWordList.add("参考利率");
//     fixedWordList.add("人已申请");
//     fixedWordList.add("立即申请");
//    	HashMap<String,Object> map=new HashMap<>();
//    	map.put("listLoansBusinByLike",list);
//    	map.put("pageutil", pageUtil);
//      map.put("fixedWordList", fixedWordList);
//    	return map;
//    } 
    
  //小程序---通过贷款分类的名称，查询出当前贷款分类下的所有贷款商家的信息（为了审核）
    @ResponseBody
    @RequestMapping("/queryLoanbusinByLoanClass")
    @Transactional
    public Map<String,Object> queryLoanbusinByLoanClass(String businessClassification,Integer page,String company){
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
//    	List<LoansBusinesses> list=intTypeService.queryLoanbusinByLoanClass(businessClassification,pages,pageUtil.getPageSize(),company);
    	List<LoansBusinessesCopy> list=intTypeService.queryLoanbusinByLoanClass1(businessClassification,pages,pageUtil.getPageSize(),company);
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
    	map.put("listLoansBusinByLike",list);
    	map.put("pageutil", pageUtil);
    	map.put("fixedWordList", fixedWordList);
    	return map;
    }   

}

package com.zhita.controller.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.Advertising;
import com.zhita.model.manage.LoanClassification;
import com.zhita.service.type.IntTypeService;
import com.zhita.util.PageUtil;

@Controller
@RequestMapping("/type")
public class TypeController {
	@Resource(name="typeServiceImp")
	private IntTypeService intTypeService;

	public IntTypeService getIntTypeService() {
		return intTypeService;
	}

	public void setIntTypeService(IntTypeService intTypeService) {
		this.intTypeService = intTypeService;
	}
	//后台管理---查询贷款分类所有信息，含分页
    @ResponseBody
    @RequestMapping("/queryAllPage")
    public Map<String,Object> queryAllPage(Integer page,String[] company){
		PageUtil pageUtil=null;
		List<LoanClassification> list=new ArrayList<>();
		if(company.length==1) {
			
			System.out.println("company.length==1");
			
	    	int totalCount=intTypeService.pageCount(company[0]);//该方法是查询贷款分类总数量
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
	    	list=intTypeService.queryAllPage(company[0],pageUtil.getPage(),pageUtil.getPageSize());
		}
		else if(company.length>1) {
			
			System.out.println("company.length>1");
			
    		int totalCountfor=0;
    		List<LoanClassification> listfor=null;
			for (int i = 0; i < company.length; i++) {
		    	int totalCountfor1=intTypeService.pageCount(company[i]);//该方法是查询贷款分类总数量
            	totalCountfor=totalCountfor+totalCountfor1;
            	
            	System.out.println("totalCountfor："+totalCountfor);
		    	pageUtil=new PageUtil(page,2,totalCountfor);
		    	if(page<1) {
		    		page=1;
		    	}
		    	else if(page>pageUtil.getTotalPageCount()) {
		    		if(totalCountfor==0) {
		    			page=pageUtil.getTotalPageCount()+1;
		    		}else {
		    			page=pageUtil.getTotalPageCount();
		    		}
		    	}
		    	int pages=(page-1)*pageUtil.getPageSize();
		    	pageUtil.setPage(pages);
		    	listfor=intTypeService.queryAllPage(company[i],pageUtil.getPage(),pageUtil.getPageSize());
            	list.addAll(listfor);
			}
		}
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listLoanClass", list);
    	map.put("pageutil",pageUtil);
    	return map;
    }
    //后台管理---模糊查询贷款分类信息,并且有分页功能
    @ResponseBody
    @RequestMapping("/queryByLike")
    public Map<String,Object> queryByLike(HttpServletRequest request,String company,Integer page,String businessClassification){
    	List<LoanClassification> list=null;
    	PageUtil pageUtil=null;
    	if(businessClassification==null||"".equals(businessClassification)) {
        	int totalCount=intTypeService.pageCount(company);//该方法是查询贷款分类总数量
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
        	
        	list=intTypeService.queryAllPage(company,pageUtil.getPage(),pageUtil.getPageSize());
    	}else {
          	int totalCount=intTypeService.pageCountByLike(businessClassification,company);//该方法是模糊查询的贷款分类总数量
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
        	list=intTypeService.queryByLike(businessClassification,company,pageUtil.getPage(),pageUtil.getPageSize() );
    	}
    	HashMap<String, Object> map=new HashMap<>();
    	map.put("listLoanClaByLike", list);
    	map.put("pageutil",pageUtil);
		return map;
    }
	//后台管理---添加贷款分类信息
    @Transactional
    @ResponseBody
    @RequestMapping("/insertAllfind")
    public Integer insertAll(LoanClassification loanClassification){
    	Integer selnum=intTypeService.addAll(loanClassification);
    	return selnum;
    }
	//后台管理---通过主键id查询出贷款分类信息
    @ResponseBody
    @RequestMapping("/selectByPrimaryKey")
    public LoanClassification selectByPrimaryKey(Integer id){
    	LoanClassification loanClassification=intTypeService.selectByPrimaryKey(id);
    	return loanClassification;
    }
    //通过传过来的贷款分类对象，对当前对象进行修改保存
    @Transactional
    @ResponseBody
    @RequestMapping("/updateByPrimaryKey")
    public Integer updateByPrimaryKey(LoanClassification loanClassification){
    	Integer num=intTypeService.updateByPrimaryKey(loanClassification);
    	return num;
    }
}

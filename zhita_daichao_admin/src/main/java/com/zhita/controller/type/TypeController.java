package com.zhita.controller.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.LoanClassification;
import com.zhita.service.type.IntTypeService;
import com.zhita.util.ListPageUtil;
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

    public Map<String,Object> queryAllPage(Integer page,String string){
		string = string.replaceAll("\"", "").replace("[","").replace("]","");
		String [] company= string.split(",");
    	System.out.println("刚进来时候的page"+page);  	
		PageUtil pageUtil=null;
		List<LoanClassification> list=new ArrayList<>();
		List<LoanClassification> listto=new ArrayList<>();
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
	    	listto=intTypeService.queryAllPage(company[0],pageUtil.getPage(),pageUtil.getPageSize());
	    	pageUtil=new PageUtil(page,2,totalCount);
		}
		else if(company.length>1) {
			
			System.out.println("company.length>1");
			
    		List<LoanClassification> listfor=null;
			for (int i = 0; i < company.length; i++) {
		    	listfor=intTypeService.queryAllNoPage(company[i]);
            	list.addAll(listfor);
			}
			
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i)+"整合后的集合");
			}
			
			System.out.println("传进工具类的page"+page);
			
			ListPageUtil listPageUtil=new ListPageUtil(list,page,2);
			listto.addAll(listPageUtil.getData());
			
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
		}
		
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listLoanClass", listto);
    	map.put("pageutil",pageUtil);
    	map.put("company", company);
    	return map;
    }
    //后台管理---模糊查询贷款分类信息,并且有分页功能
    @ResponseBody
    @RequestMapping("/queryByLike")
    public Map<String,Object> queryByLike(String businessClassification,Integer page,String string){
		string = string.replaceAll("\"", "").replace("[","").replace("]","");
		String [] company= string.split(",");
    	PageUtil pageUtil=null;
    	List<LoanClassification> list=new ArrayList<>();
    	List<LoanClassification> listto=new ArrayList<>();
    	//类型为空并且公司名不为空    公司名选择的是 全部项
    	if((businessClassification==null||"".equals(businessClassification))&&(company.length>1)) {
    		
    		System.out.println("第一个if");
    		
    		List<LoanClassification> listfor=null;
			for (int i = 0; i < company.length; i++) {
		    	listfor=intTypeService.queryAllNoPage(company[i]);
            	list.addAll(listfor);
			}
			
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i)+"整合后的集合");
			}
			
			System.out.println("传进工具类的page"+page);
			
			ListPageUtil listPageUtil=new ListPageUtil(list,page,2);
			listto.addAll(listPageUtil.getData());
			
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
    	}
    	//类型为空并且公司名不为空    公司名选择的不是 全部项
    	else if((businessClassification==null||"".equals(businessClassification))&&(company.length==1)) {
    		
    		System.out.println("第二个if");
    		
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
	    	listto=intTypeService.queryAllPage(company[0],pageUtil.getPage(),pageUtil.getPageSize());
	    	pageUtil=new PageUtil(page,2,totalCount);
    	}
    	//类型不为空并且公司名不为空    公司名选择的是 全部项
    	else if((businessClassification!=null||!"".equals(businessClassification))&&(company.length>1)) {
    		
    		System.out.println("第三个if");
    		
    		List<LoanClassification> listfor=null;
			for (int i = 0; i < company.length; i++) {
		    	listfor=intTypeService.queryByLike1(businessClassification,company[i]);
            	list.addAll(listfor);
			}
			
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i)+"整合后的集合");
			}
			
			System.out.println("传进工具类的page"+page);
			
			ListPageUtil listPageUtil=new ListPageUtil(list,page,2);
			listto.addAll(listPageUtil.getData());
			
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
    	
    	}
    	//类型不为空并且公司名不为空  公司名选择的不是 全部项
    	else if((businessClassification!=null||!"".equals(businessClassification))&&(company.length==1)){
    		
    		System.out.println("第四个if");
    		
        	int totalCount=intTypeService.pageCountByLike(businessClassification,company[0]);//该方法是模糊查询的贷款分类总数量
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
	    	listto=intTypeService.queryByLike(businessClassification,company[0],pageUtil.getPage(),pageUtil.getPageSize());
	    	pageUtil=new PageUtil(page,2,totalCount);
    	}
    	HashMap<String, Object> map=new HashMap<>();
    	map.put("listLoanClaByLike", listto);
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

package com.zhita.controller.type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Map<String,Object> queryAllPage(HttpServletRequest request,Integer page){
    	int totalCount=intTypeService.pageCount();//该方法是查询贷款分类总数量
    	PageUtil pageUtil=new PageUtil(page, totalCount);
    	if(page<1) {
    		page=1;
    	}
    	else if(page>pageUtil.getTotalPageCount()) {
    		page=pageUtil.getTotalPageCount();
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil=new PageUtil(pages, totalCount);
    	
    	List<LoanClassification> list=intTypeService.queryAllPage(pages);
    	
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listLoanClass", list);
    	map.put("pageutil",pageUtil);
    	return map;
    }
    //后台管理---模糊查询贷款分类信息,并且有分页功能
    @ResponseBody
    @RequestMapping("/queryByLike")
    public Map<String,Object> queryByLike(HttpServletRequest request,Integer page,String businessClassification){
    	List<LoanClassification> list=null;
    	PageUtil pageUtil=null;
    	if(businessClassification==null||"".equals(businessClassification)) {
        	int totalCount=intTypeService.pageCount();//该方法是查询贷款分类总数量
        	pageUtil=new PageUtil(page, totalCount);
        	if(page<1) {
        		page=1;
        	}
        	else if(page>pageUtil.getTotalPageCount()) {
        		page=pageUtil.getTotalPageCount();
        	}
        	int pages=(page-1)*pageUtil.getPageSize();
        	pageUtil=new PageUtil(pages, totalCount);
        	
        	list=intTypeService.queryAllPage(pages);
    	}else {
          	int totalCount=intTypeService.pageCountByLike(businessClassification);//该方法是模糊查询的贷款分类总数量
        	pageUtil=new PageUtil(page, totalCount);
        	if(page<1) {
        		page=1;
        	}
        	else if(page>pageUtil.getTotalPageCount()) {
        		page=pageUtil.getTotalPageCount();
        	}
        	int pages=(page-1)*pageUtil.getPageSize();
        	pageUtil=new PageUtil(pages, totalCount);
        	list=intTypeService.queryByLike(businessClassification, pages);
    	}
    	HashMap<String, Object> map=new HashMap<>();
    	map.put("listLoanClaByLike", list);
    	map.put("pageutil",pageUtil);
		return map;
    }
	//后台管理---添加贷款分类信息
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
    @ResponseBody
    @RequestMapping("/updateByPrimaryKey")
    public Integer updateByPrimaryKey(LoanClassification loanClassification){
    	Integer num=intTypeService.updateByPrimaryKey(loanClassification);
    	return num;
    }
}

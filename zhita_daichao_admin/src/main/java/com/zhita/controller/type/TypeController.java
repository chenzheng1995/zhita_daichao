package com.zhita.controller.type;

import java.util.List;

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
    public List<LoanClassification> queryAllPage(HttpServletRequest request,Integer page){
    	int totalCount=intTypeService.pageCount();
    	PageUtil pageUtil=new PageUtil(page, totalCount);
    	if(page==0) {
    		page=1;
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil=new PageUtil(pages, totalCount);
    	
    	List<LoanClassification> list=intTypeService.queryAllPage(pages);
    	return list;
    }
    //后台管理---模糊查询贷款分类信息,并且有分页功能
    @ResponseBody
    @RequestMapping("/queryByLike")
    public List<LoanClassification> queryByLike(HttpServletRequest request,Integer page,String businessClassification){
       	int totalCount=intTypeService.pageCount();
    	PageUtil pageUtil=new PageUtil(page, totalCount);
    	if(page==0) {
    		page=1;
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil=new PageUtil(pages, totalCount);
    	List<LoanClassification> list=intTypeService.queryByLike(businessClassification, pages);
		return list;
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
}

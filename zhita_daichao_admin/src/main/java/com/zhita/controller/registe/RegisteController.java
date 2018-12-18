package com.zhita.controller.registe;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.LoansBusinesses;
import com.zhita.service.registe.IntRegisteService;
import com.zhita.util.PageUtil;

@Controller
@RequestMapping("/registe")
public class RegisteController {
	@Resource(name="registeServiceImp")
	 private IntRegisteService intRegisteService;

	public IntRegisteService getIntRegisteService() {
		return intRegisteService;
	}

	public void setIntRegisteService(IntRegisteService intRegisteService) {
		this.intRegisteService = intRegisteService;
	}
	
	//后台管理---查询贷款商家部分字段信息，含分页
    @ResponseBody
    @RequestMapping("/queryAllAdmin")
    public List<LoansBusinesses> queryAll(HttpServletRequest request,Integer page){
    	int totalCount=intRegisteService.pageCount();
    	PageUtil pageUtil=new PageUtil(page, totalCount);
    	if(page==0) {
    		page=1;
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil=new PageUtil(pages, totalCount);
    	
    	System.out.println(page+"===="+pageUtil.getPage()+"==="+pageUtil.getPage());
    	System.out.println(pageUtil.getPage()+"===="+pageUtil.getPage());
    	List<LoansBusinesses> list=intRegisteService.queryAllAdmain(pageUtil.getPage());
    	return list;
    }
	//后台管理---添加贷款商家信息
    @ResponseBody
    @RequestMapping("/insertAllAdmin")
    public Integer insertAll(LoansBusinesses loansBusinesses){
    	Integer selnum=intRegisteService.insert(loansBusinesses);
    	return selnum;
    }
	//后台管理---通过商家名称模糊查询，并且有分页功能
    @ResponseBody
    @RequestMapping("/queryByNameLike")
    public List<LoansBusinesses> queryByNameLike(HttpServletRequest request,Integer page,String businessName){
       	int totalCount=intRegisteService.pageCount();
    	PageUtil pageUtil=new PageUtil(page, totalCount);
    	if(page==0) {
    		page=1;
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil=new PageUtil(pages, totalCount);
    	List<LoansBusinesses> list=intRegisteService.queryByNameLike(businessName,pageUtil.getPage());
		return list;
    }
	//后台管理---根据主键id删除商家  假删除,只修改假删除状态
    @ResponseBody
    @RequestMapping("/falsedeleteByPrimaryKey")
    public Integer falsedeleteByPrimaryKey(Integer id){
    	int selnum=intRegisteService.upaFalseDel(id);
    	return selnum;
    }
    //后台管理---通过主键id查询出贷款商家信息
    @ResponseBody
    @RequestMapping("/selectByPrimaryKey")
    public LoansBusinesses selectByPrimaryKey(Integer id){
    	LoansBusinesses loansBusinesses=intRegisteService.selectByPrimaryKey(id);
    	return loansBusinesses;
    }
}
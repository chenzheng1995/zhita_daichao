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
}
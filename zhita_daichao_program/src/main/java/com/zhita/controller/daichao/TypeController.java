package com.zhita.controller.daichao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.LoansBusinesses;
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
	//小程序---通过贷款分类的名称，查询出当前贷款分类下的所有贷款商家的信息
    @ResponseBody
    @RequestMapping("/queryLoanbusinByLoanClass")
    public Map<String,Object> queryLoanbusinByLoanClass(String businessClassification,Integer page){
    	int totalCount=intTypeService.pageCountByBusinessClassification(businessClassification);
    	PageUtil pageUtil=new PageUtil(page, totalCount);
    	if(page==0) {
    		page=1;
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil=new PageUtil(pages, totalCount);
    	List<LoansBusinesses> list=intTypeService.queryLoanbusinByLoanClass(businessClassification, pageUtil.getPage());
    	
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listLoansBusinByLike",list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
}

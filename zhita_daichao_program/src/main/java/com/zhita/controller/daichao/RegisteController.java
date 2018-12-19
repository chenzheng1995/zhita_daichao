package com.zhita.controller.daichao;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.LoansBusinesses;
import com.zhita.service.registe.IntRegisteService;


@Controller
@RequestMapping(value="/registe")

public class RegisteController {
	@Resource(name="registeServiceImp")
	 private IntRegisteService intRegisteService;

	public IntRegisteService getIntRegisteService() {
		return intRegisteService;
	}

	public void setIntRegisteService(IntRegisteService intRegisteService) {
		this.intRegisteService = intRegisteService;
	}
	//查询所有贷款商家信息
    @ResponseBody
    @RequestMapping("/queryAll")
    public List<LoansBusinesses> queryAll(){
    	System.out.println("test");
    	List<LoansBusinesses> list=intRegisteService.queryAll();
    	for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getBusinessname());
		}
    	return list;
    }
	
}
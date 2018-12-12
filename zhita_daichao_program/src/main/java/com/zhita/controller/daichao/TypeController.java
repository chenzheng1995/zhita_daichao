package com.zhita.controller.daichao;


import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;

import com.zhita.service.type.IntTypeService;

@RestController("type")
public class TypeController {
	@Resource(name="typeServiceImp")
	private IntTypeService intTypeService;

	public IntTypeService getIntTypeService() {
		return intTypeService;
	}

	public void setIntTypeService(IntTypeService intTypeService) {
		this.intTypeService = intTypeService;
	}
	
}

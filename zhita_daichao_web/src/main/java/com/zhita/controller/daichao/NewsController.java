package com.zhita.controller.daichao;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.zhita.service.news.IntNewsService;

@Controller
@RequestMapping(value="/news")
public class NewsController {
	@Resource(name="newsServiceImp")
	private IntNewsService intNewsService;

	public IntNewsService getIntNewsService() {
		return intNewsService;
	}

	public void setIntNewsService(IntNewsService intNewsService) {
		this.intNewsService = intNewsService;
	}
	
}

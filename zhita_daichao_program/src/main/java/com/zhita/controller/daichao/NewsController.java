package com.zhita.controller.daichao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhita.service.news.IntNewsService;

@RestController("news")
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

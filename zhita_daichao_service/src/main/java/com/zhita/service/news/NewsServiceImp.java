package com.zhita.service.news;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhita.dao.manage.StrategyMapper;


@Transactional
@Service(value="newsServiceImp")
public class NewsServiceImp implements IntNewsService{
	@Resource(name="strategyMapper")
	private StrategyMapper strategyMapper;

	public StrategyMapper getStrategyMapper() {
		return strategyMapper;
	}
	public void setStrategyMapper(StrategyMapper strategyMapper) {
		this.strategyMapper = strategyMapper;
	}
	
	
}

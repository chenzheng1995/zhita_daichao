package com.zhita.service.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.NewsMapper;
import com.zhita.model.manage.News;

@Service(value="articleServiceImp")
public class ArticleServiceImp implements ArticleService{
	@Autowired
	NewsMapper newsMapper;

	@Override
	public int setnews(String title, String company, String ossimagePath, String author, String ossarticlePath,
			String isStick,String registrationTime) {
		int number = newsMapper.setnews(title,company,ossimagePath,author,ossarticlePath,isStick,registrationTime);
		return number;
	}

	@Override
	public int setNewsNotOssimagePath(String title, String company, String author, String ossarticlePath,
			String isStick, String registrationTime) {
		int number = newsMapper.setNewsNotOssimagePath(title,company,author,ossarticlePath,isStick,registrationTime);
		return number;
	}

	@Override
	public int pageCount1(String company) {
		int totalCount=newsMapper.pageCount1(company);
		return totalCount;
	}

	@Override
	public List<News> getAllnews(int page, int pageSize, String company) {
		List<News> list=newsMapper.getAllnews(page,pageSize,company);
		return list;
	}



}

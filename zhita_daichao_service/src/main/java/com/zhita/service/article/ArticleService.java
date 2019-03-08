package com.zhita.service.article;

import java.util.List;

import com.zhita.model.manage.News;

public interface ArticleService {

	int setnews(String title, String company, String ossimagePath, String author, String ossarticlePath,String isStick, String registrationTime);

	int setNewsNotOssimagePath(String title, String company, String author, String ossarticlePath, String isStick,
			String registrationTime);

	int pageCount1(String company);

	List<News> getAllnews(int page, int pageSize, String company);

}

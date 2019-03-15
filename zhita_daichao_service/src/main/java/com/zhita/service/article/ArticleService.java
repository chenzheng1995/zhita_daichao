package com.zhita.service.article;

import java.util.List;

import com.zhita.model.manage.News;

public interface ArticleService {

	int setnews(String title, String company, String ossimagePath, String author, String ossarticlePath,String isStick, String registrationTime, Integer typeId);

	int setNewsNotOssimagePath(String title, String company, String author, String ossarticlePath, String isStick,
			String registrationTime, Integer typeId);

	int pageCount1(String company, int typeId);

	List<News> getAllnews(int page, int pageSize, String company, int typeId);

	int updatenews(String title, String company, String ossimagePath, String author, String ossarticlePath,
			String isStick, String registrationTime, int id, Integer typeId);

	int deletenews(int id);

	int getviewed(int id);

	int setviewed(int id, int newViewed);

	int pageCount2(String company, String title);

	List<News> getfuzzynews(int page, int pageSize, String company, String title);

	int pageCountAll1(String company);

	List<News> getNewsByAll(int page, int pageSize, String company);

	News getnewscontent(int id);

	List<News> getAdminNewsByAll(int page, int pageSize, String company);

	List<News> getAdminAllnews(int page, int pageSize, String company, Integer typeId);

	String getcontent(Integer id);

	List<News> getNewsById(Integer id);

}

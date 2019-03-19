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
			String isStick,String registrationTime,Integer typeId) {
		int number = newsMapper.setnews(title,company,ossimagePath,author,ossarticlePath,isStick,registrationTime,typeId);
		return number;
	}

	@Override
	public int setNewsNotOssimagePath(String title, String company, String author, String ossarticlePath,
			String isStick, String registrationTime,Integer typeId) {
		int number = newsMapper.setNewsNotOssimagePath(title,company,author,ossarticlePath,isStick,registrationTime,typeId);
		return number;
	}

	@Override
	public int pageCount1(String company,int typeId) {
		int totalCount=newsMapper.pageCount1(company,typeId);
		return totalCount;
	}

	@Override
	public List<News> getAllnews(int page, int pageSize, String company,int typeId) {
		List<News> list=newsMapper.getAllnews(page,pageSize,company,typeId);
		return list;
	}

	@Override
	public int updatenews(String title, String company, String ossimagePath, String author, String ossarticlePath,
			String isStick, String registrationTime,int id,Integer typeId) {
		int number = newsMapper.updatenews(title, company, ossimagePath, author, ossarticlePath, isStick,registrationTime,id,typeId);
		return number;
	}

	@Override
	public int deletenews(int id) {
		int number = newsMapper.deletenews(id);
		return number;
	}

	@Override
	public int getviewed(int id) {
		int viewed = newsMapper.getviewed(id);
		return viewed;
	}

	@Override
	public int setviewed(int id, int newViewed) {
		int number = newsMapper.setviewed(id,newViewed);
		return number;
	}

	@Override
	public int pageCount2(String company, String title) {
		int totalCount=newsMapper.pageCount2(company,title);
		return totalCount;
	}

	@Override
	public List<News> getfuzzynews(int page, int pageSize, String company, String title) {
		List<News> list=newsMapper.getfuzzynews(page,pageSize,company,title);
		return list;
	}

	@Override
	public int pageCountAll1(String company) {
	    int totalCount=newsMapper.pageCountAll1(company);
		return totalCount;
	}

	@Override
	public List<News> getNewsByAll(int page, int pageSize, String company) {
		List<News> list = newsMapper.getNewsByAll(page,pageSize,company);
		return list;
	}

	@Override
	public News getnewscontent(int id) {
		News news = newsMapper.getnewscontent(id);
		return news;
	}

	@Override
	public List<News> getAdminNewsByAll(int page, int pageSize, String company) {
		List<News> list = newsMapper.getAdminNewsByAll(page,pageSize,company);
		return list;
	}

	@Override
	public List<News> getAdminAllnews(int page, int pageSize, String company, Integer typeId) {
		List<News> list=newsMapper.getAdminAllnews(page,pageSize,company,typeId);
		return list;
	}

	@Override
	public String getcontent(Integer id) {
		String contentUrl = newsMapper.getcontent(id);
		return contentUrl;
	}

	@Override
	public List<News> getNewsById(Integer id) {
		List<News> list = null;
		list =newsMapper.getNewsById(id);
		return list;
	}
	//通过文章id查询出标题图片
	public String getTitleImage(Integer id){
		String titleImage=newsMapper.getTitleImage(id);
		return titleImage;
	}


}

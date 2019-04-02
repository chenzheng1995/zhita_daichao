package com.zhita.controller.daichao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.News;
import com.zhita.service.article.ArticleService;
import com.zhita.service.newstype.NewsTypeService;
import com.zhita.util.ArticleUtil;
import com.zhita.util.PageUtil;
import com.zhita.util.PostAndGet;
import com.zhita.util.Timestamps;

@Controller
@RequestMapping(value="/news")
public class ArticleController {
	@Autowired
	ArticleService articleService;
	
	@Autowired
	NewsTypeService newsTypeService;


//获取某个类型的文章的数据,含分页	
@ResponseBody
@RequestMapping("/getAllnews")
@Transactional
public Map<String, Object> getAllnews(Integer page,String company,String typename,String oneSourceName,String twoSourceName){
	int totalCount =0;
	List<News> list = null;
	Map<String, Object> map = new HashMap<>();
	Integer typeId = newsTypeService.getTypeId(typename,company);
	if("全部".equals(typename)) {
		totalCount=articleService.pageCountAll1(company);//该方法是查询文章总条数
	}else {
	    totalCount=articleService.pageCount1(company,typeId);//该方法是查询文章总条数
	}
  	PageUtil pageUtil=new PageUtil(page,10,totalCount);
  	if(page<1) {
  		page=1;
  	}
  	else if(page>pageUtil.getTotalPageCount()) {
  		if(totalCount==0) {
  			page=pageUtil.getTotalPageCount()+1;
  		}else {
  			page=pageUtil.getTotalPageCount();
  		}
  	}
  	int pages=(page-1)*pageUtil.getPageSize();
  	pageUtil.setPage(pages);
  	Timestamps timestamps = new Timestamps();
  	String date = null;
  	if("全部".equals(typename)) {
  	    list=articleService.getNewsByAll(pageUtil.getPage(),pageUtil.getPageSize(),company);
  	    pageUtil=new PageUtil(page,10,totalCount);
  	}else {
  	    list=articleService.getAllnews(pageUtil.getPage(),pageUtil.getPageSize(),company,typeId);	
  	    pageUtil=new PageUtil(page,10,totalCount);
  	} 	
      for (News news : list) {   	  
       date  = news.getDate(); //获取时间戳
       date = timestamps.stampToDate1(date);   
       news.setDate(date);
		}
      map.put("AllNewsList", list);
      map.put("pageutil", pageUtil);
	return map;
	
}


//获取指定文章的内容
@ResponseBody
@RequestMapping("/getnewscontent")
@Transactional
public Map<String, Object> getnewscontent(int id){
	Map<String, Object> map = new HashMap<>();
	int oldViewed = articleService.getviewed(id);
	int newViewed = oldViewed+1;
	int number = articleService.setviewed(id,newViewed);
	if (number == 1) {								
		map.put("msg", "浏览次数更新成功");
		map.put("SCode", "200");
	} else {
		map.put("msg", "浏览次数更新失败");
		map.put("SCode", "405");
	}
  	PostAndGet postAndGet =new PostAndGet();
    ArticleUtil articleUtil = new ArticleUtil();
  	Timestamps timestamps = new Timestamps();
	News news = articleService.getnewscontent(id);
    String date = news.getDate();
    date = timestamps.stampToDate1(date);   
    news.setDate(date);
    String contentUrl  = news.getContenturl(); //获取内容的URL
    String content = postAndGet.sendGet1(contentUrl);//获取txt文件的内容
    String result = articleUtil.txt2String(content);
    news.setContenturl(result);      
	map.put("news",news);
	return map;
	
}

//模糊查询文章的数据,含分页	
@ResponseBody
@RequestMapping("/getfuzzynews")
@Transactional
public Map<String, Object> getfuzzynews(Integer page,String company, String title,String oneSourceName,String twoSourceName){
	Map<String, Object> map = new HashMap<>();
	int totalCount=articleService.pageCount2(company,title);//该方法是查询文章总条数
	PageUtil pageUtil=new PageUtil(page,10,totalCount);
	if(page<1) {
		page=1;
	}
	else if(page>pageUtil.getTotalPageCount()) {
		if(totalCount==0) {
			page=pageUtil.getTotalPageCount()+1;
		}else {
			page=pageUtil.getTotalPageCount();
		}
	}
	int pages=(page-1)*pageUtil.getPageSize();
	pageUtil.setPage(pages);
	Timestamps timestamps = new Timestamps();
	String contentUrl = null;
	PostAndGet postAndGet =new PostAndGet();
  ArticleUtil articleUtil = new ArticleUtil();
	String date = null;
	List<News> list=articleService.getfuzzynews(pageUtil.getPage(),pageUtil.getPageSize(),company,title);
    for (News news : list) {   	  
     date  = news.getDate(); //获取时间戳
     date = timestamps.stampToDate1(date);
     contentUrl  = news.getContenturl(); //获取内容的URL
     String content = postAndGet.sendGet1(contentUrl);//获取txt文件的内容
     String result = articleUtil.txt2String(content);
     news.setContenturl(result);      
     news.setDate(date);
		}
    map.put("AllNewsList", list);
    map.put("pageutil", pageUtil);
	return map;
	
}

//查询文章分类
@ResponseBody
@RequestMapping("/getnewstype")
@Transactional
public Map<String, Object> getnewstype(String company,String oneSourceName, String twoSourceName){
	Map<String, Object> map = new HashMap<>();
	List<String> typelist = new ArrayList<>();
	typelist = newsTypeService.getnewstype(company);
    map.put("typelist", typelist);
	return map;
	
}

}

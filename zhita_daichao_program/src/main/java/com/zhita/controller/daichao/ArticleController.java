package com.zhita.controller.daichao;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.News;
import com.zhita.service.article.ArticleService;
import com.zhita.util.ArticleUtil;
import com.zhita.util.PageUtil;
import com.zhita.util.PostAndGet;
import com.zhita.util.Timestamps;

@Controller
@RequestMapping(value="/news")
public class ArticleController {
	@Autowired
	ArticleService articleService;


@ResponseBody
@RequestMapping("/getAllnews")
public Map<String, Object> setnews(Integer page,String company){
	Map<String, Object> map = new HashMap<>();
	int totalCount=articleService.pageCount1(company);//该方法是查询文章总条数
  	PageUtil pageUtil=new PageUtil(page,2,totalCount);
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
  	List<News> list=articleService.getAllnews(pageUtil.getPage(),pageUtil.getPageSize(),company);
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
	return map;
	
}
}

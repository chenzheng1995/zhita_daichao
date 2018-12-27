package com.zhita.controller.news;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.Strategy;
import com.zhita.service.news.IntNewsService;
import com.zhita.util.PageUtil;

@Controller
@RequestMapping("/news")
public class NewsController {
	@Resource(name="newsServiceImp")
	private IntNewsService intNewsService;

	public IntNewsService getIntNewsService() {
		return intNewsService;
	}

	public void setIntNewsService(IntNewsService intNewsService) {
		this.intNewsService = intNewsService;
	}
	
	//后台管理---查询攻略表所有信息，含分页
	@ResponseBody
	@RequestMapping("/queryAllNews")
    public Map<String,Object> queryAllNews(Integer page){
    	int totalCount=intNewsService.pageCount();//该方法是查询攻略总数量
    	PageUtil pageUtil=new PageUtil(page, totalCount);
    	if(page==0) {
    		page=1;
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil=new PageUtil(pages, totalCount);
    	List<Strategy> list=intNewsService.queryAllNews(pageUtil.getPage());
    	
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listnews",list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
	
	//后台管理---根据标题字段模糊查询攻略表所有信息，含分页
	@ResponseBody
	@RequestMapping("/queryNewsByLike")
    public Map<String,Object> queryNewsByLike(String title,Integer page){
		List<Strategy> list=null;
		PageUtil pageUtil=null;
		if(title==null||"".equals(title)) {
	    	int totalCount=intNewsService.pageCount();//该方法是查询攻略总数量
	    	pageUtil=new PageUtil(page, totalCount);
	    	if(page==0) {
	    		page=1;
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil=new PageUtil(pages, totalCount);
	    	list=intNewsService.queryAllNews(pageUtil.getPage());
		}else {
	    	int totalCount=intNewsService.pageCountByLike(title);//该方法是模糊查询的攻略总数量
	    	pageUtil=new PageUtil(page, totalCount);
	    	if(page==0) {
	    		page=1;
	    	}
	    	
	    	
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil=new PageUtil(pages, totalCount);
	    	list=intNewsService.queryNewsByLike(title, pageUtil.getPage());
		}
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listnewsByLike",list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
	//后台管理---添加攻略信息
	@ResponseBody
	@RequestMapping("/AddALL")
    public Integer AddALL(Strategy strategy){
		int selnum=intNewsService.addAll(strategy);
		return selnum;
	}
    //后台管理---通过主键id查询出攻略信息
	@ResponseBody
	@RequestMapping("/selectByPrimaryKey")
    public Strategy selectByPrimaryKey(Integer id) {
    	Strategy strategy=intNewsService.selectByPrimaryKey(id);
    	return strategy;
    }
	//后台管理---通过主键id修改其当前对象的假删除状态
	@ResponseBody
	@RequestMapping("/upaFalseDelById")
    public int upaFalseDelById(Integer id) {
    	int num=intNewsService.upaFalseDelById(id);
    	return num;
    }
    //后台管理---修改攻略状态
	@ResponseBody
	@RequestMapping("/upaState")
	public int upaState(String state,Integer id) {
		int num=0;
		if(state.equals("1")) {
			num=intNewsService.upaStateOpen(id);
		}else {
			num=intNewsService.upaStateClose(id);
		}
		return num;
	}
}

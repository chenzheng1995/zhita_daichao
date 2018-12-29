package com.zhita.controller.merchant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.Source;
import com.zhita.service.merchant.IntMerchantService;
import com.zhita.util.PageUtil;

@Controller
@RequestMapping("/merchant")
public class MerchantController {
	@Autowired
	private IntMerchantService intMerchantService;
	
	
	//后台管理---查询渠道表所有信息，含分页
	@ResponseBody
	@RequestMapping("/queryAllSource")
    public Map<String,Object> queryAllSource(Integer page){
    	int totalCount=intMerchantService.pageCount();//该方法是查询渠道表总数量
    	PageUtil pageUtil=new PageUtil(page, totalCount);
    	if(page<1) {
    		page=1;
    	}
    	else if(page>pageUtil.getTotalPageCount()) {
    		page=pageUtil.getTotalPageCount();
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil=new PageUtil(pages, totalCount);
    	List<Source> list=intMerchantService.queryAllSource(pageUtil.getPage());
    	
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listsource",list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
	
	//后台管理---根据姓名字段模糊查询渠道表所有信息，含分页
	@ResponseBody
	@RequestMapping("/querySourceByLike")
    public Map<String,Object> querySourceByLike(String sourceName,Integer page){
		List<Source> list=null;
		PageUtil pageUtil=null;
		if(sourceName==null||"".equals(sourceName)) {
	    	int totalCount=intMerchantService.pageCount();//该方法是查询渠道总数量
	    	pageUtil=new PageUtil(page, totalCount);
	    	if(page<1) {
	    		page=1;
	    	}
	    	else if(page>pageUtil.getTotalPageCount()) {
	    		page=pageUtil.getTotalPageCount();
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil=new PageUtil(pages, totalCount);
	    	list=intMerchantService.queryByLike(sourceName, pageUtil.getPage());
		}else {
	    	int totalCount=intMerchantService.pageCountByLike(sourceName);//该方法是模糊查询的攻略总数量
	    	pageUtil=new PageUtil(page, totalCount);
	    	if(page<1) {
	    		page=1;
	    	}
	    	else if(page>pageUtil.getTotalPageCount()) {
	    		page=pageUtil.getTotalPageCount();
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil=new PageUtil(pages, totalCount);
	    	list=intMerchantService.queryByLike(sourceName, pageUtil.getPage());
		}
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listsourceByLike",list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
	//后台管理---添加渠道
	@ResponseBody
	@RequestMapping("/AddALL")
    public int AddALL(Source source){
		int num=intMerchantService.addAll(source);
		return num;
	}
    //后台管理---通过主键id查询出渠道信息
	@ResponseBody
	@RequestMapping("/selectByPrimaryKey")
    public Source selectByPrimaryKey(Integer id) {
    	Source source=intMerchantService.selectByPrimaryKey(id);
    	return source;
    }
	//后台管理---通过传过来的渠道对象，对当前对象进行修改保存
	@ResponseBody
	@RequestMapping("/updateSource")
    public int updateSource(Source source){
		int num=intMerchantService.updateSource(source);
		return num;
	}
	
	//后台管理---通过主键id修改其当前对象的假删除状态
	@ResponseBody
	@RequestMapping("/upaFalseDelById")
    public int upaFalseDelById(Integer id) {
    	int num=intMerchantService.upaFalseDel(id);
    	return num;
    }
    //后台管理---修改攻略状态
	@ResponseBody
	@RequestMapping("/upaState")
	public int upaState(String state,Integer id) {
		int num=0;
		if(state.equals("1")) {
			num=intMerchantService.upaStateOpen(id);
		}else {
			num=intMerchantService.upaStateClose(id);
		}
		return num;
	}
}

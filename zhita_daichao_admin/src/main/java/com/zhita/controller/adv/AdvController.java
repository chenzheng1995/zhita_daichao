package com.zhita.controller.adv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.Advertising;
import com.zhita.service.adv.IntAdvService;
import com.zhita.util.PageUtil;

@Controller
@RequestMapping("/adv")
public class AdvController {
	@Autowired
	private IntAdvService intAdvService;
	
    //后台管理---查询广告表全部信息,含分页
	@ResponseBody
	@RequestMapping("/queryAll")
    public Map<String,Object> queryAll(Integer page){
    	int totalCount=intAdvService.pageCount();//该方法是查询广告表总数量
    	PageUtil pageUtil=new PageUtil(page, totalCount);
    	if(page==0) {
    		page=1;
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil=new PageUtil(pages, totalCount);
    	List<Advertising> list=intAdvService.queryAll(pageUtil.getPage());
    	
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listadv",list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
    //后台管理---根据标题字段模糊查询广告表信息，含分页
	@ResponseBody
	@RequestMapping("/queryAllByLike")
    public Map<String,Object> queryAllByLike(String title,Integer page){
		List<Advertising> list=null;
		PageUtil pageUtil=null;
		if(title==null||"".equals(title)) {
	    	int totalCount=intAdvService.pageCount();//该方法是查询广告表总数量
	    	pageUtil=new PageUtil(page, totalCount);
	    	if(page==0) {
	    		page=1;
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil=new PageUtil(pages, totalCount);
	    	list=intAdvService.queryAll(pageUtil.getPage());
		}else {
	    	int totalCount=intAdvService.pageCountByLike(title);//该方法是根据标题模糊查询轮播图总数量
	    	pageUtil=new PageUtil(page, totalCount);
	    	if(page==0) {
	    		page=1;
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil=new PageUtil(pages, totalCount);
	    	list=intAdvService.queryAllByLike(title, pageUtil.getPage());
		}
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listadvByLike",list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
    //后台管理---添加广告表信息
	@ResponseBody
	@RequestMapping("/AddAll")
    public int AddAll(Advertising advertising) {
    	int num=intAdvService.AddAll(advertising);
    	return num;
    }
    //后台管理 ---根据主键id查询出广告表信息
	@ResponseBody
	@RequestMapping("/selectByPrimaryKey")
    public Advertising selectByPrimaryKey(Integer id) {
		Advertising advertising=intAdvService.selectByPrimaryKey(id);
    	return advertising;
    }
    //后台管理---根据删除按钮，修改广告表假删除状态
	@ResponseBody
	@RequestMapping("/upaFalseDel")
    public int upaFalseDel(Integer id) {
    	int num=intAdvService.upaFalseDel(id);
    	return num;
    }
	//后台管理---根据前端传过来的状态值，对当前对象的状态值进行修改
	@ResponseBody
	@RequestMapping("/upaState")
	public int upaState(String state,Integer id) {
		int num=0;
		if(state.equals("1")) {
			num=intAdvService.upaStateOpen(id);
		}else {
			num=intAdvService.upaStateClose(id);
		}
		return num;
	}
	
}

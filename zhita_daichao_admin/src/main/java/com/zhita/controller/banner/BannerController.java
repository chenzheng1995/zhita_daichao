package com.zhita.controller.banner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.ShufflingFigure;
import com.zhita.service.banner.IntBannerService;
import com.zhita.util.PageUtil;

@Controller
@RequestMapping("/banner")
public class BannerController {
	@Resource(name="bannerServiceImp")
	 private IntBannerService intBannerService;

	public IntBannerService getIntBannerService() {
		return intBannerService;
	}

	public void setIntBannerService(IntBannerService intBannerService) {
		this.intBannerService = intBannerService;
	}
    //后台管理---查询轮播图全部信息,含分页
	@ResponseBody
	@RequestMapping("/queryAll")
    public Map<String,Object> queryAll(Integer page){
    	int totalCount=intBannerService.pageCount();//该方法是查询轮播图总数量
    	PageUtil pageUtil=new PageUtil(page, totalCount);
    	if(page==0) {
    		page=1;
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil=new PageUtil(pages, totalCount);
    	List<ShufflingFigure> list=intBannerService.queryAll(pageUtil.getPage());
    	
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listshuff",list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
    //后台管理---根据标题字段模糊查询轮播图信息，含分页
	@ResponseBody
	@RequestMapping("/queryAllByLike")
    public Map<String,Object> queryAllByLike(String title,Integer page){
    	int totalCount=intBannerService.pageCountByLike(title);//该方法是根据标题模糊查询轮播图总数量
    	PageUtil pageUtil=new PageUtil(page, totalCount);
    	if(page==0) {
    		page=1;
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil=new PageUtil(pages, totalCount);
    	List<ShufflingFigure> list=intBannerService.queryAllByLike(title, pageUtil.getPage());
    	
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listshuffByLike",list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
    //后台管理---添加轮播图信息
	@ResponseBody
	@RequestMapping("/AddAll")
    public int AddAll(ShufflingFigure shufflingFigure) {
    	int num=intBannerService.AddAll(shufflingFigure);
    	return num;
    }
    //后台管理 ---根据主键id查询出轮播图信息
	@ResponseBody
	@RequestMapping("/selectByPrimaryKey")
    public ShufflingFigure selectByPrimaryKey(Integer id) {
    	ShufflingFigure shufflingFigure=intBannerService.selectByPrimaryKey(id);
    	return shufflingFigure;
    }
    //后台管理---根据删除按钮，修改轮播图假删除状态
	@ResponseBody
	@RequestMapping("/upaFalseDel")
    public int upaFalseDel(Integer id) {
    	int num=intBannerService.upaFalseDel(id);
    	return num;
    }
	//后台管理---根据前端传过来的状态值，对当前对象的状态值进行修改
	@ResponseBody
	@RequestMapping("/upaState")
	public int upaState(String state,Integer id) {
		int num=0;
		if(state.equals("1")) {
			num=intBannerService.upaStateOpen(id);
		}else {
			num=intBannerService.upaStateClose(id);
		}
		return num;
	}
	
}

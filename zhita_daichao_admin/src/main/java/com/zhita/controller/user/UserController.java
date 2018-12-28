package com.zhita.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.ButtonFootprint;
import com.zhita.model.manage.Source;
import com.zhita.model.manage.User;
import com.zhita.service.merchant.IntMerchantService;
import com.zhita.service.user.UserService;
import com.zhita.util.PageUtil;

@Controller
@RequestMapping("/uer")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private IntMerchantService IntMerchantService;
	//后台管理---查询出用户表所有信息，含分页
    @ResponseBody
    @RequestMapping("/queryAllUser")
    public Map<String,Object> queryAllUser(HttpServletRequest request,Integer page){
    	List<Source> list1=IntMerchantService.queryAll();//查询出所有的渠道信息，将渠道名称渲染到下拉框中
    	
       	int totalCount=userService.pageCount();//该方法是查询出用户表总数量
    	PageUtil pageUtil=new PageUtil(page, totalCount);
    	if(page<1) {
    		page=1;
    	}
    	else if(page>pageUtil.getTotalPageCount()) {
    		page=pageUtil.getTotalPageCount();
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil=new PageUtil(pages, totalCount);
    	List<User> list=userService.queryAllUser(pageUtil.getPage());
    
    	HashMap<String, Object> map=new HashMap<>();
    	map.put("listSource", list1);
    	map.put("listUser",list);
    	map.put("pageutil",pageUtil);
		return map;
    }
	//后台管理---通过传过来的值，进行多种情况的模糊查询，含分页
    @ResponseBody
    @RequestMapping("/queryByLike")
    public Map<String,Object> queryByLike(String phone,String sourceName,String registrationTimeStart,String registrationTimeEnd,Integer page){
    	 Map<String,Object> map=userService.queryByLike(phone,sourceName,registrationTimeStart,registrationTimeEnd,page);
    	 return map;
    }
	//后台管理---根据用户id查询出按钮足迹  商品足迹和贷款分类足迹    并按足迹时间倒排序，含分页
    @ResponseBody
    @RequestMapping("/queryAllButton")
	public Map<String,Object> queryAllButton(Integer userId,Integer page){
       	int totalCount=userService.pageCountThreeFootprint(userId);//该方法是根据用户id查询出按钮足迹  商品足迹和贷款分类足迹的总数量
    	PageUtil pageUtil=new PageUtil(page, totalCount);
    	if(page<1) {
    		page=1;
    	}
    	else if(page>pageUtil.getTotalPageCount()) {
    		page=pageUtil.getTotalPageCount();
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil=new PageUtil(pages, totalCount);
    	List<ButtonFootprint> list=userService.queryAllButton(userId,page);
    
    	HashMap<String, Object> map=new HashMap<>();
    	map.put("listFootprint",list);
    	map.put("pageutil",pageUtil);
		return map;
	}
}

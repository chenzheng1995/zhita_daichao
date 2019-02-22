package com.zhita.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.ButtonFootprint;
import com.zhita.model.manage.Source;
import com.zhita.model.manage.User;
import com.zhita.service.merchant.IntMerchantService;
import com.zhita.service.user.UserService;
import com.zhita.util.ListPageUtil;
import com.zhita.util.PageUtil;
import com.zhita.util.Timestamps;

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
    public Map<String,Object> queryAllUser(Integer page,String[] company){
    	List<Source> list1=new ArrayList<>();//存渠道的集合
		PageUtil pageUtil=null;
		List<User> list=new ArrayList<>();
		List<User> listto=new ArrayList<>();
		
    	Timestamps times=new Timestamps();//创建时间戳实体类对象
		long todayZeroTimestamps = times.getTodayZeroTimestamps(); //今天0点的时间戳
		long tomorrowZeroTimestamps = todayZeroTimestamps+86400000; //明天0点的时间戳
		
    	if(company.length==1){
			System.out.println("company.length==1");
			
	    	list1=IntMerchantService.queryAll(company[0]);//查询出所有的渠道信息，将渠道名称渲染到下拉框中
	    	List<User> list2=userService.queryAllPhone(company[0]);//查询出所有用户的手机号
	    	for (int i = 0; i < list2.size(); i++) {
				//System.out.println("userid:"+list.get(i).getId()+"dayfen:"+userService.queryAmountByUserId(list.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps)));
				//将用户的当日分发系数字段进行修改
				userService.upaDayFen(userService.queryAmountByUserId(list2.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),company[0]), list2.get(i).getPhone());
			}
	    	
	       	int totalCount=userService.pageCount(company[0]);//该方法是查询出用户表总数量
	    	pageUtil=new PageUtil(page,2,totalCount);
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
	    	listto=userService.queryAllUser(company[0],pageUtil.getPage(),pageUtil.getPageSize());
	    	
	    	for (int i = 0; i < listto.size(); i++) {
	    		listto.get(i).setRegistrationtime(Timestamps.stampToDate(listto.get(i).getRegistrationtime()));
	    		listto.get(i).setLoginTime(Timestamps.stampToDate(listto.get(i).getLoginTime()));
			}
	    	pageUtil=new PageUtil(page,2,totalCount);
    	}
    	else if(company.length>1){
    		
    		System.out.println("company.length>1");
    		
    		List<Source> list1for=null;
    		List<User> listfor=null;
    		for (int j = 0; j < company.length;j++) {
    	    	list1for=IntMerchantService.queryAll(company[j]);//查询出所有的渠道信息，将渠道名称渲染到下拉框中
    	    	list1.addAll(list1for);
    	    	List<User> list2=userService.queryAllPhone(company[j]);//查询出所有用户的手机号
    	    	for (int i = 0; i < list2.size(); i++) {
    				//System.out.println("userid:"+list.get(i).getId()+"dayfen:"+userService.queryAmountByUserId(list.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps)));
    				//将用户的当日分发系数字段进行修改
    				userService.upaDayFen(userService.queryAmountByUserId(list2.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),company[j]), list2.get(i).getPhone());
    			}
    	    	listfor=userService.queryAllUser(company[j],pageUtil.getPage(),pageUtil.getPageSize());
            	list.addAll(listfor);
			}
	    	for (int i = 0; i < list.size(); i++) {
	    		list.get(i).setRegistrationtime(Timestamps.stampToDate(list.get(i).getRegistrationtime()));
				list.get(i).setLoginTime(Timestamps.stampToDate(list.get(i).getLoginTime()));
			}
	    	
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i)+"整合后的集合");
			}
			
			System.out.println("传进工具类的page"+page);
			
			ListPageUtil listPageUtil=new ListPageUtil(list,page,2);
			listto.addAll(listPageUtil.getData());
			
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
	    	
    	}
    	HashMap<String, Object> map=new HashMap<>();
    	map.put("listSource", list1);
    	map.put("listUser",listto);
    	map.put("pageutil",pageUtil);
    	map.put("company", company);
		return map;
    }
	//后台管理---通过传过来的值，进行多种情况的模糊查询，含分页
    @ResponseBody
    @RequestMapping("/queryByLike")
    public Map<String,Object> queryByLike(String phone,String sourceName,String registrationTimeStart,String registrationTimeEnd,String company,Integer page){
    	 Map<String,Object> map=userService.queryByLike(phone,sourceName,registrationTimeStart,registrationTimeEnd,company,page);
    	 return map;
    }
	//后台管理---根据用户id查询出按钮足迹  商品足迹和贷款分类足迹    并按足迹时间倒排序，含分页
    @ResponseBody
    @RequestMapping("/queryAllButton")
	public Map<String,Object> queryAllButton(Integer userId,Integer page){
       	int totalCount=userService.pageCountThreeFootprint(userId);//该方法是根据用户id查询出按钮足迹  商品足迹和贷款分类足迹的总数量
    	PageUtil pageUtil=new PageUtil(page,20,totalCount);
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
    	List<ButtonFootprint> list=userService.queryAllButton(userId,pageUtil.getPage(),pageUtil.getPageSize());
    	for (int i = 0; i < list.size(); i++) {
			list.get(i).setFootprinttime(Timestamps.stampToDate(list.get(i).getFootprinttime()));
		}
    
    	HashMap<String, Object> map=new HashMap<>();
    	map.put("listFootprint",list);
    	map.put("pageutil",pageUtil);
		return map;
	}
}

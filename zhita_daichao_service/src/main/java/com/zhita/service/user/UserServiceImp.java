package com.zhita.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.SourceMapper;
import com.zhita.dao.manage.UserMapper;
import com.zhita.model.manage.ButtonFootprint;
import com.zhita.model.manage.Source;
import com.zhita.model.manage.User;
import com.zhita.util.PageUtil;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	SourceMapper sourceMapper;

	public Long getregistered() {
		Long registered = userMapper.getregistered();
		return registered;
	}

	@Override
	public Long getdailyUsers(long todayZeroTimestamps, long tomorrowZeroTimestamps) {
		Long dailyUsers = userMapper.getdailyUsers(todayZeroTimestamps, tomorrowZeroTimestamps);
		return dailyUsers;
	}

	@Override
	public Long getmonthlyUsers(long monthlyZeroTimestamps, long nextMonthlyZeroTimestamps) {
		Long monthlyUsers = userMapper.getmonthlyUsers(monthlyZeroTimestamps, nextMonthlyZeroTimestamps);
		return monthlyUsers;
	}
	//后台管理---查询出用户表总数量
	public int pageCount() {
		int count=userMapper.pageCount();
		return count;
	}

	//后台管理---根据用户id查询出按钮足迹  商品足迹和贷款分类足迹的总数量
	public int pageCountThreeFootprint(Integer id) {
		int count=userMapper.pageCountThreeFootprint(id);
		return count;
	}
	//后台管理---查询出用户表所有信息，含分页
	public List<User> queryAllUser(Integer page){
		List<User> list=userMapper.queryAllUser(page);
		return list;
	}

	//后台管理---通过传过来的值，进行多种情况的模糊查询，含分页
	public Map<String, Object> queryByLike(String phone,String sourceName,String registrationTimeStart,String registrationTimeEnd,Integer page) {
		List<Source> list1=null;
		List<User> list=null;
		PageUtil pageUtil=null;
		
		//所有条件都为空
		if((phone==null||"".equals(phone))&&(sourceName==null||"".equals(sourceName))&&(registrationTimeStart==null||"".equals(registrationTimeStart))&&(registrationTimeEnd==null||"".equals(registrationTimeEnd))) {
			System.out.println("第一个if");
	    	list1=sourceMapper.queryAll();//查询出所有的渠道信息，将渠道名称渲染到下拉框中
	    	
	       	int totalCount=userMapper.pageCount();//该方法是查询出用户表总数量
	    	pageUtil=new PageUtil(page, totalCount);
	    	if(page<1) {
	    		page=1;
	    	}
	    	else if(page>pageUtil.getTotalPageCount()) {
	    		page=pageUtil.getTotalPageCount();
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil=new PageUtil(pages, totalCount);
	    	list=userMapper.queryAllUser(pageUtil.getPage());
	    
	    	HashMap<String, Object> map=new HashMap<>();
	    	map.put("listSource", list1);
	    	map.put("listUser",list);
	    	map.put("pageutil",pageUtil);
			return map;
		}

		//通过电话模糊查询
		else if((phone!=null||!"".equals(phone))&&(sourceName==null||"".equals(sourceName))&&(registrationTimeStart==null||"".equals(registrationTimeStart))&&(registrationTimeEnd==null||"".equals(registrationTimeEnd))) {
			System.out.println("第二个if");
	    	list1=sourceMapper.queryAll();//查询出所有的渠道信息，将渠道名称渲染到下拉框中
	    	
			int totalCount=userMapper.pageCountByPhone(phone);//该方法是通过手机号模糊查询出用户总数量
			pageUtil=new PageUtil(page, totalCount);
	    	if(page<1) {
	    		page=1;
	    	}
	    	else if(page>pageUtil.getTotalPageCount()) {
	    		page=pageUtil.getTotalPageCount();
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil=new PageUtil(pages, totalCount);
	    	list=userMapper.queryByPhone(phone, pageUtil.getPage());
		}
		//通过电话和渠道名称模糊查询
		else if((phone!=null||!"".equals(phone))&&(sourceName!=null||!"".equals(sourceName))&&(registrationTimeStart==null||"".equals(registrationTimeStart))&&(registrationTimeEnd==null||"".equals(registrationTimeEnd))) {
			System.out.println("第三个if");
	 		int totalCount=userMapper.pageCountByPhoneAndSourceName(phone,sourceName);//该方法是通过电话和渠道名称模糊查询出用户总数量
			pageUtil=new PageUtil(page, totalCount);
	    	if(page<1) {
	    		page=1;
	    	}
	    	else if(page>pageUtil.getTotalPageCount()) {
	    		page=pageUtil.getTotalPageCount();
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil=new PageUtil(pages, totalCount);
	    	list=userMapper.queryByPhoneAndSourceName(phone,sourceName, pageUtil.getPage());
	    }
		//通过电话和注册时间模糊查询
		else if((phone!=null||!"".equals(phone))&&(sourceName==null||"".equals(sourceName))&&(registrationTimeStart!=null||!"".equals(registrationTimeStart))&&(registrationTimeEnd!=null||!"".equals(registrationTimeEnd))) {
			System.out.println("第四个if");
			
	    	list1=sourceMapper.queryAll();//查询出所有的渠道信息，将渠道名称渲染到下拉框中
			int totalCount=userMapper.pageCountByPhoneAndRegistrationtime(phone,registrationTimeStart,registrationTimeEnd);//该方法是通过电话和注册时间模糊查询出用户总数量
			pageUtil=new PageUtil(page, totalCount);
	    	if(page<1) {
	    		page=1;
	    	}
	    	else if(page>pageUtil.getTotalPageCount()) {
	    		page=pageUtil.getTotalPageCount();
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil=new PageUtil(pages, totalCount);
	    	list=userMapper.queryByPhoneAndRegistrationtime(phone,registrationTimeStart,registrationTimeEnd,pageUtil.getPage());
	    }
		//通过电话，渠道名称，注册时间模糊查询
		else if((phone!=null||!"".equals(phone))&&(sourceName!=null||!"".equals(sourceName))&&(registrationTimeStart!=null||!"".equals(registrationTimeStart))&&(registrationTimeEnd!=null||!"".equals(registrationTimeEnd))) {
			System.out.println("第五个if");
			int totalCount=userMapper.pageCountByPhoneSourceNameAndRegistrationtime(phone,sourceName,registrationTimeStart,registrationTimeEnd);//该方法是通过电话、渠道名称和注册时间模糊查询出用户总数量
			pageUtil=new PageUtil(page, totalCount);
	    	if(page<1) {
	    		page=1;
	    	}
	    	else if(page>pageUtil.getTotalPageCount()) {
	    		page=pageUtil.getTotalPageCount();
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil=new PageUtil(pages, totalCount);
	    	list=userMapper.queryByPhoneSourceNameAndRegistrationtime(phone,sourceName,registrationTimeStart,registrationTimeEnd,page);
	    	
		}
		//通过渠道名称模糊查询
		else if((phone==null||"".equals(phone))&&(sourceName!=null||!"".equals(sourceName))&&(registrationTimeStart==null||"".equals(registrationTimeStart))&&(registrationTimeEnd==null||"".equals(registrationTimeEnd))) {
			System.out.println("第六个if");
			int totalCount=userMapper.pageCountBySourceName(sourceName);//该方法是通过渠道名称模糊查询出用户总数量
			pageUtil=new PageUtil(page, totalCount);
	    	if(page<1) {
	    		page=1;
	    	}
	    	else if(page>pageUtil.getTotalPageCount()) {
	    		page=pageUtil.getTotalPageCount();
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil=new PageUtil(pages, totalCount);
	    	list=userMapper.queryBySourceName(sourceName, pageUtil.getPage());
		}
		//通过渠道名称，注册时间模糊查询
		else if((phone==null||"".equals(phone))&&(sourceName!=null||!"".equals(sourceName))&&(registrationTimeStart!=null||!"".equals(registrationTimeStart))&&(registrationTimeEnd!=null||!"".equals(registrationTimeEnd))) {
			System.out.println("第七个if");
			int totalCount=userMapper.pageCountBySourceNameAndRegistrationtime(sourceName,registrationTimeStart,registrationTimeEnd);//该方法是通过渠道名称和注册时间模糊查询出用户总数量
			pageUtil=new PageUtil(page, totalCount);
	    	if(page<1) {
	    		page=1;
	    	}
	    	else if(page>pageUtil.getTotalPageCount()) {
	    		page=pageUtil.getTotalPageCount();
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil=new PageUtil(pages, totalCount);
	    	list=userMapper.queryBySourceNameAndRegistrationtime(sourceName,registrationTimeStart,registrationTimeEnd, pageUtil.getPage());
		}
		//通过注册时间模糊查询
		else if((phone==null||"".equals(phone))&&(sourceName==null||"".equals(sourceName))&&(registrationTimeStart!=null||!"".equals(registrationTimeStart))&&(registrationTimeEnd!=null||!"".equals(registrationTimeEnd))) {
			System.out.println("第八个if");
	    	list1=sourceMapper.queryAll();//查询出所有的渠道信息，将渠道名称渲染到下拉框中
			int totalCount=userMapper.pageCountByRegistrationtime(registrationTimeStart,registrationTimeEnd);//该方法是通过注册时间模糊查询出用户总数量
			pageUtil=new PageUtil(page, totalCount);
	    	if(page<1) {
	    		page=1;
	    	}
	    	else if(page>pageUtil.getTotalPageCount()) {
	    		page=pageUtil.getTotalPageCount();
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil=new PageUtil(pages, totalCount);
	    	list=userMapper.queryByRegistrationtime(registrationTimeStart,registrationTimeEnd, pageUtil.getPage());
	    }
		
		HashMap<String, Object> map=new HashMap<>();
		map.put("listSource", list1);
		map.put("listUser", list);
		map.put("pageUtil", pageUtil);
		return map;
	}
	
	//后台管理---根据用户id查询出按钮足迹  商品足迹和贷款分类足迹    将其封装到按钮足迹实体类中，含分页
	public List<ButtonFootprint> queryAllButton(Integer id,Integer page){
		List<ButtonFootprint> list=userMapper.queryAllButton(id,page);
		return list;
	}
}

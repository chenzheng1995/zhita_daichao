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
import com.zhita.util.Timestamps;

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
	public int pageCount(String company) {
		int count=userMapper.pageCount(company);
		return count;
	}

	//后台管理---根据用户id查询出按钮足迹  商品足迹和贷款分类足迹的总数量
	public int pageCountThreeFootprint(Integer id) {
		int count=userMapper.pageCountThreeFootprint(id);
		return count;
	}
	//后台管理---查询出用户表所有信息，含分页
	public List<User> queryAllUser(String company,Integer page,Integer pagesize){
		List<User> list=userMapper.queryAllUser(company,page,pagesize);
		return list;
	}
	
	//后台管理---查询出用户表所有信息，不含分页
	public List<User> queryAllUser1(String company){
		List<User> list=userMapper.queryAllUser1(company);
		return list;
	}

	//后台管理---通过传过来的值，进行多种情况的模糊查询，含分页
	public Map<String, Object> queryByLike(String phone,String sourceName,String registrationTimeStart,String registrationTimeEnd,String company,Integer page) {
		List<Source> list1=null;
		List<User> list=null;
		PageUtil pageUtil=null;
		
		//所有条件都为空
		if((phone==null||"".equals(phone))&&(sourceName==null||"".equals(sourceName))&&(registrationTimeStart==null||"".equals(registrationTimeStart))&&(registrationTimeEnd==null||"".equals(registrationTimeEnd))) {
			System.out.println("第一个if");
	    	list1=sourceMapper.queryAll(company);//查询出所有的渠道信息，将渠道名称渲染到下拉框中
	    	
	    	List<User> list2=userMapper.queryAllPhone(company);//查询出所有用户的手机号
	    	
	    	Timestamps times=new Timestamps();//创建时间戳实体类对象
			long todayZeroTimestamps = times.getTodayZeroTimestamps(); //今天0点的时间戳
			long tomorrowZeroTimestamps = todayZeroTimestamps+86400000; //明天0点的时间戳
	    	
	    	for (int i = 0; i < list2.size(); i++) {
				//System.out.println("userid:"+list.get(i).getId()+"dayfen:"+userService.queryAmountByUserId(list.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps)));
				//将用户的当日分发系数字段进行修改
	    		userMapper.upaDayFen(userMapper.queryAmountByUserId(list2.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),company), list2.get(i).getPhone());
			}
	    	
	       	int totalCount=userMapper.pageCount(company);//该方法是查询出用户表总数量
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
	    	list=userMapper.queryAllUser(company,pageUtil.getPage(),pageUtil.getPageSize());
	    
	    	HashMap<String, Object> map=new HashMap<>();
	    	map.put("listSource", list1);
	    	map.put("listUser",list);
	    	map.put("pageutil",pageUtil);
			return map;
		}

		//通过电话模糊查询
		else if((phone!=null||!"".equals(phone))&&(sourceName==null||"".equals(sourceName))&&(registrationTimeStart==null||"".equals(registrationTimeStart))&&(registrationTimeEnd==null||"".equals(registrationTimeEnd))) {
			System.out.println("第二个if");
	    	list1=sourceMapper.queryAll(company);//查询出所有的渠道信息，将渠道名称渲染到下拉框中
	    	
	    	List<User> list2=userMapper.queryAllPhoneByPhoneLike(phone, company);//通过手机号模糊查询出手机号
	    	
	    	Timestamps times=new Timestamps();//创建时间戳实体类对象
			long todayZeroTimestamps = times.getTodayZeroTimestamps(); //今天0点的时间戳
			long tomorrowZeroTimestamps = todayZeroTimestamps+86400000; //明天0点的时间戳
	    	
	    	for (int i = 0; i < list2.size(); i++) {
				//System.out.println("userid:"+list.get(i).getId()+"dayfen:"+userService.queryAmountByUserId(list.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps)));
				//将用户的当日分发系数字段进行修改
	    		userMapper.upaDayFen(userMapper.queryAmountByUserId(list2.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),company), list2.get(i).getPhone());
			}
	    	
			int totalCount=userMapper.pageCountByPhone(phone,company);//该方法是通过手机号模糊查询出用户总数量
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
	    	list=userMapper.queryByPhone(phone,company,pageUtil.getPage(),pageUtil.getPageSize());
		}
		//通过电话和渠道名称模糊查询
		else if((phone!=null||!"".equals(phone))&&(sourceName!=null||!"".equals(sourceName))&&(registrationTimeStart==null||"".equals(registrationTimeStart))&&(registrationTimeEnd==null||"".equals(registrationTimeEnd))) {
			System.out.println("第三个if");
			
	    	List<User> list2=userMapper.queryAllPhoneByPhoneSouNameLike(phone, sourceName, company);//通过电话和渠道名称模糊查询手机号
	    	
	    	Timestamps times=new Timestamps();//创建时间戳实体类对象
			long todayZeroTimestamps = times.getTodayZeroTimestamps(); //今天0点的时间戳
			long tomorrowZeroTimestamps = todayZeroTimestamps+86400000; //明天0点的时间戳
	    	
	    	for (int i = 0; i < list2.size(); i++) {
				//System.out.println("userid:"+list.get(i).getId()+"dayfen:"+userService.queryAmountByUserId(list.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps)));
				//将用户的当日分发系数字段进行修改
	    		userMapper.upaDayFen(userMapper.queryAmountByUserId(list2.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),company), list2.get(i).getPhone());
			}
	 		int totalCount=userMapper.pageCountByPhoneAndSourceName(phone,sourceName,company);//该方法是通过电话和渠道名称模糊查询出用户总数量
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
	    	list=userMapper.queryByPhoneAndSourceName(phone,sourceName,company,pageUtil.getPage(),pageUtil.getPageSize());
	    }
		//通过电话和注册时间模糊查询
		else if((phone!=null||!"".equals(phone))&&(sourceName==null||"".equals(sourceName))&&(registrationTimeStart!=null||!"".equals(registrationTimeStart))&&(registrationTimeEnd!=null||!"".equals(registrationTimeEnd))) {
			System.out.println("第四个if");
			
	    	List<User> list2=userMapper.queryAllPhoneByPhoneTimeLike(phone, registrationTimeStart, registrationTimeEnd, company);//通过电话和注册时间模糊查询手机号
	    	
	    	Timestamps times=new Timestamps();//创建时间戳实体类对象
			long todayZeroTimestamps = times.getTodayZeroTimestamps(); //今天0点的时间戳
			long tomorrowZeroTimestamps = todayZeroTimestamps+86400000; //明天0点的时间戳
	    	
	    	for (int i = 0; i < list2.size(); i++) {
				//System.out.println("userid:"+list.get(i).getId()+"dayfen:"+userService.queryAmountByUserId(list.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps)));
				//将用户的当日分发系数字段进行修改
	    		userMapper.upaDayFen(userMapper.queryAmountByUserId(list2.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),company), list2.get(i).getPhone());
			}
			
	    	list1=sourceMapper.queryAll(company);//查询出所有的渠道信息，将渠道名称渲染到下拉框中
			int totalCount=userMapper.pageCountByPhoneAndRegistrationtime(phone,registrationTimeStart,registrationTimeEnd,company);//该方法是通过电话和注册时间模糊查询出用户总数量
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
	    	list=userMapper.queryByPhoneAndRegistrationtime(phone,registrationTimeStart,registrationTimeEnd,pageUtil.getPage(),pageUtil.getPageSize());
	    }
		//通过电话，渠道名称，注册时间模糊查询
		else if((phone!=null||!"".equals(phone))&&(sourceName!=null||!"".equals(sourceName))&&(registrationTimeStart!=null||!"".equals(registrationTimeStart))&&(registrationTimeEnd!=null||!"".equals(registrationTimeEnd))) {
			System.out.println("第五个if");
	    	List<User> list2=userMapper.queryAllPhoneByPhoneSouNameTimeLike(phone, sourceName, registrationTimeStart, registrationTimeEnd, company);//通过电话  渠道名称  注册时间模糊查询手机号
	    	
	    	Timestamps times=new Timestamps();//创建时间戳实体类对象
			long todayZeroTimestamps = times.getTodayZeroTimestamps(); //今天0点的时间戳
			long tomorrowZeroTimestamps = todayZeroTimestamps+86400000; //明天0点的时间戳
	    	
	    	for (int i = 0; i < list2.size(); i++) {
				//System.out.println("userid:"+list.get(i).getId()+"dayfen:"+userService.queryAmountByUserId(list.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps)));
				//将用户的当日分发系数字段进行修改
	    		userMapper.upaDayFen(userMapper.queryAmountByUserId(list2.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),company), list2.get(i).getPhone());
			}
			
			int totalCount=userMapper.pageCountByPhoneSourceNameAndRegistrationtime(phone,sourceName,registrationTimeStart,registrationTimeEnd,company);//该方法是通过电话、渠道名称和注册时间模糊查询出用户总数量
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
	    	list=userMapper.queryByPhoneSourceNameAndRegistrationtime(phone,sourceName,registrationTimeStart,registrationTimeEnd,company,pageUtil.getPage(),pageUtil.getPageSize());
	    	
		}
		//通过渠道名称模糊查询
		else if((phone==null||"".equals(phone))&&(sourceName!=null||!"".equals(sourceName))&&(registrationTimeStart==null||"".equals(registrationTimeStart))&&(registrationTimeEnd==null||"".equals(registrationTimeEnd))) {
			System.out.println("第六个if");
	    	List<User> list2=userMapper.queryAllPhoneBySouNameLike(sourceName,company);//通过 渠道名称 模糊查询手机号
	    	
	    	Timestamps times=new Timestamps();//创建时间戳实体类对象
			long todayZeroTimestamps = times.getTodayZeroTimestamps(); //今天0点的时间戳
			long tomorrowZeroTimestamps = todayZeroTimestamps+86400000; //明天0点的时间戳
	    	
	    	for (int i = 0; i < list2.size(); i++) {
				//System.out.println("userid:"+list.get(i).getId()+"dayfen:"+userService.queryAmountByUserId(list.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps)));
				//将用户的当日分发系数字段进行修改
	    		userMapper.upaDayFen(userMapper.queryAmountByUserId(list2.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),company), list2.get(i).getPhone());
			}
			
			int totalCount=userMapper.pageCountBySourceName(sourceName,company);//该方法是通过渠道名称模糊查询出用户总数量
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
	    	list=userMapper.queryBySourceName(sourceName,company,pageUtil.getPage(),pageUtil.getPageSize());
		}
		//通过渠道名称，注册时间模糊查询
		else if((phone==null||"".equals(phone))&&(sourceName!=null||!"".equals(sourceName))&&(registrationTimeStart!=null||!"".equals(registrationTimeStart))&&(registrationTimeEnd!=null||!"".equals(registrationTimeEnd))) {
			System.out.println("第七个if");
	    	List<User> list2=userMapper.queryAllPhoneBySouNameTimeLike(sourceName, registrationTimeStart, registrationTimeEnd, company);//通过 渠道名称   注册时间模糊查询手机号
	    	
	    	Timestamps times=new Timestamps();//创建时间戳实体类对象
			long todayZeroTimestamps = times.getTodayZeroTimestamps(); //今天0点的时间戳
			long tomorrowZeroTimestamps = todayZeroTimestamps+86400000; //明天0点的时间戳
	    	
	    	for (int i = 0; i < list2.size(); i++) {
				//System.out.println("userid:"+list.get(i).getId()+"dayfen:"+userService.queryAmountByUserId(list.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps)));
				//将用户的当日分发系数字段进行修改
	    		userMapper.upaDayFen(userMapper.queryAmountByUserId(list2.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),company), list2.get(i).getPhone());
			}
			
			int totalCount=userMapper.pageCountBySourceNameAndRegistrationtime(sourceName,registrationTimeStart,registrationTimeEnd,company);//该方法是通过渠道名称和注册时间模糊查询出用户总数量
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
	    	list=userMapper.queryBySourceNameAndRegistrationtime(sourceName,registrationTimeStart,registrationTimeEnd, pageUtil.getPage(),pageUtil.getPageSize());
		}
		//通过注册时间模糊查询
		else if((phone==null||"".equals(phone))&&(sourceName==null||"".equals(sourceName))&&(registrationTimeStart!=null||!"".equals(registrationTimeStart))&&(registrationTimeEnd!=null||!"".equals(registrationTimeEnd))) {
			System.out.println("第八个if");
	    	List<User> list2=userMapper.queryAllPhoneByTimeLike(registrationTimeStart, registrationTimeEnd, company);//通过 注册时间模糊查询手机号
	    	
	    	Timestamps times=new Timestamps();//创建时间戳实体类对象
			long todayZeroTimestamps = times.getTodayZeroTimestamps(); //今天0点的时间戳
			long tomorrowZeroTimestamps = todayZeroTimestamps+86400000; //明天0点的时间戳
	    	
	    	for (int i = 0; i < list2.size(); i++) {
				//System.out.println("userid:"+list.get(i).getId()+"dayfen:"+userService.queryAmountByUserId(list.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps)));
				//将用户的当日分发系数字段进行修改
	    		userMapper.upaDayFen(userMapper.queryAmountByUserId(list2.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),company), list2.get(i).getPhone());
			}
			
	    	list1=sourceMapper.queryAll(company);//查询出所有的渠道信息，将渠道名称渲染到下拉框中
			int totalCount=userMapper.pageCountByRegistrationtime(registrationTimeStart,registrationTimeEnd,company);//该方法是通过注册时间模糊查询出用户总数量
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
	    	list=userMapper.queryByRegistrationtime(registrationTimeStart,registrationTimeEnd,company,pageUtil.getPage(),pageUtil.getPageSize());
	    }
		
		HashMap<String, Object> map=new HashMap<>();
		map.put("listSource", list1);
		map.put("listUser", list);
		map.put("pageUtil", pageUtil);
		return map;
	}
	
	//后台管理---根据用户id查询出按钮足迹  商品足迹和贷款分类足迹    将其封装到按钮足迹实体类中，含分页
	public List<ButtonFootprint> queryAllButton(Integer id,Integer page,Integer pagesize){
		List<ButtonFootprint> list=userMapper.queryAllButton(id,page,pagesize);
		return list;
	}

	@Override
	public String getProgramQrCode(String scene) {
		String programQrCode = userMapper.getProgramQrCode(scene);
		return programQrCode;
	}

	@Override
	public int setProgramQrCode(String scene, String qrurl) {
		int number = userMapper.setProgramQrCode(scene,qrurl);
		return number;
	}
	//后台管理---根据用户id查询出当前用户   当天 在商品足迹表的数量
	public int queryAmountByUserId(Integer id,String dayStateTime,String dayEndTime,String company) {
		int count=userMapper.queryAmountByUserId(id,dayStateTime,dayEndTime,company);
		return count;
	}
	//后台管理---根据用户电话    更新用户表里的当日分发系数字段
	public int upaDayFen(Integer dayfen,String phone) {
		int sum=userMapper.upaDayFen(dayfen, phone);
		return sum;
	}
	//后台管理---查询出所有的用户手机号
	public List<User> queryAllPhone(String company){
		List<User> list=userMapper.queryAllPhone(company);
		return list;
	}

}

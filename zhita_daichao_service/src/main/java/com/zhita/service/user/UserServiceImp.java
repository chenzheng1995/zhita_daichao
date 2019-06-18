package com.zhita.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.SourceMapper;
import com.zhita.dao.manage.UserMapper;
import com.zhita.model.manage.ButtonFootprint;
import com.zhita.model.manage.User;
import com.zhita.util.PageUtil;
import com.zhita.util.PhoneDeal;
import com.zhita.util.Timestamps;
import com.zhita.util.TuoMinUtil;

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
	public List<User> ByLikeQuery(String phone,String sourceName,String registrationTimeStart,String registrationTimeEnd,String[] company,Integer page) {
		List<User> list=userMapper.qeuryAllUserByLike(phone, sourceName, registrationTimeStart, registrationTimeEnd, company, page);
		return list;
	}
	
	/*//后台管理---通过传过来的值，进行多种情况的模糊查询，含分页
	public Map<String, Object> ByLikeQuery(String phone,String[] sourceName,String registrationTimeStart,String registrationTimeEnd,String[] company,Integer page) {
		List<User> list2=null;
		List<User> list=null;
		PageUtil pageUtil=null;
		
		//手机号为空  渠道  时间为空  公司
		if((phone==null||"".equals(phone))&&(sourceName!=null)&&(registrationTimeStart==null||"".equals(registrationTimeStart))&&(registrationTimeEnd==null||"".equals(registrationTimeEnd))&&(company!=null)) {
			System.out.println("第一个if");
			
	    	//list2=userMapper.queryAllPhoneBySouNameLike(sourceName,company);//通过 渠道名称 ,公司名模糊查询手机号
	    	
	    	Timestamps times=new Timestamps();//创建时间戳实体类对象
			long todayZeroTimestamps = times.getTodayZeroTimestamps(); //今天0点的时间戳
			long tomorrowZeroTimestamps = todayZeroTimestamps+86400000; //明天0点的时间戳
	    	
			for (int j = 0; j < company.length; j++) {
				for (int i = 0; i < list2.size(); i++) {
	    			if(company.length==1) {
	    				//将用户的当日分发系数字段进行修改
	    				userMapper.upaDayFen(userMapper.queryAmountByUserId(list2.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),company[0]), list2.get(i).getPhone(),company[0]);
	    			}
	    			if(company.length>1) {
	    				//将用户的当日分发系数字段进行修改
	    				userMapper.upaDayFen(userMapper.queryAmountByUserId(list2.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),company[j]), list2.get(i).getPhone(),company[j]);
	    			}
				}
			}
			
			int totalCount=userMapper.pageCountBySourceName(sourceName,company);//该方法是通过渠道名称模糊查询出用户总数量
	    	pageUtil=new PageUtil(page,10,totalCount);
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
	    	
	    	for (int i = 0; i < list.size(); i++) {
	    		list.get(i).setDayfen(userMapper.queryAmountByUserId(list.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),list.get(i).getCompany()));
			}
	    	pageUtil=new PageUtil(page,10,totalCount);
		}
		
		//手机号为空  渠道  时间不为空   公司
		else if((phone==null||"".equals(phone))&&(sourceName!=null)&&(registrationTimeStart!=null||!"".equals(registrationTimeStart))&&(registrationTimeEnd!=null||!"".equals(registrationTimeEnd))&&(company!=null)) {
			System.out.println("第二个if");
			
	    	//list2=userMapper.queryAllPhoneBySouNameTimeLike(sourceName, registrationTimeStart, registrationTimeEnd, company);//通过渠道名称  注册时间,公司名模糊查询手机号
	    	
	    	Timestamps times=new Timestamps();//创建时间戳实体类对象
			long todayZeroTimestamps = times.getTodayZeroTimestamps(); //今天0点的时间戳
			long tomorrowZeroTimestamps = todayZeroTimestamps+86400000; //明天0点的时间戳
	    	
			for (int j = 0; j < company.length; j++) {
				for (int i = 0; i < list2.size(); i++) {
	    			if(company.length==1) {
	    				//将用户的当日分发系数字段进行修改
	    				userMapper.upaDayFen(userMapper.queryAmountByUserId(list2.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),company[0]), list2.get(i).getPhone(),company[0]);
	    			}
	    			if(company.length>1) {
	    				//将用户的当日分发系数字段进行修改
	    				userMapper.upaDayFen(userMapper.queryAmountByUserId(list2.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),company[j]), list2.get(i).getPhone(),company[j]);
	    			}
				}
			}
			
			int totalCount=userMapper.pageCountBySourceNameAndRegistrationtime(sourceName,registrationTimeStart,registrationTimeEnd,company);//该方法是通过渠道名称,注册时间,公司名模糊查询出用户总数量
	    	pageUtil=new PageUtil(page,10,totalCount);
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
	    	list=userMapper.queryBySourceNameAndRegistrationtime(sourceName,registrationTimeStart,registrationTimeEnd,company,pageUtil.getPage(),pageUtil.getPageSize());
	    	
	    	for (int i = 0; i < list.size(); i++) {
	    		list.get(i).setDayfen(userMapper.queryAmountByUserId(list.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),list.get(i).getCompany()));
			}
	    	pageUtil=new PageUtil(page,10,totalCount);
		}
		//手机号不为空   渠道   时间为空  公司
		else if((phone!=null||!"".equals(phone))&&(sourceName!=null)&&(registrationTimeStart==null||"".equals(registrationTimeStart))&&(registrationTimeEnd==null||"".equals(registrationTimeEnd))&&(company!=null)) {
			System.out.println("第三个if");
			
			PhoneDeal phoneDeal=new PhoneDeal();
			String phone1=phoneDeal.encryption(phone);//将传进来的手机号进行加密
			
	    	//list2=userMapper.queryAllPhoneByPhoneSouNameLike(phone1, sourceName, company);//通过手机号，渠道名称，公司名模糊查询手机号
	    	
	    	Timestamps times=new Timestamps();//创建时间戳实体类对象
			long todayZeroTimestamps = times.getTodayZeroTimestamps(); //今天0点的时间戳
			long tomorrowZeroTimestamps = todayZeroTimestamps+86400000; //明天0点的时间戳
	    	
			for (int j = 0; j < company.length; j++) {
				for (int i = 0; i < list2.size(); i++) {
	    			if(company.length==1) {
	    				//将用户的当日分发系数字段进行修改
	    				userMapper.upaDayFen(userMapper.queryAmountByUserId(list2.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),company[0]), list2.get(i).getPhone(),company[0]);
	    			}
	    			if(company.length>1) {
	    				//将用户的当日分发系数字段进行修改
	    				userMapper.upaDayFen(userMapper.queryAmountByUserId(list2.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),company[j]), list2.get(i).getPhone(),company[j]);
	    			}
				}
			}
			int totalCount=userMapper.pageCountByPhoneAndSourceName(phone1,sourceName,company);//该方法是通过手机号，渠道名称，公司名模糊查询出用户总数量
	    	pageUtil=new PageUtil(page,10,totalCount);
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
	    	list=userMapper.queryByPhoneAndSourceName(phone1,sourceName,company,pageUtil.getPage(),pageUtil.getPageSize());
	    	
	    	for (int i = 0; i < list.size(); i++) {
	    		list.get(i).setDayfen(userMapper.queryAmountByUserId(list.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),list.get(i).getCompany()));
			}
	    	pageUtil=new PageUtil(page,10,totalCount);
		}
		//手机号不为空    渠道   时间不为空  公司
		else if((phone!=null||!"".equals(phone))&&(sourceName!=null)&&(registrationTimeStart!=null||!"".equals(registrationTimeStart))&&(registrationTimeEnd!=null||!"".equals(registrationTimeEnd))&&(company!=null)) {
			System.out.println("第四个if");
			
			PhoneDeal phoneDeal=new PhoneDeal();
			String phone1=phoneDeal.encryption(phone);//将传进来的手机号进行加密
			
	    	//list2=userMapper.queryAllPhoneByPhoneSouNameTimeLike(phone1, sourceName, registrationTimeStart, registrationTimeEnd, company);//通过电话  渠道名称  注册时间,公司名模糊查询手机号
	    	
	    	Timestamps times=new Timestamps();//创建时间戳实体类对象
			long todayZeroTimestamps = times.getTodayZeroTimestamps(); //今天0点的时间戳
			long tomorrowZeroTimestamps = todayZeroTimestamps+86400000; //明天0点的时间戳
	    	
			for (int j = 0; j < company.length; j++) {
				for (int i = 0; i < list2.size(); i++) {
	    			if(company.length==1) {
	    				//将用户的当日分发系数字段进行修改
	    				userMapper.upaDayFen(userMapper.queryAmountByUserId(list2.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),company[0]), list2.get(i).getPhone(),company[0]);
	    			}
	    			if(company.length>1) {
	    				//将用户的当日分发系数字段进行修改
	    				userMapper.upaDayFen(userMapper.queryAmountByUserId(list2.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),company[j]), list2.get(i).getPhone(),company[j]);
	    			}
				}
			}
			
			int totalCount=userMapper.pageCountByPhoneSourceNameAndRegistrationtime(phone1,sourceName,registrationTimeStart,registrationTimeEnd,company);//该方法是通过电话、渠道名称和注册时间模糊查询出用户总数量
	    	pageUtil=new PageUtil(page,10,totalCount);
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
	    	list=userMapper.queryByPhoneSourceNameAndRegistrationtime(phone1,sourceName,registrationTimeStart,registrationTimeEnd,company,pageUtil.getPage(),pageUtil.getPageSize());
	    	
	    	for (int i = 0; i < list.size(); i++) {
	    		list.get(i).setDayfen(userMapper.queryAmountByUserId(list.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),list.get(i).getCompany()));
			}
	    	pageUtil=new PageUtil(page,10,totalCount);
		}
		TuoMinUtil tuoMinUtil=new TuoMinUtil();//将用户模块的手机号进行脱名
		PhoneDeal phoneDeal=new PhoneDeal();//将用户手机号进行解密
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setPhone(phoneDeal.decryption(list.get(i).getPhone()));
			String tuomingphone=tuoMinUtil.mobileEncrypt(list.get(i).getPhone());
    		list.get(i).setPhone(tuomingphone);
    		list.get(i).setRegistrationtime(Timestamps.stampToDate(list.get(i).getRegistrationtime()));
			list.get(i).setLoginTime(Timestamps.stampToDate(list.get(i).getLoginTime()));
		}
		for (int i = 0; i < list2.size(); i++) {
			list2.get(i).setPhone(phoneDeal.decryption(list2.get(i).getPhone()));
		}
		HashMap<String, Object> map=new HashMap<>();
		map.put("listSource", list2);
		map.put("listUser", list);
		map.put("pageutil", pageUtil);
		return map;
	}*/
	
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
	public int upaDayFen(Integer dayfen,String phone,String company) {
		int sum=userMapper.upaDayFen(dayfen, phone,company);
		return sum;
	}
	//后台管理---查询出所有的用户手机号
	public List<User> queryAllPhone(String company){
		List<User> list=userMapper.queryAllPhone(company);
		return list;
	}
  	//后台管理---根据userId和传过来的年  月  日(例如：2019-01-01——2019-01-20)  获取当前用户这个时间段的所有足迹时间
  	public List<String> queryDayFenByTime(Integer userId,String LikeTime1,String LikeTime2){
  		List<String> list=userMapper.queryDayFenByTime(userId, LikeTime1,LikeTime2);
  		return list;
  	}
  	//后台管理---根据userId和传过来的年  月  日(例如：2019-01-01) 获取当前用户这一天的足迹数量
  	public int queryAmount(Integer userId,String LikeTime1,String LikeTime2){
  		int amount=userMapper.queryAmount(userId, LikeTime1, LikeTime2);
  		return amount;
  	}

	@Override
	public String getUserPhone(int startId1) {
		String phone = userMapper.getUserPhone(startId1);
		return phone;
	}

	@Override
	public void updatePhone(int startId1, String newPhone) {
		userMapper.updatePhone(startId1,newPhone);
		
	}
}

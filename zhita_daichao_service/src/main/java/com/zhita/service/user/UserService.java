package com.zhita.service.user;

import java.util.List;
import java.util.Map;

import com.zhita.model.manage.ButtonFootprint;
import com.zhita.model.manage.User;

public interface UserService {

	Long getregistered();

	Long getdailyUsers(long todayZeroTimestamps, long tomorrowZeroTimestamps);

	Long getmonthlyUsers(long monthlyZeroTimestamps, long nextMonthlyZeroTimestamps);
	
	//后台管理---查询出用户表总数量
	public int pageCount();
	
	//后台管理---根据用户id查询出按钮足迹  商品足迹和贷款分类足迹的总数量
	public int pageCountThreeFootprint(Integer id);

	//后台管理---查询出用户表所有信息，含分页
	public List<User> queryAllUser(Integer page);
	
	//后台管理---通过传过来的值，进行多种情况的模糊查询，含分页
	public Map<String,Object> queryByLike(String phone,String sourceName,String registrationTimeStart,String registrationTimeEnd,Integer page);
	
	//后台管理---根据用户id查询出按钮足迹  商品足迹和贷款分类足迹    将其封装到按钮足迹实体类中，含分页
	public List<ButtonFootprint> queryAllButton(Integer id,Integer page);
	
}

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
	public int pageCount(String company);
	
	//后台管理---根据用户id查询出按钮足迹  商品足迹和贷款分类足迹的总数量
	public int pageCountThreeFootprint(Integer id);

	//后台管理---查询出用户表所有信息，含分页
	public List<User> queryAllUser(String company,Integer page,Integer pagesize);
	
	//后台管理---通过传过来的值，进行多种情况的模糊查询，含分页
	public Map<String,Object> queryByLike(String phone,String sourceName,String registrationTimeStart,String registrationTimeEnd,String company,Integer page);
	
	//后台管理---根据用户id查询出按钮足迹  商品足迹和贷款分类足迹    将其封装到按钮足迹实体类中，含分页
	public List<ButtonFootprint> queryAllButton(Integer id,Integer page,Integer pagesize);

	String getProgramQrCode(String scene);

	int setProgramQrCode(String scene, String qrurl);
	//后台管理---根据用户id查询出当前用户   当天 在商品足迹表的数量
	public int queryAmountByUserId(Integer id,String dayStateTime,String dayEndTime,String company);
	//后台管理---根据用户电话    更新用户表里的当日分发系数字段
	public int upaDayFen(Integer dayfen,String phone);
	//后台管理---查询出所有的用户手机号
	public List<User> queryAllPhone(String company);
	
}

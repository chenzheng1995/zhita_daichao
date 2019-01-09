package com.zhita.dao.manage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.ButtonFootprint;
import com.zhita.model.manage.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    Long getregistered();

	Long getdailyUsers(@Param("todayZeroTimestamps") long todayZeroTimestamps,@Param("tomorrowZeroTimestamps")long tomorrowZeroTimestamps);

	Long getmonthlyUsers(@Param("monthlyZeroTimestamps")long monthlyZeroTimestamps,@Param("nextMonthlyZeroTimestamps") long nextMonthlyZeroTimestamps);

	User findFormatByLoginName(@Param("phone")String phone,@Param("openId") String openId);

	int insertfootprint(@Param("phone")String phone,@Param("nickName") String nickName,@Param("openId") String openId,@Param("registrationTime") String registrationTime,@Param("loginStatus") String loginStatus);
	
	//后台管理---查询出用户表总数量
	int pageCount();
	
	//后台管理---通过手机号模糊查询用户总数量
	int pageCountByPhone(String phone);
	
	//后台管理---通过渠道名称模糊查询出用户总数量
	int pageCountBySourceName(String sourceName);
	
	//后台管理---通过注册时间模糊查询出用户总数量
	int pageCountByRegistrationtime(String registrationtime1,String registrationtime2);
	
	//后台管理---通过手机号和渠道名称模糊查询用户总数量
	int pageCountByPhoneAndSourceName(String phone,String sourceName);
	
	//后台管理---通过手机号和注册时间模糊查询用户总数量
	int pageCountByPhoneAndRegistrationtime(String phone,String registrationtime1,String registrationtime2);
	
	//后台管理---通过渠道名称和注册时间模糊查询用户总数量
	int pageCountBySourceNameAndRegistrationtime(String sourceName,String registrationtime1,String registrationtime2);
	
	//后台管理---通过手机号、渠道名称和注册时间模糊查询用户总数量
	int pageCountByPhoneSourceNameAndRegistrationtime(String phone,String sourceName,String registrationtime1,String registrationtime2);
	
	//后台管理---根据用户id查询出按钮足迹  商品足迹和贷款分类足迹的总数量
	int pageCountThreeFootprint(Integer id);
	
	//后台管理---查询出用户表所有信息，含分页
	List<User> queryAllUser(Integer page,Integer pagesize);
	
	//后台管理---通过手机号模糊查询，含分页
	List<User> queryByPhone(String phone,Integer page,Integer pagesize);
	
	//后台管理---通过渠道名称模糊查询，含分页
	List<User> queryBySourceName(String sourceName,Integer page,Integer pagesize);
	
	//后台管理---通过注册时间模糊查询，含分页
	List<User> queryByRegistrationtime(String registrationtime1,String registrationtime2,Integer page,Integer pagesize);
	
	//后台管理--根据手机号和渠道名称进行模糊查询，含分页
	List<User> queryByPhoneAndSourceName(String phone,String sourceName,Integer page,Integer pagesize);
	
	//后台管理--根据手机号和注册时间进行模糊查询，含分页
	List<User> queryByPhoneAndRegistrationtime(String phone,String registrationtime1,String registrationtime2,Integer page,Integer pagesize);
	
	//后台管理--根据渠道名称和注册时间进行模糊查询，含分页
	List<User> queryBySourceNameAndRegistrationtime(String sourceName,String registrationtime1,String registrationtime2,Integer page,Integer pagesize);
	
	//后台管理--根据手机号、渠道名称和注册时间进行模糊查询，含分页
	List<User> queryByPhoneSourceNameAndRegistrationtime(String phone,String sourceName,String registrationtime1,String registrationtime2,Integer page,Integer pagesize);
	
	//后台管理---根据用户id查询出按钮足迹  商品足迹和贷款分类足迹    将其封装到按钮足迹实体类中,含分页
	List<ButtonFootprint> queryAllButton(Integer id,Integer page,Integer pagesize);

	int updateloginStatus(@Param("loginStatus")String loginStatus, @Param("openId")String openId,@Param("phone") String phone);

	int getId(@Param("phone")String phone,@Param("openId") String openId);

	int updatelogOutStatus(@Param("loginStatus")String loginStatus,@Param("userId") int userId);

	String getLoginStatus(String openId);

	String getUserId(String openId);

	String getPhone(String openId);

}
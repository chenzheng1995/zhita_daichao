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

	User findphone(@Param("phone")String phone,@Param("company") String company);
	
	int insertfootprint(@Param("phone")String phone,@Param("nickName") String nickName,@Param("openId") String openId,@Param("registrationTime") String registrationTime,@Param("loginStatus") String loginStatus,@Param("company") String company,@Param("registrationType") String registrationType);
	
	//后台管理---查询出用户表总数量
	int pageCount(String company);
	
	//后台管理---通过手机号模糊查询用户总数量
	int pageCountByPhone(String phone,String company);
	
	//后台管理---通过渠道名称模糊查询出用户总数量
	int pageCountBySourceName(String[] sourceName,String[] company);
	
	//后台管理---通过注册时间模糊查询出用户总数量
	int pageCountByRegistrationtime(String registrationtime1,String registrationtime2,String company);
	
	//后台管理---通过手机号和渠道名称模糊查询用户总数量
	int pageCountByPhoneAndSourceName(String phone,String[] sourceName,String[] company);
	
	//后台管理---通过手机号和注册时间模糊查询用户总数量
	int pageCountByPhoneAndRegistrationtime(String phone,String registrationtime1,String registrationtime2,String company);
	
	//后台管理---通过渠道名称和注册时间模糊查询用户总数量
	int pageCountBySourceNameAndRegistrationtime(String[] sourceName,String registrationtime1,String registrationtime2,String[] company);
	
	//后台管理---通过手机号、渠道名称和注册时间模糊查询用户总数量
	int pageCountByPhoneSourceNameAndRegistrationtime(String phone,String[] sourceName,String registrationtime1,String registrationtime2,String[] company);
	
	//后台管理---根据用户id查询出按钮足迹  商品足迹和贷款分类足迹的总数量
	int pageCountThreeFootprint(Integer id);
	
	//后台管理---查询出用户表所有信息，含分页
	List<User> queryAllUser(String company,Integer page,Integer pagesize);
	
	//后台管理---查询出用户表所有信息，不含分页
	List<User> queryAllUser1(String company);
	
	//后台管理---通过手机号模糊查询，含分页
	List<User> queryByPhone(String phone,String company,Integer page,Integer pagesize);
	
	//后台管理---通过渠道名称模糊查询，含分页
	List<User> queryBySourceName(String[] sourceName,String[] company,Integer page,Integer pagesize);
	
	//后台管理---通过注册时间模糊查询，含分页
	List<User> queryByRegistrationtime(String registrationtime1,String registrationtime2,String company,Integer page,Integer pagesize);
	
	//后台管理--根据手机号和渠道名称进行模糊查询，含分页
	List<User> queryByPhoneAndSourceName(String phone,String[] sourceName,String[] company,Integer page,Integer pagesize);
	
	//后台管理--根据手机号和注册时间进行模糊查询，含分页
	List<User> queryByPhoneAndRegistrationtime(String phone,String registrationtime1,String registrationtime2,Integer page,Integer pagesize);
	
	//后台管理--根据渠道名称和注册时间进行模糊查询，含分页
	List<User> queryBySourceNameAndRegistrationtime(String[] sourceName,String registrationtime1,String registrationtime2,String[] company,Integer page,Integer pagesize);
	
	//后台管理--根据手机号、渠道名称和注册时间进行模糊查询，含分页
	List<User> queryByPhoneSourceNameAndRegistrationtime(String phone,String[] sourceName,String registrationtime1,String registrationtime2,String[] company,Integer page,Integer pagesize);
	
	//后台管理---根据用户id查询出按钮足迹  商品足迹和贷款分类足迹    将其封装到按钮足迹实体类中,含分页
	List<ButtonFootprint> queryAllButton(Integer id,Integer page,Integer pagesize);
	
	//后台管理---根据用户id查询出当前用户   当天 在商品足迹表的数量
	int queryAmountByUserId(Integer id,String dayStateTime,String dayEndTime,String company);
	
	//后台管理---根据用户电话    更新用户表里的当日分发系数字段
	int upaDayFen(Integer dayfen,String phone,String company);
	
	//后台管理---查询出所有的用户手机号
	List<User> queryAllPhone(String company);
	//后台管理   根据手机号和公司名查询出所有用户的手机号 
	List<User> queryAllPhoneByPhoneLike(String phone,String company);
	//后台管理   根据手机号,渠道名称和公司名查询出所有用户的手机号 
	List<User> queryAllPhoneByPhoneSouNameLike(String phone,String[] sourceName,String[] company);
	//后台管理   根据手机号,注册时间和公司名查询出所有用户的手机号 
	List<User> queryAllPhoneByPhoneTimeLike(String phone,String registrationtime1,String registrationtime2,String company);
	// 后台管理   根据手机号,渠道名称,注册时间和公司名查询出所有用户的手机号 
	List<User> queryAllPhoneByPhoneSouNameTimeLike(String phone,String[] sourceName,String registrationtime1,String registrationtime2,String[] company);
	//后台管理   根据渠道名称号和公司名查询出所有用户的手机号
	List<User> queryAllPhoneBySouNameLike(String[] sourceName,String[] company);
	//后台管理   根据渠道名称,注册时间和公司名查询出所有用户的手机号
	List<User> queryAllPhoneBySouNameTimeLike(String[] sourceName,String registrationtime1,String registrationtime2,String[] company);
	//后台管理   根据注册时间和公司名查询出所有用户的手机号 
	List<User> queryAllPhoneByTimeLike(String registrationtime1,String registrationtime2,String company);
  	//后台管理---根据userId和传过来的年  月  日(例如：2019-01-01——2019-01-20)  获取当前用户这个时间段的所有足迹时间
  	List<String> queryDayFenByTime(Integer userId,String LikeTime1,String LikeTime2);
  	//后台管理---根据userId和传过来的年  月  日(例如：2019-01-01) 获取当前用户这一天的足迹数量
  	int  queryAmount(Integer userId,String LikeTime1,String LikeTime2);

	int updateloginStatus(@Param("loginStatus")String loginStatus, @Param("openId")String openId,@Param("phone") String phone,@Param("company") String company,@Param("loginTime") String loginTime);

	int getId(@Param("phone")String phone,@Param("company") String company);

	int updatelogOutStatus(@Param("loginStatus")String loginStatus,@Param("userId") int userId,@Param("company") String company);

	String getLoginStatus(@Param("openId")String openId,@Param("company") String company);

	String getUserId(@Param("openId")String openId,@Param("company") String company);

	String getPhone(@Param("openId")String openId,@Param("company") String company);

	int updateStatus(@Param("loginStatus")String loginStatus,@Param("phone") String phone,@Param("company") String company,@Param("loginTime") String loginTime);

	int setAPPUser(@Param("phone")String phone,@Param("md5Pwd") String md5Pwd,@Param("sourceId") int sourceId,@Param("registrationTime") String registrationTime,@Param("loginStatus") String loginStatus,@Param("registrationType") String registrationType,@Param("company") String company);

	int updatePwd(@Param("phone")String phone,@Param("md5Pwd") String md5Pwd,@Param("company") String company);

	String getMd5pwd(@Param("phone")String phone,@Param("company") String company);

	String getProgramQrCode(String scene);

	int setProgramQrCode(@Param("scene")String scene,@Param("qrurl") String qrurl);

	int insertfootprint1(@Param("phone")String phone,@Param("nickName") String nickName,@Param("openId") String openId,@Param("registrationTime") String registrationTime,@Param("loginStatus") String loginStatus,@Param("company") String company,@Param("registrationType") String registrationType,@Param("fatherId") Integer fatherId);

	int updateloginStatus1(@Param("loginStatus")String loginStatus, @Param("openId")String openId,@Param("phone") String phone,@Param("company") String company,@Param("loginTime") String loginTime,@Param("fatherId") Integer fatherId);

	int updateStatus1(@Param("loginStatus")String loginStatus,@Param("phone") String phone,@Param("company") String company,@Param("loginTime") String loginTime,@Param("fatherId") Integer fatherId);

	String getPwd(int id);

	int setPwd(@Param("userId")int userId,@Param("md5Pwd") String md5Pwd);

	int insertUser(@Param("phone")String phone, @Param("loginStatus")String loginStatus, @Param("company")String company,@Param("registrationType") String registrationType,@Param("registrationTime") String registrationTime,@Param("sourceId") int sourceId);

	int setAPPUser1(@Param("phone")String phone,@Param("md5Pwd") String md5Pwd,@Param("merchantId") int merchantId,@Param("registrationTime") String registrationTime,@Param("loginStatus") String loginStatus,
			@Param("registrationType")String registrationType,@Param("company") String company,@Param("sonSourceName") String sonSourceName);

	int insertUser1(@Param("phone")String phone, @Param("loginStatus")String loginStatus, @Param("company")String company,@Param("registrationType") String registrationType,@Param("registrationTime") String registrationTime,@Param("merchantId") int merchantId,@Param("sonSourceName") String sonSourceName);

	String getUserPhone(int startId1);

	void updatePhone(@Param("startId1")int startId1,@Param("newPhone") String newPhone);

	//后台管理       通过传过来的值，进行多种情况的模糊查询，含分页 
	List<User> qeuryAllUserByLike(@Param("phone") String phone,@Param("sourceName") String sourceName,@Param("registrationTimeStart") String registrationTimeStart,@Param("registrationTimeEnd") String registrationTimeEnd,@Param("company") String[] company,Integer page);
	
	int setAPPUser2(@Param("phone")String phone,@Param("merchantId") int merchantId,@Param("registrationTime") String registrationTime,@Param("loginStatus") String loginStatus,
			@Param("registrationType")String registrationType,@Param("company") String company,@Param("sonSourceName") String sonSourceName);
	
}
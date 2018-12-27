package com.zhita.dao.manage;

import org.apache.ibatis.annotations.Param;

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

	int insertfootprint(@Param("phone")String phone,@Param("nickName") String nickName,@Param("openId") String openId,@Param("registrationTime") String registrationTime);
}
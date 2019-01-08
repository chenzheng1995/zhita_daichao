package com.zhita.dao.manage;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.ManageLogin;

public interface ManageLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManageLogin record);

    int insertSelective(ManageLogin record);

    ManageLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManageLogin record);

    int updateByPrimaryKey(ManageLogin record);

	ManageLogin findFormatByLoginName(String userName);

	int updateAdminLoginStatus(@Param("loginStatus")String loginStatus, @Param("phone")String phone,@Param("userName") String userName,@Param("registrationTime") String registrationTime);

	int getAdminId(@Param("phone")String phone,@Param("userName") String userName);

	int updateAdminLogOutStatus(@Param("loginStatus")String loginStatus,@Param("userId") int userId);
}
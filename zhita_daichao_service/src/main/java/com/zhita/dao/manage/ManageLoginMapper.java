package com.zhita.dao.manage;

import java.util.List;

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
	
	//后台管理---查询出管理登陆用户表一共有多少条数据
	int pageCountManageLogin();
	
	//后台管理---查询出所有用户信息——含用户信息  用户的角色  以及权限   含分页
	List<ManageLogin> queryManageLogin();
	
}
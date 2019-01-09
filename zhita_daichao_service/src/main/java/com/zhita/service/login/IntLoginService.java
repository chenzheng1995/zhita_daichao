package com.zhita.service.login;

import java.util.List;

import com.zhita.model.manage.ManageLogin;
import com.zhita.model.manage.User;

public interface IntLoginService {
	User findFormatByLoginName(String phone, String openId);

	int insertfootprint(String phone, String nickName, String openId, String registrationTime, String loginStatus);

	int updateloginStatus(String loginStatus, String openId, String phone);

	int getId(String phone, String openId);

	int updatelogOutStatus(String loginStatus, int userId);

	ManageLogin findFormatByLoginName(String userName);

	int updateAdminLoginStatus(String loginStatus, String phone, String userName, String registrationTime);

	int getAdminId(String phone, String userName);

	int updateAdminLogOutStatus(String loginStatus, int userId);

	String getLoginStatus(String openId);

	String getUserId(String openId);

	String getPhone(String openId);
	
	//后台管理---查询出管理登陆用户表一共有多少条数据
	public int pageCountManageLogin();
	//后台管理 ----查询出所有用户信息——含用户信息  用户的角色  以及权限   含分页
	public List<ManageLogin> queryManageLogin();

}

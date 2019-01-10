package com.zhita.service.login;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhita.dao.manage.ManageLoginMapper;
import com.zhita.dao.manage.UserMapper;
import com.zhita.model.manage.ManageLogin;
import com.zhita.model.manage.User;


@Service(value="loginServiceImp")
public class LoginServiceImp implements IntLoginService{

	@Autowired
	ManageLoginMapper manageLoginMapper;
	
	@Autowired
	UserMapper userMapper;

	public ManageLogin findFormatByLoginName(String userName) {
		ManageLogin manageLogin = manageLoginMapper.findFormatByLoginName(userName);
		return manageLogin;
	}

	@Override
	public User findphone(String phone) {
		User user = userMapper.findphone(phone);
		return user;
	}

	@Override
	public int insertfootprint(String phone, String nickName, String openId,String registrationTime,String loginStatus) {
		int number = userMapper.insertfootprint(phone, nickName, openId,registrationTime,loginStatus);
		return number;
	}

	@Override
	public int updateloginStatus(String loginStatus,String openId,String phone) {
		int number = userMapper.updateloginStatus(loginStatus,openId,phone);
		return number;
	}

	@Override
	public int getId(String phone) {
		int id = userMapper.getId(phone);
		return id;
	}

	@Override
	public int updatelogOutStatus(String loginStatus, int userId) {
		int number = userMapper.updatelogOutStatus(loginStatus,userId);
		return number;
	}

	@Override
	public int updateAdminLoginStatus(String loginStatus, String phone, String userName, String registrationTime) {
		int number = manageLoginMapper.updateAdminLoginStatus(loginStatus,phone,userName,registrationTime);
		return number;
	}

	@Override
	public int getAdminId(String phone, String userName) {
		int id = manageLoginMapper.getAdminId(phone,userName);
		return id;
	}

	@Override
	public int updateAdminLogOutStatus(String loginStatus, int userId) {
		int number = manageLoginMapper.updateAdminLogOutStatus(loginStatus,userId);
		return number;
	}

	@Override
	public String getLoginStatus(String openId) {
		String loginStatus = userMapper.getLoginStatus(openId);
		return loginStatus;
	}

	@Override
	public String getUserId(String openId) {
		String userId = userMapper.getUserId(openId);
		return userId;
	}

	@Override
	public String getPhone(String openId) {
		String phone = userMapper.getPhone(openId);
		return phone;
	}

	//后台管理---查询出管理登陆用户表一共有多少条数据
	public int pageCountManageLogin() {
		int count=manageLoginMapper.pageCountManageLogin();
		return count;
	}
	//后台管理----查询出所有用户信息——含用户信息  用户的角色  以及权限   含分页
	public List<ManageLogin> queryManageLogin(){
		List<ManageLogin> list=manageLoginMapper.queryManageLogin();
		return list;
	}

	@Override
	public int updateStatus(String loginStatus, String phone) {
		int num = userMapper.updateStatus(loginStatus,phone);
		return num;
	}

	public int setAPPUser(String phone, String md5Pwd, int sourceId, String registrationTime,String loginStatus) {
		int number = userMapper.setAPPUser(phone, md5Pwd, sourceId,registrationTime,loginStatus);
		return number;
	}

}

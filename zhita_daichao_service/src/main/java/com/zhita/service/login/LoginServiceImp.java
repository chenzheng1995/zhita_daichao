package com.zhita.service.login;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhita.dao.manage.ManageLoginMapper;
import com.zhita.dao.manage.UserMapper;
import com.zhita.model.manage.ManageLogin;
import com.zhita.model.manage.User;

@Transactional
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
	public User findFormatByLoginName(String phone, String openId) {
		User user = userMapper.findFormatByLoginName(phone,openId);
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
	public int getId(String phone, String openId) {
		int id = userMapper.getId(phone,openId);
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

}

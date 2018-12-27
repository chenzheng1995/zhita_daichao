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
	public int insertfootprint(String phone, String nickName, String openId,String registrationTime) {
		int number = userMapper.insertfootprint(phone, nickName, openId,registrationTime);
		return number;
	}
}

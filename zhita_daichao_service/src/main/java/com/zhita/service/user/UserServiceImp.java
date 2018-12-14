package com.zhita.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.UserMapper;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserMapper userMapper;

	public Integer getregistered() {
		Integer registered = userMapper.getregistered();
		return registered;
	}

}

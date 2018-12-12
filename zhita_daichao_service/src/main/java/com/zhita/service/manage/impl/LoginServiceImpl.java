package com.zhita.service.manage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.ManageLoginMapper;
import com.zhita.model.manage.ManageLogin;
import com.zhita.service.manage.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	ManageLoginMapper manageLoginMapper;

	@Override
	public ManageLogin findFormatByLoginName(String userName) {
		ManageLogin manageLogin = manageLoginMapper.findFormatByLoginName(userName);
		return manageLogin;
	}

}

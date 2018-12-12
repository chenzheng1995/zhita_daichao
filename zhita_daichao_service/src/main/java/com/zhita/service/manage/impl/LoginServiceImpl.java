package com.zhita.service.manage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.ManageLoginMapper;
import com.zhita.model.manage.ManageLogin;

@Service
public class LoginServiceImpl implements IntLoginService {
	@Autowired
	ManageLoginMapper manageLoginMapper;
	
	public ManageLogin findFormatByLoginName(String userName) {
		ManageLogin manageLogin = manageLoginMapper.findFormatByLoginName(userName);
		return manageLogin;
	}

}

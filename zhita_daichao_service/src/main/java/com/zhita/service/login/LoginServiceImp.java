package com.zhita.service.login;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhita.dao.manage.ManageLoginMapper;
import com.zhita.model.manage.ManageLogin;

@Transactional
@Service(value="loginServiceImp")
public class LoginServiceImp implements IntLoginService{
	@Resource(name="manageLoginMapper")
	private ManageLoginMapper manageLoginMapper;
	
	public ManageLoginMapper getManageLoginMapper() {
		return manageLoginMapper;
	}


	public void setManageLoginMapper(ManageLoginMapper manageLoginMapper) {
		this.manageLoginMapper = manageLoginMapper;
	}

	public ManageLogin findFormatByLoginName(String userName) {
		ManageLogin manageLogin = manageLoginMapper.findFormatByLoginName(userName);
		return manageLogin;
	}
}

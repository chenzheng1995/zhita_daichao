package com.zhita.service.vestbugversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.VestBagVersionMapper;

@Service
public class VestBugVersionServiceImp implements VestBugVersionService{

	@Autowired
	VestBagVersionMapper vestBagVersionMapper;

	@Override
	public String getVersion(Integer id) {
		String version = vestBagVersionMapper.getVersion(id);
		return version;
	}
}

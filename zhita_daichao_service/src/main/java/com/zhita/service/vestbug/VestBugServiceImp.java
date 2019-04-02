package com.zhita.service.vestbug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.VestBagMapper;

@Service
public class VestBugServiceImp implements VestBugService{

	@Autowired
	VestBagMapper vestBagMapper;

	@Override
	public Integer getVestBag(String vestBagName, String company) {
		Integer id = vestBagMapper.getVestBag(vestBagName,company);
		return id;
	}

	@Override
	public void setVestBag(String vestBagName, String company) {
		vestBagMapper.setVestBag(vestBagName,company);
		
	}

}

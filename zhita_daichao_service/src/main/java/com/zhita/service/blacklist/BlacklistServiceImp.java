package com.zhita.service.blacklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.BlacklistMapper;

@Service
public class BlacklistServiceImp implements BlacklistService{

	@Autowired
	BlacklistMapper blacklistMapper;
	
	@Override
	public int setblacklist(int userId, String name, String idCard, String phone, String creationTime) {
		int number = blacklistMapper.setblacklist(userId,name,idCard,phone,creationTime);
		return number;
	}

}

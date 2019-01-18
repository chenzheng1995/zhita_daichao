package com.zhita.service.buttonfootprint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.ButtonFootprintMapper;

@Service
public class ButtonFootprintServiceImp implements ButtonFootprintService{

	@Autowired
	ButtonFootprintMapper buttonFootprintMapper;
	
	@Override
	public int insertfootprint(String footprintName, String userId, long currentTimestamp,String company) {
		int number = buttonFootprintMapper.insertfootprint(footprintName,userId,currentTimestamp,company);
		return number;
	}

}

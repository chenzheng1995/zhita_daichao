package com.zhita.service.loanclassificationfootprint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.LoanClassificationFootprintMapper;

@Service
public class LCFootprintServiceImp implements LCFootprintService{
	
	@Autowired
	LoanClassificationFootprintMapper lcFootprintMapper;

	@Override
	public int insertfootprint(String footprintName, String userId, long currentTimestamp) {
		int number = lcFootprintMapper.insertfootprint(footprintName,userId,currentTimestamp);
		return number;
	}

}

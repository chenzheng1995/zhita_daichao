package com.zhita.service.loanclassificationcopy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.LoanClassificationCopyMapper;
import com.zhita.dao.manage.LoanClassificationFootprintMapper;
import com.zhita.model.manage.LoanClassification;
import com.zhita.model.manage.LoanClassificationCopy;

@Service
public class LCFicationCopyServiceImp implements LCFicationCopyService{
	
	@Autowired
	LoanClassificationCopyMapper loanClassificationCopyMapper;

	@Override
	public List<LoanClassification> queryLoanClass(String company) {
		List<LoanClassification> list = loanClassificationCopyMapper.queryLoanClass(company);
		return list;
	}


}

package com.zhita.service.type;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhita.dao.manage.LoanClassificationMapper;

@Transactional
@Service(value="typeServiceImp")
public class TypeServiceImp implements IntTypeService{
	@Resource(name="loanClassificationMapper")
	private LoanClassificationMapper loanClassificationMapper;

	public LoanClassificationMapper getLoanClassificationMapper() {
		return loanClassificationMapper;
	}

	public void setLoanClassificationMapper(LoanClassificationMapper loanClassificationMapper) {
		this.loanClassificationMapper = loanClassificationMapper;
	}
	
	
	
}

package com.zhita.service.registe;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhita.dao.manage.LoansBusinessesMapper;
import com.zhita.model.manage.LoansBusinesses;

@Transactional
@Service(value ="registeServiceImp")
public class RegisteServiceImp implements IntRegisteService{
	@Resource(name="loansBusinessesMapper")
    private LoansBusinessesMapper loansBusinessesMapper;

	public LoansBusinessesMapper getLoansBusinessesMapper() {
		return loansBusinessesMapper;
	}

	public void setLoansBusinessesMapper(LoansBusinessesMapper loansBusinessesMapper) {
		this.loansBusinessesMapper = loansBusinessesMapper;
	}
	
	//查询出所有贷款商家信息
	@Override
	public List<LoansBusinesses> queryAll() {
		List<LoansBusinesses> list=loansBusinessesMapper.queryAll();
		return list;
	}
	//通过商家分类查询出商家信息
	@Override
	public List<LoansBusinesses> queryByLoansClass(String businessClassification) {
		List<LoansBusinesses> list=loansBusinessesMapper.queryByLoansClass(businessClassification);
		return list;
	}

	
	
}

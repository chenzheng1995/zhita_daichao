package com.zhita.service.expenditurebigtype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.ExpenditureBigtypeMapper;
import com.zhita.model.manage.ExpenditureBigtype;

@Service
public class ExpenditureServiceImp implements IntexpenditurebigtypeService{
	@Autowired
	private ExpenditureBigtypeMapper expenditureBigtypeMapper;
	
    //查询出所有的大类别
    public  List<ExpenditureBigtype> queryAllBigtype(){
    	List<ExpenditureBigtype> list=expenditureBigtypeMapper.queryAllBigtype();
    	return list;
    }
}

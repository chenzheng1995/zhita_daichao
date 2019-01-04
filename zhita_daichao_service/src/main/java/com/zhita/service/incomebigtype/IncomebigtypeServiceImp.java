package com.zhita.service.incomebigtype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.IncomeBigtypeMapper;
import com.zhita.model.manage.IncomeBigtype;

@Service
public class IncomebigtypeServiceImp implements IntincomebigtypeService{
	@Autowired
	private IncomeBigtypeMapper incomeBigtypeMapper;
	
    //查询出所有的大类别
    public List<IncomeBigtype> queryAllBigtype(){
    	List<IncomeBigtype> list=incomeBigtypeMapper.queryAllBigtype();
    	return list;
    }
}

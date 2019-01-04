package com.zhita.service.incomesmalltype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.IncomeSmalltypeMapper;
import com.zhita.model.manage.IncomeSmalltype;

@Service
public class IncomesmalltypeServiceImp implements IntincomesmalltypeService{
	@Autowired
	private IncomeSmalltypeMapper incomeSmalltypeMapper;
	
	
    //通过传过来的收入大分类查询出该大分类下的小分类数据
    public List<IncomeSmalltype> querySmallTypeByBigType(String bigtype){
    	List<IncomeSmalltype> list=incomeSmalltypeMapper.querySmallTypeByBigType(bigtype);
    	return list;
    }
}

package com.zhita.service.expendituresmalltype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.ExpenditureSmalltypeMapper;
import com.zhita.model.manage.ExpenditureSmalltype;

@Service
public class ExpendituresmalltypeServiceImp implements IntexpendituresmalltypeService{
	@Autowired
	private ExpenditureSmalltypeMapper expenditureSmalltypeMapper;
	
    //通过传过来的收支出大分类查询出该大分类下的小分类数据
    public List<ExpenditureSmalltype> querySmallTypeByBigType(String bigtype){
    	List<ExpenditureSmalltype> list=expenditureSmalltypeMapper.querySmallTypeByBigType(bigtype);
    	return list;
    }
}

package com.zhita.service.incomesmalltype;

import java.util.List;

import com.zhita.model.manage.IncomeSmalltype;

public interface IntincomesmalltypeService {
	
    //通过传过来的收入大分类查询出该大分类下的小分类数据
    public List<IncomeSmalltype> querySmallTypeByBigType(String bigtype);
}
 
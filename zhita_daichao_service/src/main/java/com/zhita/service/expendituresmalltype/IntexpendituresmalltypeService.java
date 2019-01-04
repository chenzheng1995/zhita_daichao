package com.zhita.service.expendituresmalltype;

import java.util.List;

import com.zhita.model.manage.ExpenditureSmalltype;

public interface IntexpendituresmalltypeService {
	
    //通过传过来的收支出大分类查询出该大分类下的小分类数据
    public List<ExpenditureSmalltype> querySmallTypeByBigType(String bigtype);
}

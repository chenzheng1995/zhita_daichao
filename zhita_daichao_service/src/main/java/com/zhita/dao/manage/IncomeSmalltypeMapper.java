package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.IncomeSmalltype;

public interface IncomeSmalltypeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(IncomeSmalltype record);

    int insertSelective(IncomeSmalltype record);

    IncomeSmalltype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IncomeSmalltype record);

    int updateByPrimaryKey(IncomeSmalltype record);
    
    //通过传过来的收入大分类查询出该大分类下的小分类数据
    List<IncomeSmalltype> querySmallTypeByBigType(String bigtype);
}
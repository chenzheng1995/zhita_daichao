package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.ExpenditureSmalltype;

public interface ExpenditureSmalltypeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ExpenditureSmalltype record);

    int insertSelective(ExpenditureSmalltype record);

    ExpenditureSmalltype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExpenditureSmalltype record);

    int updateByPrimaryKey(ExpenditureSmalltype record);
    
    //通过传过来的支出大分类查询出该大分类下的小分类数据
    List<ExpenditureSmalltype> querySmallTypeByBigType(String bigtype);
}
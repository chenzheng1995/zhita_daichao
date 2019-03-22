package com.zhita.dao.manage;

import com.zhita.model.manage.TopUpAmount;

public interface TopUpAmountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TopUpAmount record);

    int insertSelective(TopUpAmount record);

    TopUpAmount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TopUpAmount record);

    int updateByPrimaryKey(TopUpAmount record);
}
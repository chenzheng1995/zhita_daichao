package com.zhita.dao.manage;

import com.zhita.model.manage.CreditCard;

public interface CreditCardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CreditCard record);

    int insertSelective(CreditCard record);

    CreditCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CreditCard record);

    int updateByPrimaryKey(CreditCard record);
}
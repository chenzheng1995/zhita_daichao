package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.CreditCard;

public interface CreditCardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CreditCard record);

    int insertSelective(CreditCard record);

    CreditCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CreditCard record);

    int updateByPrimaryKey(CreditCard record);
    //后台管理---查询信用卡所有的信息
    List<CreditCard> queryAllCard();
}
package com.zhita.service.card;

import java.util.List;

import com.zhita.model.manage.CreditCard;

public interface IntCardService {
	//后台管理---查询所有信用卡信息，含分页
	public List<CreditCard> queryAllCard(Integer page);
    //后台管理---用于获取总页数
    int pageCount();
    //后台管理---模糊查询信用卡信息,并且有分页功能
    List<CreditCard> queryByLike(String title,Integer page);
    //后台管理---添加信用卡信息
    int addAll(CreditCard record);
    //后台管理---通过信用卡id，查询信用卡信息
    CreditCard selectByPrimaryKey(Integer id);
    //后台管理---通过删除按钮，改变当前银行卡的假删除状态，将状态改为删除
    int upaFalseDel(Integer id);
}

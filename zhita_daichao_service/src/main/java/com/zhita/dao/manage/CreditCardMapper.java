package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.CreditCard;
import com.zhita.model.manage.LoanClassification;

public interface CreditCardMapper {
    int deleteByPrimaryKey(Integer id);
    
    int insert(CreditCard record);

    int insertSelective(CreditCard record);

    //后台管理---通过信用卡id，查询信用卡信息
    CreditCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CreditCard record);

    int updateByPrimaryKey(CreditCard record);
    
    //后台管理---通过传过来的信用卡对象，对当前对象进行修改保存
    int updateCreditCard(CreditCard creditCard);
    
    //后台管理---查询信用卡所有的信息,含分页
    List<CreditCard> queryAllCard(String company,Integer page,Integer pagesize);
    
    //后台管理---查询信用卡所有的信息,不含分页
    List<CreditCard> queryAllCard1(String company);
    
    //后台管理---用于获取总页数
    int pageCount(String company);
    
    //后台管理---用于获取模糊查询总页数
    int pageCountByLike(String title,String company);
    
    //后台管理---模糊查询信用卡信息,并且有分页功能
    List<CreditCard> queryByLike(String title,String company,Integer page,Integer pagesize);
    
    //后台管理---模糊查询信用卡信息,并且没有分页功能
    List<CreditCard> queryByLike1(String title,String company);
    
    //后台管理---添加信用卡信息
    int addAll(CreditCard record);
    
    //后台管理---通过删除按钮，改变当前银行卡的假删除状态，将状态改为删除
    int upaFalseDel(Integer id);
    
    //后台管理---修改信用卡状态为开启
    int upaStateOpen(Integer id);
    
    //后台管理---修改信用卡状态为关闭
    int upaStateClose(Integer id);

	String getCover(int id);
}
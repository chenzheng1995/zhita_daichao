package com.zhita.service.card;

import java.util.List;

import com.zhita.model.manage.Creditcard1Customvalue;

public interface IntCreditCard1CustomService {
    //后台管理---信用卡1——自定义值表   通过id查询对象
    public Creditcard1Customvalue selectByPrimaryKey(Integer id);

    //后台管理---信用卡1——自定义值表  修改保存
    public int updateByPrimaryKey(Creditcard1Customvalue record);
    
    //后台管理---信用卡1——自定义值表查询所有数据的数量
    public int queryAllCount();
    
    //后台管理---信用卡1——自定义值表查询所有   含分页
    public List<Creditcard1Customvalue> queryAllByPage(Integer page,Integer pagesize);
    
    //后台管理---信用卡1——自定义值表查询所有   不含分页
    public List<Creditcard1Customvalue> queryAll();
}

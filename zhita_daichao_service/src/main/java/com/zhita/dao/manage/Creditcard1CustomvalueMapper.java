package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.Creditcard1Customvalue;

public interface Creditcard1CustomvalueMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Creditcard1Customvalue record);

    int insertSelective(Creditcard1Customvalue record);
    
    //后台管理---信用卡1——自定义值表   通过id查询对象
    Creditcard1Customvalue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Creditcard1Customvalue record);
    
    //后台管理---信用卡1——自定义值表  修改保存
    int updateByPrimaryKey(Creditcard1Customvalue record);
    
    //后台管理---信用卡1——自定义值表查询所有数据的数量
    int queryAllCount();
    
    //后台管理---信用卡1——自定义值表查询所有   含分页
    List<Creditcard1Customvalue> queryAllByPage(Integer page,Integer pagesize);
    
    //后台管理---信用卡1——自定义值表查询所有   不含分页
    List<Creditcard1Customvalue> queryAll();
}
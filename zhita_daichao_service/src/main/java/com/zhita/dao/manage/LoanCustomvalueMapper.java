package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.LoanCustomvalue;

public interface LoanCustomvalueMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(LoanCustomvalue record);

    int insertSelective(LoanCustomvalue record);
    
    //后台管理---甲方商家——自定义值表   通过id查询对象
    LoanCustomvalue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoanCustomvalue record);
    
    //后台管理---甲方商家——自定义值表  修改保存
    int updateByPrimaryKey(LoanCustomvalue record);
    
    //后台管理---甲方商家——自定义值表查询所有数据的数量
    int queryAllCount();
    
    //后台管理---甲方商家——自定义值表查询所有   含分页
    List<LoanCustomvalue> queryAllByPage(Integer page,Integer pagesize);
    
    //后台管理---甲方商家——自定义值表查询所有   不含分页
    List<LoanCustomvalue> queryAll();
}
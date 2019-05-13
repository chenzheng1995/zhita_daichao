package com.zhita.service.registe;

import java.util.List;

import com.zhita.model.manage.LoanCustomvalue;

public interface IntRegisteCustomvalueService {
	
    //后台管理---甲方商家——自定义值表查询所有数据的数量
    public int queryAllCount();
    
	//后台管理---甲方商家——自定义值表查询所有  含分页
   public List<LoanCustomvalue> queryAllByPage(Integer page,Integer pagesize);
   
   //后台管理---甲方商家——自定义值表查询所有   不含分页
   public List<LoanCustomvalue> queryAll();
   
   //后台管理---甲方商家——自定义值表   通过id查询对象
   public LoanCustomvalue selectByPrimaryKey(Integer id);
   
   //后台管理---甲方商家——自定义值表  修改保存
   public int updateByPrimaryKey(LoanCustomvalue record);
}

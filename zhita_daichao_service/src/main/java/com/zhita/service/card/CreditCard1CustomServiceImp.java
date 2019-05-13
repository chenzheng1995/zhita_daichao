package com.zhita.service.card;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.Creditcard1CustomvalueMapper;
import com.zhita.dao.manage.LoanCustomvalueMapper;
import com.zhita.model.manage.Creditcard1Customvalue;

@Service
public class CreditCard1CustomServiceImp implements IntCreditCard1CustomService{
	@Autowired
	private Creditcard1CustomvalueMapper creditcard1CustomvalueMapper;
	
	//后台管理---信用卡1——自定义值表   通过id查询对象
    public Creditcard1Customvalue selectByPrimaryKey(Integer id){
    	Creditcard1Customvalue creditcard1Customvalue=creditcard1CustomvalueMapper.selectByPrimaryKey(id);
    	return creditcard1Customvalue;
    }

    //后台管理---信用卡1——自定义值表  修改保存
   public int updateByPrimaryKey(Creditcard1Customvalue record){
	   int sum=creditcard1CustomvalueMapper.updateByPrimaryKey(record);
	   return sum;
   }
    
    //后台管理---信用卡1——自定义值表查询所有数据的数量
    public int queryAllCount(){
    	int count=creditcard1CustomvalueMapper.queryAllCount();
    	return count;
    }
    
    //后台管理---信用卡1——自定义值表查询所有   含分页
    public List<Creditcard1Customvalue> queryAllByPage(Integer page,Integer pagesize){
    	List<Creditcard1Customvalue> list=creditcard1CustomvalueMapper.queryAllByPage(page,pagesize);
    	return list;
    }
    
    //后台管理---信用卡1——自定义值表查询所有   不含分页
    public List<Creditcard1Customvalue> queryAll(){
    	List<Creditcard1Customvalue> list=creditcard1CustomvalueMapper.queryAll();
    	return list;
    }
}

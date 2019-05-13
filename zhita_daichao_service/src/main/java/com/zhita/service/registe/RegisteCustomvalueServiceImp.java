package com.zhita.service.registe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.LoanCustomvalueMapper;
import com.zhita.model.manage.LoanCustomvalue;

@Service
public class RegisteCustomvalueServiceImp implements IntRegisteCustomvalueService{
	@Autowired
	private LoanCustomvalueMapper loanCustomvalueMapper;
	
    //后台管理---甲方商家——自定义值表查询所有数据的数量
    public int queryAllCount(){
    	int count=loanCustomvalueMapper.queryAllCount();
    	return count;
    }
	
	//后台管理---甲方商家——自定义值表查询所有  含分页
    public List<LoanCustomvalue> queryAllByPage(Integer page,Integer pagesize){
    	List<LoanCustomvalue> list=loanCustomvalueMapper.queryAllByPage(page,pagesize);
    	return list;
    }
    
    //后台管理---甲方商家——自定义值表查询所有   不含分页
    public List<LoanCustomvalue> queryAll(){
    	List<LoanCustomvalue> list=loanCustomvalueMapper.queryAll();
    	return list;
    }
    
    //后台管理---甲方商家——自定义值表   通过id查询对象
    public LoanCustomvalue selectByPrimaryKey(Integer id){
    	LoanCustomvalue loanCustomvalue=loanCustomvalueMapper.selectByPrimaryKey(id);
    	return loanCustomvalue;
    }
    
    //后台管理---甲方商家——自定义值表  修改保存
    public int updateByPrimaryKey(LoanCustomvalue record){
    	int sum=loanCustomvalueMapper.updateByPrimaryKey(record);
    	return sum;
    }
}

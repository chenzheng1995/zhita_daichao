package com.zhita.service.card;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.CreditCardTypeMapper;
import com.zhita.model.manage.CreditCard1;
import com.zhita.model.manage.CreditCardType;

//完全复制贷款分类TypeServiceImp
@Service
public class CreditCardTypeServiceImp implements IntCreditCardTypeService{
	@Autowired
	private CreditCardTypeMapper creditCardTypeMapper;
	
    //后台管理---查询贷款分类所有信息，含分页
	@Override
	public List<CreditCardType> queryAllPage(String company,Integer page,Integer pagesize) {
		List<CreditCardType> list=creditCardTypeMapper.queryAllPage(company,page,pagesize);
		return list;
	}
	
	@Override
    //后台管理---查询贷款分类所有信息，不含分页
    public List<CreditCardType> queryAllNoPage(String company){
		List<CreditCardType> list=creditCardTypeMapper.queryAllNoPage(company);
		return list;
    }
	
    //后台管理---用于获取总页数
	@Override
	public int pageCount(String company) {
		int count=creditCardTypeMapper.pageCount(company);
		return count;
	}
	
    //后台管理---用于获取模糊查询总页数
    public int pageCountByLike(String businessClassification,String company) {
    	int count=creditCardTypeMapper.pageCountByLike(businessClassification,company);
    	return count;
    }
    
    //后台管理---模糊查询贷款分类信息,并且有分页功能
	@Override
	public List<CreditCardType> queryByLike(String businessClassification,String company,Integer page,Integer pagesize) {
		List<CreditCardType> list=creditCardTypeMapper.queryByLike(businessClassification,company,page,pagesize);
		return list;
	}
	
    //后台管理---模糊查询贷款分类信息,没有分页功能
    public List<CreditCardType> queryByLike1(String businessClassification,String company){
    	List<CreditCardType> list=creditCardTypeMapper.queryByLike1(businessClassification,company);
		return list;
    }
    
    //后台管理---添加贷款分类信息
	@Override
	public int addAll(CreditCardType record) {
		int selnum=creditCardTypeMapper.addAll(record);
		return selnum;
	}
	
    //后台管理---通过主键id查询出贷款分类信息
	@Override
	public CreditCardType selectByPrimaryKey(Integer id) {
		CreditCardType loanClassification=creditCardTypeMapper.selectByPrimaryKey(id);
		return loanClassification;
	}
	
	//后台管理---  根据分类id   查询当前分类的图标
    public String queryIconById(Integer id){
    	String icon=creditCardTypeMapper.queryIconById(id);
    	return icon;
    }
	
    //后台管理---查询贷款分类所有信息，不含分页,做贷款商家添加功能时，下拉框取贷款分类的值时使用
    public List<CreditCardType> queryAllLoanCla(String company){
    	List<CreditCardType> list=creditCardTypeMapper.queryAllLoanCla(company);
    	return list;
    }
    
    //后台管理---通过传过来的贷款分类对象，对当前对象进行修改保存
    public int updateByPrimaryKey(CreditCardType record) {
    	int num=creditCardTypeMapper.updateByPrimaryKey(record);
    	return num;
    }
    //小程序---查询出所有的信用卡分类
    public List<CreditCardType> queryAll(String[] company){
    	List<CreditCardType> list=creditCardTypeMapper.queryAll(company);
    	return list;
    }
    
    //小程序---用于获取通过贷款分类的名称查询出贷款商家的个数
    public int pageCountByBusinessClassification(@Param("businessClassification")String businessClassification,@Param("company") String company){
    	int count=creditCardTypeMapper.pageCountByBusinessClassification(businessClassification, company);
    	return count;
    }
    
    //小程序---通过贷款分类的名称，查询出当前贷款分类下的所有贷款商家的信息,含分页
    public List<CreditCard1> queryLoanbusinByLoanClass(String businessClassification,Integer page,Integer pageSize){
    	List<CreditCard1> list=creditCardTypeMapper.queryLoanbusinByLoanClass(businessClassification, page, pageSize);
    	return list;
    }
}

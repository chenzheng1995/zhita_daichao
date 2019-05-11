package com.zhita.service.card;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.CreditCard1Mapper;
import com.zhita.model.manage.CreditCard1;


//完全复制商家RegisteServiceImp
@Service
public class Card1ServiceImp implements IntCard1Service{

	@Autowired
    private CreditCard1Mapper creditCard1Mapper;

	
/*	//小程序---查询出所有贷款商家信息
	@Cacheable(key="'mytest'", value="test")
	@Override
	public List<LoansBusinesses> queryAll(Integer page) {
		List<LoansBusinesses> list=loansBusinessesMapper.queryAll(page);
		System.out.println("打印则没有走缓存");
		return list;
	}
*/	
	//后台管理---查询贷款商家部分字段信息，含分页
  	public List<CreditCard1> queryAllAdmain(String company,Integer page,Integer pagesize) {
		List<CreditCard1> list=creditCard1Mapper.queryAllAdmain(company,page,pagesize);
		return list;
	}
	//后台管理---查询贷款商家部分字段信息，不含分页
  	public List<CreditCard1> queryAllAdmain1(String company) {
		List<CreditCard1> list=creditCard1Mapper.queryAllAdmain1(company);
		return list;
	}
  	//后台管理---查询贷款商家总条数
  	public int pageCount(String company) {
  		int count=creditCard1Mapper.pageCount(company);
  		return count;
  	}
  	
  	//后台管理---通过模糊查询的条件查询贷款商家总条数
  	public int pageCountByLike(String businessName,String company) {
  		int count=creditCard1Mapper.pageCountByLike(businessName,company);
  		return count;
  	}
    //后台管理---添加贷款商家信息
    public int insert(CreditCard1 record) {
    	int selnum=creditCard1Mapper.insert(record);
    	return selnum;
    }
  	//后台管理---通过商家名称模糊查询，并且有分页功能
  	public List<CreditCard1> queryByNameLike(String businessName,String company,Integer page,Integer pagesize){
  		List<CreditCard1> list=creditCard1Mapper.queryByNameLike(businessName,company,page,pagesize);
  		return list;
  	}
  	//后台管理---通过商家名称模糊查询，没有分页功能
  	public List<CreditCard1> queryByNameLike1(String businessName,String company){
  		List<CreditCard1> list=creditCard1Mapper.queryByNameLike1(businessName,company);
  		return list;
  	}
  	//后台管理---通过商家主键id修改假删除字段的值
	@Override
	public int upaFalseDel(Integer id) {
		int num=creditCard1Mapper.upaFalseDel(id);
		return num;
	}
	//后台管理---通过主键id查询出贷款商家信息
	@Override
	public CreditCard1 selectByPrimaryKey(Integer id) {
		CreditCard1 loansBusinesses=creditCard1Mapper.selectByPrimaryKey(id);
		return loansBusinesses;
	}
  	//后台管理---修改贷款商家状态为开启
  	public int upaStateOpen(Integer id) {
  		int num=creditCard1Mapper.upaStateOpen(id);
  		return num;
  	}
  	
  	//后台管理---修改贷款商家状态为关闭
  	public int upaStateClose(Integer id) {
  		int num=creditCard1Mapper.upaStateClose(id);
  		return num;
  	}

	@Override
	public void insertPath(String ossPath) {
		creditCard1Mapper.insertPath(ossPath);		
	}


  	//后台管理---查询出贷款商家表所有的商家名称，将所有的商家名称存入一个集合中
  	public List<String> queryAllBusinessName(String company){
  		List<String> list=creditCard1Mapper.queryAllBusinessName(company);
  		return list;
  	}
  	//后台管理---通过商家名称模糊查询出贷款商家表所有的商家名称，将所有的商家名称存入一个集合中
  	public List<String> queryAllBusinessNameByLike(String businessName,String company){
  		List<String> list=creditCard1Mapper.queryAllBusinessNameByLike(businessName,company);
  		return list;
  	}
  	//后台管理---根据商家名称更新被申请次数字段
  	public int upaApplicationNumber(Integer num,String businessName) {
  		int nums=creditCard1Mapper.upaApplicationNumber(num, businessName);
  		return nums;
  	}
    //后台管理---通过传过来的贷款商家对象，对当前对象进行修改保存
    public int updateLoansBusinesses(CreditCard1 loans) {
    	int num=creditCard1Mapper.updateLoansBusinesses(loans);
    	return num;
    }

	//后台管理---根据商品名称和传过来的年  月  日(例如：2019-01-01——2019-01-20)  获取当前甲方商家这个时间段的所有足迹时间
  	public List<String> queryTime(String businessName,String LikeTime,String LikeTime2){
  		List<String> list=creditCard1Mapper.queryTime(businessName, LikeTime,LikeTime2);
  		return list;
  	}

  	//后台管理---根据商品名称和传过来的年  月  日(例如：2019-01-01) 获取当前甲方商家这一天的足迹数量
  	public int  queryAmount(String businessName,String LikeTime1,String LikeTime2) {
  		int count=creditCard1Mapper.queryAmount(businessName, LikeTime1, LikeTime2);
  		return count;
  	}

	//后台管理---根据id  修改商家的排序字段
  	public int upaSortByLoanId(Integer sort,Integer id){
  		int num=creditCard1Mapper.upaSortByLoanId(sort, id);
  		return num;
  	}
  	
  	@Override
	public String getTrademark(Integer id) {
		String trademark = creditCard1Mapper.getTrademark(id);
		return trademark;
	}
  	//小程序
	@Override
	public int pageCount2(String company) {
		int num=creditCard1Mapper.pageCount2(company);
		return num;
	}
	//小程序---查询贷款商家部分字段信息，含分页
	@Override
	public List<CreditCard1> queryAllAdmainpro(Integer page, Integer pageSize, String company) {
		List<CreditCard1> list=creditCard1Mapper.queryAllAdmainpro(page, pageSize, company);
		return list;
	}

}

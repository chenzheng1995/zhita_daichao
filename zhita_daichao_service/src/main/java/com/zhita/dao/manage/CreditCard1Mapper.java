package com.zhita.dao.manage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.CreditCard1;
import com.zhita.model.manage.LoansBusinesses;

//完全复制商家mapper.java
public interface CreditCard1Mapper {
    int deleteByPrimaryKey(Integer id);
    
    //后台管理---添加贷款商家信息
    int insert(CreditCard1 record);

    int insertSelective(LoansBusinesses record);
    
    //后台管理---通过主键id查询出贷款商家信息
    CreditCard1 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoansBusinesses record);

    int updateByPrimaryKey(LoansBusinesses record);
    
    //后台管理---通过传过来的贷款商家对象，对当前对象进行修改保存
    int updateLoansBusinesses(CreditCard1 loans);
    
    String getTrademark(Integer id); 
   
  	//后台管理---查询贷款商家部分字段信息，含分页
  	List<CreditCard1> queryAllAdmain(String company,Integer page,Integer pagesize);
  	
  	//后台管理---查询贷款商家部分字段信息，不含分页
  	List<CreditCard1> queryAllAdmain1(String company);
  	
  	//后台管理---查询贷款商家总条数
  	int pageCount(String company);
  	
  	//后台管理---通过模糊查询的条件查询贷款商家总条数
  	int pageCountByLike(String businessName,String company);
  	
  	//后台管理---通过商家名称模糊查询，并且有分页功能
  	List<CreditCard1> queryByNameLike(String businessName,String company,Integer page,Integer pagesize);
  	
  	//后台管理---通过商家名称模糊查询，没有分页功能
  	List<CreditCard1> queryByNameLike1(String businessName,String company);
  	
  	//后台管理---通过商家主键id修改假删除字段的值
  	int upaFalseDel(Integer id);
  	
  	//后台管理---修改贷款商家状态为开启
  	int upaStateOpen(Integer id);
  	
  	//后台管理---修改贷款商家状态为关闭
  	int upaStateClose(Integer id);
  	
    //后台管理---插入图片的RUL
	void insertPath(String ossPath);

  	//后台管理---查询出贷款商家表所有的商家名称，将所有的商家名称存入一个集合中
  	List<String> queryAllBusinessName(String company);
  	
  	//后台管理---通过名称模糊查询出所有的商家名称，将所有的商家名称存入一个集合中
  	List<String> queryAllBusinessNameByLike(String businessName,String company);
  	
  	//后台管理---根据商家名称更新被申请次数字段
  	int upaApplicationNumber(Integer num,String businessName);
  	
  	//后台管理---根据商品名称和传过来的年  月  日(例如：2019-01-01——2019-01-20)  获取当前甲方商家这个时间段的所有足迹时间
  	List<String> queryTime(String businessName,String LikeTime1,String LikeTime2);
  	
  	//后台管理---根据商品名称和传过来的年  月  日(例如：2019-01-01) 获取当前甲方商家这一天的足迹数量
  	int  queryAmount(String businessName,String LikeTime1,String LikeTime2);
  	//后台管理---根据id  修改商家的排序字段
  	int upaSortByLoanId(Integer sort,Integer id);
  	
  	//小程序
  	int pageCount2(String company);
  	
	//小程序---查询贷款商家部分字段信息，含分页
  	List<CreditCard1> queryAllAdmainpro(@Param("page")Integer page,@Param("pageSize") Integer pageSize,@Param("company") String company);
}
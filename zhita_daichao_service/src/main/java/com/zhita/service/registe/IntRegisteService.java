package com.zhita.service.registe;

import java.util.List;
import java.util.Map;

import com.zhita.model.manage.LoansBusinesses;


public interface IntRegisteService{
	//后台管理---查询贷款商家部分字段信息，含分页
	public List<LoansBusinesses> queryAllAdmain(String company,Integer page,Integer pagesize) ;
	//后台管理---查询贷款商家部分字段信息，不含分页
	public List<LoansBusinesses> queryAllAdmain1(String company) ;
  	//后台管理---查询贷款商家总条数
  	public int pageCount(String company);
  	//后台管理---通过模糊查询的条件查询贷款商家总条数
  	public int pageCountByLike(String businessName,String company);
    //后台管理---添加贷款商家信息
    public int insert(LoansBusinesses record);
  	//后台管理---通过商家名称模糊查询，并且有分页功能
  	public List<LoansBusinesses> queryByNameLike(String businessName,String company,Integer page,Integer pagesize);
  	//后台管理---通过商家名称模糊查询，没有分页功能
  	public List<LoansBusinesses> queryByNameLike1(String businessName,String company);
  	//后台管理---通过商家主键id修改假删除字段的值
  	public int upaFalseDel(Integer id);
    //后台管理---通过主键id查询出贷款商家信息
    public LoansBusinesses selectByPrimaryKey(Integer id);
  	//后台管理---修改贷款商家状态为开启
  	public int upaStateOpen(Integer id);


    //后台管理---插入图片的URL
	public void insertPath(String ossPath);
	
	public Map<String, Object> getLoansBusinesses(String businessName, String company);
  	//后台管理---修改贷款商家状态为关闭
  	public int upaStateClose(Integer id);
  	//后台管理---查询出贷款商家表所有的商家名称，将所有的商家名称存入一个集合中
  	public List<String> queryAllBusinessName(String company);
  	//后台管理---通过名称模糊查询出所有的商家名称，将所有的商家名称存入一个集合中
  	public List<String> queryAllBusinessNameByLike(String businessName,String company);
  	//后台管理---根据商家名称更新被申请次数字段
  	public int upaApplicationNumber(Integer num,String businessName);
    //后台管理---通过传过来的贷款商家对象，对当前对象进行修改保存
    public int updateLoansBusinesses(LoansBusinesses loans);
  	//小程序---查询贷款商家部分字段信息，含分页

  	public List<LoansBusinesses> queryAllAdmainpro(Integer page,Integer pagesize, String company);
  	
  	//后台管理---通过传过来的贷款商家名字，查询商标的URL
	public String getTrademark(Integer id);
  	//后台管理---根据商品名称和传过来的年  月  日(例如：2019-01-01——2019-01-20)  获取当前甲方商家这个时间段的所有足迹时间
  	public List<String> queryTime(String businessName,String LikeTime,String LikeTime2);

  	//后台管理---根据商品名称和传过来的年  月  日(例如：2019-01-01) 获取当前甲方商家这一天的足迹数量
  	public int  queryAmount(String businessName,String LikeTime1,String LikeTime2);
	public List<String> BusinessesName(String company);
	//后台管理---根据id  修改商家的排序字段
  	public int upaSortByLoanId(Integer sort,Integer id);


}

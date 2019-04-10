package com.zhita.service.registe;

import java.util.List;
import java.util.Map;

import com.zhita.model.manage.LoansBusinesses;
import com.zhita.model.manage.LoansBusinessesCopy;


public interface IntRegisteCopyService{
	//后台管理---查询贷款商家部分字段信息，含分页
	public List<LoansBusinesses> queryAllAdmain2(String company,Integer page,Integer pagesize) ;
	//后台管理---查询贷款商家部分字段信息，不含分页
	public List<LoansBusinesses> queryAllAdmain11(String company) ;
  	//后台管理---查询贷款商家总条数
  	public int pageCount1(String company);
  	//后台管理---通过模糊查询的条件查询贷款商家总条数
  	public int pageCountByLike1(String businessName,String company);
    //后台管理---添加贷款商家信息
    public int insert1(LoansBusinesses record);
  	//后台管理---通过商家名称模糊查询，并且有分页功能
  	public List<LoansBusinesses> queryByNameLike2(String businessName,String company,Integer page,Integer pagesize);
  	//后台管理---通过商家名称模糊查询，没有分页功能
  	public List<LoansBusinesses> queryByNameLike11(String businessName,String company);
  	//后台管理---通过商家主键id修改假删除字段的值
  	public int upaFalseDel1(Integer id);
    //后台管理---通过主键id查询出贷款商家信息
    public LoansBusinesses selectByPrimaryKey1(Integer id);
  	//后台管理---修改贷款商家状态为开启
  	public int upaStateOpen1(Integer id);


    //后台管理---插入图片的URL
	public void insertPath1(String ossPath);
	
	public Map<String, Object> getLoansBusinesses1(String businessName, String company);
  	//后台管理---修改贷款商家状态为关闭
  	public int upaStateClose1(Integer id);
  	//后台管理---查询出贷款商家表所有的商家名称，将所有的商家名称存入一个集合中
  	public List<String> queryAllBusinessName1(String company);
  	//后台管理---通过名称模糊查询出所有的商家名称，将所有的商家名称存入一个集合中
  	public List<String> queryAllBusinessNameByLike1(String businessName,String company);
  	//后台管理---根据商家名称更新被申请次数字段
  	public int upaApplicationNumber1(Integer num,String businessName);
    //后台管理---通过传过来的贷款商家对象，对当前对象进行修改保存
    public int updateLoansBusinesses1(LoansBusinesses loans);
  	//小程序---查询贷款商家部分字段信息，含分页

  	public List<LoansBusinessesCopy> queryAllAdmainpro1(Integer page,Integer pagesize, String company);
  	
  	//后台管理---通过传过来的贷款商家名字，查询商标的URL
	public String getTrademark1(String businessname);
  	//后台管理---根据商品名称和传过来的年  月  日(例如：2019-01-01——2019-01-20)  获取当前甲方商家这个时间段的所有足迹时间
  	public List<String> queryTime1(String businessName,String LikeTime,String LikeTime2);
  	//后台管理---根据商品名称和传过来的年  月  日(例如：2019-01-01) 获取当前甲方商家这一天的足迹数量
  	public int  queryAmount1(String businessName,String LikeTime1,String LikeTime2);
	public int pageCountAppCopy(String company, String oneSourceName, String twoSourceName);
	public List<LoansBusinesses> queryAllAdmainproAppCopy(int page, int pageSize, String company, String oneSourceName,
			String twoSourceName);

    //后台管理---添加贷款商家信息（loans_businesses_copy表）
    public int insertCopy(LoansBusinessesCopy record);
 
    //后台管理---通过主键id查询出贷款商家信息（loans_businesses_copy表）
    public LoansBusinessesCopy selectByPrimaryKeyCopy(Integer id);
    
    //后台管理---通过传过来的贷款商家对象，对当前对象进行修改保存（loans_businesses_copy表）
    public int updateLoansBusinessesCopy(LoansBusinessesCopy loans);
    
    
  	//后台管理---查询贷款商家部分字段信息，含分页（loans_businesses_copy表）
  	public List<LoansBusinessesCopy> queryAllAdmainCopy(String company,Integer page,Integer pagesize);
  	
  	//后台管理---查询贷款商家部分字段信息，不含分页（loans_businesses_copy表）
  	public List<LoansBusinessesCopy> queryAllAdmain1Copy(String company);
  	
  	//后台管理---查询贷款商家总条数（loans_businesses_copy表）
  	public int pageCountCopy(String company);
  	
  	//后台管理---通过模糊查询的条件查询贷款商家总条数（loans_businesses_copy表）
  	public int pageCountByLikeCopy(String businessName,String company);
  	
  	//后台管理---通过商家名称模糊查询，并且有分页功能（loans_businesses_copy表）
  	public List<LoansBusinessesCopy> queryByNameLikeCopy(String businessName,String company,Integer page,Integer pagesize);
  	
  	//后台管理---通过商家名称模糊查询，没有分页功能（loans_businesses_copy表）
  	public List<LoansBusinessesCopy> queryByNameLike1Copy(String businessName,String company);
  	
  	//后台管理---通过商家主键id修改假删除字段的值（loans_businesses_copy表）
  	public int upaFalseDelCopy(Integer id);
  	
  	//后台管理---修改贷款商家状态为开启（loans_businesses_copy表）
  	public int upaStateOpenCopy(Integer id);
  	
  	//后台管理---修改贷款商家状态为关闭（loans_businesses_copy表）
  	public int upaStateCloseCopy(Integer id);
  
  	//后台管理---查询出贷款商家表所有的商家名称，将所有的商家名称存入一个集合中（loans_businesses_copy表）
  	public List<String> queryAllBusinessNameCopy(String company);
  	
  	//后台管理---通过名称模糊查询出所有的商家名称，将所有的商家名称存入一个集合中（loans_businesses_copy表）
  	public List<String> queryAllBusinessNameByLikeCopy(String businessName,String company);
  	
  	//后台管理---根据商家名称更新被申请次数字段（loans_businesses_copy表）
  	public int upaApplicationNumberCopy(Integer num,String businessName);
  
  	//后台管理---根据id  修改商家的排序字段（loans_businesses_copy表）
  	public int upaSortByLoanIdCopy(Integer sort,Integer id);
  	//后台管理----通过商家id，查询商标的URL（loans_businesses_copy表）
	public String getTrademark(Integer id);
	public Map<String, Object> getLoansBusinessesAppCopy(String businessName, String company, String oneSourceName,
			String twoSourceName);

}

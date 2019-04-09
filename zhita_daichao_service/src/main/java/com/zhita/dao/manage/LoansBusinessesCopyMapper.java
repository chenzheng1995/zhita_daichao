package com.zhita.dao.manage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.zhita.model.manage.LoansBusinesses;
import com.zhita.model.manage.LoansBusinessesCopy;

public interface LoansBusinessesCopyMapper {
    int deleteByPrimaryKey(Integer id);
    
    //后台管理---添加贷款商家信息
    int insert1(LoansBusinesses record);

    int insertSelective1(LoansBusinesses record);
    
    //后台管理---通过主键id查询出贷款商家信息
    LoansBusinesses selectByPrimaryKey1(Integer id);

    int updateByPrimaryKeySelective1(LoansBusinesses record);

    int updateByPrimaryKey1(LoansBusinesses record);
    
    //后台管理---通过传过来的贷款商家对象，对当前对象进行修改保存
    int updateLoansBusinesses1(LoansBusinesses loans);
    
  	//小程序---查询贷款商家部分字段信息，含分页

  	List<LoansBusinessesCopy> queryAllAdmainpro1(@Param("page")Integer page,@Param("pageSize") Integer pageSize,@Param("company") String company);

    
  	//后台管理---查询贷款商家部分字段信息，含分页
  	List<LoansBusinesses> queryAllAdmain2(String company,Integer page,Integer pagesize);
  	
  	//后台管理---查询贷款商家部分字段信息，不含分页
  	List<LoansBusinesses> queryAllAdmain11(String company);
  	
  	//后台管理---查询贷款商家总条数
  	int pageCount1(String company);
  	
  	//后台管理---通过模糊查询的条件查询贷款商家总条数
  	int pageCountByLike1(String businessName,String company);
  	
  	//后台管理---通过商家名称模糊查询，并且有分页功能
  	List<LoansBusinesses> queryByNameLike2(String businessName,String company,Integer page,Integer pagesize);
  	
  	//后台管理---通过商家名称模糊查询，没有分页功能
  	List<LoansBusinesses> queryByNameLike11(String businessName,String company);
  	
  	//后台管理---通过商家主键id修改假删除字段的值
  	int upaFalseDel1(Integer id);
  	
  	//后台管理---修改贷款商家状态为开启
  	int upaStateOpen1(Integer id);
  	
  	//后台管理---修改贷款商家状态为关闭
  	int upaStateClose1(Integer id);
  	
  	//后台管理---申请统计功能---查询当前商家每个月每一天的用户信息（用户流量）
  	/*List<CommodityFootprint> queryByFootprintTimeAndFootprint(String footprintTime,String footprint);*/

    //后台管理---插入图片的RUL
	void insertPath1(String ossPath);

	Map<String, Object> getLoansBusinesses1(@Param("businessName")String businessName,@Param("company") String company);

  	//后台管理---查询出贷款商家表所有的商家名称，将所有的商家名称存入一个集合中
  	List<String> queryAllBusinessName1(String company);
  	
  	//后台管理---通过名称模糊查询出所有的商家名称，将所有的商家名称存入一个集合中
  	List<String> queryAllBusinessNameByLike1(String businessName,String company);
  	
  	//后台管理---根据商家名称更新被申请次数字段
  	int upaApplicationNumber1(Integer num,String businessName);
  	
  	//后台管理---根据商品名称和传过来的年  月  日(例如：2019-01-01——2019-01-20)  获取当前甲方商家这个时间段的所有足迹时间
  	List<String> queryTime1(String businessName,String LikeTime1,String LikeTime2);
  	
  	//后台管理---根据商品名称和传过来的年  月  日(例如：2019-01-01) 获取当前甲方商家这一天的足迹数量
  	int  queryAmount1(String businessName,String LikeTime1,String LikeTime2);

    //小程序---通过贷款分类的名称，查询出当前贷款分类下的所有贷款商家的信息,含分页
    List<LoansBusinessesCopy> queryLoanbusinByLoanClass1(@Param("businessClassification")String businessClassification,@Param("page")Integer page,@Param("pageSize") int pageSize,@Param("company") String company);

	String getTrademark1(String businessname);

	int pageCountAppCopy(@Param("company")String company,@Param("oneSourceName") String oneSourceName,@Param("twoSourceName") String twoSourceName);

	List<LoansBusinesses> queryAllAdmainproAppCopy(@Param("page")int page,@Param("pageSize") int pageSize,@Param("company") String company,@Param("oneSourceName") String oneSourceName,
			@Param("twoSourceName") String twoSourceName);




    //后台管理---添加贷款商家信息（loans_businesses_copy表）
    int insertCopy(LoansBusinessesCopy record);
 
    //后台管理---通过主键id查询出贷款商家信息（loans_businesses_copy表）
    LoansBusinessesCopy selectByPrimaryKeyCopy(Integer id);
    
    //后台管理---通过传过来的贷款商家对象，对当前对象进行修改保存（loans_businesses_copy表）
    int updateLoansBusinessesCopy(LoansBusinessesCopy loans);
    
    
  	//后台管理---查询贷款商家部分字段信息，含分页（loans_businesses_copy表）
  	List<LoansBusinessesCopy> queryAllAdmainCopy(String company,Integer page,Integer pagesize);
  	
  	//后台管理---查询贷款商家部分字段信息，不含分页（loans_businesses_copy表）
  	List<LoansBusinessesCopy> queryAllAdmain1Copy(String company);
  	
  	//后台管理---查询贷款商家总条数（loans_businesses_copy表）
  	int pageCountCopy(String company);
  	
  	//后台管理---通过模糊查询的条件查询贷款商家总条数（loans_businesses_copy表）
  	int pageCountByLikeCopy(String businessName,String company);
  	
  	//后台管理---通过商家名称模糊查询，并且有分页功能（loans_businesses_copy表）
  	List<LoansBusinessesCopy> queryByNameLikeCopy(String businessName,String company,Integer page,Integer pagesize);
  	
  	//后台管理---通过商家名称模糊查询，没有分页功能（loans_businesses_copy表）
  	List<LoansBusinessesCopy> queryByNameLike1Copy(String businessName,String company);
  	
  	//后台管理---通过商家主键id修改假删除字段的值（loans_businesses_copy表）
  	int upaFalseDelCopy(Integer id);
  	
  	//后台管理---修改贷款商家状态为开启（loans_businesses_copy表）
  	int upaStateOpenCopy(Integer id);
  	
  	//后台管理---修改贷款商家状态为关闭（loans_businesses_copy表）
  	int upaStateCloseCopy(Integer id);
  
  	//后台管理---查询出贷款商家表所有的商家名称，将所有的商家名称存入一个集合中（loans_businesses_copy表）
  	List<String> queryAllBusinessNameCopy(String company);
  	
  	//后台管理---通过名称模糊查询出所有的商家名称，将所有的商家名称存入一个集合中（loans_businesses_copy表）
  	List<String> queryAllBusinessNameByLikeCopy(String businessName,String company);
  	
  	//后台管理---根据商家名称更新被申请次数字段（loans_businesses_copy表）
  	int upaApplicationNumberCopy(Integer num,String businessName);
  
  	//后台管理---根据id  修改商家的排序字段（loans_businesses_copy表）
  	int upaSortByLoanIdCopy(Integer sort,Integer id);
  	//后台管理----通过商家id，查询商标的URL（loans_businesses_copy表）
  	String getTrademark(Integer id);

	List<LoansBusinessesCopy> queryLoanbusinByLoanClassAppCopy(@Param("businessClassification")String businessClassification,@Param("pages") int pages,@Param("pageSize") int pageSize,
			@Param("company") String company,@Param("oneSourceName") String oneSourceName,@Param("twoSourceName") String twoSourceName);



}
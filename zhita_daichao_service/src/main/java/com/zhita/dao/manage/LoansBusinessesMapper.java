package com.zhita.dao.manage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhita.model.manage.CommodityFootprint;
import com.zhita.model.manage.LoansBusinesses;

public interface LoansBusinessesMapper {
    int deleteByPrimaryKey(Integer id);
    
    //后台管理---添加贷款商家信息
    int insert(LoansBusinesses record);

    int insertSelective(LoansBusinesses record);
    
    //后台管理---通过主键id查询出贷款商家信息
    LoansBusinesses selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoansBusinesses record);

    int updateByPrimaryKey(LoansBusinesses record);
    
    //后台管理---通过传过来的贷款商家对象，对当前对象进行修改保存
    int updateLoansBusinesses(LoansBusinesses loans);
    
  	//小程序---查询贷款商家部分字段信息，含分页

  	List<LoansBusinesses> queryAllAdmainpro(@Param("page")Integer page,@Param("pageSize") Integer pageSize,@Param("company") String company);

    
  	//后台管理---查询贷款商家部分字段信息，含分页
  	List<LoansBusinesses> queryAllAdmain(String company,Integer page,Integer pagesize);
  	
  	//后台管理---查询贷款商家部分字段信息，不含分页
  	List<LoansBusinesses> queryAllAdmain1(String company);
  	
  	//后台管理---查询贷款商家总条数
  	int pageCount(String company);
  	
  	//后台管理---通过模糊查询的条件查询贷款商家总条数
  	int pageCountByLike(String businessName,String company);
  	
  	//后台管理---通过商家名称模糊查询，并且有分页功能
  	List<LoansBusinesses> queryByNameLike(String businessName,String company,Integer page,Integer pagesize);
  	
  	//后台管理---通过商家名称模糊查询，没有分页功能
  	List<LoansBusinesses> queryByNameLike1(String businessName,String company);
  	
  	//后台管理---通过商家主键id修改假删除字段的值
  	int upaFalseDel(Integer id);
  	
  	//后台管理---修改贷款商家状态为开启
  	int upaStateOpen(Integer id);
  	
  	//后台管理---修改贷款商家状态为关闭
  	int upaStateClose(Integer id);
  	
  	//后台管理---申请统计功能---查询当前商家每个月每一天的用户信息（用户流量）
  	/*List<CommodityFootprint> queryByFootprintTimeAndFootprint(String footprintTime,String footprint);*/

    //后台管理---插入图片的RUL
	void insertPath(String ossPath);

	Map<String, Object> getLoansBusinesses(@Param("businessName")String businessName,@Param("company") String company);

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

    //小程序---通过贷款分类的名称，查询出当前贷款分类下的所有贷款商家的信息,含分页
    List<LoansBusinesses> queryLoanbusinByLoanClass(@Param("businessClassification")String businessClassification,@Param("page")Integer page,@Param("pageSize") int pageSize,@Param("company") String company);

	String getTrademark(Integer id);

	List<String> BusinessesName(String company);

	List<String> getTwoFirm(String company);

	int pageCount2(String company);

}
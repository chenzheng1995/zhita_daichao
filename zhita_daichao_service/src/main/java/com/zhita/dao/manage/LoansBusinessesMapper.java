package com.zhita.dao.manage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

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
  	List<LoansBusinesses> queryAllAdmain(Integer page,Integer pagesize);
  	
  	//后台管理---查询贷款商家总条数
  	int pageCount(String company);
  	
  	//后台管理---通过模糊查询的条件查询贷款商家总条数
  	int pageCountByLike(String businessName);
  	
  	//后台管理---通过商家名称模糊查询，并且有分页功能
  	List<LoansBusinesses> queryByNameLike(String businessName,Integer page,Integer pagesize);
  	
  	//后台管理---通过商家主键id修改假删除字段的值
  	int upaFalseDel(Integer id);
  	
  	//后台管理---修改贷款商家状态为开启
  	int upaStateOpen(Integer id);
  	
  	//后台管理---修改贷款商家状态为关闭
  	int upaStateClose(Integer id);
  	

    //后台管理---插入图片的RUL
	void insertPath(String ossPath);

	Map<String, Object> getLoansBusinesses(@Param("businessName")String businessName,@Param("company") String company);

  	//后台管理---查询出贷款商家表所有的商家名称，将所有的商家名称存入一个集合中
  	List<String> queryAllBusinessName();
  	
  	//后台管理---通过名称模糊查询出所有的商家名称，将所有的商家名称存入一个集合中
  	List<String> queryAllBusinessNameByLike(String businessName);
  	
  	//后台管理---根据商家名称更新被申请次数字段
  	int upaApplicationNumber(Integer num,String businessName);

    //小程序---通过贷款分类的名称，查询出当前贷款分类下的所有贷款商家的信息,含分页
    List<LoansBusinesses> queryLoanbusinByLoanClass(@Param("businessClassification")String businessClassification,@Param("page")Integer page,@Param("pageSize") int pageSize,@Param("company") String company);

	String getTrademark(String businessname);

}
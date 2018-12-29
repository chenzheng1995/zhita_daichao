package com.zhita.service.registe;

import java.util.List;
import java.util.Map;

import com.zhita.model.manage.LoansBusinesses;


public interface IntRegisteService{
	//后台管理---查询贷款商家部分字段信息，含分页
	public List<LoansBusinesses> queryAllAdmain(Integer page,Integer pagesize) ;
  	//后台管理---查询贷款商家总条数
  	public int pageCount();
  	//后台管理---通过模糊查询的条件查询贷款商家总条数
  	public int pageCountByLike(String businessName);
    //后台管理---添加贷款商家信息
    public int insert(LoansBusinesses record);
  	//后台管理---通过商家名称模糊查询，并且有分页功能
  	public List<LoansBusinesses> queryByNameLike(String businessName,Integer page,Integer pagesize);
  	//后台管理---通过商家主键id修改假删除字段的值
  	public int upaFalseDel(Integer id);
    //后台管理---通过主键id查询出贷款商家信息
    public LoansBusinesses selectByPrimaryKey(Integer id);
  	//后台管理---修改贷款商家状态为开启
  	public int upaStateOpen(Integer id);


    //后台管理---插入图片的URL
	public void insertPath(String ossPath);
	
	public Map<String, Object> getLoansBusinesses(String businessName);
  	//后台管理---修改贷款商家状态为关闭
  	public int upaStateClose(Integer id);
  	//后台管理---查询出贷款商家表所有的商家名称，将所有的商家名称存入一个集合中
  	public List<String> queryAllBusinessName();
  	//后台管理---通过名称模糊查询出所有的商家名称，将所有的商家名称存入一个集合中
  	public List<String> queryAllBusinessNameByLike(String businessName);
  	//后台管理---根据商家名称更新被申请次数字段
  	public int upaApplicationNumber(Integer num,String businessName);
    //后台管理---通过传过来的贷款商家对象，对当前对象进行修改保存
    public int updateLoansBusinesses(LoansBusinesses loans);
  	//小程序---查询贷款商家部分字段信息，含分页
  	public List<LoansBusinesses> queryAllAdmainpro(Integer page,Integer pagesize);

}

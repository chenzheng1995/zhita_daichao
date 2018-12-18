package com.zhita.service.registe;

import java.util.List;

import com.zhita.model.manage.LoansBusinesses;
import com.zhita.util.PageUtil;

public interface IntRegisteService{
	//小程序---查询出所有贷款商家的信息
	public List<LoansBusinesses> queryAll();
	//小程序---通过商家分类查询出商家信息
	public List<LoansBusinesses> queryByLoansClass(String businessClassification);
	//后台管理---查询贷款商家部分字段信息，含分页
	public List<LoansBusinesses> queryAllAdmain(Integer page) ;
  	//后台管理---查询贷款商家总条数
  	public int pageCount();
    //后台管理---添加贷款商家信息
    public int insert(LoansBusinesses record);
  	//后台管理---通过商家名称模糊查询，并且有分页功能
  	public List<LoansBusinesses> queryByNameLike(String businessName,Integer page);
  	//后台管理---通过商家主键id修改假删除字段的值
  	int upaFalseDel(Integer id);
    //后台管理---通过主键id查询出贷款商家信息
    LoansBusinesses selectByPrimaryKey(Integer id);
}

package com.zhita.service.type;

import java.util.List;

import com.zhita.model.manage.LoanClassification;
import com.zhita.model.manage.LoanClassificationCopy;
import com.zhita.model.manage.LoansBusinesses;
import com.zhita.model.manage.LoansBusinessesCopy;

public interface IntTypeService{
    //后台管理---查询贷款分类所有信息，含分页
    public List<LoanClassification> queryAllPage(String company,Integer page,Integer pagesize);
    //后台管理---查询贷款分类所有信息，不含分页
    public List<LoanClassification> queryAllNoPage(String company);
    //后台管理---用于获取总页数
    public int pageCount(String company);
    //后台管理---用于获取模糊查询总页数
    public int pageCountByLike(String businessClassification,String company);
    //小程序---用于获取通过贷款分类的名称查询出贷款商家的个数
    public int pageCountByBusinessClassification(String businessClassification, String company);
    //后台管理---模糊查询贷款分类信息,并且有分页功能
    public List<LoanClassification> queryByLike(String businessClassification,String company,Integer page,Integer pagesize);
    //后台管理---模糊查询贷款分类信息,没有分页功能
    public List<LoanClassification> queryByLike1(String businessClassification,String company);
    //后台管理---添加贷款分类信息
    public int addAll(LoanClassification record);
    //后台管理---通过主键id查询出贷款分类信息
    public LoanClassification selectByPrimaryKey(Integer id);
    //小程序---通过贷款分类的名称，查询出当前贷款分类下的所有贷款商家的信息,含分页

    public List<LoansBusinesses> queryLoanbusinByLoanClass(String businessClassification,Integer page,Integer pagesize, String company);
    //后台管理---查询贷款分类所有信息，不含分页,做贷款商家添加功能时，下拉框取贷款分类的值时使用
    public List<LoanClassification> queryAllLoanCla(String company);
    //后台管理---  根据分类id   查询当前分类的图标
    public String queryIconById(Integer id);
    //通过传过来的贷款分类对象，对当前对象进行修改保存
    public int updateByPrimaryKey(LoanClassification record);
	public List<LoanClassificationCopy> queryLoanClass(String company);
	public List<LoanClassificationCopy> queryLoanClassAfter(String company);
	public List<LoansBusinessesCopy> queryLoanbusinByLoanClass1(String businessClassification, int pages, int pageSize,
			String company);
	public int pageCountByBusinessClassification1(String businessClassification, String company);
	public List<LoanClassification> queryLoanClass1(String company);
	public List<LoanClassification> queryLoanClassAfter1(String company);
	public int pageCountByBusinessClassificationAppCopy(String businessClassification, String company,
			String oneSourceName, String twoSourceName);
	public List<LoansBusinessesCopy> queryLoanbusinByLoanClassAppCopy(String businessClassification, int pages,
			int pageSize, String company, String oneSourceName, String twoSourceName);


}

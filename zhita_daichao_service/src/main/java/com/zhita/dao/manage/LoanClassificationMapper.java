package com.zhita.dao.manage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.LoanClassification;
import com.zhita.model.manage.LoansBusinesses;

public interface LoanClassificationMapper {
    int deleteByPrimaryKey(Integer id);
    
    int insert(LoanClassification record);

    int insertSelective(LoanClassification record);
    
    //后台管理---通过主键id查询出贷款分类信息
    LoanClassification selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoanClassification record);
    
    //通过传过来的贷款分类对象，对当前对象进行修改保存
    int updateByPrimaryKey(LoanClassification record);
    
    //后台管理---查询贷款分类所有信息，含分页
    List<LoanClassification> queryAllPage(String company,Integer page,Integer pagesize);
    
    //后台管理---用于获取总页数
    int pageCount(String company);
    
    //后台管理---用于获取模糊查询总页数
    int pageCountByLike(String businessClassification,String company);
    
    //小程序---用于获取通过贷款分类的名称查询出贷款商家的个数
    int pageCountByBusinessClassification(@Param("businessClassification")String businessClassification,@Param("company") String company);
    
    //后台管理---模糊查询贷款分类信息,并且有分页功能
    List<LoanClassification> queryByLike(String businessClassification,String company,Integer page,Integer pagesize);
    
    //后台管理---添加贷款分类信息
    int addAll(LoanClassification loanClassification);
    
    //小程序---通过贷款分类的名称，查询出当前贷款分类下的所有贷款商家的信息,含分页
    List<LoansBusinesses> queryLoanbusinByLoanClass(String businessClassification,Integer page,Integer pageSize);

    //后台管理---查询贷款分类所有信息，不含分页,做贷款商家添加功能时，下拉框取贷款分类的值时使用
    List<LoanClassification> queryAllLoanCla();


    
}
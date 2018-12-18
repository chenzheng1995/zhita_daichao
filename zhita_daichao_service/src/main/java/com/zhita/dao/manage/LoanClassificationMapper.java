package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.LoanClassification;

public interface LoanClassificationMapper {
    int deleteByPrimaryKey(Integer id);
    
    //后台管理---添加贷款分类信息
    int insert(LoanClassification record);

    int insertSelective(LoanClassification record);
    
    //后台管理---通过主键id查询出贷款分类信息
    LoanClassification selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoanClassification record);

    int updateByPrimaryKey(LoanClassification record);
    
    //后台管理---查询贷款分类所有信息，含分页
    List<LoanClassification> queryAllPage(Integer page);
    
    //后台管理---用于获取总页数
    int pageCount();
    
    //后台管理---模糊查询贷款分类信息,并且有分页功能
    List<LoanClassification> queryByLike(String businessClassification,Integer page);
}
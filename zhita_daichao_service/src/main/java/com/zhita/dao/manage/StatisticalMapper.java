package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.Statistical;

public interface StatisticalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Statistical record);

    int insertSelective(Statistical record);

    Statistical selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Statistical record);

    int updateByPrimaryKey(Statistical record);
    
    //后台管理---查询出统计表所有的商家名称
    List<String> queryAllLoansName();
    
    //后台管理---通过渠道名称  查询出统计表所有的商家名称
    List<String> quereyAllLoansNameBySourceName(String sourceName);
    
    //后台管理---查询出统计表数据总数量
    int pageCount();
    
    //后台管理---通过渠道名称   查询出统计表数据总数量
    int pageCountBySourceName(String sourceName);
    
    //后台管理---查询统计表所有信息,含分页
    List<Statistical> queryAllPage(Integer page,Integer pagesize);
    
    //后台管理---通过渠道名称   查询统计表所有信息,含分页
    List<Statistical> queryAllPageBySourceName(String sourceName,Integer page,Integer pagesize);
    
}
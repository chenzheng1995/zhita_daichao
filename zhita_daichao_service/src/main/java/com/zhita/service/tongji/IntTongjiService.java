package com.zhita.service.tongji;

import java.util.List;

import com.zhita.model.manage.Statistical;

public interface IntTongjiService {
	
    //后台管理---通过渠道名称  查询出统计表所有的商家名称
    public List<String> quereyAllLoansNameBySourceName(String sourceName);
    //后台管理---查询出统计表所有的商家名称
    public List<String> queryAllLoansName();
    //后台管理---查询出统计表数据总数量
    public int pageCount();
    //后台管理---通过渠道名称   查询出统计表数据总数量
    public int pageCountBySourceName(String sourceName);
    //后台管理---查询统计表所有信息，含分页
    public List<Statistical> queryAllPage(Integer page,Integer pagesize);
    //后台管理---通过渠道名称   查询统计表所有信息，含分页
    public List<Statistical> queryAllPageBySourceName(String sourceName,Integer page,Integer pagesize);
}

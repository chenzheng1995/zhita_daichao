package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.SourceDiscountHistory;
import com.zhita.model.manage.TongjiSorce;

public interface SourceDiscountHistoryMapper {

    int deleteByPrimaryKey(Integer id);
    
    //后台管理---往渠道申请数（折扣量）历史表插入数据
    int insert(TongjiSorce record);

    int insertSelective(SourceDiscountHistory record);

    SourceDiscountHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SourceDiscountHistory record);

    int updateByPrimaryKey(SourceDiscountHistory record);
    
    //后台管理---通过渠道名称查询历史表信息
    List<TongjiSorce> queryAllBySourceName(String sourcename);
    
    //后台管理---通过渠道查询历史表当前渠道的所有日期
    List<String> queryDate(String sourcename);
    
    //后台管理---通过渠道和日期查询是否有数据
    TongjiSorce queryBySourcenameAndDate(String sourcename,String date);
}
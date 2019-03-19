package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.SourceTongji;
import com.zhita.model.manage.Statistical;

public interface StatisticalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Statistical record);

    int insertSelective(Statistical record);

    Statistical selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Statistical record);

    int updateByPrimaryKey(Statistical record);
    
    //后台管理---通过渠道名称  查询出统计表所有的商家名称
    List<String> quereyAllLoansNameBySourceName(String sourceName);
    
    //后台管理---查询出统计表数据总数量
    int pageCount(String source,String startTime,String endTime);
    
    //后台管理---通过渠道名称   查询出统计表数据总数量
    int pageCountBySourceName(String company,String sourceName);
    
    //后台管理---查询渠道统计所有信息，含分页
    List<SourceTongji> queryAllPage(String source,String startTime,String endTime,Integer page,Integer pagesize);
    
    //后台管理---查询渠道统计所有信息，不含分页
    List<SourceTongji> queryAllPage1(String company);
    
    //后台管理---通过渠道名称   查询统计表所有信息,含分页
    List<SourceTongji> queryAllPageBySourceName(String company,String sourceName,Integer page,Integer pagesize);
    
    //后台管理---通过渠道名称   查询统计表所有信息,不含分页
    List<SourceTongji> queryAllPageBySourceName1(String company,String sourceName);
    
    //后台管理---查询统计pv
    Integer queryPV(String company,String sourceName);
    
    //后台管理---查询统计uv
    Integer queryUV(String company,String sourceName,String startTime,String endTime);
    
    //后台管理---查询统计uv1
    Integer queryUV1(String bussnname,String sourceName,String startTime,String endTime);
    
    //后台管理---查询统计申请数
    Integer queryApplicationNumber(String company,String sourceName,String startTime,String endTime);
    
    //后台管理---查询当前渠道的折扣率
    String queryDiscount(String source,String company);
    
    //后台管理---查询当前渠道在user表的所有注册时间
    List<String> queryTime(String company,String sourceName);
    
    //后台管理---查询在user表的所有注册时间
    List<String> queryTimeme1(String company);
    
}
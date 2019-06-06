package com.zhita.service.tongji;

import java.util.List;

import com.zhita.model.manage.SourceDiscountHistory;
import com.zhita.model.manage.SourceTongji;
import com.zhita.model.manage.TongjiSorce;

public interface IntTongjiService {
    //后台管理---通过渠道名称  查询出统计表所有的商家名称
    public List<String> quereyAllLoansNameBySourceName(String sourceName);
    
    //后台管理---查询出统计表数据总数量
    public int pageCount(String source,String startTime,String endTime);
    
    //后台管理---通过渠道名称   查询出统计表数据总数量
    public int pageCountBySourceName(String company,String sourceName);
    
    //后台管理---查询渠道统计所有信息，含分页
    public List<SourceTongji> queryAllPage(String source,String startTime,String endTime);
    
    //后台管理---查询渠道统计所有信息，不含分页
    public List<SourceTongji> queryAllPage1(String company);
    
    //后台管理---通过渠道名称   查询统计表所有信息，含分页
    public List<SourceTongji> queryAllPageBySourceName(String company,String sourceName,Integer page,Integer pagesize);
    
    //后台管理---通过渠道名称   查询统计表所有信息，不含分页
    public List<SourceTongji> queryAllPageBySourceName1(String company,String sourceName);
    
    //后台管理---查询统计pv
    public Integer queryPV(String company,String sourceName);
    
    //后台管理---查询统计uv
    public Integer queryUV(String company,String sourceName,String startTime,String endTime);
    
    //后台管理---查询统计uv1
    public Integer queryUV1(String businame,String sourceName,String startTime,String endTime);
    
    //后台管理---查询统计申请数
    public Integer queryApplicationNumber(String company,String sourceName,String startTime,String endTime);
    
    //后台管理---查询当前渠道的折扣率
    public String queryDiscount(String source,String company);
    
    //后台管理---查询当前渠道在user表的所有注册时间
    public List<String> queryTime(String company,String sourceName);
    
    //后台管理---查询在user表的所有注册时间
    public List<String> queryTimeme1(String company);
    
    //后台管理---查询该时间段里    在用户表一共哪些渠道，以及这些渠道的在用户表的注册数量
    List<TongjiSorce> queryAllSourceByUser(String[] company,String StartTime,String EndTime);
    
    //后台管理---往渠道申请数（折扣量）历史表插入数据
    public int insertAll(TongjiSorce sourceDiscountHistory);
    
    //后台管理---通过渠道名称查询历史表信息
    public List<TongjiSorce> queryAllBySourceName(String sourcename);
    
    //后台管理---通过渠道查询历史表当前渠道的所有日期
    public List<String> queryDate(String sourcename);
    
    //后台管理---通过渠道和日期查询是否有数据
    public TongjiSorce queryBySourcenameAndDate(String sourcename,String startdate,String enddate);
    
    //后台管理---根据渠道和日期更新历史表数据
    public int updateByPrimaryKey(TongjiSorce record);
    
    //test   查询source_discount_history表所有的对象
    public List<TongjiSorce> queryTest();
    
    //test   将source_discount_history表的date  改为时间戳格式
    public int updateTest(String dateTimstamp,Integer id);
}

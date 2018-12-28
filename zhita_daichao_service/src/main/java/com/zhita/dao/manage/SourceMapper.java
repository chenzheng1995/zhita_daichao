package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.Source;

public interface SourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Source record);

    int insertSelective(Source record);
    
    //后台管理---通过渠道id，查询渠道信息
    Source selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Source record);

    int updateByPrimaryKey(Source record);
    
    //后台管理---查询出所有渠道表信息，不含分页
    List<Source> queryAll();
    
    //后台管理---通过传过来的渠道对象，对当前对象进行修改保存
    int updateSource(Source source);
    
    //后台管理---查询出所有渠道表信息，含分页
    List<Source> queryAllSource(Integer page);
    
    //后台管理---用于获取总页数
    int pageCount();
    
    //后台管理---用于获取模糊查询总页数
    int pageCountByLike(String sourceName);
    
    //后台管理---模糊查询渠道信息,并且有分页功能
    List<Source> queryByLike(String sourceName,Integer page);
    
    //后台管理---添加渠道信息
    int addAll(Source source);
    
    //后台管理---通过删除按钮，改变当前渠道的假删除状态，将状态改为删除
    int upaFalseDel(Integer id);
    
    //后台管理---修改信用卡状态为开启
    int upaStateOpen(Integer id);
    
    //后台管理---修改信用卡状态为关闭
    int upaStateClose(Integer id);
}
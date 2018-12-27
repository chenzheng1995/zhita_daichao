package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.Source;

public interface SourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Source record);

    int insertSelective(Source record);

    Source selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Source record);

    int updateByPrimaryKey(Source record);
    //后台管理---查询出所有渠道表信息，不含分页
    List<Source> queryAll();
}
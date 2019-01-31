package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.Functions;

public interface FunctionsMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Functions record);

    int insertSelective(Functions record);

    Functions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Functions record);

    int updateByPrimaryKey(Functions record);
    
    //后台管理---根据用户名查询出当前用户所拥有的功能
    List<Functions> queryFunctionByName(String name);
    //后台管理---根据权限id查询权限名称
    String queryFunctionsByFunctionId(Integer functionid);
}
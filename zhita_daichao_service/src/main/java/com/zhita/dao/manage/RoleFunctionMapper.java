package com.zhita.dao.manage;

import com.zhita.model.manage.RoleFunction;

public interface RoleFunctionMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(RoleFunction record);

    int insertSelective(RoleFunction record);

    RoleFunction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleFunction record);

    int updateByPrimaryKey(RoleFunction record);
}
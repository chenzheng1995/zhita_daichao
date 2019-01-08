package com.zhita.dao.manage;

import com.zhita.model.manage.ManageloginRole;

public interface ManageloginRoleMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ManageloginRole record);

    int insertSelective(ManageloginRole record);


    ManageloginRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManageloginRole record);

    int updateByPrimaryKey(ManageloginRole record);
}
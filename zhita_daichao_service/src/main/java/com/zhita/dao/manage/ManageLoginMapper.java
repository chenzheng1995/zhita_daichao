package com.zhita.dao.manage;

import com.zhita.model.manage.ManageLogin;

public interface ManageLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManageLogin record);

    int insertSelective(ManageLogin record);

    ManageLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManageLogin record);

    int updateByPrimaryKey(ManageLogin record);
    
    ManageLogin findFormatByLoginName(String userName);
}
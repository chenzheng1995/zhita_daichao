package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.ManageloginRole;

public interface ManageloginRoleMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ManageloginRole record);

    int insertSelective(ManageloginRole record);


    ManageloginRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManageloginRole record);

    int updateByPrimaryKey(ManageloginRole record);
    
    //后台管理---通过用户id查询出   在用户角色关联表中的id集合
    List<Integer> queryByManageloginId(Integer manageloginid);
    
    //后台管理---在用户角色关联表中    根据主键id进行删除
    int delManageloginRole(Integer id);
    
    //后台管理---通过传过来的用户id和角色id   在中间表进行插入数据
    int add(Integer loginuserid,Integer roleid);
}
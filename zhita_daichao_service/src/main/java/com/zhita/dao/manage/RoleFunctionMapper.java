package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.RoleFunction;

public interface RoleFunctionMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(RoleFunction record);

    int insertSelective(RoleFunction record);

    RoleFunction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleFunction record);

    int updateByPrimaryKey(RoleFunction record);
    
    //后台管理 ----通过传过来的角色id   查询出当前角色id  在角色功能中间表的所有id集合
    List<Integer> queryAllIdByRoleid(Integer roleid);
    
    //后台管理 ---通过在角色功能中间表得到的主键id集合   进行数据删除
    int delFunctionByid(Integer id);
    
    //后台管理 ---通过传过来的角色id  和  功能id  在角色功能表进行添加数据
    int add(Integer roleid,Integer functionid);
}
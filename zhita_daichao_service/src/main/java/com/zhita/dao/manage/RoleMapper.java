package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.Functions;
import com.zhita.model.manage.Role;

public interface RoleMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);
    
    //后台管理----通过角色id查询出角色信息
    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    //后台管理---查询出所有的角色信息  不含分页
    List<Role> queryAllRole();
    
    //后台管理---根据角色状态   模糊查询出角色表一共有多少条数据
    int  pageCountLike(String deleted);
    
    //后台管理---根据角色状态   模糊查询出所有角色信息   含分页
    List<Role> queryAllRolePageLike(String deleted,Integer page,Integer pagesize);
    
    //后台管理---查询出角色表一共有多少条数据
    int  pageCount();
    
    //后台管理---查询出所有角色信息   含分页
    List<Role> queryAllRolePage(Integer page,Integer pagesize);
    
    //后台管理---添加角色
    int addRole(Role role);
    
	//后台管理----查看权限功能--通过角色id查询出  当前角色的所有权限
    List<Functions> queryFunctionByRoleid(Integer roleid);
    
    //后台管理----查询出功能表所有的信息
    List<Functions> queryFunctionAll();
    
    //后台管理----保存编辑后的信息
    int upaRole(Role role);
    
}
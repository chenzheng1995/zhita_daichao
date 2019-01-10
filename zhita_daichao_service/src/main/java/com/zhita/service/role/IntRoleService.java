package com.zhita.service.role;

import java.util.List;

import com.zhita.model.manage.FunctionUtilBean;
import com.zhita.model.manage.Role;

public interface IntRoleService {
    //后台管理---查询出所有角色信息   含分页
    public List<Role> queryAllRolePage(Integer page,Integer pagesize);
    //后台管理---添加角色
    public int addRole(Role role);
	//后台管理----查看权限功能--通过角色id查询出  当前角色的所有权限
    public List<FunctionUtilBean> queryFunctionByRoleid(Integer roleid);
    //后台管理----通过角色id查询出角色信息
    public Role selectByPrimaryKey(Integer id);
    //后台管理----查询出功能表所有的信息
    public List<FunctionUtilBean> queryFunctionAll();
}

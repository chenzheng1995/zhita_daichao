package com.zhita.service.role;

import java.util.List;
import java.util.Set;

import com.zhita.model.manage.Functions;
import com.zhita.model.manage.Role;

public interface IntRoleService {
    //后台管理---查询出角色表一共有多少条数据
    public int  pageCount();
    //后台管理---查询出所有角色信息   含分页
    public List<Role> queryAllRolePage(Integer page,Integer pagesize);
    //后台管理---根据角色状态   模糊查询出角色表一共有多少条数据
    public int  pageCountLike(String deleted);
    //后台管理---根据角色状态   模糊查询出所有角色信息   含分页
    public List<Role> queryAllRolePageLike(String deleted,Integer page,Integer pagesize);
    //后台管理---添加角色
    public int addRole(Role role);
	//后台管理----查看权限功能--通过角色id查询出  当前角色的所有权限
    public List<Functions> queryFunctionByRoleid(Integer roleid);
    //后台管理----通过角色id查询出角色信息
    public Role selectByPrimaryKey(Integer id);
    //后台管理----查询出功能表所有的信息
    public List<Functions> queryFunctionAll();
    //后台管理 ----通过传过来的角色id   查询出当前角色id  在角色功能中间表的所有id集合
    public List<Integer> queryAllIdByRoleid(Integer roleid);
    //后台管理 ---通过在角色功能中间表得到的主键id集合   进行数据删除
    public int delFunctionByid(Integer id);
    //后台管理 ---通过传过来的角色id  和  功能id  在角色功能表进行添加数据
    public int add(Integer roleid,Integer functionid);
    //后台管理----保存编辑后的信息
    public int upaRole(Role role);
    //后台管理---根据用户名查询出当前用户所拥有的功能
    public List<Functions> queryFunctionByName(String name);
    //后台管理---根据权限id查询权限名称
    public String queryFunctionsByFunctionId(Integer functionid);
}

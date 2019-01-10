package com.zhita.service.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.RoleMapper;
import com.zhita.model.manage.FunctionUtilBean;
import com.zhita.model.manage.Role;

@Service
public class RoleServieImp implements IntRoleService{
	@Autowired
	private RoleMapper roleMapper;
	
    //后台管理---查询出所有角色信息   含分页
    public List<Role> queryAllRolePage(Integer page,Integer pagesize){
    	List<Role> list=roleMapper.queryAllRolePage(page, pagesize);
    	return list;
    }
    //后台管理---添加角色
    public int addRole(Role role) {
    	int num=roleMapper.addRole(role);
    	return num;
    }
	//后台管理----查看权限功能--通过角色id查询出  当前角色的所有权限
    public List<FunctionUtilBean> queryFunctionByRoleid(Integer roleid){
    	List<FunctionUtilBean> list=roleMapper.queryFunctionByRoleid(roleid);
    	return list;
    }
    //后台管理----通过角色id查询出角色信息
    public Role selectByPrimaryKey(Integer id) {
    	Role role=roleMapper.selectByPrimaryKey(id);
    	return role;
    }
    //后台管理----查询出功能表所有的信息
    public List<FunctionUtilBean> queryFunctionAll(){
    	List<FunctionUtilBean> list=roleMapper.queryFunctionAll();
    	return list;
    }
    
}

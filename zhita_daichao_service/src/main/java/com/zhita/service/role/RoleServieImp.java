package com.zhita.service.role;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.FunctionsMapper;
import com.zhita.dao.manage.RoleFunctionMapper;
import com.zhita.dao.manage.RoleMapper;
import com.zhita.model.manage.Functions;
import com.zhita.model.manage.Role;

@Service
public class RoleServieImp implements IntRoleService{
	@Autowired
	private RoleMapper roleMapper;
	@Autowired 
	private RoleFunctionMapper roleFunctionMapper;
	@Autowired
	private FunctionsMapper functionsMapper;
	
    //后台管理---查询出角色表一共有多少条数据
    public int  pageCount() {
    	int count=roleMapper.pageCount();
    	return count;
    }
	
    //后台管理---查询出所有角色信息   含分页
    public List<Role> queryAllRolePage(Integer page,Integer pagesize){
    	List<Role> list=roleMapper.queryAllRolePage(page, pagesize);
    	return list;
    }
    public int  pageCountLike(String deleted) {
    	int count=roleMapper.pageCountLike(deleted);
    	return count;
    }
    //后台管理---根据角色状态   模糊查询出所有角色信息   含分页
    public List<Role> queryAllRolePageLike(String deleted,Integer page,Integer pagesize){
    	List<Role> list=roleMapper.queryAllRolePageLike(deleted, page, pagesize);
    	return list;
    }
    //后台管理---添加角色
    public int addRole(Role role) {
    	int num=roleMapper.addRole(role);
    	return num;
    }
	//后台管理----查看权限功能--通过角色id查询出  当前角色的所有权限
    public List<Functions> queryFunctionByRoleid(Integer roleid){
    	List<Functions> list=roleMapper.queryFunctionByRoleid(roleid);
    	return list;
    }
    //后台管理----通过角色id查询出角色信息
    public Role selectByPrimaryKey(Integer id) {
    	Role role=roleMapper.selectByPrimaryKey(id);
    	return role;
    }
    //后台管理----查询出功能表所有的信息
    public List<Functions> queryFunctionAll(){
    	List<Functions> list=roleMapper.queryFunctionAll();
    	return list;
    }
    //后台管理 ----通过传过来的角色id   查询出当前角色id  在角色功能中间表的所有id集合
    public List<Integer> queryAllIdByRoleid(Integer roleid){
    	List<Integer> list=roleFunctionMapper.queryAllIdByRoleid(roleid);
    	return list;
    }
    //后台管理 ---通过在角色功能中间表得到的主键id集合   进行数据删除
    public int delFunctionByid(Integer id) {
    	int sum=roleFunctionMapper.delFunctionByid(id);
    	return sum;
    }
    //后台管理 ---通过传过来的角色id  和  功能id  在角色功能表进行添加数据
    public int add(Integer roleid,Integer functionid) {
    	int sum=roleFunctionMapper.add(roleid, functionid);
    	return sum;
    }
    //后台管理----保存编辑后的信息
    public int upaRole(Role role) {
    	int count=roleMapper.upaRole(role);
    	return count;
    }
    //后台管理---根据用户名查询出当前用户所拥有的功能
    public List<Functions> queryFunctionByName(String name){
    	List<Functions> list=functionsMapper.queryFunctionByName(name);
    	return list;
    }
    //后台管理---根据权限id查询权限名称
    public String queryFunctionsByFunctionId(Integer functionid) {
    	String functionSecond=functionsMapper.queryFunctionsByFunctionId(functionid);
    	return functionSecond;
    }
}

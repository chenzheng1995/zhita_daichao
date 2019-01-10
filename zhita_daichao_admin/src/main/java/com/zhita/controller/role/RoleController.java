package com.zhita.controller.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.FunctionUtilBean;
import com.zhita.model.manage.Role;
import com.zhita.service.role.IntRoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	IntRoleService intRoleService;
	
    //后台管理---查询出所有角色信息   含分页
	@ResponseBody
	@RequestMapping("/queryAllRolePage")
    public List<Role> queryAllRolePage(Integer page,Integer pagesize){
    	List<Role> list=intRoleService.queryAllRolePage(page, pagesize);
    	return list;
    }
	//后台管理--添加角色
	@ResponseBody
	@RequestMapping("/addRole")
    public int addRole(Role role){
    	int num=intRoleService.addRole(role);
    	return num;
    }
	//后台管理----查看权限功能--通过角色id查询出  当前角色的所有权限
	@ResponseBody
	@RequestMapping("/queryFunctionByRoleid")
    public List<FunctionUtilBean> queryFunctionByRoleid(Integer roleid){
    	List<FunctionUtilBean> list=intRoleService.queryFunctionByRoleid(roleid);
    	return list;
    }
	//后台管理----编辑功能——通过角色id  查询出角色信息
	@ResponseBody
	@RequestMapping("/")
    public Map<String,Object> selectByPrimaryKey(Integer id) {
    	Role role=intRoleService.selectByPrimaryKey(id);//通过角色id 查询角色信息
    	List<FunctionUtilBean> listall=intRoleService.queryFunctionAll();//查询出功能表所有的信息  进行页面渲染
    	List<FunctionUtilBean> listByRoleid=intRoleService.queryFunctionByRoleid(id);//通过角色id查询出当前角色的所有功能
    	
    	HashMap<String, Object> map=new HashMap<>();
    	map.put("role",role);
    	map.put("listall",listall);
    	map.put("listByRoleid",listByRoleid);
    	return map;
    }
	
}

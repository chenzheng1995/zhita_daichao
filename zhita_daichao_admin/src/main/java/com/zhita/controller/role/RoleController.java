package com.zhita.controller.role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.Functions;
import com.zhita.model.manage.Role;
import com.zhita.service.role.IntRoleService;
import com.zhita.util.PageUtil;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	IntRoleService intRoleService;
	
    //后台管理---查询出所有角色信息   含分页
	@ResponseBody
	@RequestMapping("/queryAllRolePage")
    public Map<String, Object> queryAllRolePage(Integer page){
		int totalCount=intRoleService.pageCount();//查询出角色表一共有多少数据
    	PageUtil pageUtil=new PageUtil(page,1,totalCount);
    	if(page<1) {
    		page=1;
    	}
    	else if(page>pageUtil.getTotalPageCount()) {
    		if(totalCount==0) {
    			page=pageUtil.getTotalPageCount()+1;
    		}else {
    			page=pageUtil.getTotalPageCount();
    		}
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil.setPage(pages);
    	List<Role> list=intRoleService.queryAllRolePage(pageUtil.getPage(), pageUtil.getPageSize());
    	
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listRole",list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
	
    //后台管理---根据角色状态  模糊查询出所有角色信息   含分页
	@ResponseBody
	@RequestMapping("/queryAllRolePageLike")
    public Map<String, Object> queryAllRolePageLike(String deleted,Integer page){
		List<Role> list=null;
		PageUtil pageUtil=null;
		if(deleted==null||"".equals(deleted)) {
			int totalCount=intRoleService.pageCount();//查询出角色表一共有多少数据
	    	pageUtil=new PageUtil(page,1,totalCount);
	    	if(page<1) {
	    		page=1;
	    	}
	    	else if(page>pageUtil.getTotalPageCount()) {
	    		if(totalCount==0) {
	    			page=pageUtil.getTotalPageCount()+1;
	    		}else {
	    			page=pageUtil.getTotalPageCount();
	    		}
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil.setPage(pages);
	    	list=intRoleService.queryAllRolePage(pageUtil.getPage(), pageUtil.getPageSize());
		}else {
			int totalCount=intRoleService.pageCountLike(deleted);//根据角色状态  模糊查询出角色表一共有多少数据
			pageUtil=new PageUtil(page,1,totalCount);
			if(page<1) {
				page=1;
			}
			else if(page>pageUtil.getTotalPageCount()) {
				if(totalCount==0) {
					page=pageUtil.getTotalPageCount()+1;
				}else {
					page=pageUtil.getTotalPageCount();
				}
			}
			int pages=(page-1)*pageUtil.getPageSize();
			pageUtil.setPage(pages);
			list=intRoleService.queryAllRolePageLike(deleted, pageUtil.getPage(), pageUtil.getPageSize());
		}
    	
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listRolelike",list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
	
	//后台管理--添加角色
	@Transactional
	@ResponseBody
	@RequestMapping("/addRole")
    public int addRole(Role role){
    	int num=intRoleService.addRole(role);
    	return num;
    }
	//后台管理----查看权限功能--通过角色id查询出  当前角色的所有权限
	@ResponseBody
	@RequestMapping("/queryFunctionByRoleid")
    public List<Functions> queryFunctionByRoleid(Integer roleid){
		List<Functions> list=intRoleService.queryFunctionByRoleid(roleid);
    	
		List<Functions> list1=new ArrayList<>();
    	
    	for (int i = 0; i < list.size(); i++) {
    		Functions functions=new Functions();
    		functions.setId(list.get(i).getId());
    		functions.setFunctionFirst(list.get(i).getFunctionFirst());
    		functions.setFunctionSecond(list.get(i).getFunctionSecond());
    		functions.setSecondlist(Arrays.asList(list.get(i).getFunctionSecond().split(",")));
    		list1.add(functions);
		}
    	return list1;
    }
	//后台管理----编辑功能——通过角色id  查询出角色信息
	@ResponseBody
	@RequestMapping("/selectByPrimaryKey")
    public Map<String,Object> selectByPrimaryKey(Integer id) {
    	Role role=intRoleService.selectByPrimaryKey(id);//通过角色id 查询角色信息
    	List<Functions> listall=intRoleService.queryFunctionAll();//查询出功能表所有的信息  进行页面渲染
    	List<Functions> list=intRoleService.queryFunctionByRoleid(id);//通过角色id查询出当前角色的所有功能
    	
    	List<Functions> listByRoleid=new ArrayList<>();
    	for (int i = 0; i < list.size(); i++) {
    		Functions functions=new Functions();
    		functions.setId(list.get(i).getId());
    		functions.setFunctionFirst(list.get(i).getFunctionFirst());
    		functions.setFunctionSecond(list.get(i).getFunctionSecond());
    		functions.setSecondlist(Arrays.asList(list.get(i).getFunctionSecond().split(",")));
    		listByRoleid.add(functions);
		}
    	
    	HashMap<String, Object> map=new HashMap<>();
    	map.put("role",role);
    	map.put("listall",listall);
    	map.put("listByRoleid",listByRoleid);
    	return map;
    }
	//后台管理---保存编辑后的信息
	@Transactional
	@ResponseBody
	@RequestMapping("/upaBaocun")
	public int upaBaocun(Role role){
		List<Integer> list=intRoleService.queryAllIdByRoleid(role.getId());
		if(list.size()!=0) {
			for (int i = 0; i < list.size(); i++) {
				intRoleService.delFunctionByid(list.get(i));
			}
		}
		for (int i = 0; i < role.getListfunction().size(); i++) {
			intRoleService.add(role.getId(), role.getListfunction().get(i).getId());
		}
		int num=intRoleService.upaRole(role);
		return num;
	}
}

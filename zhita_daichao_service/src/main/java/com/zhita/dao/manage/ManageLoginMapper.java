package com.zhita.dao.manage;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.ManageLogin;

public interface ManageLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManageLogin record);

    int insertSelective(ManageLogin record);
    
    //后台管理---通过id查询出管理登陆用户信息
    ManageLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManageLogin record);

    int updateByPrimaryKey(ManageLogin record);
    
	ManageLogin findFormatByLoginName(String userName);

	int updateAdminLoginStatus(@Param("loginStatus")String loginStatus, @Param("phone")String phone,@Param("userName") String userName,@Param("registrationTime") String registrationTime);

	int getAdminId(@Param("phone")String phone,@Param("userName") String userName);
	
	int updateAdminLogOutStatus(@Param("loginStatus")String loginStatus,@Param("userId") int userId);
	
	//后台管理---查询出管理登陆用户表一共有多少条数据
	int pageCountManageLogin();
	
	//后台管理---查询出所有用户信息——含用户信息  用户的角色 含分页
	List<ManageLogin> queryManageLogin(Integer page,Integer pagesize);
	
	//后台管理---根据用户名  模糊查询出管理登陆用户表一共有多少条数据
	int pageCountManageLoginLike(String userName);
	
	//后台管理---根据用户名  模糊查询出所有用户信息——含用户信息  用户的角色 含分页
	List<ManageLogin> queryManageLoginLike(String userName,Integer page,Integer pagesize);
	
	//后台管理---根据账号状态  模糊查询出管理登陆用户表一共有多少条数据
	int pageCountManageLoginLike1(String deleted);
	
	//后台管理---根据账号状态  模糊查询出所有用户信息——含用户信息  用户的角色 含分页
	List<ManageLogin> queryManageLoginLike1(String deleted,Integer page,Integer pagesize);
	
	//后台管理---根据用户名和账号状态  模糊查询出管理登陆用户表一共有多少条数据
	int pageCountManageLoginLike2(String userName,String deleted);
	
	//后台管理---根据用户名和账号状态  模糊查询出所有用户信息——含用户信息  用户的角色 含分页
	List<ManageLogin> queryManageLoginLike2(String userName,String deleted,Integer page,Integer pagesize);
	
	//后台管理---添加后台管理用户
	int addManageLogin(ManageLogin manageLogin);
	
	//后台管理----将修改后的管理登陆用户   信息进行保存
	int upaManageLogin(ManageLogin manageLogin);
	
	//后台管理----修改管理登陆用户的假删除状态
	int upaManageloginFalseDel(Integer id);
	
	//后台管理---根据用户名查询出当前用户所拥有的角色
	List<String> queryRoleByName(String username);
	
	//后台管理---通过手机号查询用户信息
	ManageLogin  queryByPhone(String phone);
	
	//后台管理---通过手机号更新用户的登录状态和登录时间
	int upaStateTime(ManageLogin manageLogin);
	
	//后台管理---通过手机号获取用户的id
	ManageLogin getIdByPhone(String phone);
	
}
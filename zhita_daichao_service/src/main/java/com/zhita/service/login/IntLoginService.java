package com.zhita.service.login;

import java.util.List;
import java.util.Map;

import com.zhita.model.manage.ManageLogin;
import com.zhita.model.manage.Role;
import com.zhita.model.manage.User;

public interface IntLoginService {
	User findphone(String phone);

	int insertfootprint(String phone, String nickName, String openId, String registrationTime, String loginStatus);

	int updateloginStatus(String loginStatus, String openId, String phone);

	int getId(String phone);

	int updatelogOutStatus(String loginStatus, int userId);

	ManageLogin findFormatByLoginName(String userName);

	int updateAdminLoginStatus(String loginStatus, String phone, String userName, String registrationTime);

	int getAdminId(String phone, String userName);

	int updateAdminLogOutStatus(String loginStatus, int userId);

	String getLoginStatus(String openId);

	String getUserId(String openId);

	String getPhone(String openId);
	
	//后台管理---查询出管理登陆用户表一共有多少条数据
	public int pageCountManageLogin();

	int updateStatus(String loginStatus, String phone);

	int setAPPUser(String phone, String md5Pwd, int sourceId, String registrationTime, String loginStatus);


	//后台管理 ----查询出所有用户信息——含用户信息  用户的角色含分页
	public List<ManageLogin> queryManageLogin(Integer page,Integer pagesize);
	//后台管理---添加后台管理用户
	public int addManageLogin(ManageLogin manageLogin);
    //后台管理---通过id查询出管理登陆用户信息
    public ManageLogin selectByPrimaryKey(Integer id);
	//后台管理----将修改后的管理登陆用户   信息进行保存
	public int upaManageLogin(ManageLogin manageLogin);
    //后台管理---通过用户id查询出   在用户角色关联表中的id集合
    public List<Integer> queryByManageloginId(Integer manageloginid);
    //后台管理---在用户角色关联表中    根据主键id进行删除
    public int delManageloginRole(Integer id);
	//后台管理----修改管理登陆用户的假删除状态
	public int upaManageloginFalseDel(Integer id);
    //后台管理---通过传过来的用户id和角色id   在中间表进行插入数据
    public int add(Integer loginuserid,Integer roleid);
    
    //后台管理---通过传过来的条件个数  做各种情况的模糊查询
    public Map<String, Object> queryManageloginByLike(String userName,String deleted,Integer page);
    //后台管理---查询出所有的角色信息  不含分页
    public List<Role> queryAllRole();

}

package com.zhita.service.login;

import java.util.List;
import java.util.Map;

import com.zhita.model.manage.ManageLogin;
import com.zhita.model.manage.Role;
import com.zhita.model.manage.Source;
import com.zhita.model.manage.User;

public interface IntLoginService {
	User findphone(String phone, String company);

	int insertfootprint(String phone, String nickName, String openId, String registrationTime, String loginStatus, String company, String registrationType);

	int updateloginStatus(String loginStatus, String openId, String phone, String company, String loginTime);

	int getId(String phone, String company);

	int updatelogOutStatus(String loginStatus, int userId, String company);

	ManageLogin findFormatByLoginName(String userName);

	int updateAdminLoginStatus(String loginStatus, String phone, String userName, String registrationTime);

	int getAdminId(String phone, String userName);

	int updateAdminLogOutStatus(String loginStatus, int userId);
	
	int updateAdminLogOutStatus1(String loginStatus, int userId);

	String getLoginStatus(String openId, String company);

	String getUserId(String openId, String company);

	String getPhone(String openId, String company);
	
	//后台管理---查询出管理登陆用户表一共有多少条数据
	public int pageCountManageLogin();

	int updateStatus(String loginStatus, String phone, String company, String loginTime);

	int setAPPUser(String phone, String md5Pwd, int sourceId, String registrationTime, String loginStatus, String registrationType, String company);


	//后台管理 ----查询出所有用户信息——含用户信息  用户的角色含分页
	public List<ManageLogin> queryManageLogin(Integer page,Integer pagesize);
	//后台管理---添加后台管理用户
	public int addManageLogin(ManageLogin manageLogin);
	//后台管理---添加后台管理用户1
	public int addManageLogin1(ManageLogin manageLogin);
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
    //后台管理   通过手机号修改管理登陆用户的假删除状态
	public int upaMFalseDelByPhone(String phone);
	
    //后台管理---通过传过来的用户id和角色id  在中间表进行插入数据 
    public int add(Integer loginuserid,Integer roleid);
    
    //后台管理---通过传过来的条件个数  做各种情况的模糊查询
    public Map<String, Object> queryManageloginByLike(String userName,String deleted,Integer page);
    //后台管理---查询出所有的角色信息  不含分页
    public List<Role> queryAllRole();

	int updatePwd(String phone, String md5Pwd, String company);

	String getMd5pwd(String phone, String company);

	int insertfootprint1(String phone, String nickName, String openId, String registrationTime, String loginStatus,
			String company, String registrationType, Integer fatherId);

	int updateloginStatus1(String loginStatus, String openId, String phone, String company, String loginTime,
			Integer fatherId);

	int updateStatus1(String loginStatus, String phone, String company, String loginTime, Integer fatherId);

	String getPwd(int id);

	int setPwd(int userId, String md5Pwd);

	int insertUser(String phone, String loginStatus, String company, String registrationType, String registrationTime, int sourceId);

	//后台管理---根据用户名查询出当前用户所拥有的角色
	public List<String> queryRoleByName(String username);
	
	//后台管理---通过手机号查询用户信息
	public ManageLogin  queryByPhone(String phone);
	
	//后台管理---通过账号查询渠道信息
	public Source queryByAcount(String acount);
	
	//后台管理---通过手机号更新用户的登录状态和登录时间
	public int  upaStateTime(ManageLogin manageLogin);
	//后台管理---通过手机号获取用户的id
	public ManageLogin getIdByPhone(String phone);

	int setAPPUser1(String phone, String md5Pwd, int merchantId, String registrationTime, String loginStatus,
			String registrationType, String company, String sonSourceName);

	int insertUser1(String phone, String loginStatus, String company, String registrationType, String registrationTime,
			int merchantId, String sonSourceName);

}

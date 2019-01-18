package com.zhita.service.login;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhita.dao.manage.ManageLoginMapper;
import com.zhita.dao.manage.ManageloginRoleMapper;
import com.zhita.dao.manage.RoleMapper;
import com.zhita.dao.manage.UserMapper;
import com.zhita.model.manage.ManageLogin;
import com.zhita.model.manage.Role;
import com.zhita.model.manage.User;
import com.zhita.util.PageUtil;


@Service(value="loginServiceImp")
public class LoginServiceImp implements IntLoginService{

	@Autowired
	ManageLoginMapper manageLoginMapper;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	ManageloginRoleMapper manageloginRoleMapper;
	
	@Autowired
	RoleMapper roleMapper;

	public ManageLogin findFormatByLoginName(String userName) {
		ManageLogin manageLogin = manageLoginMapper.findFormatByLoginName(userName);
		return manageLogin;
	}

	@Override
	public User findphone(String phone,String company) {
		User user = userMapper.findphone(phone,company);
		return user;
	}

	@Override
	public int insertfootprint(String phone, String nickName, String openId,String registrationTime,String loginStatus,String company,String registrationType) {
		int number = userMapper.insertfootprint(phone, nickName, openId,registrationTime,loginStatus,company,registrationType);
		return number;
	}

	@Override
	public int updateloginStatus(String loginStatus,String openId,String phone,String company,String loginTime) {
		int number = userMapper.updateloginStatus(loginStatus,openId,phone,company,loginTime);
		return number;
	}

	@Override
	public int getId(String phone,String company) {
		int id = userMapper.getId(phone,company);
		return id;
	}

	@Override
	public int updatelogOutStatus(String loginStatus, int userId,String company) {
		int number = userMapper.updatelogOutStatus(loginStatus,userId,company);
		return number;
	}

	@Override
	public int updateAdminLoginStatus(String loginStatus, String phone, String userName, String registrationTime) {
		int number = manageLoginMapper.updateAdminLoginStatus(loginStatus,phone,userName,registrationTime);
		return number;
	}

	@Override
	public int getAdminId(String phone, String userName) {
		int id = manageLoginMapper.getAdminId(phone,userName);
		return id;
	}

	@Override
	public int updateAdminLogOutStatus(String loginStatus, int userId) {
		int number = manageLoginMapper.updateAdminLogOutStatus(loginStatus,userId);
		return number;
	}

	@Override
	public String getLoginStatus(String openId,String company) {
		String loginStatus = userMapper.getLoginStatus(openId,company);
		return loginStatus;
	}

	@Override
	public String getUserId(String openId,String company) {
		String userId = userMapper.getUserId(openId,company);
		return userId;
	}

	@Override
	public String getPhone(String openId,String company) {
		String phone = userMapper.getPhone(openId,company);
		return phone;
	}

	//后台管理---查询出管理登陆用户表一共有多少条数据
	public int pageCountManageLogin() {
		int count=manageLoginMapper.pageCountManageLogin();
		return count;
	}
	//后台管理----查询出所有用户信息——含用户信息  用户的角色含分页
	public List<ManageLogin> queryManageLogin(Integer page,Integer pagesize){
		List<ManageLogin> list=manageLoginMapper.queryManageLogin(page,pagesize);
		return list;
	}

	@Override
	public int updateStatus(String loginStatus, String phone,String company,String loginTime) {
		int num = userMapper.updateStatus(loginStatus,phone,company,loginTime);
		return num;
	}

	public int setAPPUser(String phone, String md5Pwd, int sourceId, String registrationTime,String loginStatus,String registrationType,String company) {
		int number = userMapper.setAPPUser(phone, md5Pwd, sourceId,registrationTime,loginStatus,registrationType,company);
		return number;
	}

	//后台管理---添加后台管理用户
	public int addManageLogin(ManageLogin manageLogin) {
		int num=manageLoginMapper.addManageLogin(manageLogin);
		return num;
	}
    //后台管理---通过id查询出管理登陆用户信息
    public ManageLogin selectByPrimaryKey(Integer id) {
    	ManageLogin manageLogin=manageLoginMapper.selectByPrimaryKey(id);
    	return manageLogin;
    }
	//后台管理----将修改后的管理登陆用户   信息进行保存
	public int upaManageLogin(ManageLogin manageLogin) {
		int num=manageLoginMapper.upaManageLogin(manageLogin);
		return num;
	}
    //后台管理---通过用户id查询出   在用户角色关联表中的id集合
    public List<Integer> queryByManageloginId(Integer manageloginid){
    	List<Integer> list=manageloginRoleMapper.queryByManageloginId(manageloginid);
    	return list;
    }
    //后台管理---在用户角色关联表中    根据主键id进行删除
    public int delManageloginRole(Integer id) {
    	int num=manageloginRoleMapper.delManageloginRole(id);
    	return num;
    }
	//后台管理----修改管理登陆用户的假删除状态
	public int upaManageloginFalseDel(Integer id) {
		int num=manageLoginMapper.upaManageloginFalseDel(id);
		return num;
	}
    //后台管理---通过传过来的用户id和角色id   在中间表进行插入数据
    public int add(Integer loginuserid,Integer roleid) {
    	int num=manageloginRoleMapper.add(loginuserid, roleid);
    	return num;
    }
    //后台管理---通过传过来的条件个数  做各种情况的模糊查询
    public Map<String, Object> queryManageloginByLike(String userName,String deleted,Integer page){
    	PageUtil pageUtil=null;
    	List<ManageLogin> list=null;
    	//不带任何条件
    	if((userName==null||"".equals(userName))&&(deleted==null||"".equals(deleted))) {
    		System.out.println("第一个if");
    	   	int totalCount=manageLoginMapper.pageCountManageLogin();//查询出管理登陆用户表一共有多少条数据
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
        	list=manageLoginMapper.queryManageLogin(pageUtil.getPage(),pageUtil.getPageSize());
        	for (int i = 0; i < list.size(); i++) {
    			System.out.println(list.get(i).getUsername());
    		}
    	}
    	//通过用户名模糊查询
    	else if((userName!=null||!"".equals(userName))&&(deleted==null||"".equals(deleted))) {
    		System.out.println("第二个if");
           	int totalCount=manageLoginMapper.pageCountManageLoginLike(userName);//通过用户名查询出管理登陆用户表一共有多少条数据
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
        	list=manageLoginMapper.queryManageLoginLike(userName, pageUtil.getPage(), pageUtil.getPageSize());
        	for (int i = 0; i < list.size(); i++) {
    			System.out.println(list.get(i).getUsername());
    		}
    	}
    	//通过账号状态模糊查询
    	else if((userName==null||"".equals(userName))&&(deleted!=null||!"".equals(deleted))) {
    		System.out.println("第三个if");
           	int totalCount=manageLoginMapper.pageCountManageLoginLike1(deleted);//通过用户状态查询出管理登陆用户表一共有多少条数据
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
        	list=manageLoginMapper.queryManageLoginLike1(deleted, pageUtil.getPage(), pageUtil.getPageSize());
        	for (int i = 0; i < list.size(); i++) {
    			System.out.println(list.get(i).getUsername());
    		}
    	}
    	else if((userName!=null||!"".equals(userName))&&(deleted!=null||!"".equals(deleted))) {
    		System.out.println("第四个if");
           	int totalCount=manageLoginMapper.pageCountManageLoginLike2(userName, deleted);//通过用户名和用户状态查询出管理登陆用户表一共有多少条数据
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
        	list=manageLoginMapper.queryManageLoginLike2(userName, deleted, pageUtil.getPage(), pageUtil.getPageSize());
        	for (int i = 0; i < list.size(); i++) {
    			System.out.println(list.get(i).getUsername());
    		}
    	}
       	
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listManagelogin",list);
    	map.put("pageutil", pageUtil);
    	return map;
    
    }
    //后台管理---查询出所有的角色信息  不含分页
    public List<Role> queryAllRole(){
    	List<Role> list=roleMapper.queryAllRole();
    	return list;
    }

	@Override
	public int updatePwd(String phone, String md5Pwd) {
		int number = userMapper.updatePwd(phone, md5Pwd);
		return number;
	}

	@Override
	public String getMd5pwd(String phone) {
		String dataMd5Pwd = userMapper.getMd5pwd(phone);
		return dataMd5Pwd;
	}

	@Override
	public int insertfootprint1(String phone, String nickName, String openId, String registrationTime,
			String loginStatus, String company, String registrationType, Integer fatherId) {
		int number = userMapper.insertfootprint1(phone, nickName, openId,registrationTime,loginStatus,company,registrationType,fatherId);
		return number;
	}

	@Override
	public int updateloginStatus1(String loginStatus, String openId, String phone, String company, String loginTime,
			Integer fatherId) {
		int num = userMapper.updateloginStatus1(loginStatus,openId,phone,company,loginTime,fatherId); 
		return num;
	}

	@Override
	public int updateStatus1(String loginStatus, String phone, String company, String loginTime, Integer fatherId) {
		int num = userMapper.updateStatus1(loginStatus,phone,company,loginTime,fatherId); 
		return num;
	}

	@Override
	public String getPwd(int id) {
		String pwd = userMapper.getPwd(id);
		return pwd;
	}

	@Override
	public int setPwd(int userId, String md5Pwd) {
		int number = userMapper.setPwd(userId, md5Pwd);
		return number;
	}

	@Override
	public int insertUser(String phone, String loginStatus, String company, String registrationType,
			String registrationTime) {
		int number = userMapper.insertUser(phone,loginStatus,company,registrationType,registrationTime);
		return number;
	}


	//后台管理---根据用户名查询出当前用户所拥有的角色
	public List<String> queryRoleByName(String username){
		List<String> list=manageLoginMapper.queryRoleByName(username);
		return list;
	}
	//后台管理---通过手机号查询用户信息
	public ManageLogin  queryByPhone(String phone) {
		ManageLogin manageLogin=manageLoginMapper.queryByPhone(phone);
		return manageLogin;
	}
	//后台管理---通过手机号更新用户的登录状态和登录时间
	public int  upaStateTime(ManageLogin manageLogin) {
		int sum=manageLoginMapper.upaStateTime(manageLogin);
		return sum;
	}
	//后台管理---通过手机号获取用户的id
	public int getIdByPhone(String phone) {
		int id=manageLoginMapper.getIdByPhone(phone);
		return id;
	}

}

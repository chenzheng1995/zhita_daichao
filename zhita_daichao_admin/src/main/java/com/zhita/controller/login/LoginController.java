package com.zhita.controller.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.controller.shiro.PhoneToken;
import com.zhita.model.manage.ManageLogin;
import com.zhita.model.manage.Role;
import com.zhita.service.login.IntLoginService;
import com.zhita.util.PageUtil;
import com.zhita.util.RedisClientUtil;
import com.zhita.util.SMSUtil;
import com.zhita.util.Timestamps;



@Controller
@RequestMapping("/admin_login")
public class LoginController {
	@Autowired
	IntLoginService loginService;
	
	//后台管理----登录验证  以及授权
	@ResponseBody
	@RequestMapping(value="/logintest", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public Map<String, Object> login(String phone,String code){
		Map<String, Object> map = new HashMap<String, Object>();
		int a=0;
		
		RedisClientUtil redisClientUtil = new RedisClientUtil();
		String key = phone+"Key";
		String redisCode = redisClientUtil.get(key);
		
		if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)) {
			map.put("msg", "phone或验证码不能为空");
		}else {
			PhoneToken token=new PhoneToken(phone);
	        Subject subject = SecurityUtils.getSubject();
	        	try {
	                //执行认证操作. 
	                subject.login(token);
	                a=1;
	            }catch (UnknownAccountException e) {
	            	map.put("msg", "没有此手机号");
	           }
	        	System.out.println("最外层："+"a:"+a+"rediscode:"+redisCode+"code:"+code);
	        	if(a==1) {
	        		if(redisCode==null||"".equals(redisCode)){
	        			map.put("msg", "验证码过期，请重新发送");
	        		}else{
	        			if(redisCode.equals(code)) {
		                    String loginStatus="1";
		                	String registrationTime = System.currentTimeMillis()+"";  //获取当前时间戳
		                	
		                	ManageLogin manageLogin=new ManageLogin();
		                	manageLogin.setLoginstatus(loginStatus);
		                	manageLogin.setLogintime(registrationTime);
		                	manageLogin.setPhone(phone);
		                	int num=loginService.upaStateTime(manageLogin);
							if (num == 1) {	
								ManageLogin manageLogin2 = loginService.getIdByPhone(phone);//通过手机号获取当前用户对象
								int id=manageLogin2.getId();//获取用户id
								String company=manageLogin2.getCompany();//获取用户公司名
								
								String[] listCompany=company.split(",");//该数组里面存了当前用户所有的公司名
								//List<String> listcom=new ArrayList<>();//new集合  存通过公司id查询出来的公司名称
								/*for (int i = 0; i < listCompany.length; i++) {
									listcom.add(company);
								}*/
								
								map.put("msg", "用户登录成功，登录状态修改成功");
								map.put("loginStatus", loginStatus);
								map.put("userId", id);
								map.put("company", listCompany);//集合里面存的是当前用户的所有公司名
							} else {
								map.put("msg", "用户登录失败，登录状态修改失败");
							}
	        			}else {
	        				map.put("msg2", "验证码错误");
	        			}
	        		}
	        	}
		}
		return map;
	}
	
	//发送验证码
	@RequestMapping("/sendSMS")
	@ResponseBody
	public Map<String, String> sendSMS(String phone,String companyName){
		Map<String, String> map = new HashMap<>();
		SMSUtil smsUtil = new SMSUtil();
		String state = smsUtil.sendSMS(phone, "json",companyName);
        map.put("msg",state);		
		return map;	
	}

	//通过验证码登录
	/**
	 * 
	 * @param userName 用户名
	 * @param code 验证码
	 * @param phone 手机号
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(String userName,String code,String phone) {
		RedisClientUtil redisClientUtil = new RedisClientUtil();
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(code) || StringUtils.isEmpty(phone)) {
			map.put("msg", "userName,code或phone不能为空");
			return map;
		} else {
			String key = phone+"Key";
			String redisCode = redisClientUtil.get(key);
			if(redisCode==null) {
				map.put("msg", "验证码已过期，请重新发送");
				return map;
			}
			if(redisCode.equals(code)) {
				redisClientUtil.delkey(key);//验证码正确就从redis里删除这个key
				ManageLogin manageLogin = loginService.findFormatByLoginName(userName); // 判断该用户是否存在
				if (manageLogin == null) {
					map.put("msg", "用户名不存在");
					return map;
				}else {
					String loginStatus = "1";
					String registrationTime = System.currentTimeMillis()+"";  //获取当前时间戳
					int number = loginService.updateAdminLoginStatus(loginStatus,phone,userName,registrationTime);
					if (number == 1) {	
						int id = loginService.getAdminId(phone,userName); //获取该用户的id
						map.put("msg", "用户登录成功，登录状态修改成功");
						map.put("loginStatus", loginStatus);
						map.put("userId", id);
					} else {
						map.put("msg", "用户登录失败，登录状态修改失败");
					}
				}

			}else {
				map.put("msg", "验证码输入错误");
			}

		}

		return map;

	}
	
	// 退出登录
	@RequestMapping("/logOut")
	@ResponseBody
	public Map<String, String> logOut(int userId) {
		Map<String, String> map = new HashMap<>();
		if (StringUtils.isEmpty(userId)) {
			map.put("msg", "userId不能为空");
			return map;
			}else {
				String loginStatus = "0";
				int number = loginService.updateAdminLogOutStatus(loginStatus,userId);
				if (number == 1) {														
					map.put("msg", "用户退出成功，登录状态修改成功");
					map.put("loginStatus", loginStatus);
				} else {
					map.put("msg", "用户退出失败，登录状态修改失败");
				}
			}

		return map;

	}
	//后台管理----查询出所有用户信息——含用户信息  用户的角色 含分页
    @ResponseBody
    @RequestMapping("/queryAllManageLogin")
    public Map<String,Object> queryAllManageLogin(Integer page){
    	int totalCount=loginService.pageCountManageLogin();//查询出管理登陆用户表一共有多少条数据
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
    	List<ManageLogin> list=loginService.queryManageLogin(pageUtil.getPage(),pageUtil.getPageSize());
    	for (int i = 0; i < list.size(); i++) {
    		list.get(i).setLogintime(Timestamps.stampToDate(list.get(i).getLogintime()));
		}
    	
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listManagelogin",list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
	//后台管理----根据传进去的条件 做各种条件的模糊查询  含分页
    @ResponseBody
    @RequestMapping("/queryManageloginByLike")
    public Map<String,Object> queryManageloginByLike(String username,String deleted,Integer page){
    	Map<String,Object> map=loginService.queryManageloginByLike(username, deleted, page);
    	return map;
    }
	//后台管理---添加后台管理用户
    @Transactional
    @ResponseBody
    @RequestMapping("/addManageLogin")
    public List<Role> addManageLogin(ManageLogin manageLogin){
    	List<Role> listrole=loginService.queryAllRole();//添加用户信息时   先查询出所有角色的id 和  角色名称    以供添加角色时做选择
    	List<String> list=manageLogin.getListcompany();//得到前端传过来的公司名的集合
    	String str="";
    	for (int i = 0; i < list.size(); i++) {
    		if(i==0) {
    			str=list.get(i)+",";
    		}else {
    			str=str+list.get(i)+",";
    		}
    		
		}
    	manageLogin.setCompany(str);
    	int num=loginService.addManageLogin(manageLogin);
    	for (int i = 0; i < manageLogin.getListRole().size(); i++) {
    		int num1=loginService.add(manageLogin.getId(), manageLogin.getListRole().get(i).getId());
		}
    	return listrole;
    }
	//后台管理---通过id查询出管理登陆用户信息
    @ResponseBody
    @RequestMapping("/selectByPrimaryKey")
    public Map<String, Object> selectByPrimaryKey(Integer id){
    	List<Role> list=loginService.queryAllRole();//修改用户信息时   先查询出所有角色的id 和  角色名称    以供修改角色时做选择
    	List<String> listcompany=new ArrayList<>();//用来存当前用户所有的公司名
    	ManageLogin manageLogin=loginService.selectByPrimaryKey(id);
    	String company=manageLogin.getCompany();//得到当前用户所有的公司名
    	String[] companyarray=company.split(",");
    	for (int i = 0; i < companyarray.length; i++) {
    		listcompany.add(companyarray[i]);
		}
    	
    	manageLogin.setListcompany(listcompany);
    	
      	HashMap<String,Object> map=new HashMap<>();
    	map.put("listRole",list);
    	map.put("manageLogin", manageLogin);
    	return map;
    }
	//后台管理---将修改后的管理登陆用户   信息进行保存
    @Transactional
    @ResponseBody
    @RequestMapping("/upaManageLogin")
    public Integer upaManageLogin(@RequestBody ManageLogin manageLogin){
    	List<Integer> list=loginService.queryByManageloginId(manageLogin.getId());//保存修改信息时   先查询出当前用户在中间表的数据id集合（因为一个用户可能有多个角色）
    	if(list.size()!=0) {
        	for (int i = 0; i < list.size(); i++) {
    			loginService.delManageloginRole(list.get(i));//修改保存信息时   先删除中间表的信息
    		}
    	}
    	for (int i = 0; i < manageLogin.getListRole().size(); i++) {
			loginService.add(manageLogin.getId(), manageLogin.getListRole().get(i).getId());
		}
    	List<String> list1=manageLogin.getListcompany();//得到前端传过来的公司名的集合
    	String str="";
    	for (int i = 0; i < list1.size(); i++) {
    		if(i==0) {
    			str=list1.get(i)+",";
    		}else {
    			str=str+list1.get(i)+",";
    		}
    	}
    	manageLogin.setCompany(str);
    	int num=loginService.upaManageLogin(manageLogin);
    	return num;
    }
	//后台管理---删除用户(可有可无)
    @Transactional
    @ResponseBody
    @RequestMapping("/delManagelogin")
    public int delManagelogin(Integer id){
    	List<Integer> list=loginService.queryByManageloginId(id);//将管理登陆用户的假删除状态改为  删除  的时候      先查询出当前用户在中间表的数据id集合（因为一个用户可能有多个角色）
    	for (int i = 0; i < list.size(); i++) {
			loginService.delManageloginRole(list.get(i));//将管理登陆用户的假删除状态改为  删除  的时候      先删除中间表的信息
		}
    	int num=loginService.upaManageloginFalseDel(id);//修改用户假删除状态为删除
    	return num;
    }

}

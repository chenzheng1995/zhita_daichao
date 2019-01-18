package com.zhita.controller.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.ManageLogin;
import com.zhita.model.manage.Role;
import com.zhita.service.login.IntLoginService;
import com.zhita.util.PageUtil;
import com.zhita.util.RedisClientUtil;
import com.zhita.util.SMSUtil;



@Controller
@RequestMapping("/admin_login")
public class LoginController {
	@Autowired
	IntLoginService loginService;
	
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
			System.out.println(list.get(i).getUsername());
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
    @ResponseBody
    @RequestMapping("/addManageLogin")
    public int addManageLogin(ManageLogin manageLogin){
    	int num=loginService.addManageLogin(manageLogin);
    	return num;
    }
	//后台管理---通过id查询出管理登陆用户信息
    @ResponseBody
    @RequestMapping("/selectByPrimaryKey")
    public Map<String, Object> selectByPrimaryKey(Integer id){
    	List<Role> list=loginService.queryAllRole();//修改用户信息时   先查询出所有角色的id 和  角色名称    以供修改角色时做选择
    	ManageLogin manageLogin=loginService.selectByPrimaryKey(id);
    	
      	HashMap<String,Object> map=new HashMap<>();
    	map.put("listRole",list);
    	map.put("manageLogin", manageLogin);
    	return map;
    }
	//后台管理---将修改后的管理登陆用户   信息进行保存
    @ResponseBody
    @RequestMapping("/upaManageLogin")
    public int upaManageLogin(ManageLogin manageLogin){
    	List<Integer> list=loginService.queryByManageloginId(manageLogin.getId());//保存修改信息时   先查询出当前用户在中间表的数据id集合（因为一个用户可能有多个角色）
    	if(list.size()!=0) {
        	for (int i = 0; i < list.size(); i++) {
    			loginService.delManageloginRole(list.get(i));//修改保存信息时   先删除中间表的信息
    		}
    	}
    	for (int i = 0; i < manageLogin.getListRole().size(); i++) {
			loginService.add(manageLogin.getId(), manageLogin.getListRole().get(i).getId());
		}
    	int num=loginService.upaManageLogin(manageLogin);
    	return num;
    }
	//后台管理---删除用户(可有可无)
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

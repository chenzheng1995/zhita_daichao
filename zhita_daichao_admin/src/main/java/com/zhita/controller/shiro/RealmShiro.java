package com.zhita.controller.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhita.model.manage.Functions;
import com.zhita.model.manage.ManageLogin;
import com.zhita.service.login.IntLoginService;
import com.zhita.service.role.IntRoleService;

public class RealmShiro extends AuthorizingRealm{
	@Resource(name = "loginServiceImp")
	private IntLoginService intLoginService;
	
	@Autowired
	private IntRoleService intRoleService;
	
	private String phone;
	
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("进入权限模块");
		// 能进入到这里，表示账号已经通过验证了
		ManageLogin manageLogin = (ManageLogin) arg0.getPrimaryPrincipal();
		String phone=manageLogin.getPhone();
		// 获取账号的角色和权限信息
		List<String> list = intLoginService.queryRoleByName(phone);//查询当前用户所拥有的角色
		Set<String> role = new HashSet(list);
		
		for (String str : role) {  
		      System.out.println("角色："+str);  
		} 
		
		List<Functions> list1 = intRoleService.queryFunctionByName(phone);//查询当前用户所拥有的权限
		Set<String> function=new HashSet<>();
		for (int i = 0; i < list1.size(); i++) {
			String functionFirst=list1.get(i).getFunctionFirst();
			String functionSecond=list1.get(i).getFunctionSecond();
			String hebing=functionFirst+"-"+functionSecond;
			function.add(hebing);
		}
		
		for (String str : function) {  
		      System.out.println("权限："+str);  
		} 
		
		// 授权对象信息
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setStringPermissions(function);
		authorizationInfo.setRoles(role);
		System.out.println("输出返回的内容："+authorizationInfo);
		return authorizationInfo;
	}
	
	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		 PhoneToken token=(PhoneToken)arg0; 
	     //第一步：从token中取出手机号，这个手机号是用户在页面输入的信息，传递给token
		 String phone = (String) token.getPrincipal();
		 System.out.println("phone:"+phone); //输出当前登录用户的用户名
		 ManageLogin manageLogin = intLoginService.queryByPhone(phone);// 判断该用户是否存在
		 if (manageLogin == null) {
		    // 手机号不存在抛出异常
		    System.out.println("认证：当前登录的手机号不存在");
			throw new UnknownAccountException();//抛出未知账号异常
		}
		 return new SimpleAuthenticationInfo(manageLogin, phone, this.getName());
	}
	
}

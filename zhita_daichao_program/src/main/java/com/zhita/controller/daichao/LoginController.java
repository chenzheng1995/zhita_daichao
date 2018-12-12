package com.zhita.controller.daichao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.ManageLogin;
import com.zhita.service.login.IntLoginService;
import com.zhita.util.MD5Util;


@Controller
@RequestMapping(value="/admin_login")
public class LoginController {
	@Resource(name="loginServiceImp")
	private IntLoginService intLoginService;

	public IntLoginService getIntLoginService() {
		return intLoginService;
	}

	public void setIntLoginService(IntLoginService intLoginService) {
		this.intLoginService = intLoginService;
	}

	@RequestMapping("/login")
	@ResponseBody
	public Map<String, String> login(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		String userName = request.getParameter("userName");
		String Pwd = request.getParameter("Pwd");

		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(Pwd)) {
			map.put("msg", "用户名密码不能为空");
			return map;
		} else {
			ManageLogin manageLogins = intLoginService.findFormatByLoginName(userName); // 判断用户名是否存在
			if (manageLogins == null) {
				map.put("msg", "用户名不存在");
				return map;
			}
			MD5Util md5Util = new MD5Util();
			String dataMd5Pwd = manageLogins.getMd5pwd();
			String Md5Pwd = md5Util.EncoderByMd5(Pwd); // md5加密
			if (!Md5Pwd.equals(dataMd5Pwd)) {
				map.put("msg", "密码错误");
				return map;
			}else {
				map.put("msg", "200");
			}
		}

		return map;

	}

}

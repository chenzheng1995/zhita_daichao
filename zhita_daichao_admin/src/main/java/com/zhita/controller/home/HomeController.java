package com.zhita.controller.home;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.service.apply.ApplyService;
import com.zhita.service.user.UserService;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	UserService userService;
	ApplyService applyService;

	@RequestMapping("/gethome")
	@ResponseBody
	public Map<String, Integer> getHome(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Integer> map = new HashMap<>();
		Integer registered = userService.getregistered(); // 获取总注册数
		map.put("registered", registered);
		return map;

	}
}

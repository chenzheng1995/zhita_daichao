package com.zhita.controller.vestbag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhita.service.vestbug.VestBugService;

@Controller
@RequestMapping("/vestbag")
public class VestBagController {
	@Autowired
	VestBugService vestBugService;

}

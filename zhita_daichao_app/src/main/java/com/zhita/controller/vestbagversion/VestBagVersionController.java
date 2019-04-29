package com.zhita.controller.vestbagversion;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.service.vestbug.VestBugService;
import com.zhita.service.vestbugversion.VestBugVersionService;

@Controller
@RequestMapping("/vestbagversion")
public class VestBagVersionController {
	@Autowired
	VestBugVersionService vestBugVersionService;
	
	@Autowired
	VestBugService vestBugService;
	
	
	//查询版本号version
	@RequestMapping("/getVestBagVersion")
	@ResponseBody
	@Transactional
	public Map<String, Object> getVestBagVersion(String company,String vestBagName,String oneSourceName,String twoSourceName) {
		Map<String, Object> map = new HashMap<>();
 		Integer id = vestBugService.getVestBag(vestBagName,company);
//		if (id == null) {								
//			vestBugService.setVestBag(vestBagName,company);
//			id = vestBugService.getVestBag(vestBagName,company);
//		}
		String version = vestBugVersionService.getVersion(id);
		map.put("version", version);	
		return map;
	}
}

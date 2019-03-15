package com.zhita.controller.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.service.notice.NoticeService;
import com.zhita.service.registe.IntRegisteService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	NoticeService noticeService;
	
	@Autowired
	IntRegisteService intRegisteService;
	
	@ResponseBody
	@RequestMapping("/getnotice")
	@Transactional
	public Map<String, Object> getnotice(String company){
		Map<String, Object> map = new HashMap<>();
		List<String> namelist = noticeService.getname();
		List<String> moneylist = noticeService.getmoney();
		List<String> loansBusinesseslist = intRegisteService.BusinessesName(company);
		map.put("namelist", namelist);
		map.put("moneylist", moneylist);
		map.put("loansBusinesseslist", loansBusinesseslist);		
		return map;
		
	}

}

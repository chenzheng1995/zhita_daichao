package com.zhita.controller.source;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.ShufflingFigure;
import com.zhita.service.banner.IntBannerService;
import com.zhita.util.RedisClientUtil;

@Controller
@RequestMapping("/Source")
public class SourceController {
	
	@Autowired
	IntBannerService intBannerService;

	@RequestMapping("/getSourceClick")
	@ResponseBody
	@Transactional
	public Map<String, Object> getqrcode(String company,String source) {
		Map<String, Object> map = new HashMap<>();
    	RedisClientUtil redisClientUtil = new RedisClientUtil();
    	String SourceClick = redisClientUtil.get(company+source+"Key");
    	if(SourceClick==null) {
    		redisClientUtil.set(company+source+"Key","1");
    		System.out.println(redisClientUtil.getSourceClick(company+source+"Key"));
    		map.put("msg","200");
    	}else {
    		redisClientUtil.set(company+source+"Key",Integer.parseInt(redisClientUtil.getSourceClick(company+source+"Key"))+1+""); //由于value是string类型的，所以先转换成int类型，+1之后在转换成string类型
    		System.out.println(redisClientUtil.getSourceClick(company+source+"Key"));
    		map.put("msg","200");
		}
    	return map;
	
	}
}

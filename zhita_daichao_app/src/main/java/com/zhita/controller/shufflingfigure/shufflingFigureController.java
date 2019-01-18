package com.zhita.controller.shufflingfigure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.ShufflingFigure;
import com.zhita.service.banner.IntBannerService;

@Controller
@RequestMapping("/shufflingFigure")
public class shufflingFigureController {
	
	@Autowired
	IntBannerService intBannerService;

	@RequestMapping("/getshufflingFigure")
	@ResponseBody
	public Map<String, Object> getqrcode(String company) {
    	List<ShufflingFigure> list=intBannerService.getShufflingFigure(company); //获取轮播图的所有数据   	
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listshuff",list);
    	return map;
	
	}
}

package com.zhita.controller.shufflingfigure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.ShufflingFigure;
import com.zhita.model.manage.ShufflingFigureCopy;
import com.zhita.service.banner.IntBannerService;
import com.zhita.service.banner.IntBannerServiceCopy;
import com.zhita.service.sourcedadson.SourceDadSonService;

@Controller
@RequestMapping("/shufflingFigure")
public class shufflingFigureController {
	
	@Autowired
	IntBannerService intBannerService;
	
	@Autowired
	IntBannerServiceCopy intBannerServiceCopy;
	
	@Autowired
	SourceDadSonService sourceDadSonService;

	@RequestMapping("/getshufflingFigure")
	@ResponseBody
	public Map<String, Object> getqrcode(String company,String oneSourceName,String twoSourceName) {
		HashMap<String,Object> map=new HashMap<>();
    	String  tableType = sourceDadSonService.getTableType(oneSourceName,twoSourceName,company);	
    	if(tableType.equals("1")) {
    	List<ShufflingFigure> list=intBannerService.getShufflingFigure(company); //获取轮播图的所有数据   	
    	map.put("listshuff",list);
    	}
    	if(tableType.equals("2")) {
    	List<ShufflingFigureCopy> list=intBannerServiceCopy.getShufflingFigureAppCopy(company,oneSourceName,twoSourceName); //获取轮播图的所有数据   	
    	map.put("listshuff",list);
    	}
    	return map;
	}
}

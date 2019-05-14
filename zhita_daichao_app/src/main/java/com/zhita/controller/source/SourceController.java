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
import com.zhita.model.manage.Source;
import com.zhita.service.banner.IntBannerService;
import com.zhita.service.merchant.IntMerchantService;
import com.zhita.util.RedisClientUtil;

@Controller
@RequestMapping("/Source")
public class SourceController {
	
	@Autowired
	IntMerchantService intMerchantService;

	@RequestMapping("/getSourceClick")
	@ResponseBody
	@Transactional
	public Map<String, Object> getqrcode(String company,String source,String date,String sonSource,String oneSourceName,String twoSourceName) {
		String	RedisCompany= null;
		if(company.equals("借吧")) {
		    RedisCompany="融51";
		}
		Map<String, Object> map = new HashMap<>();
    	RedisClientUtil redisClientUtil = new RedisClientUtil();
    	String SourceClick = redisClientUtil.get(RedisCompany+source+date+"Key");
    	if(SourceClick==null) {
    		redisClientUtil.set(RedisCompany+source+date+"Key","1");
    		System.out.println(redisClientUtil.getSourceClick(RedisCompany+source+date+"Key"));
    		map.put("msg","父渠道点击量添加成功");
    	}else {
    		redisClientUtil.set(RedisCompany+source+date+"Key",Integer.parseInt(redisClientUtil.getSourceClick(RedisCompany+source+date+"Key"))+1+""); //由于value是string类型的，所以先转换成int类型，+1之后在转换成string类型
    		System.out.println(redisClientUtil.getSourceClick(RedisCompany+source+date+"Key"));
    		map.put("msg","父渠道点击量添加成功");
		}
    	
    	String sonSourceClick = redisClientUtil.get(RedisCompany+sonSource+date+"Key");
    	if(sonSourceClick==null) {
    		redisClientUtil.set(RedisCompany+sonSource+date+"Key","1");
    		System.out.println(redisClientUtil.getSourceClick(RedisCompany+sonSource+date+"Key"));
    		map.put("msg","子渠道点击量添加成功");
    	}else {
    		redisClientUtil.set(RedisCompany+sonSource+date+"Key",Integer.parseInt(redisClientUtil.getSourceClick(RedisCompany+sonSource+date+"Key"))+1+""); //由于value是string类型的，所以先转换成int类型，+1之后在转换成string类型
    		System.out.println(redisClientUtil.getSourceClick(RedisCompany+sonSource+date+"Key"));
    		map.put("msg","子渠道点击量添加成功");
		}

    	return map;
	
	}
}

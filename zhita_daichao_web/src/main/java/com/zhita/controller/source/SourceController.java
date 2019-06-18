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
	
	
	//判断这个渠道有没有删除或禁用，如果删除或禁用了就不让用户显示推广页
	@RequestMapping("/isPromotion")
	@ResponseBody
	@Transactional
	public Map<String, Object> ispromotion (String company,String sourceName) {
		Map<String, Object> map = new HashMap<>();
		 map.put("msg","成功");
		 map.put("code","200");
		List<String> list = intMerchantService.getstateAndDeleted(company,sourceName);
	    for (String string : list) {
	     String	state = string;
		 if("2".equals(state)) {
			 map.put("msg","渠道被禁用");
			 map.put("code","300");
			 return map;
		 }
		}
	    
	    List<String> list1 = intMerchantService.getDeleted(company,sourceName);
	    for (String string : list1) {
		     String	deleted = string;
			 if("1".equals(deleted)) {
				 map.put("msg","渠道被删除");
				 map.put("code","300");
				 return map;
			 }
			}
	    
		return map;
		
	}

	@RequestMapping("/getSourceClick")
	@ResponseBody
	@Transactional
	public Map<String, Object> getqrcode(String company,String source,String date) {
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
    		map.put("msg","200");
    	}else {
    		redisClientUtil.set(RedisCompany+source+date+"Key",Integer.parseInt(redisClientUtil.getSourceClick(RedisCompany+source+date+"Key"))+1+""); //由于value是string类型的，所以先转换成int类型，+1之后在转换成string类型
    		System.out.println(redisClientUtil.getSourceClick(RedisCompany+source+date+"Key"));
    		map.put("msg","200");
		}
    	List<Source> list = intMerchantService.queryByLike1(source,company);
    	Source source2 = list.get(0);
    	int sourceId = source2.getId();
    	map.put("sourceId", sourceId);
    	return map;
	
	}
}

package com.zhita.controller.daichao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zhita.util.PostAndGet;

@Controller
@RequestMapping("/qrcode")
public class QrCode {
     
	@RequestMapping("/getqrcode")
	@ResponseBody
	public Map<String, String> getqrcode(String grant_type,String appid,String secret,String scene) {
		Map<String, String> map = new HashMap<String, String>();
		Map<String, Object> accessTokenMap = new HashMap<String, Object>();
		PostAndGet postAndGet = new PostAndGet();
		accessTokenMap = JSON.parseObject(postAndGet.sendGet("https://api.weixin.qq.com/cgi-bin/token?grant_type="+grant_type+"&appid="+appid+"&secret="+secret)); //获取access_token
		String accessToken = (String) accessTokenMap.get("access_token");
		if(accessToken!=null) {
			map.put("msg", postAndGet.sendPost("https://api.weixin.qq.com/wxa/getwxacodeunlimit?",("access_token="+accessToken+"&scene="+scene))); //获取errcode
		}else {
			map.put("msg", "accessToken获取失败");
		}
		return map;
		
	}
	
	@RequestMapping("/geterrcode")
	@ResponseBody
	public Map<String, String> getErrcode(String accessToken,String scene) {
		Map<String, String> map = new HashMap<String, String>();
		PostAndGet postAndGet = new PostAndGet();
			map.put("msg", postAndGet.sendPost("https://api.weixin.qq.com/wxa/getwxacodeunlimit?",("access_token="+accessToken+"&scene="+scene))); //获取errcode
			return map;

	}
}

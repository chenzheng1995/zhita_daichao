package com.zhita.controller.daichao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.zhita.util.PostAndGet;

@Controller
@RequestMapping("/qrcode")
public class QrCodeController {

	@RequestMapping("/getqrcode")
	@ResponseBody
	public Map<String, String> getqrcode(String grant_type,String appid,String secret,String scene) {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isEmpty(grant_type) || StringUtils.isEmpty(appid) || StringUtils.isEmpty(secret) || StringUtils.isEmpty(scene)) {
			map.put("msg", "grant_type,appid,secret或scene不能为空");
			return map;
			}else {
				Map<String, Object> AllMap = new HashMap<String, Object>();
				Map<String, Object> sceneMap = new HashMap<String, Object>();
				PostAndGet postAndGet = new PostAndGet();
				AllMap = JSON.parseObject(postAndGet.sendGet("https://api.weixin.qq.com/cgi-bin/token?grant_type="+grant_type+"&appid="+appid+"&secret="+secret)); //获取access_token
				String accessToken = (String) AllMap.get("access_token");
				sceneMap.put("scene", scene);
				String sceneToString = new Gson().toJson(sceneMap);	
				if(accessToken!=null) {
					map.put("msg", postAndGet.sendPost2("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken,sceneToString)); //获取二维码二进制图片
				}else {
					map.put("msg", "accessToken获取失败");
				}
				return map;
			}

		
	}

}

package com.zhita.controller.qrcode;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.zhita.service.user.UserService;
import com.zhita.util.PostAndGet;

@Controller
@RequestMapping("/qrcode")
public class QrCodeController {

	@Autowired
	UserService userService;

	@RequestMapping("/getqrcode")
	@ResponseBody
	public Map<String, String> getqrcode(String grant_type, String appid, String secret, String scene, String company,String oneSourceName,String twoSourceName) {// scene传的是userid
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isEmpty(grant_type) || StringUtils.isEmpty(appid) || StringUtils.isEmpty(secret)
				|| StringUtils.isEmpty(scene) || StringUtils.isEmpty(company)) {
			map.put("msg", "grant_type,appid,secret,scene或company不能为空");
			return map;
		} else {
			String programQrCode = userService.getProgramQrCode(scene);
			if (programQrCode != null) {
				map.put("programQrCode", programQrCode);
				return map;
			} else {
				String softwareType = "program";
				Map<String, Object> AllMap = new HashMap<String, Object>();
				Map<String, Object> sceneMap = new HashMap<String, Object>();
				PostAndGet postAndGet = new PostAndGet();
				AllMap = JSON.parseObject(postAndGet.sendGet("https://api.weixin.qq.com/cgi-bin/token?grant_type="+grant_type + "&appid=" + appid + "&secret=" + secret)); // 获取access_token
				String accessToken = (String) AllMap.get("access_token");
				sceneMap.put("scene", scene);
				String sceneToString = new Gson().toJson(sceneMap);
				if (accessToken != null) {
//						map.put("msg", postAndGet.sendPost2("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken,sceneToString,company,scene,softwareType)); //获取二维码二进制图片
					String string = postAndGet.sendPost2("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken,sceneToString, company, scene, softwareType);
					JSONObject jsonObject = JSON.parseObject(string);
					if (jsonObject.get("qrurl") != null) {
						String qrurl = (String) jsonObject.get("qrurl");
						int number = userService.setProgramQrCode(scene,qrurl);
						if (number == 1) {														
							map.put("programQrCode", qrurl);
						} else {
							map.put("msg", "数据插入失败");
						}
					} else {
						map.put("msg", string);
					}
				} else {
					map.put("msg", "accessToken获取失败");
				}
			}

			return map;
		}

	}

}

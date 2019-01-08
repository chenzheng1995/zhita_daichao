package com.zhita.controller.daichao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.service.blacklist.BlacklistService;
import com.zhita.util.RedisClientUtil;

@Controller
@RequestMapping("/blacklist")
public class BlacklistController {
	
	@Autowired
	BlacklistService blacklistService;

	
	/**
	 * 
	 * @param userId 用户id
	 * @param name   姓名
	 * @param phone  手机号码
	 * @param code   短信验证码
	 * @param idCard 身份证号
	 * @return
	 */
	@RequestMapping("/setblacklist")
	@ResponseBody
	public Map<String, Object> setblacklist(int userId, String name, String idCard, String phone, String code) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(name) || StringUtils.isEmpty(idCard)
				|| StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)) {
			map.put("msg", "userId,name,idCard,phone或code不能为空");
			return map;
		} else {
			RedisClientUtil redisClientUtil = new RedisClientUtil();
			String key = phone + "Key";
			String redisCode = redisClientUtil.get(key);
			if (redisCode == null) {
				map.put("msg", "验证码已过期，请重新发送");
				return map;
			}
			if(redisCode.equals(code)) {
				String creationTime = System.currentTimeMillis()+"";
				int number = blacklistService.setblacklist(userId,name,idCard,phone,creationTime);
				if (number == 1) {		
					map.put("msg","数据插入成功");
				} else {
					map.put("msg", "数据插入失败");
				}
			}else {
				map.put("msg","验证码输入错误");
			}	
		}
		return map;
	}
}

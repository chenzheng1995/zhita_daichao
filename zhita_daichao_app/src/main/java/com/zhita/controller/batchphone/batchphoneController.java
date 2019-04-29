package com.zhita.controller.batchphone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.service.user.UserService;
import com.zhita.util.PhoneDeal;


@Controller
@RequestMapping(value="/batchphone")
public class batchphoneController {
	
	@Autowired
	UserService userService;

	//批量加密	
	@ResponseBody
	@RequestMapping("/encryption")
	@Transactional
	public void encryption(int startId,int endId){
		for (int startId1 = startId; startId1 <= endId; startId1++) {
			String phone = userService.getUserPhone(startId1);
			if(phone==null) {
				continue;
			}
			PhoneDeal phoneDeal = new PhoneDeal();
			String newPhone = phoneDeal.encryption(phone);
			userService.updatePhone(startId1,newPhone);
		}
	
	}
	
	
	//批量解密	
	@ResponseBody
	@RequestMapping("/decryption")
	@Transactional
	public void decryption(int startId,int endId){
		for (int startId1 = startId; startId1 <= endId; startId1++) {
			String phone = userService.getUserPhone(startId1);
			if(phone==null) {
				continue;
			}
			PhoneDeal phoneDeal = new PhoneDeal();
			String newPhone = phoneDeal.decryption(phone);
			userService.updatePhone(startId1,newPhone);
		}
	
	}

}

package com.zhita.controller.daichao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.service.buttonfootprint.ButtonFootprintService;
import com.zhita.service.commodityfootprint.CommodityFootprintService;
import com.zhita.service.loanclassificationfootprint.LCFootprintService;

@Controller
@RequestMapping("/a")
public class Test implements Runnable{
	
	
	@Autowired
	CommodityFootprintService commodityFootprintService;

	@Autowired
	LCFootprintService lcFootprintService;

	@Autowired
	ButtonFootprintService buttonFootprintService;
	
	@RequestMapping("/b")
	@ResponseBody
	public void getqrcode(){
	Thread threads[]=new Thread[5];
	for(int i=0;i<5;i++){
		threads[i]=new Thread(new Test());
		threads[i].start();
	}
	}
	
	public void run() {
    String footprintName = "万金花";
    String footprintType = "1";
    String userId = "9";
    String company = "多米记";
	long currentTimestamp = System.currentTimeMillis();
	if (footprintType.equals("1")) {
		commodityFootprintService.insertfootprint(footprintName, userId, currentTimestamp,company);
		
	}
	if (footprintType.equals("2")) {
		lcFootprintService.insertfootprint(footprintName, userId, currentTimestamp,company);
		
	}
	if (footprintType.equals("3")) {
		buttonFootprintService.insertfootprint(footprintName, userId, currentTimestamp,company);
	
	}
}
}
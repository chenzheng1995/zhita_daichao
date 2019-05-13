package com.zhita.controller.creditcardfootprint;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.service.buttonfootprint.ButtonFootprintService;
import com.zhita.service.card.IntCreditCardFootprintService;
import com.zhita.service.commodityfootprint.CommodityFootprintService;
import com.zhita.service.commodityfootprintcopy.CommodityFootprintCopyService;
import com.zhita.service.loanclassificationfootprint.LCFootprintService;
import com.zhita.service.sourcedadson.SourceDadSonService;

@Controller
@RequestMapping("/CreditCardFootprint")
public class CreditCardFootprintController {

	@Autowired
	CommodityFootprintService commodityFootprintService;

	@Autowired
	LCFootprintService lcFootprintService;

	@Autowired
	ButtonFootprintService buttonFootprintService;
	
	@Autowired
	SourceDadSonService sourceDadSonService;
	
	@Autowired
	CommodityFootprintCopyService commodityFootprintCopyService;
	
	@Autowired
	private IntCreditCardFootprintService intCreditCardFootprintService;

	//插入足迹
	@RequestMapping("/insertfootprint")
	@ResponseBody
	@Transactional
	public Map<String, String> insertFootprint(String footprintName,String userId,String company,String oneSourceName,String twoSourceName) { 
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isEmpty(footprintName) || StringUtils.isEmpty(userId) || StringUtils.isEmpty(company)) {
			map.put("msg", "footprintName,userId或company不能为空");
			map.put("SCode", "401");
			return map;
		} else {
				long currentTimestamp = System.currentTimeMillis();

					int number = intCreditCardFootprintService.insertfootprint(footprintName, userId, currentTimestamp,company);
					if (number == 1) {
						map.put("msg", "插入成功");
						map.put("SCode", "200");
					} else {
						map.put("SCode", "405");
					}
				}
		return map;
	}

}

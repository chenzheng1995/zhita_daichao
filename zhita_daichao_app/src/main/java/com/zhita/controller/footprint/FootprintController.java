package com.zhita.controller.footprint;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.service.buttonfootprint.ButtonFootprintService;
import com.zhita.service.commodityfootprint.CommodityFootprintService;
import com.zhita.service.loanclassificationfootprint.LCFootprintService;
import com.zhita.service.sourcedadson.SourceDadSonService;

@Controller
@RequestMapping("/footprint")
public class FootprintController {

	@Autowired
	CommodityFootprintService commodityFootprintService;

	@Autowired
	LCFootprintService lcFootprintService;

	@Autowired
	ButtonFootprintService buttonFootprintService;
	
	@Autowired
	SourceDadSonService sourceDadSonService;

	//插入足迹
	@RequestMapping("/insertfootprint")
	@ResponseBody
	@Transactional
	public Map<String, String> insertFootprint(String footprintName, String footprintType, String userId,String company,String oneSourceName,String twoSourceName) { // footprintType = 1为商品， 2为贷款分类 ， 3为按钮
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isEmpty(footprintName) || StringUtils.isEmpty(footprintType) || StringUtils.isEmpty(userId) || StringUtils.isEmpty(company)) {
			map.put("msg", "footprintName,footprintType,userId或company不能为空");
			map.put("SCode", "401");
			return map;
		} else {
			String  tableType = sourceDadSonService.getTableType(oneSourceName,twoSourceName,company);	
			if(tableType.equals("1")){
				long currentTimestamp = System.currentTimeMillis();
				if (footprintType.equals("1")) {
					int number = commodityFootprintService.insertfootprint(footprintName, userId, currentTimestamp,company);
					if (number == 1) {
						map.put("msg", "插入成功");
						map.put("SCode", "200");
					} else {
						map.put("SCode", "405");
					}
				}
				if (footprintType.equals("2")) {
					int number = lcFootprintService.insertfootprint(footprintName, userId, currentTimestamp,company);
					if (number == 1) {
						map.put("msg", "插入成功");
						map.put("SCode", "200");
					} else {
						map.put("msg", "插入失败");
						map.put("SCode", "405");
					}
				}
				if (footprintType.equals("3")) {
					int number = buttonFootprintService.insertfootprint(footprintName, userId, currentTimestamp,company);
					if (number == 1) {
						map.put("msg", "插入成功");
						map.put("SCode", "200");
					} else {
						map.put("msg", "插入失败");
						map.put("SCode", "405");
					}
				}
			}
			if(tableType.equals("2")) {
				long currentTimestamp = System.currentTimeMillis();
				int number = commodityFootprintService.insertfootprint(footprintName, userId, currentTimestamp,company);
				if (number == 1) {
					map.put("msg", "插入成功");
					map.put("SCode", "200");
				} else {
					map.put("SCode", "405");
				}
			}

			}

		return map;
	}

}

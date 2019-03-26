package com.zhita.controller.editbill;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zhita.service.editbill.EditBillService;

@Controller
@RequestMapping(value = "/editbill")
public class EditBillController {

	@Autowired
	EditBillService editBillService;

	// 编辑账单中添加数据
	/**
	 * 
	 * @param operationDate      操作日期
	 * @param account            账号
	 * @param registrationNumber 注册人数
	 * @param price              价格
	 * @param realPay            实付金额
	 * @param note               备注
	 * @param accountType        账号类型
	 * @param firmType           公司类型（渠道方为1，甲方为2）
	 * @param sourceId           渠道来源
	 * @param businessesId       甲方id
	 * @param modifyTime         修改时间
	 * @param registrationTime   注册时间
	 * @param company            公司名
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("/setEditBill")
	@Transactional
	public Map<String, Object> setEditBill(String operationDate, String account, int registrationNumber, int price,
			String note, String accountType, String firmType, int sourceId, int businessesId, String modifyTime,
			String registrationTime, String company) throws ParseException {
		HashMap<String, Object> map = new HashMap<>();
		if (StringUtils.isEmpty(operationDate) || StringUtils.isEmpty(account)
				|| StringUtils.isEmpty(registrationNumber) || StringUtils.isEmpty(price) || StringUtils.isEmpty(note)
				|| StringUtils.isEmpty(accountType) || StringUtils.isEmpty(firmType) || StringUtils.isEmpty(modifyTime)
				|| StringUtils.isEmpty(registrationTime) || StringUtils.isEmpty(company)) {
			map.put("msg","operationDate,account,registrationNumber,price,note,accountType,firmType,modifyTime,company和registrationTime不能为空");
			map.put("SCode", "401");
			return map;
		} else {
			int realPay = registrationNumber * price;
				int number = editBillService.setEditBill(operationDate, account, registrationNumber, price, realPay,
						note, accountType, firmType, modifyTime, registrationTime, company,sourceId,businessesId);
				if (number != 0) {
					map.put("msg", "数据插入成功");
					map.put("SCode", "200");
				} else {
					map.put("msg", "数据插入失败");
					map.put("SCode", "405");
				}
			return map;
		}
	}
}

package com.zhita.controller.editbill;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.EditBill;
import com.zhita.model.manage.News;
import com.zhita.model.manage.TopUpAmount;
import com.zhita.service.bill.UnitPriceService;
import com.zhita.service.editbill.EditBillService;
import com.zhita.service.registe.IntRegisteService;
import com.zhita.util.PageUtil;
import com.zhita.util.Timestamps;

@Controller
@RequestMapping(value = "/editbill")
public class EditBillController {

	@Autowired
	EditBillService editBillService;
	
	@Autowired
	UnitPriceService unitPriceService;
	
	@Autowired
	IntRegisteService intRegisteService;

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
	public Map<String, Object> setEditBill(String operationDate, String account, Integer registrationNumber, Integer price,
			String note, String accountType, String firmType, Integer sourceId, Integer businessesId,
			String registrationTime, String company,String sourceTo) throws ParseException {
		HashMap<String, Object> map = new HashMap<>();
		if (StringUtils.isEmpty(operationDate)|| StringUtils.isEmpty(registrationNumber) || StringUtils.isEmpty(price)
				|| StringUtils.isEmpty(note) || StringUtils.isEmpty(accountType) || StringUtils.isEmpty(firmType) 
				|| StringUtils.isEmpty(registrationTime) || StringUtils.isEmpty(company)) {
			map.put("msg","operationDate,registrationNumber,price,note,accountType,firmType,modifyTime,company和registrationTime不能为空");
			map.put("SCode", "401");
			return map;
		} else {
			if(firmType.equals("1")) {
				int id = unitPriceService.getbusinessesId(account,company);
				sourceTo = intRegisteService.getBusinessesName(id);
				businessesId = 0;
			}
			if(firmType.equals("2")) {
				account ="";
				sourceId = 0;
			}
			Timestamps timestamps = new Timestamps();
			String oDatetimestamps = timestamps.dateToStamp(operationDate);//转换成时间戳
			String modifyTimestamps =  System.currentTimeMillis() + ""; // 获取当前时间戳
			String registrationTimestamps = timestamps.dateToStamp(registrationTime);//转换成时间戳
			int realPay = registrationNumber * price;
				int number = editBillService.setEditBill(oDatetimestamps, account, registrationNumber, price, realPay,
						note, accountType, firmType, modifyTimestamps, registrationTimestamps, company,sourceId,businessesId,sourceTo);
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
	
	
	// 编辑账单中更新数据
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
	@RequestMapping("/updateEditBill")
	@Transactional
	public Map<String, Object> updateEditBill(String operationDate, String account, Integer registrationNumber, Integer price,
			String note, String accountType, String firmType, Integer sourceId, Integer businessesId, String modifyTime,
			String registrationTime, String company,String sourceTo,int id) throws ParseException {
		HashMap<String, Object> map = new HashMap<>();
		if (StringUtils.isEmpty(operationDate)|| StringUtils.isEmpty(registrationNumber) || StringUtils.isEmpty(price)
				|| StringUtils.isEmpty(note) || StringUtils.isEmpty(accountType) || StringUtils.isEmpty(firmType) 
				|| StringUtils.isEmpty(modifyTime) || StringUtils.isEmpty(registrationTime) || StringUtils.isEmpty(company)) {
			map.put("msg","operationDate,registrationNumber,price,note,accountType,firmType,modifyTime,company和registrationTime不能为空");
			map.put("SCode", "401");
			return map;
		} else {
			if(firmType.equals("1")) {
				int bId = unitPriceService.getbusinessesId(account,company);
				sourceTo = intRegisteService.getBusinessesName(bId);
				businessesId = 0;
			}
			if(firmType.equals("2")) {
				account ="";
				sourceId = 0;
			}
			Timestamps timestamps = new Timestamps();
			String oDatetimestamps = timestamps.dateToStamp(operationDate);//转换成时间戳
			String modifyTimestamps =  System.currentTimeMillis() + ""; // 获取当前时间戳
			String registrationTimestamps = timestamps.dateToStamp(registrationTime);//转换成时间戳
			int realPay = registrationNumber * price;
				int number = editBillService.updateEditBill(oDatetimestamps, account, registrationNumber, price, realPay,
						note, accountType, firmType, modifyTimestamps, registrationTimestamps,sourceId,businessesId,sourceTo,id);
				if (number != 0) {
					map.put("msg", "数据更新成功");
					map.put("SCode", "200");
				} else {
					map.put("msg", "数据更新失败");
					map.put("SCode", "405");
				}
			return map;
		}
	}
	
	
	// 编辑账单中根据id删除数据（假删除）

	@ResponseBody
	@RequestMapping("/deleteEditBillById")
	@Transactional
	public Map<String, Object> deleteEditBillById(int id) throws ParseException {
		HashMap<String, Object> map = new HashMap<>();
		if (StringUtils.isEmpty(id)) {
			map.put("msg","id不能为空");
			map.put("SCode", "401");
			return map;
		} else {		
			    String modifyTimestamps =  System.currentTimeMillis() + ""; // 获取当前时间戳
				int number = editBillService.deleteEditBillById(id,modifyTimestamps);
				if (number != 0) {
					map.put("msg", "数据删除成功");
					map.put("SCode", "200");
				} else {
					map.put("msg", "数据删除失败");
					map.put("SCode", "405");
				}
			return map;
		}
	}
	
	
	// 编辑账单中根据操作日期删除数据（假删除）

	@ResponseBody
	@RequestMapping("/deleteEditBillByOperationDate")
	@Transactional
	public Map<String, Object> deleteEditBillByOperationDate(String firmType,String[] account,String operationDate,String company,Integer businessesId) throws ParseException {
		HashMap<String, Object> map = new HashMap<>();	
		HashMap<String, Object> map1 = new HashMap<>();	
		Timestamps timestamps = new Timestamps();
	    String modifyTimestamps =  System.currentTimeMillis() + ""; // 获取当前时间戳
	    String oDatetimestamps = timestamps.dateToStamp(operationDate);//转换成时间戳
		if(firmType.equals("1")) {
			ArrayList<String> accountList = new ArrayList<String>(Arrays.asList(account));
            map1.put("accountList", accountList);
            map1.put("modifyTimestamps", modifyTimestamps);
            map1.put("operationDate", oDatetimestamps);
            map1.put("company", company);
				int number = editBillService.deleteEditBillByOperationDate(map1);
				if (number != 0) {
					map.put("msg", "数据删除成功");
					map.put("SCode", "200");
				} else {
					map.put("msg", "数据删除失败");
					map.put("SCode", "405");
				}
		}
		
		if(firmType.equals("2")) {
				int number = editBillService.deleteEditBillByOperationDate1(businessesId,company,oDatetimestamps,modifyTimestamps);
				if (number != 0) {
					map.put("msg", "数据删除成功");
					map.put("SCode", "200");
				} else {
					map.put("msg", "数据删除失败");
					map.put("SCode", "405");
				}
		}
			return map;

	}
	
	
	// 编辑账单中查询数据

	@ResponseBody
	@RequestMapping("/getEditBill")
	@Transactional
	public Map<String, Object> getEditBill(String[] account,String company, String firmType,Integer businessesId,Integer page) throws ParseException {
		HashMap<String, Object> map = new HashMap<>();		
		HashMap<String, Object> map1 = new HashMap<>();	
		HashMap<String, Object> map2 = new HashMap<>();	
		String operationDate = "";
		String Registrationtime ="";
		if(firmType.equals("1")) {
			ArrayList<String> accountList = new ArrayList<String>(Arrays.asList(account));
			map2.put("accountList", accountList);
			map2.put("company", company);
			int totalCount=editBillService.pageCountByEditBill1(map2);
	    	PageUtil pageUtil=new PageUtil(page,10,totalCount);
	    	if(page<1) {
	    		page=1;
	    	}else if(page>pageUtil.getTotalPageCount()) {
	      		if(totalCount==0) {
	    			page=pageUtil.getTotalPageCount()+1;
	    		}else {
	    			page=pageUtil.getTotalPageCount();
	    		}
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil.setPage(pages); 
	    	map1.put("accountList", accountList);
	    	map1.put("company", company);
	    	map1.put("page", pageUtil.getPage());
	    	map1.put("pageSize", pageUtil.getPageSize());
	    	map1.put("firmType", firmType);
	    	Timestamps timestamps = new Timestamps();	
	    	List<EditBill> EditBillList = editBillService.getEditBill1(map1);
	    	for (EditBill editBill : EditBillList) {
	    		operationDate = timestamps.stampToDate1(editBill.getOperationdate());
	    		Registrationtime = timestamps.stampToDate1(editBill.getRegistrationtime());
	    		editBill.setOperationdate(operationDate);
	    		editBill.setRegistrationtime(Registrationtime);
	    	}
	    	map.put("EditBillList", EditBillList);
	    	map.put("pageutil", pageUtil);
		}
		
		if(firmType.equals("2")) {
			int totalCount=editBillService.pageCountByEditBill2(businessesId);
	    	PageUtil pageUtil=new PageUtil(page,10,totalCount);
	    	if(page<1) {
	    		page=1;
	    	}else if(page>pageUtil.getTotalPageCount()) {
	      		if(totalCount==0) {
	    			page=pageUtil.getTotalPageCount()+1;
	    		}else {
	    			page=pageUtil.getTotalPageCount();
	    		}
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil.setPage(pages); 
	    	
			Timestamps timestamps = new Timestamps();	
	    	List<EditBill> EditBillList = editBillService.getEditBill2(businessesId,company,pageUtil.getPage(),pageUtil.getPageSize());
	    	for (EditBill editBill : EditBillList) {
	    		operationDate = timestamps.stampToDate1(editBill.getOperationdate());
	    		Registrationtime = timestamps.stampToDate1(editBill.getRegistrationtime());
	    		editBill.setOperationdate(operationDate);
	    		editBill.setRegistrationtime(Registrationtime);
	    	}
	    	map.put("EditBillList", EditBillList);
	    	map.put("pageutil", pageUtil);
		}

	return map;
	}
	
	
	// 编辑账单中所有数据的总和
	@ResponseBody
	@RequestMapping("/getEditBillCount")
	@Transactional
	public Map<String, Object> getEditBill(String firmType,String[] account,String company,String startDate,String endDate,Integer businessesId) throws ParseException {
		HashMap<String, Object> map = new HashMap<>();
		HashMap<String, Object> map1 = new HashMap<>();		
		Timestamps timestamps = new Timestamps();
		startDate = timestamps.dateToStamp(startDate);//转换成时间戳
		if(endDate==null) {
			endDate = (Long.parseLong(startDate)+86400000)+"";
		}else {
			endDate = (Long.parseLong(timestamps.dateToStamp(endDate))+86400000)+"";//转换成时间戳
		}
		if(firmType.equals("1")) {
		ArrayList<String> accountList = new ArrayList<String>(Arrays.asList(account));
		map1.put("accountList", accountList);
		map1.put("company", company);
		map1.put("startDate", startDate);
		map1.put("endDate", endDate);
		map1.put("accountType", "CPA");
		List<EditBill> CPAList = editBillService.getEditBillCount1(map1);
		map1.put("accountType", "CPS");
		List<EditBill> CPSList = editBillService.getEditBillCount1(map1);
		map1.put("accountType", "UV");
		List<EditBill> UVList = editBillService.getEditBillCount1(map1);
		Integer CPARealpay = CPAList.get(0).getRealpay();
		Integer CPSRealpay = CPSList.get(0).getRealpay();
		Integer UVRealpay = UVList.get(0).getRealpay();
		if(CPARealpay==null) {
			CPARealpay = 0;
		}
		if(CPSRealpay==null) {
			CPSRealpay = 0;
		}
		if(UVRealpay==null) {
			UVRealpay = 0;
		}
		int realpaySum = CPARealpay+CPSRealpay+UVRealpay;
		map.put("CPAList", CPAList);
		map.put("CPSList", CPSList);
		map.put("UVList", UVList);
		map.put("realpaySum", realpaySum);
		}
		
		if(firmType.equals("2")) {
		String accountType ="CPA"; 
		List<EditBill> CPAList = editBillService.getEditBillCount2(businessesId,company,startDate,endDate,accountType);
		accountType ="CPS";
		List<EditBill> CPSList = editBillService.getEditBillCount2(businessesId,company,startDate,endDate,accountType);
		accountType ="UV";
		List<EditBill> UVList = editBillService.getEditBillCount2(businessesId,company,startDate,endDate,accountType);
		Integer CPARealpay = CPAList.get(0).getRealpay();
		Integer CPSRealpay = CPSList.get(0).getRealpay();
		Integer UVRealpay = UVList.get(0).getRealpay();
		if(CPARealpay==null) {
			CPARealpay = 0;
		}
		if(CPSRealpay==null) {
			CPSRealpay = 0;
		}
		if(UVRealpay==null) {
			UVRealpay = 0;
		}
		int realpaySum = CPARealpay+CPSRealpay+UVRealpay;
		map.put("CPAList", CPAList);
		map.put("CPSList", CPSList);
		map.put("UVList", UVList);
		map.put("realpaySum", realpaySum);
		}
		
		return map;
		
	}
}

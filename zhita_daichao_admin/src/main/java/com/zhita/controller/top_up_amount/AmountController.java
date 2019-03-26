package com.zhita.controller.top_up_amount;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.TopUpAmount;
import com.zhita.model.manage.UnitPrice;
import com.zhita.service.article.ArticleService;
import com.zhita.service.newstype.NewsTypeService;
import com.zhita.service.top_up_amount.AmountService;
import com.zhita.util.PageUtil;
import com.zhita.util.Timestamps;

@Controller
@RequestMapping(value = "/amount")
public class AmountController {

	@Autowired
	AmountService amountService;

	
	//添加充值金额的数据
	/**
	 * 
	 * @param billingDate    入账时间
	 * @param firm           平台公司
	 * @param topUpAmount    充值金额
	 * @param cashReceipts   现金凭证
	 * @param paymentAccount 支付账号
	 * @param contact        联系人
	 * @param note           备注
	 * @param company        公司名
	 * @param firmType       平台类型（渠道方为1，甲方为2）
	 * @param operationDate  操作时间
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping("/setTUamount")
	@Transactional
	public Map<String, Object> setTUamount(String billingDate, String firm, int topUpAmount, String cashReceipts,
			String paymentAccount, String contact, String note, String company, String firmType,String operationDate) throws ParseException {
		HashMap<String, Object> map = new HashMap<>();
		if (StringUtils.isEmpty(billingDate) || StringUtils.isEmpty(firm) || StringUtils.isEmpty(topUpAmount)
				|| StringUtils.isEmpty(cashReceipts) || StringUtils.isEmpty(paymentAccount)
				|| StringUtils.isEmpty(contact) || StringUtils.isEmpty(note) || StringUtils.isEmpty(company)
				|| StringUtils.isEmpty(firmType)) {
			map.put("msg", "billingDate,firm,topUpAmount,cashReceipts,paymentAccount,contact,note,company,operationDate和firmType不能为空");
			map.put("SCode", "401");
			return map;
		} else {
			Timestamps timestamps = new Timestamps();
			String btimestamps = timestamps.dateToStamp(billingDate);//转换成时间戳
			String otimestamps = timestamps.dateToStamp(operationDate);//转换成时间戳
			String registrationTime = System.currentTimeMillis() + ""; // 获取当前时间戳
			int number = amountService.setTUamount(btimestamps,firm,topUpAmount,cashReceipts,paymentAccount,contact,note,company,firmType,registrationTime,otimestamps);
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
	
	//更新充值记录的数据
	/**
	 * 
	 * @param billingDate    入账时间
	 * @param firm           平台公司
	 * @param topUpAmount    充值金额
	 * @param cashReceipts   现金凭证
	 * @param paymentAccount 支付账号
	 * @param contact        联系人
	 * @param note           备注
	 * @param company        公司名
	 * @param firmType       平台类型（渠道方为1，甲方为2）
	 * @param operationDate  操作时间
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping("/updateTUamount")
	@Transactional
	public Map<String, Object> updateTUamount(String billingDate, String firm, int topUpAmount, String cashReceipts,
			String paymentAccount, String contact, String note, String company, String firmType,int id,String operationDate) throws ParseException {
		HashMap<String, Object> map = new HashMap<>();
		if (StringUtils.isEmpty(billingDate) || StringUtils.isEmpty(firm) || StringUtils.isEmpty(topUpAmount)
				|| StringUtils.isEmpty(cashReceipts) || StringUtils.isEmpty(paymentAccount)
				|| StringUtils.isEmpty(contact) || StringUtils.isEmpty(note) || StringUtils.isEmpty(company)
				|| StringUtils.isEmpty(firmType)) {
			map.put("msg", "billingDate,firm,topUpAmount,cashReceipts,paymentAccount,contact,note,company,id和firmType不能为空");
			map.put("SCode", "401");
			return map;
		} else {
			Timestamps timestamps = new Timestamps();
			String btimestamps = timestamps.dateToStamp(billingDate);//转换成时间戳
			String otimestamps = timestamps.dateToStamp(operationDate);//转换成时间戳
			String registrationTime = System.currentTimeMillis() + ""; // 获取当前时间戳
			int number = amountService.updateTUamount(btimestamps,firm,topUpAmount,cashReceipts,paymentAccount,contact,note,company,firmType,registrationTime,id,otimestamps);
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
	
	//再充值金额里根据id删除记录（假删除）
	@ResponseBody
	@RequestMapping("/deleteAmountById")
	@Transactional
	public Map<String, Object> deleteAmountById(int id) throws ParseException {
		HashMap<String, Object> map = new HashMap<>();
		if (StringUtils.isEmpty(id)) {
			map.put("msg", "id不能为空");
			map.put("SCode", "401");
			return map;
		} else {
			String registrationTime = System.currentTimeMillis() + ""; // 获取当前时间戳
			int number = amountService.deleteAmountById(id,registrationTime);
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
	
	//在充值金额里根据操作日期删除记录（假删除）
	@ResponseBody
	@RequestMapping("/deleteAmountByOperationDate")
	@Transactional
	public Map<String, Object> deleteAmountByOperationDate(String operationDate) throws ParseException {
		HashMap<String, Object> map = new HashMap<>();
		if (StringUtils.isEmpty(operationDate)) {
			map.put("msg", "operationDate不能为空");
			map.put("SCode", "401");
			return map;
		} else {
			String registrationTime = System.currentTimeMillis() + ""; // 获取当前时间戳
			Timestamps timestamps = new Timestamps();
			String otimestamps = timestamps.dateToStamp(operationDate);//转换成时间戳
			int number = amountService.deleteAmountByOperationDate(otimestamps,registrationTime);
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
	
    //在充值金额里查询所有数据
	/**
	 * @param company 公司名
	 * @param firmType 平台类型（渠道方为1，甲方为2）
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAmount")
    public Map<String,Object> getAmount (String firmType,String company,Integer page){
		HashMap<String,Object> map=new HashMap<>();
		if (StringUtils.isEmpty(firmType) || StringUtils.isEmpty(company) || StringUtils.isEmpty(page)) {
			map.put("msg", "firmType,page和company不能为空");
			map.put("SCode", "401");
			return map;
		} else {
	     	int totalCount=amountService.pageCountByAmount(firmType,company);
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
		String operationDate = null;
		String billingDate = null;
		Timestamps timestamps = new Timestamps();		
		List<TopUpAmount> amountList = amountService.getAmount(firmType,company,pageUtil.getPage(),pageUtil.getPageSize());
		for (TopUpAmount topUpAmount : amountList) {
			operationDate = timestamps.stampToDate1(topUpAmount.getOperationdate());
			billingDate = timestamps.stampToDate1(topUpAmount.getBillingdate());
			topUpAmount.setBillingdate(billingDate);
			topUpAmount.setOperationdate(operationDate);
		}
		map.put("amountList", amountList);
		return map;
	}
	}
}

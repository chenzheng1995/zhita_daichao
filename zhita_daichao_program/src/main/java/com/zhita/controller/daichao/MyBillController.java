package com.zhita.controller.daichao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.ExpenditureBill;
import com.zhita.model.manage.IncomeBigtype;
import com.zhita.model.manage.IncomeBill;
import com.zhita.model.manage.IncomeSmalltype;
import com.zhita.service.expenditurebigtype.IntexpenditurebigtypeService;
import com.zhita.service.expenditurebill.IntexpenditurebillService;
import com.zhita.service.expendituresmalltype.IntexpendituresmalltypeService;
import com.zhita.service.incomebigtype.IntincomebigtypeService;
import com.zhita.service.incomebill.IntincomebillService;
import com.zhita.service.incomesmalltype.IntincomesmalltypeService;

@Controller
@RequestMapping("/mybill")
public class MyBillController {
	@Autowired
	private IntincomebigtypeService intincomebigtypeService;
	
	@Autowired
	private IntincomesmalltypeService intincomesmalltypeService;
	
	@Autowired
	private IntincomebillService intincomebillService;
	
	@Autowired
	private IntexpenditurebigtypeService intexpenditurebigtypeService;
	
	@Autowired
	private IntexpendituresmalltypeService intexpendituresmalltypeService;
	
	@Autowired
	private IntexpenditurebillService intexpenditurebillService;
	
	//小程序---记账页面----查询出所有收入大分类
    @ResponseBody
    @RequestMapping("/queryAllBigType")
    public List<IncomeBigtype> queryAllBigType(){
    	List<IncomeBigtype> list=intincomebigtypeService.queryAllBigtype();
    	return list;
    }
    
	//小程序---记账页面----根据收入大分类查询出  当前大分类下的所以收入小分类
    @ResponseBody
    @RequestMapping("/queryAllSmallType")
    public List<IncomeSmalltype> queryAllSmallType(String bigtype){
    	List<IncomeSmalltype> list=intincomesmalltypeService.querySmallTypeByBigType(bigtype);
    	return list;
    }
    
    //小程序---记账页面----添加收入账单
    @ResponseBody
    @RequestMapping("/addIncomeBill")
    public int addIncomeBill(IncomeBill incomeBill) {
    	int count=intincomebillService.addIncomeBill(incomeBill);
    	return count;
    }
    
    //小程序---我的账单
/*    @ResponseBody
    @RequestMapping("/myBill")
    public Map<String, Object> myBill(Integer userid,String time) {
    	BigDecimal incomenum=intincomebillService.queryTotalMoney(userid, time);
    	BigDecimal expenditurenum=intexpenditurebillService.queryTotalMoney(userid, time);
    	
    	List<IncomeBill> incomelist=intincomebillService.queryAllByTimeLike(userid, time);
    	List<ExpenditureBill> expenditurelist=intexpenditurebillService.queryAllByTimeLike(userid, time);
    	
    	BigDecimal incomenumlike=intincomebillService.querySumByTimeLike(userid, time);
    	BigDecimal expenditurenumlike=intexpenditurebillService.querySumByTimeLike(userid, time);
    	
    }*/
}

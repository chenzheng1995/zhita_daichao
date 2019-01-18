package com.zhita.controller.daichao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.DayBill;
import com.zhita.model.manage.ExpenditureBigtype;
import com.zhita.model.manage.ExpenditureBill;
import com.zhita.model.manage.ExpenditureSmalltype;
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
    
    //小程序---记账页面---查询出所有的支出大分类
    @ResponseBody
    @RequestMapping("/queryAllexBigType")
    public List<ExpenditureBigtype> queryAllexBigType(){
    	List<ExpenditureBigtype> list=intexpenditurebigtypeService.queryAllBigtype();
    	return list;
    }
    
	//小程序---记账页面----根据收入大分类查询出  当前大分类下的所以收入小分类
    @ResponseBody
    @RequestMapping("/queryAllSmallType")
    public List<IncomeSmalltype> queryAllSmallType(String bigtype){
    	List<IncomeSmalltype> list=intincomesmalltypeService.querySmallTypeByBigType(bigtype);
    	return list;
    }
    
    //小程序---记账页面---根据支出大分类查询出  当前大分类下的所以支出小分类
    @ResponseBody
    @RequestMapping("/queryAllexSmallType")
    public List<ExpenditureSmalltype> queryAllexSmallType(String bigtype){
    	List<ExpenditureSmalltype> list=intexpendituresmalltypeService.querySmallTypeByBigType(bigtype);
    	return list;
    }
    
    //小程序---记账页面----添加收入账单
    @ResponseBody
    @RequestMapping("/addIncomeBill")
    public int addIncomeBill(IncomeBill incomeBill) {
    	if(incomeBill.getBigtypeid()==2) {
    		incomeBill.setSmalltypeid(incomeBill.getSmalltypeid()+6);
    	}
    	System.out.println(incomeBill.getBigtypeid()+"========="+incomeBill.getSmalltypeid()+"----------");
    	int count=intincomebillService.addIncomeBill(incomeBill);
    	return count;
    }
    
    //小程序---记账页面----添加支出账单
    @ResponseBody
    @RequestMapping("/addExpendBill")
    public int addExpendBill(ExpenditureBill expenditureBill) {
    	if(expenditureBill.getBigtypeid()==2) {
    		expenditureBill.setSmalltypeid(expenditureBill.getSmalltypeid()+3);
    	}
    	if(expenditureBill.getBigtypeid()==3) {
    		expenditureBill.setSmalltypeid(expenditureBill.getSmalltypeid()+6);
    	}
    	if(expenditureBill.getBigtypeid()==4) {
    		expenditureBill.setSmalltypeid(expenditureBill.getSmalltypeid()+11);
    	}
    	if(expenditureBill.getBigtypeid()==5) {
    		expenditureBill.setSmalltypeid(expenditureBill.getSmalltypeid()+14);
    	}
    	if(expenditureBill.getBigtypeid()==6) {
    		expenditureBill.setSmalltypeid(expenditureBill.getSmalltypeid()+18);
    	}
    	if(expenditureBill.getBigtypeid()==7) {
    		expenditureBill.setSmalltypeid(expenditureBill.getSmalltypeid()+23);
    	}
    	if(expenditureBill.getBigtypeid()==8) {
    		expenditureBill.setSmalltypeid(expenditureBill.getSmalltypeid()+26);
    	}
    	if(expenditureBill.getBigtypeid()==9) {
    		expenditureBill.setSmalltypeid(expenditureBill.getSmalltypeid()+30);
    	}
    	if(expenditureBill.getBigtypeid()==10) {
    		expenditureBill.setSmalltypeid(expenditureBill.getSmalltypeid()+34);
    	}
    	System.out.println(expenditureBill.getBigtypeid()+expenditureBill.getSmalltypeid());
    	int count=intexpenditurebillService.addExpenditureBill(expenditureBill);
    	return count;
    }
    
    //小程序---我的账单
    @ResponseBody
    @RequestMapping("/myBill")
    public Map<String, Object> myBill(Integer userid,String time,String company) {
    	HashMap<String, Object> map=new HashMap<>();//map存当前月的收入总和    当前月的支出总和    当前月每一天的详细信息（listmap）
    	
    	BigDecimal incomemoney=intincomebillService.queryTotalMoney(userid, time,company);//当前月的收入总和
    	BigDecimal expendituremoney=intexpenditurebillService.queryTotalMoney(userid, time,company);//当前月的支出总和
    	
    	List<String> list=new ArrayList<>();//list集合存当前月的所有收入时间
    	List<String> list1=new ArrayList<>();//list1集合存当前月的所有支出时间
    	
    	List<IncomeBill> incomelists=intincomebillService.queryAllByTimeLike(userid, time,company);//当前月    每一天 的收入信息
    	for (int i = 0; i < incomelists.size(); i++) {
			list.add(incomelists.get(i).getTime());
		}
    	//先将incomelists当前集合中重复的时间去除
	    HashSet h = new HashSet(list);   
	    list.clear();   
	    list.addAll(h);   
	    
    	List<ExpenditureBill> expenditurelists=intexpenditurebillService.queryAllByTimeLike(userid, time,company);//当前月  每一天的支出信息
    	for (int i = 0; i < expenditurelists.size(); i++) {
			list1.add(expenditurelists.get(i).getTime());
		}
    	//先将expenditurelists当前集合中重复的时间去除
	    HashSet h1 = new HashSet(list1);   
	    list1.clear();   
	    list1.addAll(h1);  
    	
		list1.removeAll(list);
		list.addAll(list1);
	
		for (int i = 0; i <list.size(); i++) {
			System.out.println(list.get(i)+"tett");
		}
		HashMap<String, Object> map1=null;//map1存的是每一天的收入  支出详细信息
		List listmap=new ArrayList();//listmap存的是每一个map1
		for (int i = 0; i < list.size(); i++) {
			List<IncomeBill> incomelist=intincomebillService.queryAllByTime(userid, list.get(i),company);//当前月    每一天 的收入信息
    		List<ExpenditureBill> expenditurelist=intexpenditurebillService.queryAllByTime(userid, list.get(i),company);//当前月  每一天的支出信息
    		List<DayBill> listincomelike=intincomebillService.querySumByTimeLike(userid, list.get(i),company);//当前月   每一天的收入总和
    		List<DayBill> listexpenditurelike=intexpenditurebillService.querySumByTimeLike(userid, list.get(i),company);//当前月  每一天的支出总和
    		map1=new HashMap<>();
    		map1.put("time", list.get(i));
    		map1.put("incomelist", incomelist);
    		map1.put("expenditurelist",expenditurelist );
    		map1.put("listincomelike",listincomelike );
    		map1.put("listexpenditurelike",listexpenditurelike );
    		listmap.add(map1);
		}
		
    	map.put("incomemoney",incomemoney);//当前月的收入总和
    	map.put("expendituremoney",expendituremoney);//当前月的支出总和
    	map.put("listmap",listmap);//当前月每一天 的详细收入和支出 信息
    	return map;
    }
}

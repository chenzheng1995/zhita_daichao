package com.zhita.controller.card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.Creditcard1Customvalue;
import com.zhita.service.card.IntCreditCard1CustomService;
import com.zhita.util.PageUtil;

@Controller
@RequestMapping("card1customvalue")
public class Card1CustomvalueController {

	@Autowired
	private IntCreditCard1CustomService intCreditCard1CustomService;
	
	//后台管理---信用卡1——自定义值表查询所有
	@ResponseBody
	@RequestMapping("queryAll")
    public Map<String,Object> queryAll(Integer page){
		int totalCount=intCreditCard1CustomService.queryAllCount();
		PageUtil pageUtil=new PageUtil(page,10,totalCount);
    	if(page<1) {
    		page=1;
    	}
    	else if(page>pageUtil.getTotalPageCount()) {
    		if(totalCount==0) {
    			page=pageUtil.getTotalPageCount()+1;
    		}else {
    			page=pageUtil.getTotalPageCount();
    		}
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil.setPage(pages);
    	List<Creditcard1Customvalue> list=intCreditCard1CustomService.queryAllByPage(pageUtil.getPage(),pageUtil.getPageSize());
    	pageUtil=new PageUtil(page,10,totalCount);
    	
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listCreditCardCustomvalue",list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
	
	 //后台管理---信用卡1——自定义值表   通过id查询对象
	@ResponseBody
	@RequestMapping("selectByPrimaryKey")
    public Creditcard1Customvalue selectByPrimaryKey(Integer id){
    	Creditcard1Customvalue loanCustomvalue=intCreditCard1CustomService.selectByPrimaryKey(id);
    	return loanCustomvalue;
    }
    
    //后台管理---信用卡1——自定义值表  修改保存
	@ResponseBody
	@RequestMapping("updateByPrimaryKey")
    public int updateByPrimaryKey(Creditcard1Customvalue record){
    	int sum=intCreditCard1CustomService.updateByPrimaryKey(record);
    	return sum;
    }

}

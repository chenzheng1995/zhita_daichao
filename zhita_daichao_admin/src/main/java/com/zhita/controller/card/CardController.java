package com.zhita.controller.card;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.CreditCard;
import com.zhita.service.card.IntCardService;
import com.zhita.util.PageUtil;

@Controller
@RequestMapping("/card")
public class CardController {
	@Resource(name="cardServiceImp")
	 private IntCardService intCardService;

	public IntCardService getIntCardService() {
		return intCardService;
	}
	public void setIntCardService(IntCardService intCardService) {
		this.intCardService = intCardService;
	}
	
	//后台管理---查询信用卡部分字段信息，含分页
    @ResponseBody
    @RequestMapping("/queryAllCard")
    public Map<String,Object> queryAllCard(HttpServletRequest request,Integer page){
    	int totalCount=intCardService.pageCount();//该方法是查询信用卡总数量
    	PageUtil pageUtil=new PageUtil(page, totalCount);
    	if(page==0) {
    		page=1;
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil=new PageUtil(pages, totalCount);
    	
    	List<CreditCard> list=intCardService.queryAllCard(pageUtil.getPage());
    	
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listCard", list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
	//后台管理---根据标题模糊查询所有信用卡信息，含分页
    @ResponseBody
    @RequestMapping("/queryByLike")
    public Map<String,Object> queryByLike(HttpServletRequest request,String title,Integer page){
    	List<CreditCard> list=null;
    	PageUtil pageUtil=null;
    	if(title==null||"".equals(title)) {
        	int totalCount=intCardService.pageCount();//该方法是查询信用卡总数量
        	pageUtil=new PageUtil(page, totalCount);
        	if(page==0) {
        		page=1;
        	}
        	int pages=(page-1)*pageUtil.getPageSize();
        	pageUtil=new PageUtil(pages, totalCount);
        	
        	list=intCardService.queryAllCard(pageUtil.getPage());
    	}else {
        	int totalCount=intCardService.pageCountByLike(title);//该方法是模糊查询的信用卡总数量
        	pageUtil=new PageUtil(page, totalCount);
        	if(page==0) {
        		page=1;
        	}
        	int pages=(page-1)*pageUtil.getPageSize();
        	pageUtil=new PageUtil(pages, totalCount);
        	
        	list=intCardService.queryByLike(title,pageUtil.getPage());
    	}
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listCardByLike", list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
    //后台管理---添加信用卡信息
    @ResponseBody
    @RequestMapping("/addAll")
    public int addAll(CreditCard creditCard){
    	System.out.println(creditCard.getContent()+"-----------"+creditCard.getCover());
    	int selnum=intCardService.addAll(creditCard);
    	return selnum;
    }
    //后台管理---通过信用卡id，查询信用卡信息
    @ResponseBody
    @RequestMapping("/selectByPrimaryKey")
    public CreditCard selectByPrimaryKey(Integer id) {
    	CreditCard creditCard=intCardService.selectByPrimaryKey(id);
    	return creditCard;
    }
    //后台管理---通过删除按钮，改变当前银行卡的假删除状态，将状态改为删除
    @ResponseBody
    @RequestMapping("/upaFalseDel")
    public int upaFalseDel(Integer id) {
    	int num=intCardService.upaFalseDel(id);
    	return num;
    }
    //后台管理---修改信用卡状态
    @ResponseBody
    @RequestMapping("/upaState")
	public int upaState(String state,Integer id) {
		int num=0;
		if(state.equals("1")) {
			num=intCardService.upaStateOpen(id);
		}else {
			num=intCardService.upaStateClose(id);
		}
		return num;
	}
}
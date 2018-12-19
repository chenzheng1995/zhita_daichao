package com.zhita.controller.card;


import java.util.List;

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
	
	//后台管理---查询贷款商家部分字段信息，含分页
    @ResponseBody
    @RequestMapping("/queryAllCard")
    public List<CreditCard> queryAllCard(HttpServletRequest request,Integer page){
    	int totalCount=intCardService.pageCount();
    	PageUtil pageUtil=new PageUtil(page, totalCount);
    	if(page==0) {
    		page=1;
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil=new PageUtil(pages, totalCount);
    	
    	List<CreditCard> list=intCardService.queryAllCard(pageUtil.getPage());
    	return list;
    }
	//后台管理---模糊查询所有信用卡信息，含分页
    @ResponseBody
    @RequestMapping("/queryByLike")
    public List<CreditCard> queryByLike(HttpServletRequest request,String title,Integer page){
    	int totalCount=intCardService.pageCount();
    	PageUtil pageUtil=new PageUtil(page, totalCount);
    	if(page==0) {
    		page=1;
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil=new PageUtil(pages, totalCount);
    	
    	List<CreditCard> list=intCardService.queryByLike(title,pageUtil.getPage());
    	return list;
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
    	int selnum=intCardService.upaFalseDel(id);
    	return selnum;
    }
}
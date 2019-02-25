package com.zhita.controller.merchant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.Advertising;
import com.zhita.model.manage.ManageLogin;
import com.zhita.model.manage.Source;
import com.zhita.model.manage.User;
import com.zhita.service.login.IntLoginService;
import com.zhita.service.merchant.IntMerchantService;
import com.zhita.util.ListPageUtil;
import com.zhita.util.PageUtil;
import com.zhita.util.TuoMinUtil;

@Controller
@RequestMapping("/merchant")
public class MerchantController {
	@Autowired
	private IntMerchantService intMerchantService;
	@Autowired
	IntLoginService loginService;
	
	
	//后台管理---查询渠道表所有信息，含分页
	@ResponseBody
	@RequestMapping("/queryAllSource")
    public Map<String,Object> queryAllSource(Integer page,String string){
		string = string.replaceAll("\"", "").replace("[","").replace("]","");
		String [] company= string.split(",");
		PageUtil pageUtil=null;
		List<Source> list=new ArrayList<>();
		List<Source> listto=new ArrayList<>();
		if(company.length==1) {
			
			System.out.println("company.length==1");
			
	    	int totalCount=intMerchantService.pageCount(company[0]);//该方法是查询渠道表总数量
	    	pageUtil=new PageUtil(page,2,totalCount);
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
	    	listto=intMerchantService.queryAllSource(company[0],pageUtil.getPage(),pageUtil.getPageSize());
	    	pageUtil=new PageUtil(page,2,totalCount);
		}
		else if(company.length>1) {
			
			System.out.println("company.length>1");
			
    		List<Source> listfor=null;
			for (int i = 0; i < company.length; i++) {
		    	listfor=intMerchantService.queryAllSource1(company[i]);
            	list.addAll(listfor);
			}
			
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i)+"整合后的集合");
			}
			
			System.out.println("传进工具类的page"+page);
			
			ListPageUtil listPageUtil=new ListPageUtil(list,page,2);
			listto.addAll(listPageUtil.getData());
			
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
		}
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listsource",listto);
    	map.put("pageutil", pageUtil);
    	map.put("company", company);
    	return map;
    }
	
	//后台管理---根据渠道名称字段模糊查询渠道表所有信息，含分页
	@ResponseBody
	@RequestMapping("/querySourceByLike")
    public Map<String,Object> querySourceByLike(String sourceName,Integer page,String string){
		string = string.replaceAll("\"", "").replace("[","").replace("]","");
		String [] company= string.split(",");
		PageUtil pageUtil=null;
		List<Source> list=new ArrayList<>();
		List<Source> listto=new ArrayList<>();
		//渠道名称为空并且公司名不为空  公司名选择的是  全部项
		if((sourceName==null||"".equals(sourceName))&&(company.length>1)) {
			
			System.out.println("第一个if");
			
    		List<Source> listfor=null;
			for (int i = 0; i < company.length; i++) {
		    	listfor=intMerchantService.queryAllSource1(company[i]);
            	list.addAll(listfor);
			}
			
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i)+"整合后的集合");
			}
			
			System.out.println("传进工具类的page"+page);
			
			ListPageUtil listPageUtil=new ListPageUtil(list,page,2);
			listto.addAll(listPageUtil.getData());
			
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
		}
		//渠道名称为空并且公司名不为空  公司名选择的不是  全部项
		else if((sourceName==null||"".equals(sourceName))&&(company.length==1)) {
			
			System.out.println("第二个if");
			
	    	int totalCount=intMerchantService.pageCount(company[0]);//该方法是查询渠道表总数量
	    	pageUtil=new PageUtil(page,2,totalCount);
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
	    	listto=intMerchantService.queryAllSource(company[0],pageUtil.getPage(),pageUtil.getPageSize());
	    	pageUtil=new PageUtil(page,2,totalCount);
			
		}
		//渠道名称不为空并且公司名不为空  公司名选择的是  全部项
		else if((sourceName!=null||!"".equals(sourceName))&&(company.length>1)) {
			
			System.out.println("第三个if");
			
    		List<Source> listfor=null;
			for (int i = 0; i < company.length; i++) {
		    	listfor=intMerchantService.queryByLike1(sourceName,company[i]);
            	list.addAll(listfor);
			}
			
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i)+"整合后的集合");
			}
			
			System.out.println("传进工具类的page"+page);
			
			ListPageUtil listPageUtil=new ListPageUtil(list,page,2);
			listto.addAll(listPageUtil.getData());
			
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
			
		}
		//渠道名称不为空并且公司名不为空  公司名选择的不是  全部项
		else if((sourceName!=null||!"".equals(sourceName))&&(company.length==1)){
			
			System.out.println("第四个if");
			
	    	int totalCount=intMerchantService.pageCountByLike(sourceName,company[0]);//该方法是模糊查询的攻略总数量
	    	pageUtil=new PageUtil(page,2,totalCount);
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
	    	listto=intMerchantService.queryByLike(sourceName,company[0],pageUtil.getPage(),pageUtil.getPageSize());
	    	pageUtil=new PageUtil(page,2,totalCount);
			
		}
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listsourceByLike",listto);
    	map.put("pageutil", pageUtil);
    	return map;
    }
	//后台管理---添加渠道
	@Transactional
	@ResponseBody
	@RequestMapping("/AddAll")
    public int AddAll(Source source){
		
		int num=intMerchantService.addAll(source);//添加渠道表信息
		
		ManageLogin manageLogin=new ManageLogin();
		manageLogin.setUsername("空");
		manageLogin.setPhone(source.getAccount());
		manageLogin.setCompany(source.getCompany());
		manageLogin.setSourcename(source.getSourcename());
	  	loginService.addManageLogin(manageLogin);//添加完一条渠道信息   往后台管理登陆表添加一条信息
		return num;
	}
    //后台管理---通过主键id查询出渠道信息
	@ResponseBody
	@RequestMapping("/selectByPrimaryKey")
    public Source selectByPrimaryKey(Integer id) {
    	Source source=intMerchantService.selectByPrimaryKey(id);
    	return source;
    }
	//后台管理---通过传过来的渠道对象，对当前对象进行修改保存
	@Transactional
	@ResponseBody
	@RequestMapping("/updateSource")
    public int updateSource(Source source){
		int num=intMerchantService.updateSource(source);
		return num;
	}
	
	//后台管理---通过主键id修改其当前对象的假删除状态
	@Transactional
	@ResponseBody
	@RequestMapping("/upaFalseDelById")
    public int upaFalseDelById(Integer id,String account) {
    	int num=intMerchantService.upaFalseDel(id);//通过渠道id更新当前渠道表的假删除状态
    	loginService.upaMFalseDelByPhone(account);//通过渠道账号   去后台登陆表修改假删除状态
    	return num;
    }
    //后台管理---修改渠道状态
	@Transactional
	@ResponseBody
	@RequestMapping("/upaState")
	public int upaState(String state,Integer id) {
		int num=0;
		if(state.equals("1")) {
			num=intMerchantService.upaStateOpen(id);
		}else {
			num=intMerchantService.upaStateClose(id);
		}
		return num;
	}
    //后台管理---查询出当前渠道id在用户表的姓名，年龄，身份证号，手机号，注册时间   含分页
	@ResponseBody
	@RequestMapping("/queryAllUserBySourceId")
    public Map<String,Object> queryAllUserBySourceId(Integer sourceId,Integer page){
    	int totalCount=intMerchantService.pageCountBySourceId(sourceId);//该方法是查询出当前渠道id在用户表的姓名，年龄，身份证号，手机号，注册时间 的总数量
    	PageUtil pageUtil=new PageUtil(page,2,totalCount);
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
    	List<User> list=intMerchantService.queryAllUserBySourceId(sourceId,pageUtil.getPage(),pageUtil.getPageSize());
    	
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listuser",list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
    //后台管理---通过条件做各种模糊查询   查询出当前渠道id在用户表的姓名，年龄，身份证号，手机号，注册时间   含分页
	@ResponseBody
	@RequestMapping("/queryAllUserByLikeAll")
    public Map<String,Object> queryAllUserByLikeAll(Integer SourceId,String registrationTimeStart,String registrationTimeEnd,String phone,Integer page){
		Map<String,Object> map=intMerchantService.queryAllUserByLikeAll(SourceId, registrationTimeStart, registrationTimeEnd, phone, page);
		String phoneNumber = (String) map.get("phone");
		String name = (String) map.get("name");
		String idCard = (String) map.get("idCard");
		TuoMinUtil tuoMinUtil = new TuoMinUtil();
		phoneNumber = tuoMinUtil.mobileEncrypt(phoneNumber);
		if(name!=null) {
			name = tuoMinUtil.nameEncrypt(name);
		}
		if(idCard!=null) {
			idCard = tuoMinUtil.nameEncrypt(idCard);
		}
		map.put("phone",phoneNumber);
		map.put("name",name);
		map.put("idCard",idCard);
		return map;
	}
}

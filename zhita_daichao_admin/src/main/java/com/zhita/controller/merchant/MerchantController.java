package com.zhita.controller.merchant;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.Source;
import com.zhita.model.manage.User;
import com.zhita.service.login.IntLoginService;
import com.zhita.service.merchant.IntMerchantService;
import com.zhita.service.sourcetemplate.SourceTemplateService;
import com.zhita.util.ListPageUtil;
import com.zhita.util.PageUtil;
import com.zhita.util.Timestamps;
import com.zhita.util.TuoMinUtil;

@Controller
@RequestMapping("/merchant")
public class MerchantController {
	@Autowired
	private IntMerchantService intMerchantService;
	@Autowired
	IntLoginService loginService;
	
	@Autowired
	SourceTemplateService sourceTemplateService;
	
	
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
	    	pageUtil=new PageUtil(page,10,totalCount);
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
	    	pageUtil=new PageUtil(page,10,totalCount);
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
			
			ListPageUtil listPageUtil=new ListPageUtil(list,page,10);
			listto.addAll(listPageUtil.getData());
			
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
		}
		
		TuoMinUtil tuoMinUtil=new TuoMinUtil();
		
		for (int i = 0; i < listto.size(); i++) {
			if(listto.get(i).getAccount()!=null) {
				String tuomingaccount=tuoMinUtil.mobileEncrypt(listto.get(i).getAccount());
				listto.get(i).setAccount(tuomingaccount);
			}
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
		PageUtil pageUtil=null;
		List<Source> list=new ArrayList<>();
		List<Source> listto=new ArrayList<>();
		string = string.replaceAll("\"", "").replace("[","").replace("]","");
		String [] company=string.split(",");
		
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
			
			ListPageUtil listPageUtil=new ListPageUtil(list,page,10);
			listto.addAll(listPageUtil.getData());
			
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
		}
		//渠道名称为空并且公司名不为空  公司名选择的不是  全部项
		else if((sourceName==null||"".equals(sourceName))&&(company.length==1)) {
			
			System.out.println("第二个if");
			
	    	int totalCount=intMerchantService.pageCount(company[0]);//该方法是查询渠道表总数量
	    	pageUtil=new PageUtil(page,10,totalCount);
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
	    	pageUtil=new PageUtil(page,10,totalCount);
			
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
			
			ListPageUtil listPageUtil=new ListPageUtil(list,page,10);
			listto.addAll(listPageUtil.getData());
			
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
			
		}
		//渠道名称不为空并且公司名不为空  公司名选择的不是  全部项
		else if((sourceName!=null||!"".equals(sourceName))&&(company.length==1)){
			
			System.out.println("第四个if");
			
	    	int totalCount=intMerchantService.pageCountByLike(sourceName,company[0]);//该方法是模糊查询的攻略总数量
	    	pageUtil=new PageUtil(page,10,totalCount);
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
	    	pageUtil=new PageUtil(page,10,totalCount);
			
		}
		TuoMinUtil tuoMinUtil=new TuoMinUtil();
		
		for (int i = 0; i < listto.size(); i++) {
			if(listto.get(i).getAccount()!=null) {
				String tuomingaccount=tuoMinUtil.mobileEncrypt(listto.get(i).getAccount());
				listto.get(i).setAccount(tuomingaccount);
			}
		}
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listsourceByLike",listto);
    	map.put("pageutil", pageUtil);
    	return map;
    }
//	//后台管理---添加渠道
//	@Transactional
//	@ResponseBody
//	@RequestMapping("/AddAll")
//    public int AddAll(Source source,String templateName) throws IOException{
//		Integer templateId = sourceTemplateService.getid(templateName);
//		source.setTemplateId(templateId);
//		FolderUtil FolderUtil = new FolderUtil();
//		FolderUtil.copyDir("D:\\nginx-1.14.2\\html\\dist\\promote\\"+templateName,"D:\\nginx-1.14.2\\html\\dist\\promote\\"+source.getSourcename());
//		source.setLink("http://tg.mis8888.com/promote/"+source.getSourcename()+"/index.html");
//		int num=intMerchantService.addAll(source);//添加渠道表信息		
//		ManageLogin manageLogin=new ManageLogin();
//		manageLogin.setCompany(source.getCompany());
//		manageLogin.setSourcename(source.getSourcename());
//		manageLogin.setPhone(source.getAccount());
//		manageLogin.setPwd(source.getAccount());
//	  	loginService.addManageLogin1(manageLogin);//添加完一条渠道信息   往后台管理登陆表添加一条信息
//		return num;
//	}
	
	
	//后台管理---添加渠道
	@Transactional
	@ResponseBody
	@RequestMapping("/AddAll")
    public int AddAll(Source source,String templateName) throws IOException{
		Integer templateId = sourceTemplateService.getid(templateName);
		source.setTemplateId(templateId);
		source.setLink("http://tg.mis8888.com/promote/"+templateName+"/index.html?code="+source.getSourcename());
		
		int count=intMerchantService.queryIsExist(source.getSourcename());
		int num=0;
		int num1=0;
		if(count==0){
			num=intMerchantService.addAll(source);//添加渠道表信息	
			return num;
		}else{
			num1=intMerchantService.updateAll(source);
			return num1;
		}
		
		//sourcename,account,pwd,link,company,discount,templateId
		
	/*	ManageLogin manageLogin=new ManageLogin();
		manageLogin.setCompany(source.getCompany());
		manageLogin.setSourcename(source.getSourcename());
		manageLogin.setPhone(source.getAccount());
		manageLogin.setPwd(source.getAccount());
	  	loginService.addManageLogin1(manageLogin);*///添加完一条渠道信息   往后台管理登陆表添加一条信息
		
	}
	
    //后台管理---通过主键id查询出渠道信息
	@ResponseBody
	@RequestMapping("/selectByPrimaryKey")
    public Map<String, Object> selectByPrimaryKey(Integer id) {
		Map<String, Object> map = new HashMap<>();
    	Source source=intMerchantService.selectByPrimaryKey(id);
    	int templateId = source.getTemplateId();
    	String templateName = sourceTemplateService.getTemplateName(templateId);
    	map.put("source",source);
    	map.put("templateName",templateName);
    	return map;
    }
//	//后台管理---通过传过来的渠道对象，对当前对象进行修改保存
//	@Transactional
//	@ResponseBody
//	@RequestMapping("/updateSource")
//    public int updateSource(Source source,String oldSourceName,String templateName) throws IOException{
//		FolderUtil FolderUtil = new FolderUtil();
//		if(!source.getSourcename().equals(oldSourceName)) {		
//		FolderUtil.copyDir("D:\\nginx-1.14.2\\html\\dist\\promote\\"+oldSourceName,"D:\\nginx-1.14.2\\html\\dist\\promote\\"+source.getSourcename());
//		FolderUtil.deleteDirectory("D:\\nginx-1.14.2\\html\\dist\\promote\\"+oldSourceName);//把旧的文件夹删掉
//		source.setLink("http://tg.mis8888.com/promote/"+source.getSourcename()+"/index.html");
//		System.out.println(source);
//		}
//		Integer oldTemplateId = intMerchantService.getTemplateId(source.getId());
//		String oldTemplateName = sourceTemplateService.getTemplateName(oldTemplateId);
//		Integer templateId = sourceTemplateService.getid(templateName);
//		source.setTemplateId(templateId);
//		source.setLink("http://tg.mis8888.com/promote/"+source.getSourcename()+"/index.html");	
//		if(!oldTemplateName.equals(templateName)) {
//			FolderUtil.copyDir("D:\\nginx-1.14.2\\html\\dist\\promote\\"+templateName,"D:\\nginx-1.14.2\\html\\dist\\promote\\"+source.getSourcename());			
//		}
//	
//		Source source1=intMerchantService.selectByPrimaryKey(source.getId());
//		int num=intMerchantService.updateSource(source);
//		intMerchantService.updateManageLogin(source.getAccount(),source.getSourcename(), source1.getAccount());
//		return num;
//	}
	
	//后台管理---通过传过来的渠道对象，对当前对象进行修改保存
	@Transactional
	@ResponseBody
	@RequestMapping("/updateSource")
    public int updateSource(Source source,String oldSourceName,String templateName) throws IOException{
		Integer templateId = sourceTemplateService.getid(templateName);
		source.setTemplateId(templateId);

		source.setLink("http://tg.mis8888.com/promote/"+templateName+"/index.html?code="+source.getSourcename());	
		int num=intMerchantService.updateSource(source);
		//intMerchantService.updateManageLogin(source.getAccount(),source.getSourcename(), source1.getAccount());
		return num;
	}
	
//	//后台管理---通过主键id修改其当前对象的假删除状态
//	@Transactional
//	@ResponseBody
//	@RequestMapping("/upaFalseDelById")
//    public int upaFalseDelById(Integer id,String account,String SourceName) {
//    	int num=intMerchantService.upaFalseDel(id);//通过渠道id更新当前渠道表的假删除状态
//    	loginService.upaMFalseDelByPhone(account);//通过渠道账号   去后台登陆表修改假删除状态
//    	FolderUtil folderUtil = new FolderUtil();
//    	folderUtil.DeleteFolder("D:\\nginx-1.14.2\\html\\dist\\promote\\"+SourceName);//删除文件夹
//    	return num;
//    }
	//后台管理---通过主键id修改其当前对象的假删除状态
	@Transactional
	@ResponseBody
	@RequestMapping("/upaFalseDelById")
    public int upaFalseDelById(Integer id,String account,String SourceName) {
    	int num=intMerchantService.upaFalseDel(id);//通过渠道id更新当前渠道表的假删除状态
    	//loginService.upaMFalseDelByPhone(account);//通过渠道账号   去后台登陆表修改假删除状态
    	return num;
    }
//    //后台管理---修改渠道状态
//	@Transactional
//	@ResponseBody
//	@RequestMapping("/upaState")
//	public int upaState(String state,Integer id) throws IOException {
//		int num=0;
//  		String link = intMerchantService.getLink(id);
//	 	String fileName = link.substring(30, link.lastIndexOf("/"));
//	 	
//	 	File file = new File("D:\\nginx-1.14.2\\html\\dist\\promote\\"+fileName);
//	 	File file2 = new File("D:\\nginx-1.14.2\\html\\dist\\promote\\"+fileName+"###");
//		if(state.equals("1")) {
//			if(fileName.endsWith("###")) {
//			 	String newLink = link.replace(fileName,fileName.substring(0,fileName.length()-3));
//				intMerchantService.updateLink(newLink,id);
////				FolderUtil folderUtil = new FolderUtil();
////				folderUtil.renameFolder("E:\\nginx-1.14.2\\html\\dist\\promote\\"+fileName,fileName.substring(0,fileName.length()-3));
//				File file3 = new File("D:\\nginx-1.14.2\\html\\dist\\promote\\"+fileName.substring(0,fileName.length()-3));
//				file.renameTo(file3);
//			}
//			num=intMerchantService.upaStateOpen(id);
//		}else {
//			if(!fileName.endsWith("###")) {
//				String newLink = link.replace("/"+fileName+"/","/"+fileName+"###"+"/");
//				intMerchantService.updateLink(newLink,id);
//				file.renameTo(file2);
////				FolderUtil folderUtil = new FolderUtil();
////				folderUtil.renameFolder("E:\\nginx-1.14.2\\html\\dist\\promote\\"+fileName,fileName+"###");
//			}
//			num=intMerchantService.upaStateClose(id);
//		}
//		return num;
//	}
	
    //后台管理---修改渠道状态
	@Transactional
	@ResponseBody
	@RequestMapping("/upaState")
	public int upaState(String state,Integer id) throws IOException {
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
    	List<User> list=intMerchantService.queryAllUserBySourceId(sourceId,pageUtil.getPage(),pageUtil.getPageSize());
    	pageUtil=new PageUtil(page,10,totalCount);
    	
    	TuoMinUtil tuoMinUtil=new TuoMinUtil();
    	for (int i = 0; i < list.size(); i++) {
    		if(list.get(i).getName()!=null) {
    			String tuomingname=tuoMinUtil.nameEncrypt(list.get(i).getName());//姓名脱名
    			list.get(i).setName(tuomingname);
    		}
    		if(list.get(i).getPhone()!=null) {
    			String tuomingphone=tuoMinUtil.mobileEncrypt(list.get(i).getPhone());//手机号脱名
    			list.get(i).setPhone(tuomingphone);
    		}
    		if(list.get(i).getIdcard()!=null) {
    			String tuomingidcard=tuoMinUtil.idEncrypt(list.get(i).getPhone());//身份证号脱名
    			list.get(i).setIdcard(tuomingidcard);
    		}
		}
    	
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listuser",list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
    //后台管理---通过条件做各种模糊查询   查询出当前渠道id在用户表的姓名，年龄，身份证号，手机号，注册时间   含分页
	@ResponseBody
	@RequestMapping("/queryAllUserByLikeAll")
    public Map<String,Object> queryAllUserByLikeAll(Integer SourceId,String registrationTimeStart,String registrationTimeEnd,String phone,Integer page) throws ParseException{
		String timeStart=null;
		String timeEnd=null;
		if((registrationTimeStart!=null&&!"".equals(registrationTimeStart))&&(registrationTimeEnd==null&&"".equals(registrationTimeEnd))){
			timeStart=Timestamps.dateToStamp(registrationTimeStart);//将开始时间转换为时间戳
			Date dateDay=new Date();
			timeEnd=Timestamps.dateToStamp1(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateDay));//将结束时间转换为时间戳
		}
		else if((registrationTimeStart!=null&&!"".equals(registrationTimeStart))&&(registrationTimeEnd!=null&&!"".equals(registrationTimeEnd))){
			String LikeTime1add=registrationTimeStart+" "+"00:00:00";
	    	String LikeTime2add=registrationTimeEnd+" "+"24:00:00";
	    	timeStart=Timestamps.dateToStamp1(LikeTime1add);//将开始时间转换为时间戳
	    	timeEnd=Timestamps.dateToStamp1(LikeTime2add);//将开始时间转换为时间戳
		}
		Map<String,Object> map=intMerchantService.queryAllUserByLikeAll(SourceId, timeStart, timeEnd, phone, page);
		return map;
	}
}

package com.zhita.controller.card;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhita.model.manage.CreditCardType;
import com.zhita.service.card.IntCreditCardTypeService;
import com.zhita.util.FolderUtil;
import com.zhita.util.ListPageUtil;
import com.zhita.util.OssUtil;
import com.zhita.util.PageUtil;

//完全复制贷款分类controller
@Controller
@RequestMapping("/creditcardtype")
public class CreditCardTypeController {
	@Autowired
	private IntCreditCardTypeService intCreditCardTypeService;
	
	//后台管理---查询贷款分类所有信息，含分页
    @ResponseBody
    @RequestMapping("/queryAllPage")
    public Map<String,Object> queryAllPage(Integer page,String string){
		string = string.replaceAll("\"", "").replace("[","").replace("]","");
		String [] company= string.split(",");
    	System.out.println("刚进来时候的page"+page);  	
		PageUtil pageUtil=null;
		List<CreditCardType> list=new ArrayList<>();
		List<CreditCardType> listto=new ArrayList<>();
		if(company.length==1) {
			
			System.out.println("company.length==1");
			
	    	int totalCount=intCreditCardTypeService.pageCount(company[0]);//该方法是查询贷款分类总数量
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
	    	listto=intCreditCardTypeService.queryAllPage(company[0],pageUtil.getPage(),pageUtil.getPageSize());
	    	pageUtil=new PageUtil(page,10,totalCount);
		}
		else if(company.length>1) {
			
			System.out.println("company.length>1");
			
    		List<CreditCardType> listfor=null;
			for (int i = 0; i < company.length; i++) {
		    	listfor=intCreditCardTypeService.queryAllNoPage(company[i]);
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
		
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listLoanClass", listto);
    	map.put("pageutil",pageUtil);
    	map.put("company", company);
    	return map;
    }
    //后台管理---模糊查询贷款分类信息,并且有分页功能
    @ResponseBody
    @RequestMapping("/queryByLike")
    public Map<String,Object> queryByLike(String businessClassification,Integer page,String string){
    	PageUtil pageUtil=null;
    	List<CreditCardType> list=new ArrayList<>();
    	List<CreditCardType> listto=new ArrayList<>();
		string = string.replaceAll("\"", "").replace("[","").replace("]","");
		String [] company=string.split(",");
		
    	//类型为空并且公司名不为空    公司名选择的是 全部项
    	if((businessClassification==null||"".equals(businessClassification))&&(company.length>1)) {
    		
    		System.out.println("第一个if");
    		
    		List<CreditCardType> listfor=null;
			for (int i = 0; i < company.length; i++) {
		    	listfor=intCreditCardTypeService.queryAllNoPage(company[i]);
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
    	//类型为空并且公司名不为空    公司名选择的不是 全部项
    	else if((businessClassification==null||"".equals(businessClassification))&&(company.length==1)) {
    		
    		System.out.println("第二个if");
    		
        	int totalCount=intCreditCardTypeService.pageCount(company[0]);//该方法是查询贷款分类总数量
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
	    	listto=intCreditCardTypeService.queryAllPage(company[0],pageUtil.getPage(),pageUtil.getPageSize());
	    	pageUtil=new PageUtil(page,10,totalCount);
    	}
    	//类型不为空并且公司名不为空    公司名选择的是 全部项
    	else if((businessClassification!=null||!"".equals(businessClassification))&&(company.length>1)) {
    		
    		System.out.println("第三个if");
    		
    		List<CreditCardType> listfor=null;
			for (int i = 0; i < company.length; i++) {
		    	listfor=intCreditCardTypeService.queryByLike1(businessClassification,company[i]);
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
    	//类型不为空并且公司名不为空  公司名选择的不是 全部项
    	else if((businessClassification!=null||!"".equals(businessClassification))&&(company.length==1)){
    		
    		System.out.println("第四个if");
    		
        	int totalCount=intCreditCardTypeService.pageCountByLike(businessClassification,company[0]);//该方法是模糊查询的贷款分类总数量
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
	    	listto=intCreditCardTypeService.queryByLike(businessClassification,company[0],pageUtil.getPage(),pageUtil.getPageSize());
	    	pageUtil=new PageUtil(page,10,totalCount);
    	}
    	HashMap<String, Object> map=new HashMap<>();
    	map.put("listLoanClaByLike", listto);
    	map.put("pageutil",pageUtil);
		return map;
    }
	//后台管理---添加贷款分类信息
    @Transactional
    @ResponseBody
    @RequestMapping("/insertAllfind")
    public Map<String,Object> insertAll(CreditCardType loanClassification,MultipartFile file) throws Exception{
		Map<String, Object> map = new HashMap<>();
		if (file != null) {// 判断上传的文件是否为空
			String path = null;// 文件路径
			String type = null;// 文件类型
			InputStream iStream = file.getInputStream();
			String fileName = file.getOriginalFilename();// 文件原名称
			// 判断文件类型
			type = fileName.indexOf(".") != -1? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()): null;
			if (type != null) {// 判断文件类型是否为空
				if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
					// 自定义的文件名称
					String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
					// 设置存放图片文件的路径
//					path = "loanClassification/" + /* System.getProperty("file.separator")+ */trueFileName;
//					OssUtil ossUtil = new OssUtil();
//					String ossPath = ossUtil.uploadFile(iStream, path);
//					if(ossPath.substring(0, 5).equals("https")) {
//						System.out.println("路径为："+ossPath);
//						loanClassification.setIcon(ossPath);
//						map.put("msg", "图片上传成功");
//					}
//					
//					System.out.println("存放图片文件的路径:" + ossPath);
					
					path = "D://nginx-1.14.2/html/dist/image/loanClassification/" + /* System.getProperty("file.separator")+ */trueFileName;
//					OssUtil ossUtil = new OssUtil();
//					String ossPath = ossUtil.uploadFile(iStream, path);
//					if(ossPath.substring(0, 5).equals("https")) {
//						System.out.println("路径为："+ossPath);
//						shufflingFigure.setCover(ossPath);
//						map.put("msg", "图片上传成功");
//					}
					InputStream inStream = file.getInputStream();
					FolderUtil folderUtil = new FolderUtil();
					String code = folderUtil.uploadImage(inStream, path);
					if(code.equals("200")) {
						loanClassification.setIcon("http://tg.mis8888.com/image/loanClassification/"+trueFileName);
						map.put("msg", "图片上传成功");
					}else {
						map.put("msg", "图片上传失败");
					}
				} else {
					map.put("msg", "不是我们想要的文件类型,请按要求重新上传");
					return map;
				}
			} else {
				map.put("msg", "文件类型为空");
				return map;
			}
		}else {
			map.put("msg", "请上传图片");
			return map;
		} 
		intCreditCardTypeService.addAll(loanClassification);
    	return map;
    }
	//后台管理---通过主键id查询出贷款分类信息
    @ResponseBody
    @RequestMapping("/selectByPrimaryKey")
    public CreditCardType selectByPrimaryKey(Integer id){
    	CreditCardType loanClassification=intCreditCardTypeService.selectByPrimaryKey(id);
    	return loanClassification;
    }
    //通过传过来的贷款分类对象，对当前对象进行修改保存
    @Transactional
    @ResponseBody
    @RequestMapping("/updateByPrimaryKey")
    public Map<String, Object> updateByPrimaryKey(CreditCardType loanClassification,MultipartFile file) throws Exception{
		Map<String, Object> map = new HashMap<>();
		if (file.getSize()!=0) {// 判断上传的文件是否为空
			String path = null;// 文件路径
			String type = null;// 文件类型
			InputStream iStream = file.getInputStream();
			String fileName = file.getOriginalFilename();// 文件原名称
			// 判断文件类型
			type = fileName.indexOf(".") != -1? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()): null;
			if (type != null) {// 判断文件类型是否为空
				if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
					// 自定义的文件名称
					String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
					// 设置存放图片文件的路径
//					path = "advertising/" + /* System.getProperty("file.separator")+ */trueFileName;
//					OssUtil ossUtil = new OssUtil();
//					String ossPath = ossUtil.uploadFile(iStream, path);
//					if(ossPath.substring(0, 5).equals("https")) {
//						System.out.println("路径为："+ossPath);
//						loanClassification.setIcon(ossPath);
//						map.put("msg", "图片上传成功");
//					}
//					
//					System.out.println("存放图片文件的路径:" + ossPath);
					
					path = "D://nginx-1.14.2/html/dist/image/advertising/" + /* System.getProperty("file.separator")+ */trueFileName;
//					OssUtil ossUtil = new OssUtil();
//					String ossPath = ossUtil.uploadFile(iStream, path);
//					if(ossPath.substring(0, 5).equals("https")) {
//						System.out.println("路径为："+ossPath);
//						shufflingFigure.setCover(ossPath);
//						map.put("msg", "图片上传成功");
//					}
					InputStream inStream = file.getInputStream();
					FolderUtil folderUtil = new FolderUtil();
					String code = folderUtil.uploadImage(inStream, path);
					if(code.equals("200")) {
						loanClassification.setIcon("http://tg.mis8888.com/image/advertising/"+trueFileName);
						map.put("msg", "图片上传成功");
					}else {
						map.put("msg", "图片上传失败");
					}
				} else {
					map.put("msg", "不是我们想要的文件类型,请按要求重新上传");
					return map;
				}
			} else {
				map.put("msg", "文件类型为空");
				return map;
			}
		}else {
			int id = loanClassification.getId();
			String icon=intCreditCardTypeService.queryIconById(id);//通过传进来的贷款分类id  查询当前分类的图标
			loanClassification.setIcon(icon);
		} 
		intCreditCardTypeService.updateByPrimaryKey(loanClassification);
    	return map;
    }
}

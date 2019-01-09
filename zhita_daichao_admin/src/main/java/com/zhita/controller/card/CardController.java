package com.zhita.controller.card;


import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhita.model.manage.CreditCard;
import com.zhita.model.manage.LoanClassification;
import com.zhita.service.card.IntCardService;
import com.zhita.util.OssUtil;
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
    	
    	List<CreditCard> list=intCardService.queryAllCard(pageUtil.getPage(),pageUtil.getPageSize());
    	
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
        	
        	list=intCardService.queryAllCard(pageUtil.getPage(),pageUtil.getPageSize());
    	}else {
        	int totalCount=intCardService.pageCountByLike(title);//该方法是模糊查询的信用卡总数量
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
        	
        	list=intCardService.queryByLike(title,pageUtil.getPage(),pageUtil.getPageSize());
    	}
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listCardByLike", list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
    //后台管理---添加信用卡信息
    @ResponseBody
    @RequestMapping("/addAll")
    public Map<String, Object> addAll(CreditCard creditCard,MultipartFile file) throws Exception{
    	System.out.println(creditCard.getContent()+"-----------"+creditCard.getCover());
    	

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
					path = "credit_card/" + /* System.getProperty("file.separator")+ */trueFileName;
					OssUtil ossUtil = new OssUtil();
					String ossPath = ossUtil.uploadFile(iStream, path);
					if(ossPath.substring(0, 5).equals("https")) {
						System.out.println("路径为："+ossPath);
						creditCard.setCover(ossPath);
						map.put("msg", "图片上传成功");
					}
					
					System.out.println("存放图片文件的路径:" + ossPath);
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
    	intCardService.addAll(creditCard);
    	return map;
    }
    //后台管理---通过信用卡id，查询信用卡信息
    @ResponseBody
    @RequestMapping("/selectByPrimaryKey")
    public CreditCard selectByPrimaryKey(Integer id) {
    	CreditCard creditCard=intCardService.selectByPrimaryKey(id);
    	return creditCard;
    }
    //后台管理---通过传过来的信用卡对象，对当前对象进行修改保存
    @ResponseBody
    @RequestMapping("/updateCreditCard")
    public Map<String, Object> updateCreditCard(CreditCard creditCard,MultipartFile file) throws Exception{
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
					path = "credit_card/" + /* System.getProperty("file.separator")+ */trueFileName;
					OssUtil ossUtil = new OssUtil();
					String ossPath = ossUtil.uploadFile(iStream, path);
					if(ossPath.substring(0, 5).equals("https")) {
						System.out.println("路径为："+ossPath);
						creditCard.setCover(ossPath);
						map.put("msg", "图片上传成功");
					}
					
					System.out.println("存放图片文件的路径:" + ossPath);
				} else {
					map.put("msg", "不是我们想要的文件类型,请按要求重新上传");
					return map;
				}
			} else {
				map.put("msg", "文件类型为空");
				return map;
			}
		}else {
			int id = creditCard.getId();
			String cover = intCardService.getCover(id); //通过传过来的信用卡id，查询商标的URL，查询图片的URL
			creditCard.setCover(cover);
		} 
    	intCardService.updateCreditCard(creditCard);
    	return map;
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
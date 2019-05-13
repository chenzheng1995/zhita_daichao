package com.zhita.controller.card;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhita.model.manage.CreditCard1;
import com.zhita.model.manage.CreditCardType;
import com.zhita.model.manage.JiaFangTongji;
import com.zhita.service.card.IntCard1Service;
import com.zhita.service.card.IntCreditCardFootprintService;
import com.zhita.service.card.IntCreditCardTypeService;
import com.zhita.util.DateListUtil;
import com.zhita.util.FolderUtil;
import com.zhita.util.ListPageUtil;
import com.zhita.util.OssUtil;
import com.zhita.util.PageUtil;
import com.zhita.util.Timestamps;

//完全复制商家controller
@Controller
@RequestMapping("/card1")
public class Card1Controller {
	
	@Autowired
	private IntCard1Service intCard1Service;
	
	@Autowired
	private IntCreditCardTypeService intCreditCardTypeService;
	
	@Autowired
	private IntCreditCardFootprintService intCreditCardFootprintService;


	//后台管理---查询贷款商家部分字段信息，含分页
    @ResponseBody
    @RequestMapping("/queryAllAdmin")
    public Map<String,Object> queryAll(Integer page,String string){
		string = string.replaceAll("\"", "").replace("[","").replace("]","");
		String[] company=string.split(",");
		PageUtil pageUtil=null;
		List<CreditCard1> list=new ArrayList<>();
		List<CreditCard1> listto=new ArrayList<>();
    	if(company.length==1) {
    		
    		System.out.println("company.length==1");
    		
    		List<String> list1=intCard1Service.queryAllBusinessName(company[0]);//查询出所有贷款商家的商家名称，存入list集合
        	for (int i = 0; i < list1.size(); i++) {
        		intCard1Service.upaApplicationNumber(intCreditCardFootprintService.queryCount(list1.get(i),company[0]),list1.get(i));//将商家的被申请人数字段进行修改
    		}
        	int totalCount=intCard1Service.pageCount(company[0]);//该方法是查询贷款商家总条数
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
        	
        	listto=intCard1Service.queryAllAdmain(company[0],pageUtil.getPage(),pageUtil.getPageSize());
        	pageUtil=new PageUtil(page,10,totalCount);
        	for (int i = 0; i < list.size(); i++) {
    			System.out.println(list.get(i).getBusinessname()+"***"+list.get(i).getApplicationnumber());
    		}
    	}
    	else if(company.length>1){
    		
    		System.out.println("company.length>1");
    		
    		List<CreditCard1> listfor=null;
    		for (int j = 0; j < company.length; j++) {
    			List<String> list1=intCard1Service.queryAllBusinessName(company[j]);//查询出所有贷款商家的商家名称，存入list集合
            	for (int i = 0; i < list1.size(); i++) {
            		intCard1Service.upaApplicationNumber(intCreditCardFootprintService.queryCount(list1.get(i),company[j]),list1.get(i));//将商家的被申请人数字段进行修改
        		}
            	
            	listfor=intCard1Service.queryAllAdmain1(company[j]);
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
    	map.put("listLoansBusin",listto);
    	map.put("pageutil", pageUtil);
    	map.put("company", company);
    	return map;
    }
    
	//后台管理---查询贷款分类的所有分类名称
    @ResponseBody
    @RequestMapping("/selAllName")
    public List<CreditCardType> selAllName(String string){
    	string = string.replaceAll("\"", "").replace("[","").replace("]","");
		String[] company=string.split(",");
		List<CreditCardType> loanlist=null;
		List<CreditCardType> loanlistto=new ArrayList<>();
		for (int i = 0; i < company.length; i++) {
			loanlist=intCreditCardTypeService.queryAllLoanCla(company[i]);//添加贷款商家信息时，先查询出贷款分类的所有类型
			loanlistto.addAll(loanlist);
		}
    	return loanlistto;
    }
    
	//后台管理---添加贷款商家信息
    @Transactional
    @ResponseBody
    @RequestMapping("/insertAllAdmin")
    public Map<String, Object> insertAll(CreditCard1 loansBusinesses,MultipartFile file) throws Exception{
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
//					path = "loans_businesses/" + /* System.getProperty("file.separator")+ */trueFileName;
//					OssUtil ossUtil = new OssUtil();
//					String ossPath = ossUtil.uploadFile(iStream, path);
//					if(ossPath.substring(0, 5).equals("https")) {
//						System.out.println("路径为："+ossPath);
//						loansBusinesses.setTrademark(ossPath);
//						map.put("msg", "图片上传成功");
//					}
//					
//					System.out.println("存放图片文件的路径:" + ossPath);
					
				
					path = "D://nginx-1.14.2/html/dist/image/loans_businesses/" + /* System.getProperty("file.separator")+ */trueFileName;
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
						loansBusinesses.setTrademark("http://tg.mis8888.com/image/loans_businesses/"+trueFileName);
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

    	BigDecimal limitsmall=loansBusinesses.getLoanlimitsmall();//得到输入框的借款额度（小）
    	BigDecimal limitbig=loansBusinesses.getLoanlimitbig();//得到输入框的借款额度（大）
    	String limit=limitsmall+"~"+limitbig;//将两个额度拼接成一个字符串，赋给loansBusinesses的loanlimit的字段 	
    	loansBusinesses.setLoanlimit(limit);
		String num=1+(((int)(Math.random()*8998)+1000+1)+"");
		int applications = Integer.parseInt(num);   	
    	loansBusinesses.setApplications(applications);
    	intCard1Service.insert(loansBusinesses);
    	
    	return map;

    }
    
	//后台管理---通过商家名称模糊查询，并且有分页功能
    @ResponseBody
    @RequestMapping("/queryByNameLike")
    public Map<String,Object> queryByNameLike(String businessName,Integer page,String string){
    	PageUtil pageUtil=null;
    	List<CreditCard1> list=new ArrayList<>();
    	List<CreditCard1> listto=new ArrayList<>();
		string = string.replaceAll("\"", "").replace("[","").replace("]","");
		String [] company= string.split(",");
    	//商家名称为空并且公司名不为空    公司名选择的是 全部项
    	if((businessName==null||"".equals(businessName))&&(company.length>1)){
    		
    		System.out.println("第一个if");
    		
    		List<CreditCard1> listfor=null;
    		for (int j = 0; j < company.length; j++) {
    			List<String> list1=intCard1Service.queryAllBusinessName(company[j]);//查询出所有贷款商家的商家名称，存入list集合
            	for (int i = 0; i < list1.size(); i++) {
            		intCard1Service.upaApplicationNumber(intCreditCardFootprintService.queryCount(list1.get(i),company[j]),list1.get(i));//将商家的被申请人数字段进行修改
        		}
            	
            	listfor=intCard1Service.queryAllAdmain1(company[j]);
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
		//商家名称为空并且公司名不为空   公司名选择的不是全部项
    	else if((businessName==null||"".equals(businessName))&&(company.length==1)) {
    		
    		System.out.println("第二个if");
    		
       		List<String> list1=intCard1Service.queryAllBusinessName(company[0]);//查询出所有贷款商家的商家名称，存入list集合
        	for (int i = 0; i < list1.size(); i++) {
        		intCard1Service.upaApplicationNumber(intCreditCardFootprintService.queryCount(list1.get(i),company[0]),list1.get(i));//将商家的被申请人数字段进行修改
    		}
        	int totalCount=intCard1Service.pageCount(company[0]);//该方法是查询贷款商家总条数
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
        	
        	listto=intCard1Service.queryAllAdmain(company[0],pageUtil.getPage(),pageUtil.getPageSize());
        	pageUtil=new PageUtil(page,10,totalCount);
        	for (int i = 0; i < list.size(); i++) {
    			System.out.println(list.get(i).getBusinessname()+"***"+list.get(i).getApplicationnumber());
    		}
    	
    	}
		//商家名称不为空并且公司名不为空   公司名选择的是全部项
    	else if((businessName!=null||!"".equals(businessName))&&(company.length>1)) {

    		System.out.println("第三个if");
    		
    		List<CreditCard1> listfor=null;
    		for (int j = 0; j < company.length; j++) {
    			List<String> list1=intCard1Service.queryAllBusinessName(company[j]);//查询出所有贷款商家的商家名称，存入list集合
            	for (int i = 0; i < list1.size(); i++) {
            		intCard1Service.upaApplicationNumber(intCreditCardFootprintService.queryCount(list1.get(i),company[j]),list1.get(i));//将商家的被申请人数字段进行修改
        		}
            	
            	listfor=intCard1Service.queryByNameLike1(businessName,company[j]);
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
    	//商家名称不为空并且公司名不为空  公司名选择的不是全部项
    	else if((businessName!=null||!"".equals(businessName))&&(company.length==1)) {
    		
    		System.out.println("第四个if");
    		
       		List<String> list1=intCard1Service.queryAllBusinessNameByLike(businessName,company[0]);//通过名称模糊查询出所有的商家名称，将所有的商家名称存入一个集合中
        	for (int i = 0; i < list1.size(); i++) {
        		intCard1Service.upaApplicationNumber(intCreditCardFootprintService.queryCount(list1.get(i),company[0]),list1.get(i));//将商家的被申请人数字段进行修改
    		}
        	int totalCount=intCard1Service.pageCountByLike(businessName,company[0]);//该方法是模糊查询的贷款商家总数量
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
        	
        	listto=intCard1Service.queryByNameLike(businessName,company[0],pageUtil.getPage(),pageUtil.getPageSize());
        	pageUtil=new PageUtil(page,10,totalCount);
        	for (int i = 0; i < listto.size(); i++) {
    			System.out.println(listto.get(i).getBusinessname()+"***"+listto.get(i).getApplicationnumber());
    		}
    		
        }
    	HashMap<String, Object> map=new HashMap<>();
    	map.put("listLoanBusinByLike",listto);
    	map.put("pageutil",pageUtil);
		return map;
    }
	//后台管理---根据主键id删除商家  假删除,只修改假删除状态
    @Transactional
    @ResponseBody
    @RequestMapping("/falsedeleteByPrimaryKey")
    public Integer falsedeleteByPrimaryKey(Integer id){
    	int num=intCard1Service.upaFalseDel(id);
    	return num;
    }
    //后台管理---通过主键id查询出贷款商家信息
    @ResponseBody
    @RequestMapping("/selectByPrimaryKey")
    public CreditCard1 selectByPrimaryKey(Integer id){
    	CreditCard1 loansBusinesses=intCard1Service.selectByPrimaryKey(id);
    	return loansBusinesses;
    }
    //后台管理---通过传过来的贷款商家对象，对当前对象进行修改保存
    @Transactional
    @ResponseBody
    @RequestMapping("/upaBaocunByPrimaryKey")
    public Map<String, Object> upaBaocunByPrimaryKey(CreditCard1 loansBusinesses,MultipartFile file)throws Exception{
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
//					path = "loans_businesses/" + /* System.getProperty("file.separator")+ */trueFileName;
//					OssUtil ossUtil = new OssUtil();
//					String ossPath = ossUtil.uploadFile(iStream, path);
//					if(ossPath.substring(0, 5).equals("https")) {
//						System.out.println("路径为："+ossPath);
//						loansBusinesses.setTrademark(ossPath);
//						map.put("msg", "图片上传成功");
//					}
//					
//					System.out.println("存放图片文件的路径:" + ossPath);
					
					path = "D://nginx-1.14.2/html/dist/image/loans_businesses/" + /* System.getProperty("file.separator")+ */trueFileName;
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
						loansBusinesses.setTrademark("http://tg.mis8888.com/image/loans_businesses/"+trueFileName);
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
			Integer id = loansBusinesses.getId();
			String trademark = intCard1Service.getTrademark(id); //通过传过来的贷款商家名字，查询商标的URL
			loansBusinesses.setTrademark(trademark);
		}	
    	BigDecimal limitsmall=loansBusinesses.getLoanlimitsmall();//得到输入框的借款额度（小）
    	BigDecimal limitbig=loansBusinesses.getLoanlimitbig();//得到输入框的借款额度（大）
    	String limit=limitsmall+"~"+limitbig;//将两个额度拼接成一个字符串，赋给loansBusinesses的loanlimit的字段 	
    	loansBusinesses.setLoanlimit(limit);		
    	int number = intCard1Service.updateLoansBusinesses(loansBusinesses);
    	if(number==1) {
    		map.put("msg2", "数据修改成功");
    	}else {
    		map.put("msg2", "数据修改失败");
		}
    	return map;
    }
    
  	//后台管理---修改贷款商家状态
    @Transactional
    @ResponseBody
    @RequestMapping("upaState")
	public int upaState(String state,Integer id) {
		int num=0;
		if(state.equals("1")) {
			num=intCard1Service.upaStateOpen(id);
		}else {
			num=intCard1Service.upaStateClose(id);
		}
		return num;
	}
    
    //后台管理 ----贷款商家申请统计功能
    @ResponseBody
    @RequestMapping("/queryTime")
	public List<JiaFangTongji> queryTime(String businessName,String LikeTime1,String LikeTime2) throws ParseException {
    	String LikeTime1add=LikeTime1+" "+"00:00:00";
    	String LikeTime2add=LikeTime2+" "+"24:00:00";
    	List<String> daysList=DateListUtil.getDays(LikeTime1,LikeTime2);//获取传进来时间段里的所有日期集合
	    String timeStart=Timestamps.dateToStamp1(LikeTime1add);//将开始时间转换为时间戳
	    String timeEnd=Timestamps.dateToStamp1(LikeTime2add);//将结束时间转换为时间戳
	    System.out.println("LikeTime1add:"+LikeTime1add+"LikeTime2add:"+LikeTime2add);

    	List<String> list=intCard1Service.queryTime(businessName,timeStart,timeEnd);//查询出  时间段里  当前商家所有的足迹时间
    	List<String> list1=new ArrayList<>();//将时间戳类型的时间转换为data类型  存入list1集合
    	for (int i = 0; i < list.size(); i++) {
    		list1.add(Timestamps.stampToDate1(list.get(i)));
		}
    	
        HashSet h = new HashSet(list1);   
        list1.clear();   
        list1.addAll(h);   
    	
    	for (int i = 0; i < list1.size(); i++) {
    		System.out.println("输出data类型的时间："+list1.get(i));//list1里面存的是传进来这个时间段里有的日期
		}
    	
       	List<String> list2=DateListUtil.getDiffrent2(daysList, list1);//list2里面存的是传进来这个时间段里没有的日期，要将数量设为0
    	
    	List<JiaFangTongji> listjia=new ArrayList<>();
    	JiaFangTongji jia=null;
    	
    	for (int i = 0; i < list2.size(); i++) {
    		jia=new JiaFangTongji();
    		jia.setDate(list2.get(i));
      	    jia.setAmount(0);
      	    listjia.add(jia);
		}
    	
    	for (int i = 0; i <list1.size(); i++) {
    		jia=new JiaFangTongji();
    		String startDay1=list1.get(i)+" "+"00:00:00";
    		String endDay1=list1.get(i)+" "+"24:00:00";
    	    
    	    String startDay=Timestamps.dateToStamp1(startDay1);
    	    String endDay=Timestamps.dateToStamp1(endDay1);
    	    
    	    int count=intCard1Service.queryAmount(businessName, startDay, endDay);//查询出当前商家某一天的用户数量
    	    jia.setDate(list1.get(i));
    	    jia.setAmount(count);
    	    listjia.add(jia);
		}
    	DateListUtil.ListSort(listjia);//将集合按照日期进行排序
    	for (int i = 0; i < listjia.size(); i++) {
			System.out.println("date:::"+listjia.get(i).getDate()+"count:::"+listjia.get(i).getAmount());
		}
    	return listjia;
    }
	//后台管理---根据id  修改商家的排序字段
    @ResponseBody
    @RequestMapping("upaSortByLoanId")
  	public int upaSortByLoanId(Integer sort,Integer id){
    	int num=intCard1Service.upaSortByLoanId(sort, id);
    	return num;
    }
    
}
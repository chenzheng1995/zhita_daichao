package com.zhita.controller.registe;

import java.io.InputStream;
import java.math.BigDecimal;
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

import com.zhita.model.manage.LoanClassification;
import com.zhita.model.manage.LoansBusinessesCopy;
import com.zhita.service.commodityfootprint.CommodityFootprintService;
import com.zhita.service.registe.IntRegisteCopyService;
import com.zhita.service.type.IntTypeService;
import com.zhita.util.ListPageUtil;
import com.zhita.util.OssUtil;
import com.zhita.util.PageUtil;

@Controller
@RequestMapping("/registeCopy")
public class RegisteControllerCopy {
	@Autowired
	private IntRegisteCopyService intRegisteCopyService;
	@Autowired
	private IntTypeService intTypeService;
	@Autowired
	private CommodityFootprintService commodityFootprintService;
	
	//后台管理---查询贷款商家部分字段信息，含分页
    @ResponseBody
    @RequestMapping("/queryAllAdminCopy")
    public Map<String,Object> queryAll(Integer page,String string){
		string = string.replaceAll("\"", "").replace("[","").replace("]","");
		String[] company=string.split(",");
		PageUtil pageUtil=null;
		List<LoansBusinessesCopy> list=new ArrayList<>();
		List<LoansBusinessesCopy> listto=new ArrayList<>();
    	if(company.length==1) {
    		
    		System.out.println("company.length==1");
    		
    		List<String> list1=intRegisteCopyService.queryAllBusinessNameCopy(company[0]);//查询出所有贷款商家的商家名称，存入list集合
        	for (int i = 0; i < list1.size(); i++) {
        		intRegisteCopyService.upaApplicationNumberCopy(commodityFootprintService.queryCount(list1.get(i),company[0]),list1.get(i));//将商家的被申请人数字段进行修改
    		}
        	int totalCount=intRegisteCopyService.pageCountCopy(company[0]);//该方法是查询贷款商家总条数
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
        	
        	listto=intRegisteCopyService.queryAllAdmainCopy(company[0],pageUtil.getPage(),pageUtil.getPageSize());
        	pageUtil=new PageUtil(page,10,totalCount);
        	for (int i = 0; i < list.size(); i++) {
    			System.out.println(list.get(i).getBusinessname()+"***"+list.get(i).getApplicationnumber());
    		}
    	}
    	else if(company.length>1){
    		
    		System.out.println("company.length>1");
    		
    		List<LoansBusinessesCopy> listfor=null;
    		for (int j = 0; j < company.length; j++) {
    			List<String> list1=intRegisteCopyService.queryAllBusinessNameCopy(company[j]);//查询出所有贷款商家的商家名称，存入list集合
            	for (int i = 0; i < list1.size(); i++) {
            		intRegisteCopyService.upaApplicationNumberCopy(commodityFootprintService.queryCount(list1.get(i),company[j]),list1.get(i));//将商家的被申请人数字段进行修改
        		}
            	
            	listfor=intRegisteCopyService.queryAllAdmain1Copy(company[j]);
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
    	map.put("listLoansBusinCopy",listto);
    	map.put("pageutil", pageUtil);
    	map.put("company", company);
    	return map;
    }
    
	//后台管理---查询贷款分类的所有分类名称
    @ResponseBody
    @RequestMapping("/selAllNameCopy")
    public List<LoanClassification> selAllName(String string){
    	string = string.replaceAll("\"", "").replace("[","").replace("]","");
		String[] company=string.split(",");
		List<LoanClassification> loanlist=null;
		List<LoanClassification> loanlistto=new ArrayList<>();
		for (int i = 0; i < company.length; i++) {
			loanlist=intTypeService.queryAllLoanCla(company[i]);//添加贷款商家信息时，先查询出贷款分类的所有类型
			loanlistto.addAll(loanlist);
		}
    	return loanlistto;
    }
    
	//后台管理---添加贷款商家信息
    @Transactional
    @ResponseBody
    @RequestMapping("/insertAllAdminCopy")
    public Map<String, Object> insertAll(LoansBusinessesCopy loansBusinesses,MultipartFile file) throws Exception{
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
					path = "loans_businesses/" + /* System.getProperty("file.separator")+ */trueFileName;
					OssUtil ossUtil = new OssUtil();
					String ossPath = ossUtil.uploadFile(iStream, path);
					if(ossPath.substring(0, 5).equals("https")) {
						System.out.println("路径为："+ossPath);
						loansBusinesses.setTrademark(ossPath);
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

    	BigDecimal limitsmall=loansBusinesses.getLoanlimitsmall();//得到输入框的借款额度（小）
    	BigDecimal limitbig=loansBusinesses.getLoanlimitbig();//得到输入框的借款额度（大）
    	String limit=limitsmall+"~"+limitbig;//将两个额度拼接成一个字符串，赋给loansBusinesses的loanlimit的字段 	
    	loansBusinesses.setLoanlimit(limit);
		String num=1+(((int)(Math.random()*8998)+1000+1)+"");
		int applications = Integer.parseInt(num);   	
    	loansBusinesses.setApplications(applications);
    	intRegisteCopyService.insertCopy(loansBusinesses);
    	
    	return map;

    }
    
	//后台管理---通过商家名称模糊查询，并且有分页功能
    @ResponseBody
    @RequestMapping("/queryByNameLikeCopy")
    public Map<String,Object> queryByNameLike(String businessName,Integer page,String string){
    	PageUtil pageUtil=null;
    	List<LoansBusinessesCopy> list=new ArrayList<>();
    	List<LoansBusinessesCopy> listto=new ArrayList<>();
		string = string.replaceAll("\"", "").replace("[","").replace("]","");
		String [] company= string.split(",");
    	//商家名称为空并且公司名不为空    公司名选择的是 全部项
    	if((businessName==null||"".equals(businessName))&&(company.length>1)){
    		
    		System.out.println("第一个if");
    		
    		List<LoansBusinessesCopy> listfor=null;
    		for (int j = 0; j < company.length; j++) {
    			List<String> list1=intRegisteCopyService.queryAllBusinessNameCopy(company[j]);//查询出所有贷款商家的商家名称，存入list集合
            	for (int i = 0; i < list1.size(); i++) {
            		intRegisteCopyService.upaApplicationNumberCopy(commodityFootprintService.queryCount(list1.get(i),company[j]),list1.get(i));//将商家的被申请人数字段进行修改
        		}
            	
            	listfor=intRegisteCopyService.queryAllAdmain1Copy(company[j]);
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
    		
       		List<String> list1=intRegisteCopyService.queryAllBusinessNameCopy(company[0]);//查询出所有贷款商家的商家名称，存入list集合
        	for (int i = 0; i < list1.size(); i++) {
        		intRegisteCopyService.upaApplicationNumberCopy(commodityFootprintService.queryCount(list1.get(i),company[0]),list1.get(i));//将商家的被申请人数字段进行修改
    		}
        	int totalCount=intRegisteCopyService.pageCountCopy(company[0]);//该方法是查询贷款商家总条数
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
        	
        	listto=intRegisteCopyService.queryAllAdmainCopy(company[0],pageUtil.getPage(),pageUtil.getPageSize());
        	pageUtil=new PageUtil(page,10,totalCount);
        	for (int i = 0; i < list.size(); i++) {
    			System.out.println(list.get(i).getBusinessname()+"***"+list.get(i).getApplicationnumber());
    		}
    	
    	}
		//商家名称不为空并且公司名不为空   公司名选择的是全部项
    	else if((businessName!=null||!"".equals(businessName))&&(company.length>1)) {

    		System.out.println("第三个if");
    		
    		List<LoansBusinessesCopy> listfor=null;
    		for (int j = 0; j < company.length; j++) {
    			List<String> list1=intRegisteCopyService.queryAllBusinessNameCopy(company[j]);//查询出所有贷款商家的商家名称，存入list集合
            	for (int i = 0; i < list1.size(); i++) {
            		intRegisteCopyService.upaApplicationNumberCopy(commodityFootprintService.queryCount(list1.get(i),company[j]),list1.get(i));//将商家的被申请人数字段进行修改
        		}
            	
            	listfor=intRegisteCopyService.queryByNameLike1Copy(businessName,company[j]);
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
    		
       		List<String> list1=intRegisteCopyService.queryAllBusinessNameByLikeCopy(businessName,company[0]);//通过名称模糊查询出所有的商家名称，将所有的商家名称存入一个集合中
        	for (int i = 0; i < list1.size(); i++) {
        		intRegisteCopyService.upaApplicationNumberCopy(commodityFootprintService.queryCount(list1.get(i),company[0]),list1.get(i));//将商家的被申请人数字段进行修改
    		}
        	int totalCount=intRegisteCopyService.pageCountByLikeCopy(businessName,company[0]);//该方法是模糊查询的贷款商家总数量
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
        	
        	listto=intRegisteCopyService.queryByNameLikeCopy(businessName,company[0],pageUtil.getPage(),pageUtil.getPageSize());
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
    @RequestMapping("/falsedeleteByPrimaryKeyCopy")
    public Integer falsedeleteByPrimaryKey(Integer id){
    	int num=intRegisteCopyService.upaFalseDelCopy(id);
    	return num;
    }
    //后台管理---通过主键id查询出贷款商家信息
    @ResponseBody
    @RequestMapping("/selectByPrimaryKeyCopy")
    public LoansBusinessesCopy selectByPrimaryKey(Integer id){
    	LoansBusinessesCopy loansBusinessesCopy=intRegisteCopyService.selectByPrimaryKeyCopy(id);
    	return loansBusinessesCopy;
    }
    //后台管理---通过传过来的贷款商家对象，对当前对象进行修改保存
    @Transactional
    @ResponseBody
    @RequestMapping("/upaBaocunByPrimaryKeyCopy")
    public Map<String, Object> upaBaocunByPrimaryKey(LoansBusinessesCopy loansBusinessesCopy,MultipartFile file)throws Exception{
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
					path = "loans_businesses/" + /* System.getProperty("file.separator")+ */trueFileName;
					OssUtil ossUtil = new OssUtil();
					String ossPath = ossUtil.uploadFile(iStream, path);
					if(ossPath.substring(0, 5).equals("https")) {
						System.out.println("路径为："+ossPath);
						loansBusinessesCopy.setTrademark(ossPath);
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
			Integer id = loansBusinessesCopy.getId();
			String trademark = intRegisteCopyService.getTrademark(id); //通过商家id，查询商标的URL
			loansBusinessesCopy.setTrademark(trademark);
		}	
    	BigDecimal limitsmall=loansBusinessesCopy.getLoanlimitsmall();//得到输入框的借款额度（小）
    	BigDecimal limitbig=loansBusinessesCopy.getLoanlimitbig();//得到输入框的借款额度（大）
    	String limit=limitsmall+"~"+limitbig;//将两个额度拼接成一个字符串，赋给loansBusinesses的loanlimit的字段 	
    	loansBusinessesCopy.setLoanlimit(limit);		
    	int number = intRegisteCopyService.updateLoansBusinessesCopy(loansBusinessesCopy);
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
    @RequestMapping("upaStateCopy")
	public int upaState(String state,Integer id) {
		int num=0;
		if(state.equals("1")) {
			num=intRegisteCopyService.upaStateOpenCopy(id);
		}else {
			num=intRegisteCopyService.upaStateCloseCopy(id);
		}
		return num;
	}
	//后台管理---根据id  修改商家的排序字段
    @ResponseBody
    @RequestMapping("upaSortByLoanIdCopy")
  	public int upaSortByLoanId(Integer sort,Integer id){
    	int num=intRegisteCopyService.upaSortByLoanIdCopy(sort, id);
    	return num;
    }
}

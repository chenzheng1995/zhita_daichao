package com.zhita.controller.registe;


import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhita.model.manage.LoanClassification;
import com.zhita.model.manage.LoansBusinesses;
import com.zhita.service.commodityfootprint.CommodityFootprintService;
import com.zhita.service.registe.IntRegisteService;
import com.zhita.service.type.IntTypeService;
import com.zhita.util.OssUtil;
import com.zhita.util.PageUtil;

@Controller
@RequestMapping("/registe")
public class RegisteController {
	@Resource(name = "registeServiceImp")
	private IntRegisteService intRegisteService;
	@Resource(name = "typeServiceImp")
	private IntTypeService intTypeService;
	@Autowired
	private CommodityFootprintService commodityFootprintService;

	public IntRegisteService getIntRegisteService() {
		return intRegisteService;
	}

	public void setIntRegisteService(IntRegisteService intRegisteService) {
		this.intRegisteService = intRegisteService;
	}

	public IntTypeService getIntTypeService() {
		return intTypeService;
	}

	public void setIntTypeService(IntTypeService intTypeService) {
		this.intTypeService = intTypeService;
	}

	//后台管理---查询贷款商家部分字段信息，含分页
    @ResponseBody
    @RequestMapping("/queryAllAdmin")
    public Map<String,Object> queryAll(HttpServletRequest request,Integer page){
    	List<String> list1=intRegisteService.queryAllBusinessName();//查询出所有贷款商家的商家名称，存入list集合
    	for (int i = 0; i < list1.size(); i++) {
    		intRegisteService.upaApplicationNumber(commodityFootprintService.queryCount(list1.get(i)), list1.get(i));//将商家的被申请人数字段进行修改
		}
    	int totalCount=intRegisteService.pageCount();//该方法是查询贷款商家总条数
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
    	
    	System.out.println(pageUtil.getPage()+"------"+pageUtil.getPageSize()+"-----"+pageUtil.getTotalCount()+"----"+pageUtil.getTotalPageCount());
    	
    	List<LoansBusinesses> list=intRegisteService.queryAllAdmain(pageUtil.getPage(),pageUtil.getPageSize());
    	for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getBusinessname()+"***"+list.get(i).getApplicationnumber());
		}
    	
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listLoansBusin",list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
    
	//后台管理---查询贷款分类的所有分类名称
    @ResponseBody
    @RequestMapping("/selAllName")
    public List<LoanClassification> selAllName(){
    	List<LoanClassification> loanlist=intTypeService.queryAllLoanCla();//添加贷款商家信息时，先查询出贷款分类的所有类型
    	return loanlist;
    }
    
	//后台管理---添加贷款商家信息
    @ResponseBody
    @RequestMapping("/insertAllAdmin")
    public Map<String, Object> insertAll(LoansBusinesses loansBusinesses,MultipartFile file) throws Exception{
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
    	List<LoanClassification> loanlist=intTypeService.queryAllLoanCla();//添加贷款商家信息时，先查询出贷款分类的所有类型,用于下拉框
    	map.put("loanlist", loanlist);

    	BigDecimal limitsmall=loansBusinesses.getLoanlimitsmall();//得到输入框的借款额度（小）
    	BigDecimal limitbig=loansBusinesses.getLoanlimitbig();//得到输入框的借款额度（大）
    	String limit=limitsmall+"~"+limitbig;//将两个额度拼接成一个字符串，赋给loansBusinesses的loanlimit的字段 	
    	loansBusinesses.setLoanlimit(limit);

    	intRegisteService.insert(loansBusinesses);
    	
    	return map;

    }
    
	//后台管理---通过商家名称模糊查询，并且有分页功能
    @ResponseBody
    @RequestMapping("/queryByNameLike")
    public Map<String,Object> queryByNameLike(String businessName,Integer page){
    	List<LoansBusinesses> list=null;
    	PageUtil pageUtil=null;
    	if(businessName==null||"".equals(businessName)) {
    	   	List<String> list1=intRegisteService.queryAllBusinessName();//查询出所有贷款商家的商家名称，存入list集合
        	for (int i = 0; i < list1.size(); i++) {
        		intRegisteService.upaApplicationNumber(commodityFootprintService.queryCount(list1.get(i)), list1.get(i));//将商家的被申请人数字段进行修改
    		}
        	
        	int totalCount=intRegisteService.pageCount();//该方法是查询贷款商家总条数
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
        	list=intRegisteService.queryAllAdmain(pageUtil.getPage(),pageUtil.getPageSize());
    	}else {
    		List<String> list1=intRegisteService.queryAllBusinessNameByLike(businessName);//通过名称模糊查询出所有的商家名称，将所有的商家名称存入一个集合中
        	for (int i = 0; i < list1.size(); i++) {
        		intRegisteService.upaApplicationNumber(commodityFootprintService.queryCount(list1.get(i)), list1.get(i));//将商家的被申请人数字段进行修改
    		}
    		
          	int totalCount=intRegisteService.pageCountByLike(businessName);//该方法是模糊查询的贷款商家总数量
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
        	list=intRegisteService.queryByNameLike(businessName,pageUtil.getPage(),pageUtil.getPageSize());
    	}
    	HashMap<String, Object> map=new HashMap<>();
    	map.put("listLoanBusinByLike",list);
    	map.put("pageutil",pageUtil);
		return map;
    }
	//后台管理---根据主键id删除商家  假删除,只修改假删除状态
    @ResponseBody
    @RequestMapping("/falsedeleteByPrimaryKey")
    public Integer falsedeleteByPrimaryKey(Integer id){
    	int num=intRegisteService.upaFalseDel(id);
    	return num;
    }
    //后台管理---通过主键id查询出贷款商家信息
    @ResponseBody
    @RequestMapping("/selectByPrimaryKey")
    public LoansBusinesses selectByPrimaryKey(Integer id){
    	LoansBusinesses loansBusinesses=intRegisteService.selectByPrimaryKey(id);
    	return loansBusinesses;
    }
    //后台管理---通过传过来的贷款商家对象，对当前对象进行修改保存
    @ResponseBody
    @RequestMapping("/upaBaocunByPrimaryKey")
    public Map<String, Object> upaBaocunByPrimaryKey(LoansBusinesses loansBusinesses,MultipartFile file)throws Exception{
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
			String businessname = loansBusinesses.getBusinessname();
			String trademark = intRegisteService.getTrademark(businessname); //通过传过来的贷款商家名字，查询商标的URL
			loansBusinesses.setTrademark(trademark);
		}	
    	BigDecimal limitsmall=loansBusinesses.getLoanlimitsmall();//得到输入框的借款额度（小）
    	BigDecimal limitbig=loansBusinesses.getLoanlimitbig();//得到输入框的借款额度（大）
    	String limit=limitsmall+"~"+limitbig;//将两个额度拼接成一个字符串，赋给loansBusinesses的loanlimit的字段 	
    	loansBusinesses.setLoanlimit(limit);		
    	int number = intRegisteService.updateLoansBusinesses(loansBusinesses);
    	if(number==1) {
    		map.put("msg2", "数据修改成功");
    	}else {
    		map.put("msg2", "数据修改失败");
		}
    	return map;
    }
    
  	//后台管理---修改贷款商家状态
    @ResponseBody
    @RequestMapping("upaState")
	public int upaState(String state,Integer id) {
		int num=0;
		if(state.equals("1")) {
			num=intRegisteService.upaStateOpen(id);
		}else {
			num=intRegisteService.upaStateClose(id);
		}
		return num;
	}
    
	// 上传贷款商家的商标
	@ResponseBody
	@RequestMapping("/uploadtrademark")
	public Map<String, Object> uploadTrademark(MultipartFile file) throws Exception {
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
//							intRegisteService.insertPath(ossPath);
							map.put("msg", "图片上传成功");
							return map;
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

		return map;

	}
}
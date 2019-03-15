package com.zhita.controller.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.ButtonFootprint;
import com.zhita.model.manage.JiaFangTongji;
import com.zhita.model.manage.Source;
import com.zhita.model.manage.User;
import com.zhita.service.merchant.IntMerchantService;
import com.zhita.service.registe.IntRegisteService;
import com.zhita.service.user.UserService;
import com.zhita.util.DateListUtil;
import com.zhita.util.ListPageUtil;
import com.zhita.util.PageUtil;
import com.zhita.util.Timestamps;
import com.zhita.util.TuoMinUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private IntMerchantService IntMerchantService;
	@Autowired
	private IntRegisteService intRegisteService;
	//后台管理---查询出用户表所有信息，含分页
    @ResponseBody
    @RequestMapping("/queryAllUser")
    public Map<String,Object> queryAllUser(Integer page,String string){
		string = string.replaceAll("\"", "").replace("[","").replace("]","");
		String [] company= string.split(",");
   	    List<Source> list1=new ArrayList<>();//存渠道的集合
		PageUtil pageUtil=null;
		List<User> list=new ArrayList<>();
		List<User> listto=new ArrayList<>();
		
    	Timestamps times=new Timestamps();//创建时间戳实体类对象
		long todayZeroTimestamps = times.getTodayZeroTimestamps(); //今天0点的时间戳
		long tomorrowZeroTimestamps = todayZeroTimestamps+86400000; //明天0点的时间戳
		
    	if(company.length==1){
			System.out.println("company.length==1");
			
	    	list1=IntMerchantService.queryAll(company[0]);//查询出所有的渠道信息，将渠道名称渲染到下拉框中
	    	List<User> list2=userService.queryAllPhone(company[0]);//查询出所有用户的手机号
	    	for (int i = 0; i < list2.size(); i++) {
				//System.out.println("userid:"+list.get(i).getId()+"dayfen:"+userService.queryAmountByUserId(list.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps)));
				//将用户的当日分发系数字段进行修改
				userService.upaDayFen(userService.queryAmountByUserId(list2.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),company[0]), list2.get(i).getPhone(),company[0]);
			}
	    	
	       	int totalCount=userService.pageCount(company[0]);//该方法是查询出用户表总数量
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
	    	listto=userService.queryAllUser1(company[0]);
	    	
	    	for (int i = 0; i < listto.size(); i++) {
	    		listto.get(i).setRegistrationtime(Timestamps.stampToDate(listto.get(i).getRegistrationtime()));
	    		listto.get(i).setLoginTime(Timestamps.stampToDate(listto.get(i).getLoginTime()));
			}
	    	pageUtil=new PageUtil(page,10,totalCount);
    	}
    	else if(company.length>1){
    		
    		System.out.println("company.length>1");
    		
    		List<Source> list1for=null;
    		List<User> listfor=null;
    		for (int j = 0; j < company.length;j++) {
    	    	list1for=IntMerchantService.queryAll(company[j]);//查询出所有的渠道信息，将渠道名称渲染到下拉框中
    	    	list1.addAll(list1for);
    	    	List<User> list2=userService.queryAllPhone(company[j]);//查询出所有用户的手机号
    	    	for (int i = 0; i < list2.size(); i++) {
    				//System.out.println("userid:"+list.get(i).getId()+"dayfen:"+userService.queryAmountByUserId(list.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps)));
    				//将用户的当日分发系数字段进行修改
    				userService.upaDayFen(userService.queryAmountByUserId(list2.get(i).getId(),String.valueOf(todayZeroTimestamps),String.valueOf(tomorrowZeroTimestamps),company[j]), list2.get(i).getPhone(),company[j]);
    			}
    	    	listfor=userService.queryAllUser1(company[j]);
            	list.addAll(listfor);
			}
	    	for (int i = 0; i < list.size(); i++) {
	    		list.get(i).setRegistrationtime(Timestamps.stampToDate(list.get(i).getRegistrationtime()));
				list.get(i).setLoginTime(Timestamps.stampToDate(list.get(i).getLoginTime()));
			}
	    	
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i)+"整合后的集合");
			}
			
			System.out.println("传进工具类的page"+page);
			
			ListPageUtil listPageUtil=new ListPageUtil(list,page,10);
			listto.addAll(listPageUtil.getData());
			
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
	    	
    	}
    	TuoMinUtil tuoMinUtil=new TuoMinUtil();//将用户模块的手机号进行脱名
    	for (int i = 0; i < listto.size(); i++) {
    		String tuomingphone=tuoMinUtil.mobileEncrypt(listto.get(i).getPhone());
    		listto.get(i).setPhone(tuomingphone);
			
		}
    	HashMap<String, Object> map=new HashMap<>();
    	map.put("listSource", list1);
    	map.put("listUser",listto);
    	map.put("pageutil",pageUtil);
    	map.put("company", company);
		return map;
    }
	//后台管理---通过传过来的值，进行多种情况的模糊查询，含分页
    @ResponseBody
    @RequestMapping("/queryByLike")
    public Map<String,Object> queryByLike(String phone,String sourceName,String registrationTimeStart,String registrationTimeEnd,String company,Integer page) throws ParseException{
    	String [] sourceNamein=null;
    	if(sourceName!=null&&!"".equals(sourceName)){
    		sourceName = sourceName.replaceAll("\"", "").replace("[","").replace("]","");
    		sourceNamein= sourceName.split(",");
    	}
    	String [] companyin=null;
		if(company!=null&&!"".equals(company)){
			company = company.replaceAll("\"", "").replace("[","").replace("]","");
			companyin= company.split(",");
		}
		 String timeStart=Timestamps.dateToStamp(registrationTimeStart);//将开始时间转换为时间戳
		 String timeEnd=Timestamps.dateToStamp(registrationTimeEnd);//将结束时间转换为时间戳
		
    	 Map<String,Object> map=userService.ByLikeQuery(phone,sourceNamein,timeStart,timeEnd,companyin,page);
    	 return map;
    }
	//后台管理---根据用户id查询出按钮足迹  商品足迹和贷款分类足迹    并按足迹时间倒排序，含分页（申请记录）
    @ResponseBody
    @RequestMapping("/queryAllButton")
	public Map<String,Object> queryAllButton(Integer userId,Integer page){
       	int totalCount=userService.pageCountThreeFootprint(userId);//该方法是根据用户id查询出按钮足迹  商品足迹和贷款分类足迹的总数量
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
    	List<ButtonFootprint> list=userService.queryAllButton(userId,pageUtil.getPage(),pageUtil.getPageSize());
    	pageUtil=new PageUtil(page,10,totalCount);
    	for (int i = 0; i < list.size(); i++) {
			list.get(i).setFootprinttime(Timestamps.stampToDate(list.get(i).getFootprinttime()));//把时间戳转换成时间格式(年 月 日 时 分 秒)
		}
    
    	HashMap<String, Object> map=new HashMap<>();
    	map.put("listFootprint",list);
    	map.put("pageutil",pageUtil);
		return map;
	}
    //后台管理---日分发系数
    @ResponseBody
    @RequestMapping("/queryDayFenByTime")
	public List<JiaFangTongji> queryDayFenByTime(Integer userId,String LikeTime1,String LikeTime2) throws ParseException {
    	List<String> daysList=DateListUtil.getDays(LikeTime1,LikeTime2);//获取传进来时间段里的所有日期集合
	    String timeStart=Timestamps.dateToStamp(LikeTime1);//将开始时间转换为时间戳
	
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	    Date dt = sdf.parse(LikeTime2);  
	    Calendar rightNow = Calendar.getInstance();  
	    rightNow.setTime(dt);  
	    rightNow.add(rightNow.DATE, 1);
	    Date dt1 = rightNow.getTime();  
	    String timeend = sdf.format(dt1);  
	    
	    String timeEnd=Timestamps.dateToStamp(timeend);//将结束时间转换为时间戳
	    System.out.println("LikeTime1:"+LikeTime1+"LikeTime2:"+timeend);

    	List<String> list=userService.queryDayFenByTime(userId,timeStart,timeEnd);//查询出  时间段里  当前用户所有的足迹时间
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
    	    Date dti = sdf.parse(list1.get(i));  
    	    Calendar calendar = Calendar.getInstance();  
    	    calendar.setTime(dti);  
    	    calendar.add(calendar.DATE, 1);
    	    Date dt1i = calendar.getTime();  
    	    String list1i = sdf.format(dt1i);  
    	    
    	    String startDay=Timestamps.dateToStamp(list1.get(i));
    	    String endDay=Timestamps.dateToStamp(list1i);
    	    
    	    int count=userService.queryAmount(userId, startDay, endDay);//查询出当前商家某一天的用户数量
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
}

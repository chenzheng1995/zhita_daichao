package com.zhita.controller.vest_bag_version;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.Source;
import com.zhita.model.manage.VestBag;
import com.zhita.model.manage.VestBagVersion;
import com.zhita.service.vestbugversion.VestBugVersionService;
import com.zhita.util.ListPageUtil;
import com.zhita.util.PageUtil;

@Controller
@RequestMapping("/vestbagversion")
public class VestBagVesionController {
	@Autowired
	private VestBugVersionService vestBugVersionService;
	
	//后台管理  ------查询vest_bag_version表所有的信息
	@ResponseBody
	@RequestMapping("/queryAllVersion")
    public Map<String,Object> queryAllVersion(Integer page,String string){
		string = string.replaceAll("\"", "").replace("[","").replace("]","");
		String [] company= string.split(",");
		PageUtil pageUtil=null;
		List<VestBagVersion> list=new ArrayList<>();
		List<VestBagVersion> listto=new ArrayList<>();
		list=vestBugVersionService.queryAllVersion(company);
		
		if(list!=null&&list.size()!=0){
			ListPageUtil listPageUtil=new ListPageUtil(list,page,10);
			listto.addAll(listPageUtil.getData());
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
		}
		
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listadv",listto);
    	map.put("pageutil", pageUtil);
    	map.put("company", company);
    	return map;
    }
	//后台管理----通过id查询vest_bag_version对象的信息
    @ResponseBody
    @RequestMapping("/selectByPrimaryKey")
    public VestBagVersion selectByPrimaryKey(Integer id){
    	VestBagVersion vestBagVersion=vestBugVersionService.selectByPrimaryKey(id);
    	return vestBagVersion;
    }
    
  //后台管理-----保存修改后的vest_bag_version表信息
    @Transactional
    @ResponseBody
    @RequestMapping("/updateSouceDadSon")
    public int upaVersion(VestBagVersion vestBagVersion){
    	int num=vestBugVersionService.upaVersion(vestBagVersion);
    	return num;
    }
  //后台管理---先查询出vest_bag表所有信息
    @ResponseBody
    @RequestMapping("/queryAll")
    public List<VestBag> queryAll(String string){
    	string = string.replaceAll("\"", "").replace("[","").replace("]","");
		String [] company= string.split(",");
   	    List<VestBag> list1=new ArrayList<>();//存渠道的集合
   	    
	   	 if(company.length==1){
	   		list1=vestBugVersionService.queryAll(company[0]);
	   	 }
	   	 else if(company.length>1){
	   		List<VestBag> list1for=null;
	   		for (int i = 0; i < company.length;i++) {
	   			list1for=vestBugVersionService.queryAll(company[i]);
    	    	list1.addAll(list1for);
	   		}
	   	}
	   	 return list1;
    }
    
    //后台管理---添加vest_bag_version表信息
    @ResponseBody
    @RequestMapping("insert")
    public int insert(VestBagVersion record){
    	int num=vestBugVersionService.insert(record);
    	return num;
    }

}

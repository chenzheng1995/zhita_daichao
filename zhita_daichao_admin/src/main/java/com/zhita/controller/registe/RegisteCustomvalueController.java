package com.zhita.controller.registe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.LoanCustomvalue;
import com.zhita.service.registe.IntRegisteCustomvalueService;
import com.zhita.util.PageUtil;

@Controller
@RequestMapping("/registecustomvalue")
public class RegisteCustomvalueController {
	@Autowired
	private IntRegisteCustomvalueService intRegisteCustomvalueService;
	
	//后台管理---甲方商家——自定义值表查询所有
	@ResponseBody
	@RequestMapping("queryAll")
    public Map<String,Object> queryAll(Integer page){
		int totalCount=intRegisteCustomvalueService.queryAllCount();
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
    	List<LoanCustomvalue> list=intRegisteCustomvalueService.queryAllByPage(pageUtil.getPage(),pageUtil.getPageSize());
    	pageUtil=new PageUtil(page,10,totalCount);
    	
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listLoanCustomvalue",list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
	
	 //后台管理---甲方商家——自定义值表   通过id查询对象
	@ResponseBody
	@RequestMapping("selectByPrimaryKey")
    public LoanCustomvalue selectByPrimaryKey(Integer id){
    	LoanCustomvalue loanCustomvalue=intRegisteCustomvalueService.selectByPrimaryKey(id);
    	return loanCustomvalue;
    }
    
    //后台管理---甲方商家——自定义值表  修改保存
	@ResponseBody
	@RequestMapping("updateByPrimaryKey")
    public int updateByPrimaryKey(LoanCustomvalue record){
    	int sum=intRegisteCustomvalueService.updateByPrimaryKey(record);
    	return sum;
    }
}

package com.zhita.controller.source_dad_son;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.SourceDadSon;
import com.zhita.service.sourcedadson.SourceDadSonService;
import com.zhita.util.ListPageUtil;
import com.zhita.util.PageUtil;

@Controller
@RequestMapping("/soucedadson")
public class SouceDadSonController {
	@Autowired
	private SourceDadSonService SourceDadSonService;
	
	  //后台管理---查询source_dad_son表所有数据
		@ResponseBody
		@RequestMapping("/queryAll")
	    public Map<String,Object> queryAll(Integer page,String string){
			string = string.replaceAll("\"", "").replace("[","").replace("]","");
			String [] company= string.split(",");
			PageUtil pageUtil=null;
			List<SourceDadSon> list=new ArrayList<>();
			List<SourceDadSon> listto=new ArrayList<>();
			list=SourceDadSonService.queryAll(company);
			
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
		
	    
	  	//后台管理---修改source_dad_son表的tableType字段
	    @Transactional
	    @ResponseBody
	    @RequestMapping("/upatableType")
		public int upaState(String state,Integer id) {
			int num=0;
			if(state.equals("1")) {
				num=SourceDadSonService.upatableTypeStart(id);
			}else {
				num=SourceDadSonService.upatableTypeClose(id);
			}
			return num;
		}
}

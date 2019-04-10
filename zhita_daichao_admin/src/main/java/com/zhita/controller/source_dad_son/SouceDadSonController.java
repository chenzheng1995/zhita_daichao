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

import com.zhita.model.manage.Source;
import com.zhita.model.manage.SourceDadSon;
import com.zhita.service.merchant.IntMerchantService;
import com.zhita.service.sourcedadson.SourceDadSonService;
import com.zhita.util.ListPageUtil;
import com.zhita.util.PageUtil;

@Controller
@RequestMapping("/soucedadson")
public class SouceDadSonController {
	@Autowired
	private SourceDadSonService sourceDadSonService;
	@Autowired
	private IntMerchantService intMerchantService;
	
	  //后台管理---查询source_dad_son表所有数据
		@ResponseBody
		@RequestMapping("/queryAll")
	    public Map<String,Object> queryAll(Integer page,String string){
			string = string.replaceAll("\"", "").replace("[","").replace("]","");
			String [] company= string.split(",");
			PageUtil pageUtil=null;
			List<SourceDadSon> list=new ArrayList<>();
			List<SourceDadSon> listto=new ArrayList<>();
			list=sourceDadSonService.queryAll(company);
			
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
				num=sourceDadSonService.upatableTypeStart(id);
			}else {
				num=sourceDadSonService.upatableTypeClose(id);
			}
			return num;
		}
	    
	  //后台管理---查询渠道表所有渠道名称,往source_dad_son表新增数据时使用
	    @ResponseBody
	    @RequestMapping("/selSourceName")
	    public List<Source> selSourceName(String company){
	    	List<Source> list=sourceDadSonService.queryAllSource(company);
	    	return list;
	    }
	    //后台管理---往source_dad_son表新增数据
	    @Transactional
	    @ResponseBody
	    @RequestMapping("/insertSouceDadSon")
	    public int insertSouceDadSon(SourceDadSon sourceDadSon){
	    	int num=sourceDadSonService.insertSouceDadSon(sourceDadSon);
	    	return num;
	    }
	    //后台管理---通过主键id查询source_dad_son表数据
	    @ResponseBody
	    @RequestMapping("/selectByPrimaryKey")
	    public SourceDadSon selectByPrimaryKey(Integer id){
	    	SourceDadSon sourceDadSon=sourceDadSonService.selectByPrimaryKey(id);
	    	return sourceDadSon;
	    }
	    
	    //后台管理---修改source_dad_son表数据
	    @Transactional
	    @ResponseBody
	    @RequestMapping("/updateSouceDadSon")
	    public int updateSouceDadSon(SourceDadSon sourceDadSon){
	    	int num=sourceDadSonService.updateSouceDadSon(sourceDadSon);
	    	return num;
	    }
	    //后台管理----修改source_dad_son表假删除状态
	    @ResponseBody
	    @RequestMapping("/updateFalseDel")
	    public int updateFalseDel(Integer id){
	    	int num=sourceDadSonService.updateFalseDel(id);
	    	return num;
	    }
}

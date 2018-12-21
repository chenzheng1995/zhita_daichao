package com.zhita.controller.registe;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.LoanClassification;
import com.zhita.model.manage.LoansBusinesses;
import com.zhita.service.registe.IntRegisteService;
import com.zhita.service.type.IntTypeService;
import com.zhita.util.PageUtil;

@Controller
@RequestMapping("/registe")
public class RegisteController {
	@Resource(name="registeServiceImp")
	private IntRegisteService intRegisteService;
	@Resource(name="typeServiceImp")
	private IntTypeService intTypeService;

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
    	int totalCount=intRegisteService.pageCount();//该方法是查询贷款商家总条数
    	PageUtil pageUtil=new PageUtil(page, totalCount);
    	if(page==0) {
    		page=1;
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil=new PageUtil(pages, totalCount);
    	List<LoansBusinesses> list=intRegisteService.queryAllAdmain(pageUtil.getPage());
    	
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listLoansBusin",list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
	//后台管理---添加贷款商家信息
    @ResponseBody
    @RequestMapping("/insertAllAdmin")
    public List<LoanClassification> insertAll(LoansBusinesses loansBusinesses){
    	List<LoanClassification> loanlist=intTypeService.queryAllLoanCla();//添加贷款商家信息时，先查询出贷款分类的所有类型
    	
    	BigDecimal limitsmall=loansBusinesses.getLoanlimitsmall();//得到输入框的借款额度（小）
    	BigDecimal limitbig=loansBusinesses.getLoanlimitbig();//得到输入框的借款额度（大）
    	String limit=limitsmall+"~"+limitbig;//将两个额度拼接成一个字符串，赋给loansBusinesses的loanlimit的字段
    	loansBusinesses.setLoanlimit(limit);
    	
    	intRegisteService.insert(loansBusinesses);
    	return loanlist;
    }
	//后台管理---通过商家名称模糊查询，并且有分页功能
    @ResponseBody
    @RequestMapping("/queryByNameLike")
    public Map<String,Object> queryByNameLike(String businessName,Integer page){
       	int totalCount=intRegisteService.pageCountByLike(businessName);//该方法是模糊查询的贷款商家总数量
    	PageUtil pageUtil=new PageUtil(page, totalCount);
    	if(page==0) {
    		page=1;
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil=new PageUtil(pages, totalCount);
    	List<LoansBusinesses> list=intRegisteService.queryByNameLike(businessName,pageUtil.getPage());
    	HashMap<String, Object> map=new HashMap<>();
    	map.put("listLoanBusinByLike",list);
    	map.put("pageutil",pageUtil);
		return map;
    }
	//后台管理---根据主键id删除商家  假删除,只修改假删除状态
    @ResponseBody
    @RequestMapping("/falsedeleteByPrimaryKey")
    public Integer falsedeleteByPrimaryKey(Integer id){
    	int selnum=intRegisteService.upaFalseDel(id);
    	return selnum;
    }
    //后台管理---通过主键id查询出贷款商家信息
    @ResponseBody
    @RequestMapping("/selectByPrimaryKey")
    public LoansBusinesses selectByPrimaryKey(Integer id){
    	LoansBusinesses loansBusinesses=intRegisteService.selectByPrimaryKey(id);
    	return loansBusinesses;
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
}
package com.zhita.controller.daichao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhita.model.manage.LoansBusinesses;
import com.zhita.service.commodityfootprint.CommodityFootprintService;
import com.zhita.service.registe.IntRegisteService;
import com.zhita.service.type.IntTypeService;
import com.zhita.util.PageUtil;


@Controller
@RequestMapping(value="/registe")
public class RegisteController {
	@Resource(name="registeServiceImp")
	private IntRegisteService intRegisteService;

	public IntRegisteService getIntRegisteService() {
		return intRegisteService;
	}

	public void setIntRegisteService(IntRegisteService intRegisteService) {
		this.intRegisteService = intRegisteService;
	}
	
	@Autowired
	CommodityFootprintService cFootprintService;
	
	//小程序---查询所有贷款商家信息,含分页
    @ResponseBody
    @RequestMapping("/queryAll")
    public Map<String,Object> queryAll(Integer page){    	
    	int pageSize = 10; //每页的条数，暂时写死，后续可以让前端传
    	int totalCount=intRegisteService.pageCount();//该方法是查询贷款商家总条数
    	PageUtil pageUtil=new PageUtil(page, totalCount);
    	pageUtil.setPageSize(10);
    	if(page<1) {
    		page=1;
    	}
    	else if(page>pageUtil.getTotalPageCount()) {
    		page=pageUtil.getTotalPageCount();
    	}
    	int pages=(page-1)*pageUtil.getPageSize();
    	pageUtil=new PageUtil(pages, totalCount);

    	List<LoansBusinesses> list=intRegisteService.queryAllAdmainpro(pageUtil.getPage(),pageSize);
        for (LoansBusinesses loansBusinesses : list) {
        String businessName = loansBusinesses.getBusinessname();
        int applications = (int)cFootprintService.getApplications(businessName);//获取申请人数	  
        loansBusinesses.setApplications(applications);
        String loanlimitbig = loansBusinesses.getLoanlimitbig().setScale(0)+"";
        String loanlimitsmall = loansBusinesses.getLoanlimitsmall().setScale(0)+"";
        String loanlimit = loanlimitsmall+"~"+loanlimitbig;
        loansBusinesses.setLoanlimit(loanlimit);
		}
 	
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listLoansBusin",list);
    	map.put("pageutil", pageUtil);
    	return map;
    }
}
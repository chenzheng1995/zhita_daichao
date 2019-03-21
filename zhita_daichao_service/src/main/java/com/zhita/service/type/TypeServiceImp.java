package com.zhita.service.type;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhita.dao.manage.LoanClassificationCopyMapper;
import com.zhita.dao.manage.LoanClassificationMapper;
import com.zhita.dao.manage.LoansBusinessesCopyMapper;
import com.zhita.dao.manage.LoansBusinessesMapper;
import com.zhita.model.manage.LoanClassification;
import com.zhita.model.manage.LoanClassificationCopy;
import com.zhita.model.manage.LoansBusinesses;
import com.zhita.model.manage.LoansBusinessesCopy;

@Service(value="typeServiceImp")
public class TypeServiceImp implements IntTypeService{
	@Resource(name="loanClassificationMapper")
	private LoanClassificationMapper loanClassificationMapper;

	public LoanClassificationMapper getLoanClassificationMapper() {
		return loanClassificationMapper;
	}

	public void setLoanClassificationMapper(LoanClassificationMapper loanClassificationMapper) {
		this.loanClassificationMapper = loanClassificationMapper;
	}

	@Resource(name="loansBusinessesMapper")
    private LoansBusinessesMapper loansBusinessesMapper;

	public LoansBusinessesMapper getLoansBusinessesMapper() {
		return loansBusinessesMapper;
	}

	public void setLoansBusinessesMapper(LoansBusinessesMapper loansBusinessesMapper) {
		this.loansBusinessesMapper = loansBusinessesMapper;
	}
	
	@Autowired
	LoansBusinessesCopyMapper loansBusinessesCopyMapper;
	
	@Autowired
	LoanClassificationCopyMapper loanClassificationCopyMapper;
	
    //后台管理---查询贷款分类所有信息，含分页
	@Override
	public List<LoanClassification> queryAllPage(String company,Integer page,Integer pagesize) {
		List<LoanClassification> list=loanClassificationMapper.queryAllPage(company,page,pagesize);
		return list;
	}
	@Override
    //后台管理---查询贷款分类所有信息，不含分页
    public List<LoanClassification> queryAllNoPage(String company){
		List<LoanClassification> list=loanClassificationMapper.queryAllNoPage(company);
		return list;
    }
    //后台管理---用于获取总页数
	@Override
	public int pageCount(String company) {
		int count=loanClassificationMapper.pageCount(company);
		return count;
	}
    //后台管理---用于获取模糊查询总页数
    public int pageCountByLike(String businessClassification,String company) {
    	int count=loanClassificationMapper.pageCountByLike(businessClassification,company);
    	return count;
    }
    //小程序---通过贷款分类的名称查询出贷款商家的个数
    public int pageCountByBusinessClassification(String businessClassification,String conpany) {
    	int count=loanClassificationMapper.pageCountByBusinessClassification(businessClassification,conpany);
    	System.out.println(count+"----");
    	return count;
    }
    //后台管理---模糊查询贷款分类信息,并且有分页功能
	@Override
	public List<LoanClassification> queryByLike(String businessClassification,String company,Integer page,Integer pagesize) {
		List<LoanClassification> list=loanClassificationMapper.queryByLike(businessClassification,company,page,pagesize);
		return list;
	}
    //后台管理---模糊查询贷款分类信息,没有分页功能
    public List<LoanClassification> queryByLike1(String businessClassification,String company){
    	List<LoanClassification> list=loanClassificationMapper.queryByLike1(businessClassification,company);
		return list;
    }
    //后台管理---添加贷款分类信息
	@Override
	public int addAll(LoanClassification record) {
		int selnum=loanClassificationMapper.addAll(record);
		return selnum;
	}
    //后台管理---通过主键id查询出贷款分类信息
	@Override
	public LoanClassification selectByPrimaryKey(Integer id) {
		LoanClassification loanClassification=loanClassificationMapper.selectByPrimaryKey(id);
		return loanClassification;
	}
	
	//后台管理---  根据分类id   查询当前分类的图标
    public String queryIconById(Integer id){
    	String icon=loanClassificationMapper.queryIconById(id);
    	return icon;
    }
    //小程序---通过贷款分类的名称，查询出当前贷款分类下的所有贷款商家的信息
//	@Override
//	public List<LoansBusinesses> queryLoanbusinByLoanClass(String businessClassification,Integer page,Integer pageSize) {
//		List<LoansBusinesses> list=loanClassificationMapper.queryLoanbusinByLoanClass(businessClassification,page,pageSize);
//		return list;
//	}
	
    //小程序---通过贷款分类的名称，查询出当前贷款分类下的所有贷款商家的信息
	@Override
	public List<LoansBusinesses> queryLoanbusinByLoanClass(String businessClassification,Integer page,Integer pageSize,String company) {
		List<LoansBusinesses> list=loansBusinessesMapper.queryLoanbusinByLoanClass(businessClassification,page,pageSize,company);
		return list;
	}
	
    //后台管理---查询贷款分类所有信息，不含分页,做贷款商家添加功能时，下拉框取贷款分类的值时使用
    public List<LoanClassification> queryAllLoanCla(String company){
    	List<LoanClassification> list=loanClassificationMapper.queryAllLoanCla(company);
    	return list;
    }
    //通过传过来的贷款分类对象，对当前对象进行修改保存
    public int updateByPrimaryKey(LoanClassification record) {
    	int num=loanClassificationMapper.updateByPrimaryKey(record);
    	return num;
    }

	@Override
	public List<LoanClassificationCopy> queryLoanClass(String company) {
		List<LoanClassificationCopy> list = loanClassificationCopyMapper.queryLoanClass(company);
		return list;
	}

	@Override
	public List<LoanClassificationCopy> queryLoanClassAfter(String company) {
		List<LoanClassificationCopy> list = loanClassificationCopyMapper.queryLoanClassAfter(company);
		return list;
	}

	@Override
	public List<LoansBusinessesCopy> queryLoanbusinByLoanClass1(String businessClassification, int pages, int pageSize,String company) {
		List<LoansBusinessesCopy> list=loansBusinessesCopyMapper.queryLoanbusinByLoanClass1(businessClassification,pages,pageSize,company);
		return list;
	}

	@Override
	public int pageCountByBusinessClassification1(String businessClassification, String company) {
		int totalCount=loanClassificationCopyMapper.pageCountByBusinessClassification1(businessClassification,company);
		return totalCount;
	}



}

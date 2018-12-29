package com.zhita.service.type;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhita.dao.manage.LoanClassificationMapper;
import com.zhita.model.manage.LoanClassification;
import com.zhita.model.manage.LoansBusinesses;

@Transactional
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
    //后台管理---查询贷款分类所有信息，含分页
	@Override
	public List<LoanClassification> queryAllPage(Integer page,Integer pagesize) {
		List<LoanClassification> list=loanClassificationMapper.queryAllPage(page,pagesize);
		return list;
	}
    //后台管理---用于获取总页数
	@Override
	public int pageCount() {
		int count=loanClassificationMapper.pageCount();
		return count;
	}
    //后台管理---用于获取模糊查询总页数
    public int pageCountByLike(String businessClassification) {
    	int count=loanClassificationMapper.pageCountByLike(businessClassification);
    	return count;
    }
    //小程序---通过贷款分类的名称查询出贷款商家的个数
    public int pageCountByBusinessClassification(String businessClassification) {
    	int count=loanClassificationMapper.pageCountByBusinessClassification(businessClassification);
    	return count;
    }
    //后台管理---模糊查询贷款分类信息,并且有分页功能
	@Override
	public List<LoanClassification> queryByLike(String businessClassification,Integer page,Integer pagesize) {
		List<LoanClassification> list=loanClassificationMapper.queryByLike(businessClassification,page,pagesize);
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
    //小程序---通过贷款分类的名称，查询出当前贷款分类下的所有贷款商家的信息
	@Override
	public List<LoansBusinesses> queryLoanbusinByLoanClass(String businessClassification,Integer page,Integer pagesize) {
		List<LoansBusinesses> list=loanClassificationMapper.queryLoanbusinByLoanClass(businessClassification,page,pagesize);
		return list;
	}
    //后台管理---查询贷款分类所有信息，不含分页,做贷款商家添加功能时，下拉框取贷款分类的值时使用
    public List<LoanClassification> queryAllLoanCla(){
    	List<LoanClassification> list=loanClassificationMapper.queryAllLoanCla();
    	return list;
    }
    //通过传过来的贷款分类对象，对当前对象进行修改保存
    public int updateByPrimaryKey(LoanClassification record) {
    	int num=loanClassificationMapper.updateByPrimaryKey(record);
    	return num;
    }

}

package com.zhita.service.type;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhita.dao.manage.LoanClassificationMapper;
import com.zhita.model.manage.LoanClassification;

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
	public List<LoanClassification> queryAllPage(Integer page) {
		List<LoanClassification> list=loanClassificationMapper.queryAllPage(page);
		return list;
	}
    //后台管理---用于获取总页数
	@Override
	public int pageCount() {
		int count=loanClassificationMapper.pageCount();
		return count;
	}
    //后台管理---模糊查询贷款分类信息,并且有分页功能
	@Override
	public List<LoanClassification> queryByLike(String businessClassification,Integer page) {
		List<LoanClassification> list=loanClassificationMapper.queryByLike(businessClassification,page);
		return list;
	}
    //后台管理---添加贷款分类信息
	@Override
	public int insert(LoanClassification record) {
		int selnum=loanClassificationMapper.insert(record);
		return selnum;
	}
    //后台管理---通过主键id查询出贷款分类信息
	@Override
	public LoanClassification selectByPrimaryKey(Integer id) {
		LoanClassification loanClassification=loanClassificationMapper.selectByPrimaryKey(id);
		return loanClassification;
	}
	
}

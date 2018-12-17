package com.zhita.service.registe;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhita.dao.manage.LoansBusinessesMapper;
import com.zhita.model.manage.LoansBusinesses;
import com.zhita.util.PageUtil;
@Transactional
@Service(value ="registeServiceImp")
public class RegisteServiceImp implements IntRegisteService{
	@Resource(name="loansBusinessesMapper")
    private LoansBusinessesMapper loansBusinessesMapper;

	public LoansBusinessesMapper getLoansBusinessesMapper() {
		return loansBusinessesMapper;
	}

	public void setLoansBusinessesMapper(LoansBusinessesMapper loansBusinessesMapper) {
		this.loansBusinessesMapper = loansBusinessesMapper;
	}
	
	//查询出所有贷款商家信息
	@Cacheable(key="'mytest'", value="test")
	@Override
	public List<LoansBusinesses> queryAll() {
		List<LoansBusinesses> list=loansBusinessesMapper.queryAll();
		System.out.println("打印则没有走缓存");
		return list;
	}
	//通过商家分类查询出商家信息
	@Override
	public List<LoansBusinesses> queryByLoansClass(String businessClassification) {
		List<LoansBusinesses> list=loansBusinessesMapper.queryByLoansClass(businessClassification);
		return list;
	}
	//后台管理---查询贷款商家部分字段信息，含分页
  	public List<LoansBusinesses> queryAllAdmain(Integer page) {
		List<LoansBusinesses> list=loansBusinessesMapper.queryAllAdmain(page);
		return list;
	}
  	//后台管理---查询贷款商家总条数
  	public int pageCount() {
  		int count=loansBusinessesMapper.pageCount();
  		return count;
  	}
    //后台管理---添加贷款商家信息
    public int insert(LoansBusinesses record) {
    	int selnum=loansBusinessesMapper.insert(record);
    	return selnum;
    }
}

package com.zhita.service.registe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhita.dao.manage.LoansBusinessesMapper;
import com.zhita.model.manage.LoansBusinesses;

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
	
	//小程序---查询出所有贷款商家信息
	@Cacheable(key="'mytest'", value="test")
	@Override
	public List<LoansBusinesses> queryAll() {
		List<LoansBusinesses> list=loansBusinessesMapper.queryAll();
		System.out.println("打印则没有走缓存");
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
  	
  	//后台管理---通过模糊查询的条件查询贷款商家总条数
  	public int pageCountByLike(String businessName) {
  		int count=loansBusinessesMapper.pageCountByLike(businessName);
  		return count;
  	}
    //后台管理---添加贷款商家信息
    public int insert(LoansBusinesses record) {
    	int selnum=loansBusinessesMapper.insert(record);
    	return selnum;
    }
  	//后台管理---通过商家名称模糊查询，并且有分页功能
  	public List<LoansBusinesses> queryByNameLike(String businessName,Integer page){
  		List<LoansBusinesses> list=loansBusinessesMapper.queryByNameLike(businessName,page);
  		return list;
  	}
  	//后台管理---通过商家主键id修改假删除字段的值
	@Override
	public int upaFalseDel(Integer id) {
		int num=loansBusinessesMapper.upaFalseDel(id);
		return num;
	}
	//后台管理---通过主键id查询出贷款商家信息
	@Override
	public LoansBusinesses selectByPrimaryKey(Integer id) {
		LoansBusinesses loansBusinesses=loansBusinessesMapper.selectByPrimaryKey(id);
		return loansBusinesses;
	}
  	//后台管理---修改贷款商家状态为开启
  	public int upaStateOpen(Integer id) {
  		int num=loansBusinessesMapper.upaStateOpen(id);
  		return num;
  	}
  	
  	//后台管理---修改贷款商家状态为关闭
  	public int upaStateClose(Integer id) {
  		int num=loansBusinessesMapper.upaStateClose(id);
  		return num;
  	}
	@Override
	public void insertPath(String ossPath) {
		loansBusinessesMapper.insertPath(ossPath);		
	}

	@Override
	public Map<String, Object> getLoansBusinesses(String businessName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = loansBusinessesMapper.getLoansBusinesses(businessName); 
		return map;
	}

}

package com.zhita.service.registe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhita.dao.manage.LoansBusinessesMapper;
import com.zhita.model.manage.LoansBusinesses;

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
	
/*	//小程序---查询出所有贷款商家信息
	@Cacheable(key="'mytest'", value="test")
	@Override
	public List<LoansBusinesses> queryAll(Integer page) {
		List<LoansBusinesses> list=loansBusinessesMapper.queryAll(page);
		System.out.println("打印则没有走缓存");
		return list;
	}
*/	
	//后台管理---查询贷款商家部分字段信息，含分页
  	public List<LoansBusinesses> queryAllAdmain(String company,Integer page,Integer pagesize) {
		List<LoansBusinesses> list=loansBusinessesMapper.queryAllAdmain(company,page,pagesize);
		return list;
	}
	//后台管理---查询贷款商家部分字段信息，不含分页
  	public List<LoansBusinesses> queryAllAdmain1(String company) {
		List<LoansBusinesses> list=loansBusinessesMapper.queryAllAdmain1(company);
		return list;
	}
  	//后台管理---查询贷款商家总条数
  	public int pageCount(String company) {
  		int count=loansBusinessesMapper.pageCount(company);
  		return count;
  	}
  	
  	//后台管理---通过模糊查询的条件查询贷款商家总条数
  	public int pageCountByLike(String businessName,String company) {
  		int count=loansBusinessesMapper.pageCountByLike(businessName,company);
  		return count;
  	}
    //后台管理---添加贷款商家信息
    public int insert(LoansBusinesses record) {
    	int selnum=loansBusinessesMapper.insert(record);
    	return selnum;
    }
  	//后台管理---通过商家名称模糊查询，并且有分页功能
  	public List<LoansBusinesses> queryByNameLike(String businessName,String company,Integer page,Integer pagesize){
  		List<LoansBusinesses> list=loansBusinessesMapper.queryByNameLike(businessName,company,page,pagesize);
  		return list;
  	}
  	//后台管理---通过商家名称模糊查询，没有分页功能
  	public List<LoansBusinesses> queryByNameLike1(String businessName,String company){
  		List<LoansBusinesses> list=loansBusinessesMapper.queryByNameLike1(businessName,company);
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
	public Map<String, Object> getLoansBusinesses(String businessName,String company) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = loansBusinessesMapper.getLoansBusinesses(businessName,company); 
		return map;
	}

  	//后台管理---查询出贷款商家表所有的商家名称，将所有的商家名称存入一个集合中
  	public List<String> queryAllBusinessName(String company){
  		List<String> list=loansBusinessesMapper.queryAllBusinessName(company);
  		return list;
  	}
  	//后台管理---通过商家名称模糊查询出贷款商家表所有的商家名称，将所有的商家名称存入一个集合中
  	public List<String> queryAllBusinessNameByLike(String businessName,String company){
  		List<String> list=loansBusinessesMapper.queryAllBusinessNameByLike(businessName,company);
  		return list;
  	}
  	//后台管理---根据商家名称更新被申请次数字段
  	public int upaApplicationNumber(Integer num,String businessName) {
  		int nums=loansBusinessesMapper.upaApplicationNumber(num, businessName);
  		return nums;
  	}
    //后台管理---通过传过来的贷款商家对象，对当前对象进行修改保存
    public int updateLoansBusinesses(LoansBusinesses loans) {
    	int num=loansBusinessesMapper.updateLoansBusinesses(loans);
    	return num;
    }
  	//小程序---查询贷款商家部分字段信息，含分页

  	public List<LoansBusinesses> queryAllAdmainpro(Integer page,Integer pagesize,String company){
  		List<LoansBusinesses> list=loansBusinessesMapper.queryAllAdmainpro(page,pagesize,company);
  		return list;
  	}

	@Override
	public String getTrademark(String businessname) {
		String trademark = loansBusinessesMapper.getTrademark(businessname);
		return trademark;
	}
	//后台管理---根据商品名称和传过来的年  月  日(例如：2019-01-01——2019-01-20)  获取当前甲方商家这个时间段的所有足迹时间
  	public List<String> queryTime(String businessName,String LikeTime,String LikeTime2){
  		List<String> list=loansBusinessesMapper.queryTime(businessName, LikeTime,LikeTime2);
  		return list;
  	}

  	//后台管理---根据商品名称和传过来的年  月  日(例如：2019-01-01) 获取当前甲方商家这一天的足迹数量
  	public int  queryAmount(String businessName,String LikeTime1,String LikeTime2) {
  		int count=loansBusinessesMapper.queryAmount(businessName, LikeTime1, LikeTime2);
  		return count;
  	}
}

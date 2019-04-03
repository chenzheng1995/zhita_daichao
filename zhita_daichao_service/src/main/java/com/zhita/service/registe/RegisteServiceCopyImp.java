package com.zhita.service.registe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhita.dao.manage.LoansBusinessesCopyMapper;
import com.zhita.dao.manage.LoansBusinessesMapper;
import com.zhita.model.manage.LoansBusinesses;
import com.zhita.model.manage.LoansBusinessesCopy;

@Service(value ="registeServiceCopyImp")
public class RegisteServiceCopyImp implements IntRegisteCopyService{
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
  	public List<LoansBusinesses> queryAllAdmain2(String company,Integer page,Integer pagesize) {
		List<LoansBusinesses> list=loansBusinessesCopyMapper.queryAllAdmain2(company,page,pagesize);
		return list;
	}
	//后台管理---查询贷款商家部分字段信息，不含分页
  	public List<LoansBusinesses> queryAllAdmain11(String company) {
		List<LoansBusinesses> list=loansBusinessesCopyMapper.queryAllAdmain11(company);
		return list;
	}
  	//后台管理---查询贷款商家总条数
  	public int pageCount1(String company) {
  		int count=loansBusinessesCopyMapper.pageCount1(company);
  		return count;
  	}
  	
  	//后台管理---通过模糊查询的条件查询贷款商家总条数
  	public int pageCountByLike1(String businessName,String company) {
  		int count=loansBusinessesCopyMapper.pageCountByLike1(businessName,company);
  		return count;
  	}
    //后台管理---添加贷款商家信息
    public int insert1(LoansBusinesses record) {
    	int selnum=loansBusinessesCopyMapper.insert1(record);
    	return selnum;
    }
  	//后台管理---通过商家名称模糊查询，并且有分页功能
  	public List<LoansBusinesses> queryByNameLike2(String businessName,String company,Integer page,Integer pagesize){
  		List<LoansBusinesses> list=loansBusinessesCopyMapper.queryByNameLike2(businessName,company,page,pagesize);
  		return list;
  	}
  	//后台管理---通过商家名称模糊查询，没有分页功能
  	public List<LoansBusinesses> queryByNameLike11(String businessName,String company){
  		List<LoansBusinesses> list=loansBusinessesCopyMapper.queryByNameLike11(businessName,company);
  		return list;
  	}
  	//后台管理---通过商家主键id修改假删除字段的值
	@Override
	public int upaFalseDel1(Integer id) {
		int num=loansBusinessesCopyMapper.upaFalseDel1(id);
		return num;
	}
	//后台管理---通过主键id查询出贷款商家信息
	@Override
	public LoansBusinesses selectByPrimaryKey1(Integer id) {
		LoansBusinesses loansBusinesses=loansBusinessesCopyMapper.selectByPrimaryKey1(id);
		return loansBusinesses;
	}
  	//后台管理---修改贷款商家状态为开启
  	public int upaStateOpen1(Integer id) {
  		int num=loansBusinessesCopyMapper.upaStateOpen1(id);
  		return num;
  	}
  	
  	//后台管理---修改贷款商家状态为关闭
  	public int upaStateClose1(Integer id) {
  		int num=loansBusinessesCopyMapper.upaStateClose1(id);
  		return num;
  	}

	@Override
	public void insertPath1(String ossPath) {
		loansBusinessesCopyMapper.insertPath1(ossPath);		
	}

	@Override
	public Map<String, Object> getLoansBusinesses1(String businessName,String company) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = loansBusinessesCopyMapper.getLoansBusinesses1(businessName,company); 
		return map;
	}

  	//后台管理---查询出贷款商家表所有的商家名称，将所有的商家名称存入一个集合中
  	public List<String> queryAllBusinessName1(String company){
  		List<String> list=loansBusinessesCopyMapper.queryAllBusinessName1(company);
  		return list;
  	}
  	//后台管理---通过商家名称模糊查询出贷款商家表所有的商家名称，将所有的商家名称存入一个集合中
  	public List<String> queryAllBusinessNameByLike1(String businessName,String company){
  		List<String> list=loansBusinessesCopyMapper.queryAllBusinessNameByLike1(businessName,company);
  		return list;
  	}
  	//后台管理---根据商家名称更新被申请次数字段
  	public int upaApplicationNumber1(Integer num,String businessName) {
  		int nums=loansBusinessesCopyMapper.upaApplicationNumber1(num, businessName);
  		return nums;
  	}
    //后台管理---通过传过来的贷款商家对象，对当前对象进行修改保存
    public int updateLoansBusinesses1(LoansBusinesses loans) {
    	int num=loansBusinessesCopyMapper.updateLoansBusinesses1(loans);
    	return num;
    }
  	//小程序---查询贷款商家部分字段信息，含分页

  	public List<LoansBusinessesCopy> queryAllAdmainpro1(Integer page,Integer pagesize,String company){
  		List<LoansBusinessesCopy> list=loansBusinessesCopyMapper.queryAllAdmainpro1(page,pagesize,company);
  		return list;
  	}

	@Override
	public String getTrademark1(String businessname) {
		String trademark = loansBusinessesCopyMapper.getTrademark1(businessname);
		return trademark;
	}
	//后台管理---根据商品名称和传过来的年  月  日(例如：2019-01-01——2019-01-20)  获取当前甲方商家这个时间段的所有足迹时间
  	public List<String> queryTime1(String businessName,String LikeTime,String LikeTime2){
  		List<String> list=loansBusinessesCopyMapper.queryTime1(businessName, LikeTime,LikeTime2);
  		return list;
  	}
  	//后台管理---根据商品名称和传过来的年  月  日(例如：2019-01-01) 获取当前甲方商家这一天的足迹数量
  	public int  queryAmount1(String businessName,String LikeTime1,String LikeTime2) {
  		int count=loansBusinessesCopyMapper.queryAmount1(businessName, LikeTime1, LikeTime2);
  		return count;
  	}

	@Override
	public int pageCountAppCopy(String company, String oneSourceName, String twoSourceName) {
		int totalCount=loansBusinessesCopyMapper.pageCountAppCopy(company,oneSourceName,twoSourceName);//该方法是查询贷款商家总条数
		return totalCount;
	}

	@Override
	public List<LoansBusinesses> queryAllAdmainproAppCopy(int page, int pageSize, String company, String oneSourceName,
			String twoSourceName) {
		List<LoansBusinesses> list=loansBusinessesCopyMapper.queryAllAdmainproAppCopy(page,pageSize,company,oneSourceName,twoSourceName);
		return list;
	}

}

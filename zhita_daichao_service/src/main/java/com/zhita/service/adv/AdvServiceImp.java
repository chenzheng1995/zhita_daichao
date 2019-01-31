package com.zhita.service.adv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.AdvertisingMapper;
import com.zhita.model.manage.Advertising;

@Service(value="advServiceImp")
public class AdvServiceImp implements IntAdvService{
	@Autowired
	private AdvertisingMapper advertisingMapper;
	
    //后台管理---根据主键id查询出广告表信息
    public Advertising selectByPrimaryKey(Integer id) {
    	Advertising advertising=advertisingMapper.selectByPrimaryKey(id);
    	return advertising;
    }
    //后台管理---根据传过来的广告对象，对当前对象进行修改保存
    public int updateAdvertising(Advertising advertising) {
    	int num=advertisingMapper.updateAdvertising(advertising);
    	return num;
    }
	
    //后台管理---查询出广告表总数量
    public int pageCount(String company) {
    	int count=advertisingMapper.pageCount(company);
    	return count;
    }
    
    //后台管理---根据标题字段模糊查询出 广告表总数量
    public int pageCountByLike(String title,String company) {
    	int count=advertisingMapper.pageCountByLike(title,company);
    	return count;
    }
    
    //后台管理---查询广告表全部信息,含分页
    public List<Advertising> queryAll(String company,Integer page,Integer pagesize){
    	List<Advertising> list=advertisingMapper.queryAll(company,page,pagesize);
    	return list;
    }
    
    //后台管理---根据标题字段模糊查询广告表信息，含分页
    public List<Advertising> queryAllByLike(String title,String company,Integer page,Integer pagesize){
    	List<Advertising> list=advertisingMapper.queryAllByLike(title,company,page,pagesize);
    	return list;
    }
    
    //后台管理---添加广告表信息
    public int AddAll(Advertising advertising){
    	int num=advertisingMapper.AddAll(advertising);
    	return num;
    }
    
    //后台管理---根据删除按钮，修改广告表假删除状态
    public int upaFalseDel(Integer id) {
    	int num=advertisingMapper.upaFalseDel(id);
    	return num;
    }
    
    //后台管理---修改广告表的状态为开启
    public int upaStateOpen(Integer id) {
    	int num=advertisingMapper.upaStateOpen(id);
    	return num;
    }
    
    //后台管理---修改广告表的状态为关闭
    public int upaStateClose(Integer id) {
    	int num=advertisingMapper.upaStateClose(id);
    	return num;
    }
	@Override
	public String getCover(int id) {
		String cover = advertisingMapper.getCover(id);
		return cover;
	}
}

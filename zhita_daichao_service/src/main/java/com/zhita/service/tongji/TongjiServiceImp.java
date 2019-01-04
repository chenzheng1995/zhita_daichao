package com.zhita.service.tongji;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.StatisticalMapper;
import com.zhita.model.manage.Statistical;
@Service
public class TongjiServiceImp implements IntTongjiService{
	@Autowired
	private StatisticalMapper statisticalMapper;
	
	
    //后台管理---通过渠道名称  查询出统计表所有的商家名称
    public List<String> quereyAllLoansNameBySourceName(String sourceName){
    	List<String> list=statisticalMapper.quereyAllLoansNameBySourceName(sourceName);
    	return list;
    }
    //后台管理---查询出统计表所有的商家名称
    public List<String> queryAllLoansName(){
    	List<String> list=statisticalMapper.queryAllLoansName();
    	return list;
    }
    //后台管理---查询出统计表数据总数量
    public int pageCount() {
    	int count=statisticalMapper.pageCount();
    	return count;
    }
    //后台管理---通过渠道名称   查询出统计表数据总数量
    public int pageCountBySourceName(String sourceName) {
    	int count=statisticalMapper.pageCountBySourceName(sourceName);
    	return count;
    }
    //后台管理---查询统计表所有信息，含分页
    public List<Statistical> queryAllPage(Integer page,Integer pagesize){
    	List<Statistical> list=statisticalMapper.queryAllPage(page,pagesize);
    	return list;
    }
    //后台管理---通过渠道名称   查询统计表所有信息，含分页
    public List<Statistical> queryAllPageBySourceName(String sourceName,Integer page,Integer pagesize){
    	List<Statistical> list=statisticalMapper.queryAllPageBySourceName(sourceName, page, pagesize);
    	return list;
    }
}

package com.zhita.service.tongji;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.StatisticalMapper;
import com.zhita.model.manage.SourceTongji;

@Service
public class TongjiServiceImp implements IntTongjiService{
	@Autowired
	private StatisticalMapper statisticalMapper;
	
	
    //后台管理---通过渠道名称  查询出统计表所有的商家名称
    public List<String> quereyAllLoansNameBySourceName(String sourceName){
    	List<String> list=statisticalMapper.quereyAllLoansNameBySourceName(sourceName);
    	return list;
    }
    //后台管理---查询出统计表数据总数量
    public int pageCount(String source,String startTime,String endTime) {
    	int count=statisticalMapper.pageCount(source,startTime,endTime);
    	return count;
    }
    //后台管理---通过渠道名称   查询出统计表数据总数量
    public int pageCountBySourceName(String company,String sourceName) {
    	int count=statisticalMapper.pageCountBySourceName(company,sourceName);
    	return count;
    }
    //后台管理---查询渠道统计所有信息，含分页
    public List<SourceTongji> queryAllPage(String source,String startTime,String endTime,Integer page,Integer pagesize){
    	List<SourceTongji> list=statisticalMapper.queryAllPage(source,startTime,endTime,page,pagesize);
    	return list;
    }
    //后台管理---查询渠道统计所有信息，不含分页
    public List<SourceTongji> queryAllPage1(String company){
    	List<SourceTongji> list=statisticalMapper.queryAllPage1(company);
    	return list;
    }
    //后台管理---通过渠道名称   查询统计表所有信息，含分页
    public List<SourceTongji> queryAllPageBySourceName(String company,String sourceName,Integer page,Integer pagesize){
    	List<SourceTongji> list=statisticalMapper.queryAllPageBySourceName(company,sourceName, page, pagesize);
    	return list;
    }
    //后台管理---通过渠道名称   查询统计表所有信息，不含分页
    public List<SourceTongji> queryAllPageBySourceName1(String company,String sourceName){
    	List<SourceTongji> list=statisticalMapper.queryAllPageBySourceName1(company,sourceName);
    	return list;
    }
    //后台管理---查询统计pv
    public Integer queryPV(String company,String sourceName) {
    	int pv=statisticalMapper.queryPV(company, sourceName);
    	return pv;
    }
    //后台管理---查询统计uv
    public Integer queryUV(String company,String sourceName,String startTime,String endTime) {
    	int uv=statisticalMapper.queryUV(company, sourceName,startTime,endTime);
    	return uv;
    }
    //后台管理---查询统计uv1
    public Integer queryUV1(String bussnname,String sourceName,String startTime,String endTime) {
    	int uv=statisticalMapper.queryUV1(bussnname, sourceName,startTime,endTime);
    	return uv;
    }
    //后台管理---查询统计申请数
    public Integer queryApplicationNumber(String company,String sourceName,String startTime,String endTime) {
    	int appnum=statisticalMapper.queryApplicationNumber(company, sourceName,startTime,endTime);
    	return appnum;
    }
    //后台管理---查询当前渠道的折扣率
    public String queryDiscount(String source,String company) {
    	String discount=statisticalMapper.queryDiscount(source, company);
    	return discount;
    }
}

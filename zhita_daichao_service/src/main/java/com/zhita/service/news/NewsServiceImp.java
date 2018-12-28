package com.zhita.service.news;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhita.dao.manage.StrategyMapper;
import com.zhita.model.manage.Strategy;


@Transactional
@Service(value="newsServiceImp")
public class NewsServiceImp implements IntNewsService{
	@Resource(name="strategyMapper")
	private StrategyMapper strategyMapper;

	public StrategyMapper getStrategyMapper() {
		return strategyMapper;
	}
	public void setStrategyMapper(StrategyMapper strategyMapper) {
		this.strategyMapper = strategyMapper;
	}
	
    //后台管理---查询攻略表所有信息，含分页
    public List<Strategy> queryAllNews(Integer page){
    	List<Strategy> list=strategyMapper.queryAllNews(page);
    	return list;
    }
    //后台管理---查询攻略表总数量
    public int pageCount() {
    	int count=strategyMapper.pageCount();
    	return count;
    }
    //后台管理---根据标题字段模糊查询攻略表总数量
    public int pageCountByLike(String title) {
    	int count=strategyMapper.pageCountByLike(title);
    	return count;
    }
    
    //后台管理---根据标题字段模糊查询，攻略表信息，含分页
    public List<Strategy> queryNewsByLike(String title,Integer page){
    	List<Strategy> list=strategyMapper.queryNewsByLike(title, page);
    	return list;
    }
    //后台管理---添加攻略信息
    public int addAll(Strategy strategy) {
    	int selnum=strategyMapper.addAll(strategy);
    	return selnum;
    }
    //后台管理---通过主键id查询出攻略信息
    public Strategy selectByPrimaryKey(Integer id) {
    	Strategy strategy=strategyMapper.selectByPrimaryKey(id);
    	return strategy;
    }
    
    //后台管理---通过传过来的攻略对象，对当前对象进行修改保存
    public int updateStrategy(Strategy strategy) {
    	int num=strategyMapper.updateStrategy(strategy);
    	return num;
    }
    //后台管理---通过主键id修改其当前对象的假删除状态
    public int upaFalseDelById(Integer id) {
    	int num=strategyMapper.upaFalseDelById(id);
    	return num;
    }
    //后台管理---修改攻略状态为开启
    public int upaStateOpen(Integer id) {
    	int num=strategyMapper.upaStateOpen(id);
    	return num;
    }
    //后台管理---修改攻略状态为关闭
    public int upaStateClose(Integer id) {
    	int num=strategyMapper.upaStateClose(id);
    	return num;
    }
}

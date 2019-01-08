package com.zhita.service.news;

import java.util.List;

import com.zhita.model.manage.Strategy;

public interface IntNewsService{
    //后台管理---查询攻略表所有信息，含分页
    public List<Strategy> queryAllNews(Integer page,Integer pagesize);
    //后台管理---查询攻略表总数量
    public int pageCount();
    //后台管理---根据标题字段模糊查询攻略表总数量
    public int pageCountByLike(String title);
    //后台管理---根据标题字段模糊查询，攻略表信息，含分页
    public List<Strategy> queryNewsByLike(String title,Integer page,Integer pagesize);
    //后台管理---添加攻略信息
    public int addAll(Strategy strategy);
    //后台管理---通过主键id查询出攻略信息
    public Strategy selectByPrimaryKey(Integer id);
    //后台管理---通过主键id修改其当前对象的假删除状态
    public int upaFalseDelById(Integer id);
    //后台管理---修改攻略状态为开启
    public int upaStateOpen(Integer id);
    //后台管理---修改攻略状态为关闭
    public int upaStateClose(Integer id);
    //后台管理---通过传过来的攻略对象，对当前对象进行修改保存
    public int updateStrategy(Strategy strategy);
    
	public String getCover(int id);
}

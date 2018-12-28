package com.zhita.service.adv;

import java.util.List;

import com.zhita.model.manage.Advertising;

public interface IntAdvService {
    //后台管理---根据主键id查询出广告表信息
    public Advertising selectByPrimaryKey(Integer id);
	
    //后台管理---查询出广告表总数量
    public int pageCount();
    
    //后台管理---根据标题字段模糊查询出 广告表总数量
    public int pageCountByLike(String title);
    
    //后台管理---查询广告表全部信息,含分页
    public List<Advertising> queryAll(Integer page);
    
    //后台管理---根据标题字段模糊查询广告表信息，含分页
    public List<Advertising> queryAllByLike(String title,Integer page);
    
    //后台管理---添加广告表信息
    public int AddAll(Advertising advertising); 
    
    //后台管理---根据删除按钮，修改广告表假删除状态
    public int upaFalseDel(Integer id);
    
    //后台管理---修改广告表的状态为开启
    public int upaStateOpen(Integer id);
    
    //后台管理---修改广告表的状态为关闭
    public int upaStateClose(Integer id);
    //后台管理---根据传过来的广告对象，对当前对象进行修改保存
    public int updateAdvertising(Advertising advertising);
}

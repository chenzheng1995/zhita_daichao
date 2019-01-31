package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.Advertising;

public interface AdvertisingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Advertising record);

    int insertSelective(Advertising record);
    
    //后台管理---根据主键id查询出广告表信息
    Advertising selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Advertising record);

    int updateByPrimaryKey(Advertising record);
    
    //后台管理---根据传过来的广告对象，对当前对象进行修改保存
    int updateAdvertising(Advertising advertising);
    
    //后台管理---查询出广告表总数量
    int pageCount(String company);
    
    //后台管理---根据标题字段模糊查询出 广告表总数量
    int pageCountByLike(String title,String company);
    
    //后台管理---查询广告表全部信息,含分页
    List<Advertising> queryAll(String company,Integer page,Integer pagesize);
    
    //后台管理---根据标题字段模糊查询广告表信息，含分页
    List<Advertising> queryAllByLike(String title,String company,Integer page,Integer pagesize);
    
    //后台管理---添加广告表信息
    int AddAll(Advertising advertising); 
    
    //后台管理---根据删除按钮，修改广告表假删除状态
    int upaFalseDel(Integer id);
    
    //后台管理---修改广告表的状态为开启
    int upaStateOpen(Integer id);
    
    //后台管理---修改广告表的状态为关闭
    int upaStateClose(Integer id);

	String getCover(int id);
}
package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.ShufflingFigure;

public interface ShufflingFigureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShufflingFigure record);

    int insertSelective(ShufflingFigure record);
    
    //后台管理 ---根据主键id查询出轮播图信息
    ShufflingFigure selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShufflingFigure record);

    int updateByPrimaryKey(ShufflingFigure record);
    
    //后台管理---通过传过来的轮播图对象，对当前对象进行修改保存
    int updateShufflingFigure(ShufflingFigure shufflingFigure);
    
    //后台管理---查询出轮播图总数量
    int pageCount();
    
    //后台管理---根据标题字段模糊查询出  轮播图总数量
    int pageCountByLike(String title);
    
    //后台管理---查询轮播图全部信息,含分页
    List<ShufflingFigure> queryAll(Integer page,Integer pagesize);
    
    //后台管理---根据标题字段模糊查询轮播图信息，含分页
    List<ShufflingFigure> queryAllByLike(String title,Integer page,Integer pagesize);
    
    //后台管理---添加轮播图信息
    int AddAll(ShufflingFigure shufflingFigure); 
    
    //后台管理---根据删除按钮，修改轮播图假删除状态
    int upaFalseDel(Integer id);
    
    //后台管理---修改轮播图的状态为开启
    int upaStateOpen(Integer id);
    
    //后台管理---修改轮播图的状态为关闭
    int upaStateClose(Integer id);

	String getCover(int id);

	List<ShufflingFigure> getShufflingFigure(String company);
    
    //后台管理 ——用于添加轮播图时进行判断----将传过来的贷款商家名称  传进贷款商家表看是否存在
	int  ifBusinessNameIfExist(String businessname);
}
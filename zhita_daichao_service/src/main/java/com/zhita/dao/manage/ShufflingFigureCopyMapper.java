package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.ShufflingFigureCopy;

public interface ShufflingFigureCopyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShufflingFigureCopy record);

    int insertSelective(ShufflingFigureCopy record);

    ShufflingFigureCopy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShufflingFigureCopy record);

    int updateByPrimaryKey(ShufflingFigureCopy record);

	List<ShufflingFigureCopy> getShufflingFigure1(String company);
	
    //后台管理 ---根据主键id查询出轮播图信息(shuffling_figure_copy表)
    ShufflingFigureCopy selectByPrimaryKeyCopy(Integer id);

    //后台管理---通过传过来的轮播图对象，对当前对象进行修改保存(shuffling_figure_copy表)
    int updateShufflingFigureCopy(ShufflingFigureCopy shufflingFigureCopy);
    
    //后台管理---查询出轮播图总数量(shuffling_figure_copy表)
    int pageCountCopy(String company);
    
    //后台管理---根据标题字段模糊查询出  轮播图总数量(shuffling_figure_copy表)
    int pageCountByLikeCopy(String title,String company);
    
    //后台管理---查询轮播图全部信息,含分页(shuffling_figure_copy表)
   List<ShufflingFigureCopy> queryAllCopy(String company,Integer page,Integer pagesize);
    
    //后台管理---查询轮播图全部信息,不含分页(shuffling_figure_copy表)
    List<ShufflingFigureCopy> queryAll1Copy(String company);
    
    //后台管理---根据标题字段模糊查询轮播图信息，含分页(shuffling_figure_copy表)
   List<ShufflingFigureCopy> queryAllByLikeCopy(String title,String company,Integer page,Integer pagesize);
    
    //后台管理---根据标题字段模糊查询轮播图信息，不含分页(shuffling_figure_copy表)
    List<ShufflingFigureCopy> queryAllByLike1Copy(String title,String company);
    
    //后台管理---添加轮播图信息(shuffling_figure_copy表)
    int AddAllCopy(ShufflingFigureCopy shufflingFigureCopy); 
    
    //后台管理---根据删除按钮，修改轮播图假删除状态(shuffling_figure_copy表)
    int upaFalseDelCopy(Integer id);
    
    //后台管理---修改轮播图的状态为开启(shuffling_figure_copy表)
    int upaStateOpenCopy(Integer id);
    
    //后台管理---修改轮播图的状态为关闭(shuffling_figure_copy表)
    int upaStateCloseCopy(Integer id);

	//后台管理----通过传过来的轮播图id，查询商标的UR(shuffling_figure_copy表)
	String getCoverCopy(int id);

    //后台管理 ——用于添加轮播图时进行判断----将传过来的贷款商家名称  传进贷款商家表看是否存在(shuffling_figure_copy表)
	int  ifBusinessNameIfExistCopy(String businessname);
}
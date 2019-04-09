package com.zhita.service.banner;

import java.util.List;

import com.zhita.model.manage.ShufflingFigure;
import com.zhita.model.manage.ShufflingFigureCopy;

public interface IntBannerServiceCopy {
    //后台管理---查询出轮播图总数量
    public int pageCount(String company);
    //后台管理---根据标题字段模糊查询出  轮播图总数量
    public int pageCountByLike(String title,String company);
    //后台管理---查询轮播图全部信息,含分页
    public List<ShufflingFigure> queryAll(String company,Integer page,Integer pagesize);
    //后台管理---查询轮播图全部信息,不含分页
    public List<ShufflingFigure> queryAll1(String company);
    //后台管理---根据标题字段模糊查询轮播图信息，含分页
    public List<ShufflingFigure> queryAllByLike(String title,String company,Integer page,Integer pagesize);
    //后台管理---根据标题字段模糊查询轮播图信息，不含分页
    public List<ShufflingFigure> queryAllByLike1(String title,String company);
    //后台管理---添加轮播图信息
    public int AddAll(ShufflingFigure shufflingFigure);
    //后台管理 ---根据主键id查询出轮播图信息
    public ShufflingFigure selectByPrimaryKey(Integer id);
    //后台管理---根据删除按钮，修改轮播图假删除状态
    public int upaFalseDel(Integer id);
    //后台管理---修改轮播图的状态为开启
    public int upaStateOpen(Integer id);
    //后台管理---修改轮播图的状态为关闭
    public int upaStateClose(Integer id);
    //后台管理---通过传过来的轮播图对象，对当前对象进行修改保存
    public int updateShufflingFigure(ShufflingFigure shufflingFigure);
	public String getCover(int id);
	public List<ShufflingFigureCopy> getShufflingFigure1(String company);
    //后台管理 ——用于添加轮播图时进行判断----将传过来的贷款商家名称  传进贷款商家表看是否存在
	public int  ifBusinessNameIfExist(String businessname);
	
    //后台管理 ---根据主键id查询出轮播图信息(shuffling_figure_copy表)
    public ShufflingFigureCopy selectByPrimaryKeyCopy(Integer id);

    //后台管理---通过传过来的轮播图对象，对当前对象进行修改保存(shuffling_figure_copy表)
    public int updateShufflingFigureCopy(ShufflingFigureCopy shufflingFigureCopy);
    
    //后台管理---查询出轮播图总数量(shuffling_figure_copy表)
    public int pageCountCopy(String company);
    
    //后台管理---根据标题字段模糊查询出  轮播图总数量(shuffling_figure_copy表)
    public int pageCountByLikeCopy(String title,String company);
    
    //后台管理---查询轮播图全部信息,含分页(shuffling_figure_copy表)
    public List<ShufflingFigureCopy> queryAllCopy(String company,Integer page,Integer pagesize);
    
    //后台管理---查询轮播图全部信息,不含分页(shuffling_figure_copy表)
    public List<ShufflingFigureCopy> queryAll1Copy(String company);
    
    //后台管理---根据标题字段模糊查询轮播图信息，含分页(shuffling_figure_copy表)
    public List<ShufflingFigureCopy> queryAllByLikeCopy(String title,String company,Integer page,Integer pagesize);
    
    //后台管理---根据标题字段模糊查询轮播图信息，不含分页(shuffling_figure_copy表)
    public List<ShufflingFigureCopy> queryAllByLike1Copy(String title,String company);
    
    //后台管理---添加轮播图信息(shuffling_figure_copy表)
    public int AddAllCopy(ShufflingFigureCopy shufflingFigureCopy); 
    
    //后台管理---根据删除按钮，修改轮播图假删除状态(shuffling_figure_copy表)
    public int upaFalseDelCopy(Integer id);
    
    //后台管理---修改轮播图的状态为开启(shuffling_figure_copy表)
    public int upaStateOpenCopy(Integer id);
    
    //后台管理---修改轮播图的状态为关闭(shuffling_figure_copy表)
    public int upaStateCloseCopy(Integer id);

	//后台管理----通过传过来的轮播图id，查询商标的UR(shuffling_figure_copy表)
	public String getCoverCopy(int id);

    //后台管理 ——用于添加轮播图时进行判断----将传过来的贷款商家名称  传进贷款商家表看是否存在(shuffling_figure_copy表)
	public int  ifBusinessNameIfExistCopy(String businessname);
}

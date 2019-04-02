package com.zhita.service.banner;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.ShufflingFigureCopyMapper;
import com.zhita.dao.manage.ShufflingFigureMapper;
import com.zhita.model.manage.ShufflingFigure;
import com.zhita.model.manage.ShufflingFigureCopy;

@Service(value="bannerServicecopyImp")
public class BannerServiceCopyImp implements IntBannerServiceCopy{
	@Resource(name="shufflingFigureMapper")
	private ShufflingFigureMapper shufflingFigureMapper;

	public ShufflingFigureMapper getShufflingFigureMapper() {
		return shufflingFigureMapper;
	}

	public void setShufflingFigureMapper(ShufflingFigureMapper shufflingFigureMapper) {
		this.shufflingFigureMapper = shufflingFigureMapper;
	}
	
	@Autowired
	ShufflingFigureCopyMapper shufflingFigureCopyMapper;
	
    //后台管理---查询出轮播图总数量
    public int pageCount(String company) {
    	int count=shufflingFigureMapper.pageCount(company);
    	return count;
    }
    //后台管理---根据标题字段模糊查询出  轮播图总数量
    public int pageCountByLike(String title,String company) {
    	int count=shufflingFigureMapper.pageCountByLike(title,company);
    	return count;
    }
    //后台管理---查询轮播图全部信息,含分页
    public List<ShufflingFigure> queryAll(String company,Integer page,Integer pagesize){
    	List<ShufflingFigure> list=shufflingFigureMapper.queryAll(company,page,pagesize);
    	return list;
    }
    //后台管理---查询轮播图全部信息,不含分页
    public List<ShufflingFigure> queryAll1(String company){
    	List<ShufflingFigure> list=shufflingFigureMapper.queryAll1(company);
    	return list;
    }
    //后台管理---根据标题字段模糊查询轮播图信息，含分页
    public List<ShufflingFigure> queryAllByLike(String title,String company,Integer page,Integer pagesize){
    	List<ShufflingFigure> list=shufflingFigureMapper.queryAllByLike(title,company,page,pagesize);
    	return list;
    }
    //后台管理---根据标题字段模糊查询轮播图信息，不含分页
    public List<ShufflingFigure> queryAllByLike1(String title,String company){
    	List<ShufflingFigure> list=shufflingFigureMapper.queryAllByLike1(title,company);
    	return list;
    }
    //后台管理---添加轮播图信息
    public int AddAll(ShufflingFigure shufflingFigure) {
    	int num=shufflingFigureMapper.AddAll(shufflingFigure);
    	return num;
    }
    //后台管理 ---根据主键id查询出轮播图信息
    public ShufflingFigure selectByPrimaryKey(Integer id) {
    	ShufflingFigure shufflingFigure=shufflingFigureMapper.selectByPrimaryKey(id);
    	return shufflingFigure;
    }
    //后台管理---通过传过来的轮播图对象，对当前对象进行修改保存
    public int updateShufflingFigure(ShufflingFigure shufflingFigure) {
    	int num=shufflingFigureMapper.updateShufflingFigure(shufflingFigure);
    	return num;
    }
    //后台管理---根据删除按钮，修改轮播图假删除状态
    public int upaFalseDel(Integer id) {
    	int num=shufflingFigureMapper.upaFalseDel(id);
    	return num;
    }
    //后台管理---修改轮播图的状态为开启
    public int upaStateOpen(Integer id) {
    	int num=shufflingFigureMapper.upaStateOpen(id);
    	return num;
    }
    //后台管理---修改轮播图的状态为关闭
    public int upaStateClose(Integer id) {
    	int num=shufflingFigureMapper.upaStateClose(id);
    	return num;
    }

	@Override
	public String getCover(int id) {
		String cover = shufflingFigureMapper.getCover(id);
		return cover;
	}

	@Override
	public List<ShufflingFigureCopy> getShufflingFigure1(String company) {
    	List<ShufflingFigureCopy> list=shufflingFigureCopyMapper.getShufflingFigure1(company); //获取轮播图的所有数据
		return list;
	}
    //后台管理 ——用于添加轮播图时进行判断----将传过来的贷款商家名称  传进贷款商家表看是否存在
	public int  ifBusinessNameIfExist(String businessname) {
		int count=shufflingFigureMapper.ifBusinessNameIfExist(businessname);
		return count;
	}
}

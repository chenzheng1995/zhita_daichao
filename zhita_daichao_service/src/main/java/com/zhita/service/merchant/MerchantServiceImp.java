package com.zhita.service.merchant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.SourceMapper;
import com.zhita.model.manage.Source;

@Service
public class MerchantServiceImp implements IntMerchantService{
	@Autowired
	private SourceMapper sourceMapper;
	
    //后台管理---查询出所有渠道表信息，不含分页
    public List<Source> queryAll(){
    	List<Source> list=sourceMapper.queryAll();
    	return list;
    }
    //后台管理---通过渠道id，查询渠道信息
    public Source selectByPrimaryKey(Integer id) {
    	Source source=sourceMapper.selectByPrimaryKey(id);
    	return source;
    }
    
    //后台管理---通过传过来的渠道对象，对当前对象进行修改保存
    public int updateSource(Source source) {
    	int num=sourceMapper.updateSource(source);
    	return num;
    }
    
    //后台管理---查询出所有渠道表信息，含分页
    public List<Source> queryAllSource(Integer page){
    	List<Source> list=sourceMapper.queryAllSource(page);
    	return list;
    }
    
    //后台管理---用于获取总页数
    public int pageCount() {
    	int count=sourceMapper.pageCount();
    	return count;
    }
    
    //后台管理---用于获取模糊查询总页数
    public int pageCountByLike(String sourceName) {
    	int count=sourceMapper.pageCountByLike(sourceName);
    	return count;
    }
    
    //后台管理---模糊查询渠道信息,并且有分页功能
    public List<Source> queryByLike(String sourceName,Integer page){
    	List<Source> list=sourceMapper.queryByLike(sourceName, page);
    	return list;
    }
    
    //后台管理---添加渠道信息
    public int addAll(Source source) {
    	int num=sourceMapper.addAll(source);
    	return num;
    }
    
    //后台管理---通过删除按钮，改变当前渠道的假删除状态，将状态改为删除
    public  int upaFalseDel(Integer id) {
    	int num=sourceMapper.upaFalseDel(id);
    	return num;
    }
    
    //后台管理---修改信用卡状态为开启
    public int upaStateOpen(Integer id) {
    	int num=sourceMapper.upaStateOpen(id);
    	return num;
    }
    
    //后台管理---修改信用卡状态为关闭
    public int upaStateClose(Integer id) {
    	int num=sourceMapper.upaStateClose(id);
    	return num;
    }
}

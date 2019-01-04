package com.zhita.service.merchant;

import java.util.List;
import java.util.Map;

import com.zhita.model.manage.Source;
import com.zhita.model.manage.User;

public interface IntMerchantService {
    //后台管理---通过渠道id，查询渠道信息
    public Source selectByPrimaryKey(Integer id);
	
    //后台管理---查询出所有渠道表信息，不含分页
    public List<Source> queryAll();
    
    //后台管理---通过传过来的渠道对象，对当前对象进行修改保存
    public int updateSource(Source source);
    
    //后台管理---查询出所有渠道表信息，含分页
    public List<Source> queryAllSource(Integer page,Integer pagesize);
    
    //后台管理---用于获取总页数
    public int pageCount();
    
    //后台管理---用于获取模糊查询总页数
    public int pageCountByLike(String sourceName);
    
    //后台管理---模糊查询渠道信息,并且有分页功能
    public List<Source> queryByLike(String sourceName,Integer page,Integer pagesize);
    
    //后台管理---添加渠道信息
    public int addAll(Source source);
    
    //后台管理---通过删除按钮，改变当前渠道的假删除状态，将状态改为删除
    public  int upaFalseDel(Integer id);
    
    //后台管理---修改渠道状态为开启
    public int upaStateOpen(Integer id);
    
    //后台管理---修改渠道状态为关闭
    public int upaStateClose(Integer id);
    
    //后台管理 ---查询出当前渠道id在用户表的姓名，年龄，身份证号，手机号，注册时间 的总数量
    public int pageCountBySourceId(Integer sourceId);
    
    //后台 管理---查询出当前渠道id在用户表的姓名，年龄，身份证号，手机号，注册时间   含分页
    public List<User> queryAllUserBySourceId(Integer SourceId,Integer page,Integer pagesize);
    
    //后台 管理---通过注册时间 。。手机号    查询出当前渠道id在用户表的姓名，年龄，身份证号，手机号，注册时间   含分页
    public Map<String,Object> queryAllUserByLikeAll(Integer SourceId,String registrationTimeStart,String registrationTimeEnd,String phone,Integer page);
}

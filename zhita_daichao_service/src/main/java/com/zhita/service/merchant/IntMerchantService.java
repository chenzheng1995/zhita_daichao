package com.zhita.service.merchant;

import java.util.List;
import java.util.Map;

import com.zhita.model.manage.CustomerUrlResult;
import com.zhita.model.manage.Source;
import com.zhita.model.manage.User;

public interface IntMerchantService {
    //后台管理---通过渠道id，查询渠道信息
    public Source selectByPrimaryKey(Integer id);
	
    //后台管理---查询出所有渠道表信息，不含分页
    public List<Source> queryAll(String company);
    
    //后台管理---通过传过来的渠道对象，对当前对象进行修改保存
    public int updateSource(Source source);
    
    //后台管理---通过渠道id查询出当前渠道的折扣率
    public String  queryDiscount(Integer id);
    
    //后台管理  ----通过传过来的数据对管理登陆表进行修改
    public int updateManageLogin(String acount,String source,String acount1);
    
    //后台管理---查询出所有渠道表信息，含分页
    public List<Source> queryAllSource(String company,Integer page,Integer pagesize);
    
    //后台管理---查询出所有渠道表信息，不含分页
    public List<Source> queryAllSource1(String company);
    
    //后台管理---用于获取总页数
    public int pageCount(String company);
    
    //后台管理---用于获取模糊查询总页数
    public int pageCountByLike(String sourceName,String company);
    
    //后台管理---模糊查询渠道信息,并且有分页功能
    public List<Source> queryByLike(String sourceName,String company,Integer page,Integer pagesize);
    
    //后台管理---模糊查询渠道信息,并且没有分页功能
    public List<Source> queryByLike1(String sourceName,String company);
    
    //后台管理---查询当前渠道是否在渠道表有数据
    public int queryIsExist(String source);
    
   //后台管理---修改渠道数据
    public int updateAll(Source source);
    
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

	public int getsourceId(String sourceId);

	public List<Object> getOneFirm(String company);

	public String getSourceName(Integer sourceId, String company);

	public String getLink(Integer id);

	public void updateLink(String link, Integer id);

	public Integer getTemplateId(Integer id);

	public List<String> getstateAndDeleted(String company, String sourceName);

	public List<String> getDeleted(String company, String sourceName);

	CustomerUrlResult searchCurrentUrl(CustomerUrlResult param);

}

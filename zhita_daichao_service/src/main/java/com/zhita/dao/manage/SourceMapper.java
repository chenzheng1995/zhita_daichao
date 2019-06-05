package com.zhita.dao.manage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.Source;
import com.zhita.model.manage.User;

public interface SourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Source record);

    int insertSelective(Source record);
    
    //后台管理---通过渠道id，查询渠道信息
    Source selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Source record);

    int updateByPrimaryKey(Source record);
    
    //后台管理---查询当前渠道是否在渠道表有数据
    int queryIsExist(String source);
    
    //后台管理---修改渠道数据
    int updateAll(Source source);
    
    //后台管理---查询出所有渠道表信息，不含分页
    List<Source> queryAll(String company);
    
    //后台管理---通过传过来的渠道对象，对当前对象进行修改保存
    int updateSource(Source source);
    
    //后台管理  ----通过传过来的数据对管理登陆表进行修改
    int updateManageLogin(String acount,String source,String acount1);
    
    //后台管理---查询出所有渠道表信息，含分页
    List<Source> queryAllSource(String company,Integer page,Integer pagesize);
    
    //后台管理---查询出所有渠道表信息，不含分页
    List<Source> queryAllSource1(String company);
    
    //后台管理---用于获取总页数
    int pageCount(String company);
    
    //后台管理---用于获取模糊查询总页数
    int pageCountByLike(String sourceName,String company);
    
    //后台管理---模糊查询渠道信息,并且有分页功能
    List<Source> queryByLike(String sourceName,String company,Integer page,Integer pagesize);
    
    //后台管理---模糊查询渠道信息,并且没有分页功能
    List<Source> queryByLike1(String sourceName,String company);
    
    //后台管理---添加渠道信息
    int addAll(Source source);
    
    //后台管理---通过删除按钮，改变当前渠道的假删除状态，将状态改为删除
    int upaFalseDel(Integer id);
    
    //后台管理---修改信用卡状态为开启
    int upaStateOpen(Integer id);
    
    //后台管理---修改信用卡状态为关闭
    int upaStateClose(Integer id);
    
    //后台管理 ---查询出当前渠道id在用户表的姓名，年龄，身份证号，手机号，注册时间 的总数量
    int pageCountBySourceId(Integer sourceId);
    
    //后台管理 ---通过注册时间模糊查询    出当前渠道id在用户表的姓名，年龄，身份证号，手机号，注册时间 的总数量
    int pageCountByRegistrationTime(Integer sourceId,String registrationTime,String registrationTime1);
    
    //后台管理 ---通过手机号模糊查询    出当前渠道id在用户表的姓名，年龄，身份证号，手机号，注册时间 的总数量
    int pageCountByPhone(Integer sourceId,String phone);
    
    //后台管理 ---通过注册时间 。。手机号模糊查询    出当前渠道id在用户表的姓名，年龄，身份证号，手机号，注册时间 的总数量
    int pageCountByRegistrationTimePhone(Integer sourceId,String registrationTime,String registrationTime1,String phone);
    
    //后台 管理---查询出当前渠道id在用户表的姓名，年龄，身份证号，手机号，注册时间   含分页
    List<User> queryAllUserBySourceId(Integer SourceId,Integer page,Integer pagesize);
    
    //后台 管理---通过注册时间  查询出当前渠道id在用户表的姓名，年龄，身份证号，手机号，注册时间   含分页
    List<User> queryAllUserByRegistrationTime(Integer SourceId,String registrationTime,String registrationTime1,Integer page,Integer pagesize);
    
    //后台 管理---通过手机号    查询出当前渠道id在用户表的姓名，年龄，身份证号，手机号，注册时间   含分页
    List<User> queryAllUserByPhone(Integer SourceId,String phone,Integer page,Integer pagesize);
    
    //后台 管理---通过注册时间 。。手机号    查询出当前渠道id在用户表的姓名，年龄，身份证号，手机号，注册时间   含分页
    List<User> queryAllUserByRegistrationTimePhone(Integer SourceId,String registrationTime,String registrationTime1,String phone,Integer page,Integer pagesize);

	int getsourceId(String sourceId);

	List<Object> getOneFirm(String company);

	String getSourceName(@Param("sourceId")Integer sourceId,@Param("company") String company);

	String getLink(Integer id);

	void updateLink(@Param("link")String link,@Param("id") Integer id);

	Integer getTemplateId(Integer id);

	List<String> getstateAndDeleted(@Param("company")String company,@Param("sourceName") String sourceName);

	List<String> getDeleted(@Param("company")String company,@Param("sourceName") String sourceName);
}
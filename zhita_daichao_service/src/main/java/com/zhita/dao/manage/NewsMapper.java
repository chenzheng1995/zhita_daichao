package com.zhita.dao.manage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.News;

public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

	int setnews(@Param("title")String title,@Param("company") String company,@Param("ossimagePath") String ossimagePath,@Param("author") String author,@Param("ossarticlePath") String ossarticlePath,@Param("isStick")String isStick,@Param("registrationTime") String registrationTime,@Param("typeId") Integer typeId);

	int setNewsNotOssimagePath(@Param("title")String title,@Param("company") String company,@Param("author") String author,@Param("ossarticlePath") String ossarticlePath,@Param("isStick")String isStick,@Param("registrationTime") String registrationTime,@Param("typeId") Integer typeId);

	int pageCount1(@Param("company")String company,@Param("typeId") int typeId);

	List<News> getAllnews(@Param("page")int page,@Param("pageSize") int pageSize,@Param("company") String company,@Param("typeId") int typeId);

	int updatenews(@Param("title")String title,@Param("company") String company,@Param("ossimagePath") String ossimagePath,@Param("author") String author,@Param("ossarticlePath") String ossarticlePath,@Param("isStick")String isStick,@Param("registrationTime") String registrationTime,@Param("id") int id,@Param("typeId") Integer typeId);
	
	//通过文章id查询出标题图片
	String getTitleImage(Integer id);

	int deletenews(int id);

	int getviewed(int id);

	int setviewed(@Param("id") int id,@Param("newViewed") int newViewed);

	int pageCount2(@Param("company") String company,@Param("title") String title);

	List<News> getfuzzynews(@Param("page") int page,@Param("pageSize") int pageSize,@Param("company") String company,@Param("title") String title);

	int pageCountAll1(String company);

	List<News> getNewsByAll(@Param("page")int page,@Param("pageSize") int pageSize,@Param("company") String company);

	News getnewscontent(int id);

	List<News> getAdminNewsByAll(@Param("page")int page,@Param("pageSize") int pageSize,@Param("company") String company);

	List<News> getAdminAllnews(@Param("page")int page,@Param("pageSize") int pageSize,@Param("company") String company,@Param("typeId") Integer typeId);

	String getcontent(Integer id);

	List<News> getNewsById(Integer id);

}
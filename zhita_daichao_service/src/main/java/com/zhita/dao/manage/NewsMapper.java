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

	int setnews(@Param("title")String title,@Param("company") String company,@Param("ossimagePath") String ossimagePath,@Param("author") String author,@Param("ossarticlePath") String ossarticlePath,@Param("isStick")String isStick,@Param("registrationTime") String registrationTime);

	int setNewsNotOssimagePath(@Param("title")String title,@Param("company") String company,@Param("author") String author,@Param("ossarticlePath") String ossarticlePath,@Param("isStick")String isStick,@Param("registrationTime") String registrationTime);

	int pageCount1(String company);

	List<News> getAllnews(@Param("page")int page,@Param("pageSize") int pageSize,@Param("company") String company);

}
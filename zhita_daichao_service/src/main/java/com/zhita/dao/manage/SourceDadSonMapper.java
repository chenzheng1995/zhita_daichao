package com.zhita.dao.manage;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.SourceDadSon;

public interface SourceDadSonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SourceDadSon record);

    int insertSelective(SourceDadSon record);

    SourceDadSon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SourceDadSon record);

    int updateByPrimaryKey(SourceDadSon record);

	int getSourceDadSon(@Param("sourceId")String sourceId,@Param("sonSourceName") String sonSourceName,@Param("company") String company);

	void setSourceDadSon(@Param("sourceId")String sourceId,@Param("sonSourceName") String sonSourceName,@Param("company") String company);

	String getTableType(@Param("oneSourceName")String oneSourceName,@Param("twoSourceName") String twoSourceName,@Param("company") String company);
}
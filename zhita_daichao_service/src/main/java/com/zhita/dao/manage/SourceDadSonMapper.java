package com.zhita.dao.manage;

import java.util.List;

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
	//后台管理---查询source_dad_son表所有数据
	List<SourceDadSon> queryAll(@Param("company") String[] company);
	//后台管理---修改source_dad_son表的tableType字段为1
	int upatableTypeStart(Integer id);
	//后台管理---修改source_dad_son表的tableType字段为2
	int upatableTypeClose(Integer id);
}
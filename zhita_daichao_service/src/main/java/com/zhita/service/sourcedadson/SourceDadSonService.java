package com.zhita.service.sourcedadson;

import java.util.List;

import com.zhita.model.manage.SourceDadSon;

public interface SourceDadSonService {

	int getSourceDadSon(String sourceId, String sonSourceName, String company);

	void setSourceDadSon(String sourceId, String sonSourceName, String company);

	String getTableType(String oneSourceName, String twoSourceName, String company);
	//后台管理---查询source_dad_son表所有数据
	public List<SourceDadSon> queryAll(String[] company);
	//后台管理---修改source_dad_son表的tableType字段为1
	public int upatableTypeStart(Integer id);
	//后台管理---修改source_dad_son表的tableType字段为2
	public int upatableTypeClose(Integer id);
}

package com.zhita.service.sourcedadson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.SourceDadSonMapper;
import com.zhita.model.manage.SourceDadSon;

@Service
public class SourceDadSonServiceImp implements SourceDadSonService{

	@Autowired
	SourceDadSonMapper sourceDadSonMapper;

	@Override
	public int getSourceDadSon(String sourceId, String sonSourceName, String company) {
		int number = sourceDadSonMapper.getSourceDadSon(sourceId,sonSourceName,company);
		return number;
	}

	@Override
	public void setSourceDadSon(String sourceId, String sonSourceName, String company) {
		sourceDadSonMapper.setSourceDadSon(sourceId,sonSourceName,company);
		
	}

	@Override
	public String getTableType(String oneSourceName, String twoSourceName, String company) {
		String  tableType = sourceDadSonMapper.getTableType(oneSourceName,twoSourceName,company);
		return tableType;
	}
	//后台管理---查询source_dad_son表所有数据
	public List<SourceDadSon> queryAll(String[] company){
		List<SourceDadSon> list=sourceDadSonMapper.queryAll(company);
		return list;
	}
	//后台管理---修改source_dad_son表的tableType字段为1
	public int upatableTypeStart(Integer id){
		int num=sourceDadSonMapper.upatableTypeStart(id);
		return num;
	}
	//后台管理---修改source_dad_son表的tableType字段为2
	public int upatableTypeClose(Integer id){
		int num=sourceDadSonMapper.upatableTypeClose(id);
		return num;
	}
}

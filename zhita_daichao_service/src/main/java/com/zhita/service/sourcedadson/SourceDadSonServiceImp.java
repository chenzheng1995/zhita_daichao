package com.zhita.service.sourcedadson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.SourceDadSonMapper;

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
}

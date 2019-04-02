package com.zhita.service.sourcedadson;

public interface SourceDadSonService {

	int getSourceDadSon(String sourceId, String sonSourceName, String company);

	void setSourceDadSon(String sourceId, String sonSourceName, String company);

	String getTableType(String oneSourceName, String twoSourceName, String company);

}

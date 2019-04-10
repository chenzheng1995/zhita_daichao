package com.zhita.service.sourcedadson;

import java.util.List;

import com.zhita.model.manage.Source;
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
	//后台管理   查询出所有渠道表信息，不含分页
	public List<Source> queryAllSource(String company);
	//后台管理---往source_dad_son表新增数据
    public int insertSouceDadSon(SourceDadSon sourceDadSon);
    //后台管理---通过主键id查询source_dad_son表数据
   public SourceDadSon selectByPrimaryKey(Integer id);
   //后台管理     修改source_dad_son表数据
   public int updateSouceDadSon(SourceDadSon sourceDadSon); 
   //后台管理----修改source_dad_son表假删除状态
   public int updateFalseDel(Integer id);
   
}

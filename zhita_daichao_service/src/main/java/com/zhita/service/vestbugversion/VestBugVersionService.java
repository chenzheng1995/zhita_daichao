package com.zhita.service.vestbugversion;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.VestBagVersion;

public interface VestBugVersionService {

	String getVersion(Integer id);
	//后台管理----通过id查询vest_bag_version对象的信息
    public VestBagVersion selectByPrimaryKey(Integer id);
	//后台管理  ------查询vest_bag_version表所有的信息
	public List<VestBagVersion> queryAllVersion(@Param("company") String[] company);
	//后台管理-----保存修改后的vest_bag_version表信息
	public int  upaVersion(VestBagVersion vestBagVersion);
}

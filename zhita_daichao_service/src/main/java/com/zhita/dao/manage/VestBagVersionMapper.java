package com.zhita.dao.manage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.VestBag;
import com.zhita.model.manage.VestBagVersion;

public interface VestBagVersionMapper {
    int deleteByPrimaryKey(Integer id);
    
    //后台管理---先查询出vest_bag表所有信息
    List<VestBag> queryAll(String company);
    
    //后台管理---添加vest_bag_version表信息
    int insert(VestBagVersion record);

    int insertSelective(VestBagVersion record);
    
    //后台管理----通过id查询vest_bag_version对象的信息
    VestBagVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VestBagVersion record);

    int updateByPrimaryKey(VestBagVersion record);

	String getVersion(Integer id);
	//后台管理  ------查询vest_bag_version表所有的信息
	List<VestBagVersion> queryAllVersion(@Param("company") String[] company);
	//后台管理-----保存修改后的vest_bag_version表信息
	int  upaVersion(VestBagVersion vestBagVersion);
}
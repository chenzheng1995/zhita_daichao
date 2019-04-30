package com.zhita.service.vestbugversion;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.VestBagVersionMapper;
import com.zhita.model.manage.VestBag;
import com.zhita.model.manage.VestBagVersion;

@Service
public class VestBugVersionServiceImp implements VestBugVersionService{

	@Autowired
	VestBagVersionMapper vestBagVersionMapper;

	@Override
	public String getVersion(Integer id) {
		String version = vestBagVersionMapper.getVersion(id);
		return version;
	}
	//后台管理----通过id查询vest_bag_version对象的信息
    public VestBagVersion selectByPrimaryKey(Integer id){
    	VestBagVersion vestBagVersion=vestBagVersionMapper.selectByPrimaryKey(id);
    	return vestBagVersion;
    }
	//后台管理  ------查询vest_bag_version表所有的信息
	public List<VestBagVersion> queryAllVersion(@Param("company") String[] company){
		List<VestBagVersion> list=vestBagVersionMapper.queryAllVersion(company);
		return list;
	}
	//后台管理-----保存修改后的vest_bag_version表信息
	public int  upaVersion(VestBagVersion vestBagVersion){
		int num=vestBagVersionMapper.upaVersion(vestBagVersion);
		return num;
	}
	//后台管理---先查询出vest_bag表所有信息
    public List<VestBag> queryAll(String company){
    	List<VestBag> list=vestBagVersionMapper.queryAll(company);
    	return list;
    }
    
    //后台管理---添加vest_bag_version表信息
    public int insert(VestBagVersion record){
    	int num=vestBagVersionMapper.insert(record);
    	return num;
    }
}

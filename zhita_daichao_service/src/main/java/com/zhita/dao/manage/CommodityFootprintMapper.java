package com.zhita.dao.manage;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.CommodityFootprint;

public interface CommodityFootprintMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommodityFootprint record);

    int insertSelective(CommodityFootprint record);

    CommodityFootprint selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommodityFootprint record);

    int updateByPrimaryKey(CommodityFootprint record);

	long getDailyApplications(@Param("todayZeroTimestamps") long todayZeroTimestamps,@Param("tomorrowZeroTimestamps")long tomorrowZeroTimestamps);

	long getdailyApplicationsUsers(@Param("todayZeroTimestamps") long todayZeroTimestamps,@Param("tomorrowZeroTimestamps")long tomorrowZeroTimestamps);

	long getmonthlyApplications(@Param("monthlyZeroTimestamps") long monthlyZeroTimestamps,@Param("nextMonthlyZeroTimestamps")long nextMonthlyZeroTimestamps);

	long getmonthlyApplicationsUsers(@Param("monthlyZeroTimestamps") long monthlyZeroTimestamps,@Param("nextMonthlyZeroTimestamps")long nextMonthlyZeroTimestamps);

	int insertfootprint(@Param("footprintName")String footprintName,@Param("userId") String userId,@Param("currentTimestamp") long currentTimestamp,@Param("company") String company);


	List<String> getbusinessName(@Param("userId")String userId,@Param("pageSize")int pageSize,@Param("startRow") int startRow,@Param("company") String company);

	long getApplications(@Param("businessName")String businessName,@Param("company") String company);
	
	//后台管理---根据传过来的足迹名称，查询出足迹的个数
	int queryCount(String businessName);

	long getRecordNumber(@Param("userId")String userId,@Param("company") String company);

}
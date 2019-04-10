package com.zhita.dao.manage;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.CommodityFootprint;

public interface CommodityFootprintCopyMapper {
    int deleteByPrimaryKey1(Integer id);

    int insert1(CommodityFootprint record);

    int insertSelective1(CommodityFootprint record);

    CommodityFootprint selectByPrimaryKey1(Integer id);

    int updateByPrimaryKeySelective1(CommodityFootprint record);

    int updateByPrimaryKey1(CommodityFootprint record);

	long getDailyApplications1(@Param("todayZeroTimestamps") long todayZeroTimestamps,@Param("tomorrowZeroTimestamps")long tomorrowZeroTimestamps);

	long getdailyApplicationsUsers1(@Param("todayZeroTimestamps") long todayZeroTimestamps,@Param("tomorrowZeroTimestamps")long tomorrowZeroTimestamps);

	long getmonthlyApplications1(@Param("monthlyZeroTimestamps") long monthlyZeroTimestamps,@Param("nextMonthlyZeroTimestamps")long nextMonthlyZeroTimestamps);

	long getmonthlyApplicationsUsers1(@Param("monthlyZeroTimestamps") long monthlyZeroTimestamps,@Param("nextMonthlyZeroTimestamps")long nextMonthlyZeroTimestamps);

	int insertfootprint1(@Param("footprintName")String footprintName,@Param("userId") String userId,@Param("currentTimestamp") long currentTimestamp,@Param("company") String company);


	List<String> getbusinessName1(@Param("userId")String userId,@Param("pageSize")int pageSize,@Param("startRow") int startRow,@Param("company") String company);

	long getApplications1(@Param("businessName")String businessName,@Param("company") String company);
	
	//后台管理---根据传过来的足迹名称，查询出足迹的个数
	int queryCount1(String businessName,String company);

	long getRecordNumber1(@Param("userId")String userId,@Param("company") String company);

	int insertfootprintAppCopy(@Param("footprintName")String footprintName,@Param("userId") String userId,@Param("currentTimestamp") long currentTimestamp,@Param("company") String company,
			@Param("oneSourceName")String oneSourceName,@Param("twoSourceName") String twoSourceName);

	long getRecordNumberAppCopy(@Param("userId")String userId,@Param("company") String company,@Param("oneSourceName") String oneSourceName,@Param("twoSourceName") String twoSourceName);

	List<String> getbusinessNameAppCopy(@Param("userId")String userId,@Param("pageSize") int pageSize,@Param("startRow") int startRow,@Param("company") String company,@Param("oneSourceName") String oneSourceName,
			@Param("twoSourceName") String twoSourceName);

	long getApplicationsAppCopy(@Param("businessName")String businessName,@Param("company") String company,@Param("oneSourceName") String oneSourceName,@Param("twoSourceName") String twoSourceName);

}
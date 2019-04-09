package com.zhita.dao.manage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.TopUpAmount;

public interface TopUpAmountMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(TopUpAmount record);

	int insertSelective(TopUpAmount record);

	TopUpAmount selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(TopUpAmount record);

	int updateByPrimaryKey(TopUpAmount record);

	Integer setTUamount(@Param("billingDate") String billingDate, @Param("firm") String firm,
			@Param("topUpAmount") int topUpAmount, @Param("cashReceipts") String cashReceipts,
			@Param("paymentAccount") String paymentAccount, @Param("contact") String contact,
			@Param("note") String note, @Param("company") String company,
			@Param("firmType") String firmType, @Param("registrationTime") String registrationTime,@Param("otimestamps") String otimestamps);

	int updateTUamount(@Param("billingDate") String billingDate, @Param("firm") String firm,
			@Param("topUpAmount") int topUpAmount, @Param("cashReceipts") String cashReceipts,
			@Param("paymentAccount") String paymentAccount, @Param("contact") String contact,
			@Param("note") String note, @Param("company") String company,
			@Param("firmType") String firmType, @Param("registrationTime") String registrationTime,@Param("id") int id,@Param("otimestamps") String otimestamps);

	int deleteAmountById(@Param("id")int id,@Param("registrationTime") String registrationTime);

	int deleteAmountByOperationDate(@Param("otimestamps")String otimestamps,@Param("registrationTime") String registrationTime,@Param("company") String company,@Param("firmtype") String firmtype);

	int pageCountByAmount(@Param("firmType")String firmType,@Param("company") String company);

	List<TopUpAmount> getAmount(@Param("firmType")String firmType,@Param("company") String company,@Param("page") int page,@Param("pagesize") int pagesize);

	Integer gettopUpAmount(@Param("sourceName")String sourceName,@Param("date") String date,@Param("company") String company);

	Integer getAmountbyfirm(@Param("startDate")String startDate,@Param("endDate") String endDate,@Param("company") String company,@Param("sourceName") String sourceName);

	Integer setTUamount1(TopUpAmount topupamount);

	int pageCountByAmountNoFirm(@Param("firmType")String firmType,@Param("company") String company,@Param("firm") String firm);

	List<TopUpAmount> getAmountNoFirm(@Param("firmType")String firmType,@Param("company") String company,@Param("page") int page,@Param("pageSize") int pageSize,@Param("firm") String firm);

}
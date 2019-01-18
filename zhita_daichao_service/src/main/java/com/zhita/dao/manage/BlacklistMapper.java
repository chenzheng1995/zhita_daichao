package com.zhita.dao.manage;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.Blacklist;

public interface BlacklistMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Blacklist record);

    int insertSelective(Blacklist record);

    Blacklist selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Blacklist record);

    int updateByPrimaryKey(Blacklist record);

	int setblacklist(@Param("userId")int userId, @Param("name")String name, @Param("idCard")String idCard,
			@Param("phone") String phone,@Param("creationTime") String creationTime,@Param("company") String company);


}
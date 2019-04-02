package com.zhita.dao.manage;

import com.zhita.model.manage.VestBagVersion;

public interface VestBagVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VestBagVersion record);

    int insertSelective(VestBagVersion record);

    VestBagVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VestBagVersion record);

    int updateByPrimaryKey(VestBagVersion record);

	String getVersion(Integer id);
}
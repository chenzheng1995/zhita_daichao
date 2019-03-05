package com.zhita.dao.manage;

import com.zhita.model.manage.Test;

public interface TestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Test record);

    int insertSelective(Test record);
}
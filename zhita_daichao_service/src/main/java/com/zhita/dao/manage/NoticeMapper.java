package com.zhita.dao.manage;

import java.util.List;

import com.zhita.model.manage.Notice;

public interface NoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);

	List<String> getname();

	List<String> getmoney();
}
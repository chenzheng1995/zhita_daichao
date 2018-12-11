package com.zhita.dao.manage;

import com.zhita.model.manage.ShufflingFigure;

public interface ShufflingFigureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShufflingFigure record);

    int insertSelective(ShufflingFigure record);

    ShufflingFigure selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShufflingFigure record);

    int updateByPrimaryKey(ShufflingFigure record);
}
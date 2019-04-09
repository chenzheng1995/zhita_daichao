package com.zhita.dao.manage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhita.model.manage.ShufflingFigureCopy;

public interface ShufflingFigureCopyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShufflingFigureCopy record);

    int insertSelective(ShufflingFigureCopy record);

    ShufflingFigureCopy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShufflingFigureCopy record);

    int updateByPrimaryKey(ShufflingFigureCopy record);

	List<ShufflingFigureCopy> getShufflingFigure1(String company);

	List<ShufflingFigureCopy> getShufflingFigureAppCopy(@Param("company")String company,@Param("oneSourceName") String oneSourceName,@Param("twoSourceName") String twoSourceName);
}
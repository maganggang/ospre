package com.safe.core.mapper;

import com.safe.core.beans.Grid;

public interface GridMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Grid record);

    int insertSelective(Grid record);

    Grid selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Grid record);

    int updateByPrimaryKey(Grid record);
}
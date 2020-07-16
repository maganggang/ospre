package com.safe.core.mapper;

import java.util.List;

import com.safe.core.beans.Position;

public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

	int insertSelectiveReturnKey(Position position);

	List<Position> findAll();

	Position selectOne(Position position);
}
package com.safe.core.service;

import java.util.List;

import com.safe.core.beans.Position;

public interface PositionService {

	List<Position> selectAll();

	Position selectByPrimaryKey(Integer id);

	Boolean deleteByPrimaryKey(Integer id);

	Position update(Position position);

	Position insert(Position position);

	Boolean insertWithCheck(List<Position> positions);

}

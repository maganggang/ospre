package com.safe.core.service;

import java.util.List;

import com.safe.core.beans.Area;
import com.safe.core.beans.Position;


public interface AreaService {
	List<Area> selectAll();

	Area selectByPrimaryKey(Integer id);

	Boolean deleteByPrimaryKey(Integer id);

	Area update(Area area);

	Area insert(Area area);

}

package com.safe.core.mapper;

import com.safe.core.beans.Area;
import com.safe.core.beans.AreaPositionRef;

public interface AreaPositionRefMapper {

	int insertBatch(Area area);
	 int deleteByPrimaryKey(Integer id);

	    int insert(AreaPositionRef record);

	    int insertSelective(AreaPositionRef record);

	    AreaPositionRef selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(AreaPositionRef record);

	    int updateByPrimaryKey(AreaPositionRef record);
		int deleteByAreaId(Integer areaId);
}
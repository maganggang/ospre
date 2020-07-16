package com.safe.core.service;

import com.safe.core.beans.Area;
public interface AreaPositionRefService {

	Boolean insertBatch(Area area);

	int deleteByAreaId(Integer id);
}

package com.safe.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safe.core.beans.Area;
import com.safe.core.mapper.AreaPositionRefMapper;
import com.safe.core.service.AreaPositionRefService;
@Service
public class AreaPositionRefServiceImpl implements AreaPositionRefService{
	@Autowired
	private AreaPositionRefMapper mapper;
	@Override
	public Boolean insertBatch(Area area) {
		if(area.getId()!=null&&area.getPositions()!=null&&area.getPositions().size()>0){
			int i=mapper.insertBatch(area);
			if(i>0){
				return true;
			}
		}
		return false;
	}
	@Override
	public int deleteByAreaId(Integer id) {
		return mapper.deleteByAreaId(id);
	}

}

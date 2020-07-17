package com.safe.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safe.core.beans.Area;
import com.safe.core.beans.Position;
import com.safe.core.mapper.AreaMapper;
import com.safe.core.service.AreaPositionRefService;
import com.safe.core.service.AreaService;
import com.safe.core.service.PositionService;
@Service
public class AreaServiceImpl implements AreaService{
	@Autowired
	private AreaMapper areaMapper;
	@Autowired
	private PositionService positionService;
	@Autowired
	private AreaPositionRefService areaPositionRefService;
	@Override
	public List<Area> selectAll(Area area) {
		return areaMapper.findAll(area);
	}

	@Override
	public Area selectByPrimaryKey(Integer id) {
		Area area=areaMapper.selectByPrimaryKey(id);
		List<Position> positions=positionService.selectByAreaId(id);
		area.setPositions(positions);
		return area;
	}

	@Override
	public Boolean deleteByPrimaryKey(Integer id) {
		areaPositionRefService.deleteByAreaId(id);
		int i=areaMapper.deleteByPrimaryKey(id);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public Area update(Area area) {
		int i=areaMapper.updateByPrimaryKeySelective(area);
		if(i>0){
			return area;
		}
		return null;
	}

	@Override
	public Area insert(Area area) {
	int i=areaMapper.insertSelectiveReturnKey(area);
	System.out.println(area.getId());
	//位置的检查插入包含主键
	positionService.insertWithCheck(area.getPositions());
	//关联表插入
	areaPositionRefService.insertBatch(area);
	if(i>0){
		return area;
	}
		return null;
	}

}

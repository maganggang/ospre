package com.safe.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safe.core.beans.Area;
import com.safe.core.beans.Position;
import com.safe.core.mapper.AreaMapper;
import com.safe.core.service.AreaService;
@Service
public class AreaServiceImpl implements AreaService{
	@Autowired
	private AreaMapper areaMapper;
	@Override
	public List<Area> selectAll(Area area) {
		return areaMapper.findAll(area);
	}

	@Override
	public Area selectByPrimaryKey(Integer id) {
		return areaMapper.selectByPrimaryKey(id);
	}

	@Override
	public Boolean deleteByPrimaryKey(Integer id) {
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
	int i=areaMapper.insertSelective(area);
	if(i>0){
		return area;
	}
		return null;
	}

}

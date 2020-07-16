package com.safe.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safe.core.beans.Company;
import com.safe.core.beans.Position;
import com.safe.core.mapper.CompanyMapper;
import com.safe.core.mapper.PositionMapper;
import com.safe.core.service.PositionService;
@Service
public class PositionServiceImpl implements PositionService{
	@Autowired
private PositionMapper positionMapper;
	public List<Position> selectAll() {
		return positionMapper.findAll();
	}

	public Position selectByPrimaryKey(Integer id) {
		return positionMapper.selectByPrimaryKey(id);
	}

	public Boolean deleteByPrimaryKey(Integer id) {
		int i=positionMapper.deleteByPrimaryKey(id);
		if(i>0){
			return true;
		}
		return false;
	}

	public Position update(Position position) {
	int i=positionMapper.updateByPrimaryKeySelective(position);
		if(i>0){
			return position;
		}
		return null;
	}

	public Position insert(Position position) {
		int i=positionMapper.insertSelectiveReturnKey(position);
		if(i>0){
			return position;
		}
		return null;
	}

	@Override
	public Boolean insertWithCheck(List<Position> positions) {
		// TODO 多个经纬度检查插入
		for (Position position:positions) {
			position=insertOrSelect(position);
		}
		return true;
	}

	private Position insertOrSelect(Position position) {
		Position position2=positionMapper.selectOne(position);
		if(position2==null||position2.getId()==null){
			positionMapper.insertSelectiveReturnKey(position);
		}else{
			position=position2;
		}
		return position;
	}

}

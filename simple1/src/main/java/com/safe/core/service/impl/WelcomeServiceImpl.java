package com.safe.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safe.core.beans.Welcome;
import com.safe.core.mapper.WelcomeMapper;
import com.safe.core.service.WelcomeService;
@Service
public class WelcomeServiceImpl implements WelcomeService {

	@Autowired
	private WelcomeMapper mapper;
	@Override
	public List<Welcome> selectAll(Welcome entity) {
		return mapper.selectAll(entity);
	}

	@Override
	public Welcome selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Welcome entity) {
		return mapper.insertSelective(entity);
	}

	@Override
	public Welcome selectOne(Welcome entity) {
		return mapper.selectOne(entity);
	}

	@Override
	public int update(Welcome entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public Welcome selectOldWelcome(Welcome welcome) {
		return mapper.selectOldWelcome(welcome);
	}

	@Override
	public int reflashStatus(int i) {
		return mapper.updateStatusByTime(i);
	}


}

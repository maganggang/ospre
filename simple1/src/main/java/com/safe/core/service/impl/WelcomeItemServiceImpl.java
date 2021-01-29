package com.safe.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safe.core.beans.WelcomeItem;
import com.safe.core.mapper.WelcomeItemMapper;
import com.safe.core.service.WelcomeItemService;
@Service
public class WelcomeItemServiceImpl implements WelcomeItemService {
	@Autowired
	private WelcomeItemMapper welcomeMapper;
	@Override
	public List<WelcomeItem> selectAll(WelcomeItem entity) {
		return welcomeMapper.selectAll(entity);
	}

	@Override
	public WelcomeItem selectByPrimaryKey(Integer id) {
		return welcomeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return welcomeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WelcomeItem entity) {
		return welcomeMapper.insertSelective(entity);
	}

	@Override
	public WelcomeItem selectOne(WelcomeItem entity) {
		return welcomeMapper.selectOne(entity);
	}

	@Override
	public int update(WelcomeItem entity) {
		return welcomeMapper.updateByPrimaryKeySelective(entity);
	}

}

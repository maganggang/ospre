package com.safe.core.service;

import java.util.List;

import com.safe.core.beans.WelcomeItem;

public interface WelcomeItemService {

	List<WelcomeItem> selectAll(WelcomeItem entity);

	WelcomeItem selectByPrimaryKey(Integer id);

	int deleteByPrimaryKey(Integer id);

	int insert(WelcomeItem entity);

	WelcomeItem selectOne(WelcomeItem entity);

	int update(WelcomeItem entity);

}

package com.safe.core.mapper;

import java.util.List;

import com.safe.core.beans.WelcomeItem;

public interface WelcomeItemMapper {
    int insert(WelcomeItem record);

    int insertSelective(WelcomeItem record);

	List<WelcomeItem> selectAll(WelcomeItem entity);

	int updateByPrimaryKeySelective(WelcomeItem entity);

	WelcomeItem selectOne(WelcomeItem entity);

	int deleteByPrimaryKey(Integer id);

	WelcomeItem selectByPrimaryKey(Integer id);
}
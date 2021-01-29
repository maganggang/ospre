package com.safe.core.mapper;

import java.util.List;

import com.safe.core.beans.Welcome;

public interface WelcomeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Welcome record);

    int insertSelective(Welcome record);

    Welcome selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Welcome record);

    int updateByPrimaryKey(Welcome record);

	List<Welcome> selectAll(Welcome entity);

	Welcome selectOne(Welcome entity);

	Welcome selectOldWelcome(Welcome welcome);

	int updateStatusByTime(int i);
}
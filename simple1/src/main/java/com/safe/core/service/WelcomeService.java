package com.safe.core.service;

import java.util.List;

import com.safe.core.beans.Welcome;

public interface WelcomeService {

	List<Welcome> selectAll(Welcome welcome);

	Welcome selectByPrimaryKey(Integer id);

	int deleteByPrimaryKey(Integer id);

	int update(Welcome welcome);

	Welcome selectOne(Welcome welcome);

	int insert(Welcome welcome);

	Welcome selectOldWelcome(Welcome welcome);

	int reflashStatus(int i);

}

package com.safe.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.safe.core.beans.User;

import config.InterceptAnnotation;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

	User selectByPrimaryKey(Integer id);

	int deleteByPrimaryKey(Integer id);

	int insertSelectiveReturnKey(User user);

	int updateByPrimaryKeySelective(User user);
	@InterceptAnnotation(flag=true)
	List<User> findAll(User user);

	User selectUserInfo(Integer userId);

	List<User> findAllByOrg(int orgId);

	Boolean deletePrimaryIds(List<String> ids);

	List<User> findAllByOrg(@Param("orgId")int orgId, @Param("user")User user);
}
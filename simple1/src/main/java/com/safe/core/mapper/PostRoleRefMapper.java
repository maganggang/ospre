package com.safe.core.mapper;

import java.util.List;

import com.safe.core.beans.PostRoleRef;
import com.safe.core.beans.Role;

public interface PostRoleRefMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PostRoleRef record);

    int insertSelective(PostRoleRef record);

    PostRoleRef selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PostRoleRef record);

    int updateByPrimaryKey(PostRoleRef record);

	List<Role> selectByPostId(Integer postId);

	int deleteByAccountId(Integer postId);

}
package com.safe.core.mapper;

import com.safe.core.beans.PostRoleRef;

public interface PostRoleRefMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PostRoleRef record);

    int insertSelective(PostRoleRef record);

    PostRoleRef selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PostRoleRef record);

    int updateByPrimaryKey(PostRoleRef record);
}
package com.safe.core.mapper;

import java.util.List;

import com.safe.core.base.bean.ListMapVo;
import com.safe.core.beans.Post;

public interface PostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);

	int insertSelectiveReturnKey(Post post);

	List<Post> findAll();

	List<ListMapVo> findTreeAll(Integer parentId);
}
package com.safe.core.service;

import java.util.List;

import com.safe.core.base.bean.ListMapVo;
import com.safe.core.beans.Post;

public interface PostService {

	List<Post> selectAll();

	Post selectByPrimaryKey(Integer id);

	Boolean deleteByPrimaryKey(Integer id);

	Post update(Post post);

	Post insert(Post post);

	List<ListMapVo> selectTreeAll(Integer parentId);

}

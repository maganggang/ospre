package com.safe.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safe.core.base.bean.ListMapVo;
import com.safe.core.beans.Post;
import com.safe.core.mapper.PostMapper;
import com.safe.core.service.PostService;
import com.safe.core.utils.TreeUtils;
@Service
public class PostServiceImpl implements PostService{
	@Autowired
private PostMapper postMapper;
	public List<Post> selectAll() {
		return postMapper.findAll();
	}

	public Post selectByPrimaryKey(Integer id) {
		return postMapper.selectByPrimaryKey(id);
	}

	public Boolean deleteByPrimaryKey(Integer id) {
		int i=postMapper.deleteByPrimaryKey(id);
		if(i>0){
			return true;
		}
		return false;
	}

	public Post update(Post post) {
	int i=postMapper.updateByPrimaryKeySelective(post);
		if(i>0){
			return post;
		}
		return null;
	}

	public Post insert(Post post) {
		int i=postMapper.insertSelectiveReturnKey(post);
		if(i>0){
			return post;
		}
		return null;
	}

	@Override
	public List<ListMapVo> selectTreeAll(Integer parentId) {
		List<ListMapVo>  list=postMapper.findTreeAll(parentId);
		TreeUtils.toTree(list, "orgId", "orgParentId");
		for (ListMapVo l:list) {
			if( l.get("children")!=null&&((List<ListMapVo>) l.get("children")).size()>0){
				l.put("open", true);
			}
		}
		return list;
	}

}

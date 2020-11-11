package com.safe.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safe.core.beans.AccountRoleRef;
import com.safe.core.beans.PostRoleRef;
import com.safe.core.mapper.PostRoleRefMapper;
import com.safe.core.service.PostRoleRefService;
@Service
public class PostRoleRefServiceImpl implements PostRoleRefService{
	@Autowired
private PostRoleRefMapper postRoleRefMapper;
	@Override
	public int insertList(Integer postId, List<Integer> postRoleIds) {
		postRoleRefMapper.deleteByAccountId(postId);
		int i=0;
		for(Integer postRoleId:postRoleIds) {
			PostRoleRef postRoleRef=new PostRoleRef();
			postRoleRef.setPostId(postId);
			postRoleRef.setRoleId(postRoleId);
			i+=postRoleRefMapper.insert(postRoleRef);
		}
		return i;
	}

}

package com.safe.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safe.core.beans.Account;
import com.safe.core.beans.Post;
import com.safe.core.beans.Role;
import com.safe.core.beans.User;
import com.safe.core.mapper.AccountMapper;
import com.safe.core.mapper.AccountRoleRefMapper;
import com.safe.core.mapper.AuthorityMapper;
import com.safe.core.mapper.PostMapper;
import com.safe.core.mapper.PostRoleRefMapper;
import com.safe.core.mapper.RoleMapper;
import com.safe.core.mapper.UserMapper;
import com.safe.core.service.RoleService;
@Service
public class RoleServiceImpl implements  RoleService{
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private AuthorityMapper authorityMapper;
	@Autowired
	private AccountRoleRefMapper accountRoleRefMapper;
	@Autowired
	private PostRoleRefMapper postRoleRefMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AccountMapper accountMapper;
	public List<Role> selectAll() {
		return roleMapper.findAll();
	}

	public Role selectByPrimaryKey(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	public Boolean deleteByPrimaryKey(Integer id) {
		int i=roleMapper.deleteByPrimaryKey(id);
		if(i>0){
			return true;
		}
		return false;
	}

	public Role update(Role role) {
	int i=roleMapper.updateByPrimaryKeySelective(role);
		if(i>0){
			return role;
		}
		return null;
	}

	public Role insert(Role role) {
		int i=roleMapper.insertSelectiveReturnKey(role);
		if(i>0){
			return role;
		}
		return null;
	}

	@Override
	public Role addRole(Role role) {
		int i=roleMapper.insertSelectiveReturnKey(role);
		if(i>0){
			return role;
		}
		return null;
	}

	@Override
	public Role selectAllByAccountId(Integer accountId) {
		Role role=new Role();
		//²éÑ¯ÕËºÅ½ÇÉ«
		List<Role> accountRoles=accountRoleRefMapper.selectByAccountId(accountId);
		role.setAccountRoles(accountRoles);
		//
		Account account=accountMapper.selectByPrimaryKey(accountId);
		if(account!=null&&account.getUserId()!=null) {
			User user=userMapper.selectByPrimaryKey(account.getUserId());
			if(user!=null&&user.getPostId()!=null) {
				List<Role> postRoles=postRoleRefMapper.selectByPostId(user.getPostId());
				role.setPostRoles(postRoles);
			}
		}
		return role;
	}

	@Override
	public Role selectByPostId(Integer postId) {
		Role role=new Role();
		List<Role> postRoles=postRoleRefMapper.selectByPostId(postId);
		role.setPostRoles(postRoles);
		return role;
	}


}

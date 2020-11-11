package com.safe.core.service;

import java.util.List;

import com.safe.core.beans.Role;

public interface RoleService {

	List<Role> selectAll();

	Role selectByPrimaryKey(Integer id);

	Boolean deleteByPrimaryKey(Integer id);

	Role update(Role role);

	Role insert(Role role);

	Role addRole(Role role);

	Role selectAllByAccountId(Integer accountId);

	Role selectByPostId(Integer postId);

}

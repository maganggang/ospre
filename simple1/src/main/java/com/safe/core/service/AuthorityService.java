package com.safe.core.service;

import java.util.List;

import com.safe.core.beans.Account;
import com.safe.core.beans.Authority;
import com.safe.core.beans.Menu;

public interface AuthorityService {

	List<Menu> selectAll();

	Authority selectByPrimaryKey(Integer id);

	Boolean deleteByPrimaryKey(Integer id);

	Authority update(Authority authority);

	Authority insert(Authority authority);

	List<Menu> selectAuthority(Integer accountId);

	List<Menu> selectAuthorityById(Integer i, Integer parentId);

	List<Menu> selectAuthorityByRoleId(Integer roleId);

	List<Integer> selectPermissIds(Integer roleId);

	Authority insertList(Authority authos);

}

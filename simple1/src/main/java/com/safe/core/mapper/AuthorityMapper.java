package com.safe.core.mapper;

import java.util.List;

import com.safe.core.beans.Authority;
import com.safe.core.beans.Menu;

public interface AuthorityMapper {
    int insert(Authority record);

    int insertSelective(Authority record);

	int insertSelectiveReturnKey(Authority authority);

	List<Menu> findAll(Authority authority);

	Authority selectByPrimaryKey(Integer id);

	int deleteByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Authority authority);

	List<Integer> selectPermissIds(Integer roleId);

	int deleteByRoleId(Integer roleId);
}
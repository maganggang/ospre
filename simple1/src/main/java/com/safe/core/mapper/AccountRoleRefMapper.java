package com.safe.core.mapper;

import java.util.List;

import com.safe.core.beans.AccountRoleRef;
import com.safe.core.beans.Role;

public interface AccountRoleRefMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountRoleRef record);

    int insertSelective(AccountRoleRef record);

    AccountRoleRef selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountRoleRef record);

    int updateByPrimaryKey(AccountRoleRef record);

	List<Role> selectByAccountId(Integer accountId);
}
package com.safe.core.mapper;

import com.safe.core.beans.AccountRoleRef;

public interface AccountRoleRefMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountRoleRef record);

    int insertSelective(AccountRoleRef record);

    AccountRoleRef selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountRoleRef record);

    int updateByPrimaryKey(AccountRoleRef record);
}
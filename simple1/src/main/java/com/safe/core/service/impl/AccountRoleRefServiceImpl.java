package com.safe.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safe.core.beans.AccountRoleRef;
import com.safe.core.mapper.AccountRoleRefMapper;
import com.safe.core.service.AccountRoleRefService;
@Service
public class AccountRoleRefServiceImpl  implements AccountRoleRefService{
	@Autowired
	private AccountRoleRefMapper accountRoleRefMapper;
	@Override
	public int insertList(Integer accountId, List<Integer> accountRoleIds) {
		accountRoleRefMapper.deleteByAccountId(accountId);
		int i=0;
		for(Integer roleId:accountRoleIds) {
			AccountRoleRef accountRoleRef=new AccountRoleRef();
			accountRoleRef.setAccountId(accountId);
			accountRoleRef.setRoleId(roleId);
			i+=accountRoleRefMapper.insert(accountRoleRef);
		}
		return i;
	}

}

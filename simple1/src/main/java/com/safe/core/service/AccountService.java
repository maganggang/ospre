package com.safe.core.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.safe.core.beans.Account;

public interface AccountService {
	public List<Account> selectAll();

	public Account select(Integer id);

	public Boolean delete(Integer id);

	public Account update(Account account);

	public Account insert(Account account);

	public Account login(String username,String password);


	public List<Integer> getAllRoleIds(Integer accountId);
}

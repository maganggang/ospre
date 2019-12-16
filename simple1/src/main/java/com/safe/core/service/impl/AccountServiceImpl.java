package com.safe.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safe.core.beans.Account;
import com.safe.core.mapper.AccountMapper;
import com.safe.core.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
private AccountMapper accountMapper;

	public List<Account> selectAll() {
		return accountMapper.findAll();
	}

	public Account selectByPrimaryKey(Integer id) {
		return accountMapper.selectByPrimaryKey(id);
	}

	public Boolean deleteAccount(Integer id) {
		int i=accountMapper.deleteByPrimaryKey(id);
		if(i>0){
			return true;
		}
		return false;
	}

	public Account updateAccount(Account account) {
		int i=accountMapper.updateByPrimaryKeySelective(account);
		if(i>0){
			return account;
		}
		return null;
	}

	public Account insertAccount(Account account) {
		int i=accountMapper.insertSelectiveReturnKey(account);
		if(i>0){
			return account;
		}
		return null;
	}

	public Account login(String username, String password) {
		Account a=accountMapper.selectAccount(username,password);
		if(a!=null&&a.getId()!=null){
			return a;
		}
		return null;
	}


}

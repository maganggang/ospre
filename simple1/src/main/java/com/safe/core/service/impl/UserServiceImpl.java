package com.safe.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safe.core.beans.Account;
import com.safe.core.beans.User;
import com.safe.core.mapper.AccountMapper;
import com.safe.core.mapper.UserMapper;
import com.safe.core.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private UserMapper userMapper;
	public List<User> selectAll(User user) {
		return userMapper.findAll(user);
	}

	public User select(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	public Boolean deleteByPrimaryKey(Integer id) {
		int i=userMapper.deleteByPrimaryKey(id);
		if(i>0){
			return true;
		}
		return false;
	}

	public User update(User user) {
		int i=userMapper.updateByPrimaryKeySelective(user);
		if(i>0){
			return user;
		}
		return null;
	}

	public User insert(User user) {
		int i=userMapper.insertSelectiveReturnKey(user);
		if(i>0){
			return user;
		}
		return null;
	}

	@Override
	public User selectUserInfo(Integer userId) {
		return userMapper.selectUserInfo(userId);
	}

	@Override
	public List<User> findAllByOrg(int orgId,User user) {
		return userMapper.findAllByOrg(orgId,user);
	}

	@Override
	public Boolean deleteList(List<String> ids) {
		return userMapper.deletePrimaryIds(ids);
	}

	@Override
	public Boolean delete(Integer id) {
		Account account=accountMapper.selectByUserId(id);
		if(account!=null){
			accountMapper.deleteByPrimaryKey(account.getId());
		}
		int i=userMapper.deleteByPrimaryKey(id);
		if(i>0){
			return true;
		}
		return false;
	}

}

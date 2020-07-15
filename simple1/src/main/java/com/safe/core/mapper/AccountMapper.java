package com.safe.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.safe.core.beans.Account;

import config.InterceptAnnotation;
public interface AccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
    
	List<Account> findAll(Account account);
	
	int insertSelectiveReturnKey(Account account);
/**
 * 验证用户密码
* @Title: selectAccount 
* @param username
* @param password
* @return
* @return: Account 
* @author mgg
* @date 2020年5月22日
 */
	Account selectAccount(@Param("username")String username,@Param("password") String password);
/**
 * 账号id获取角色ids
* @Title: selectAllRoleId 
* @param accountId
* @return
* @return: List<Integer> 
* @author mgg
* @date 2020年5月22日
 */
	List<Integer> selectAllRoleId(Integer accountId);
	/**
	 * 用户id获取账号信息
	* @Title: selectByUserId 
	* @param userId
	* @return
	* @return: Account 
	* @author mgg
	* @date 2020年5月22日
	 */
	Account selectByUserId(Integer userId);
}
package com.safe.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.safe.core.beans.Account;

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
 * ��֤�û�����
* @Title: selectAccount 
* @param username
* @param password
* @return
* @return: Account 
* @author mgg
* @date 2020��5��22��
 */
	Account selectAccount(@Param("username")String username,@Param("password") String password);
/**
 * �˺�id��ȡ��ɫids
* @Title: selectAllRoleId 
* @param accountId
* @return
* @return: List<Integer> 
* @author mgg
* @date 2020��5��22��
 */
	List<Integer> selectAllRoleId(Integer accountId);
	/**
	 * �û�id��ȡ�˺���Ϣ
	* @Title: selectByUserId 
	* @param userId
	* @return
	* @return: Account 
	* @author mgg
	* @date 2020��5��22��
	 */
	Account selectByUserId(Integer userId);
}
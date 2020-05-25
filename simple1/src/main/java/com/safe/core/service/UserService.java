package com.safe.core.service;

import java.util.List;

import com.safe.core.beans.User;

public interface UserService {

	List<User> selectAll(User user);

	User select(Integer id);

	Boolean deleteByPrimaryKey(Integer id);

	User update(User user);

	User insert(User user);

	User selectUserInfo(Integer userId);

	List<User> findAllByOrg(int orgId, User user);

	Boolean deleteList(List<String> ids);
/**
 * 删除用户 同时删除账号
* @Title: deleteUser 
* @param id
* @return
* @return: Boolean 
* @author mgg
* @date 2020年5月22日
 */
	Boolean delete(Integer id);

}

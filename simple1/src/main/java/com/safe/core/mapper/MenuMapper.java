package com.safe.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.safe.core.beans.Account;
import com.safe.core.beans.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    int insertSelectiveReturnKey(Menu menu);
	List<Menu> findAll();
	List<Menu> selectMyMenu(@Param("accountId")Integer accountId,@Param("parentId")Integer parentId);
	/**
	 * 账号id获取权限
	* @Title: selectMyAuth 
	* @param accountId
	* @return
	* @return: Account 
	* @author mgg
	* @date 2020年5月22日
	 */
	List<Menu> selectMyAuth(@Param("accountId")Integer accountId);
}
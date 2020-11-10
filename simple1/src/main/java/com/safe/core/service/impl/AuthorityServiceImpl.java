package com.safe.core.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safe.core.base.bean.ListMapVo;
import com.safe.core.beans.Account;
import com.safe.core.beans.Authority;
import com.safe.core.beans.Menu;
import com.safe.core.mapper.AccountMapper;
import com.safe.core.mapper.AuthorityMapper;
import com.safe.core.mapper.MenuMapper;
import com.safe.core.service.AuthorityService;
import com.safe.core.utils.TreeUtils;
@Service
public class AuthorityServiceImpl implements AuthorityService{
	@Autowired
	private AuthorityMapper authorityMapper;
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private MenuMapper moduleMapper;
		public List<Menu> selectAll() {
			return authorityMapper.findAll(null);
		}

		public Authority selectByPrimaryKey(Integer id) {
			return authorityMapper.selectByPrimaryKey(id);
		}

		public Boolean deleteByPrimaryKey(Integer id) {
			int i=authorityMapper.deleteByPrimaryKey(id);
			if(i>0){
				return true;
			}
			return false;
		}

		public Authority update(Authority authority) {
		int i=authorityMapper.updateByPrimaryKeySelective(authority);
			if(i>0){
				return authority;
			}
			return null;
		}

		public Authority insert(Authority authority) {
			int i=authorityMapper.insertSelectiveReturnKey(authority);
			if(i>0){
				return authority;
			}
			return null;
		}

		public List<Menu> selectAuthority(Integer id) {
			List<Menu> menus=menuMapper.selectMyAuth(id);
			//list=TreeUtils.toTree(list, "id", "parentId");
			//System.out.println(TreeUtils.toTree(list, "id", "parentId"));
			if(menus!=null&&menus.size()>0){
				TreeUtils.MenutoTree(menus);
			}
			return menus;
		}

		@Override
		public List<Menu> selectAuthorityById(Integer accountId, Integer parentId) {
			List<Menu> list=moduleMapper.selectMyMenu(accountId, parentId);
			return list;
		}

		@Override
		public List<Menu> selectAuthorityByRoleId(Integer roleId) {
			Authority authority=new Authority();
			authority.setRoleId(roleId);
			List<Menu> menus=authorityMapper.findAll(authority);
			if(menus!=null&&menus.size()>0){
				TreeUtils.MenutoTree(menus);
			}
			return menus;
		}

		@Override
		public List<Integer> selectPermissIds(Integer roleId) {
		
			return authorityMapper.selectPermissIds(roleId);
		}

		@Override
		public Authority insertList(Authority authos) {
			authorityMapper.deleteByRoleId(authos.getRoleId());
			for(Integer permissionId:authos.getPermissionIds()) {
				Authority authority=new Authority();
				authority.setPermissionId(permissionId);
				authority.setRoleId(authos.getRoleId());
				authority.setCreatetime(new Date());
				authorityMapper.insertSelective(authority);
			}
			return authos;
		}
}

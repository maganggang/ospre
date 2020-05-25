package com.safe.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safe.core.beans.Menu;
import com.safe.core.mapper.MenuMapper;
import com.safe.core.service.MenuService;
@Service
public class MenuServiceImpl implements MenuService{
	@Autowired
	private MenuMapper menuMapper;
	@Override
	public List<Menu> selectAll() {
		return menuMapper.findAll();
	}

	@Override
	public Menu select(Integer id) {
		return menuMapper.selectByPrimaryKey(id);
	}

	@Override
	public Boolean delete(Integer id) {
		int i= menuMapper.deleteByPrimaryKey(id);
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Menu update(Menu menu) {
		int i= menuMapper.updateByPrimaryKeySelective(menu);
		if(i>0){
			return menu;
		}else{
			return null;
		}
	}

	@Override
	public Menu insert(Menu menu) {
		int i= menuMapper.insertSelectiveReturnKey(menu);
		if(i>0){
			return menu;
		}else{
			return null;
		}
	}

}

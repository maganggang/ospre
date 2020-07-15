package com.safe.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safe.core.beans.Company;
import com.safe.core.beans.Item;
import com.safe.core.mapper.ItemMapper;
import com.safe.core.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
private ItemMapper itemMapper;
	public List<Item> selectAll() {
		return itemMapper.findAll();
	}

	public Item selectByPrimaryKey(Integer id) {
		return itemMapper.selectByPrimaryKey(id);
	}

	public Boolean deleteByPrimaryKey(Integer id) {
		int i=itemMapper.deleteByPrimaryKey(id);
		if(i>0){
			return true;
		}
		return false;
	}

	public Item update(Item item) {
		int i=itemMapper.updateByPrimaryKeySelective(item);
		if(i>0){
			return item;
		}
		return null;
	}
@Override
	public Item insert(Item item) {
		int i=itemMapper.insertSelectiveReturnKey(item);
		if(i>0){
			return item;
		}
		return null;
	}
	/**
	 * 查询创建的没被挂关系的项目
	 */
		@Override
		public List<Item> selectNoOrg(Item item) {
			return itemMapper.selectNoOrg(item);
		}
}

package com.safe.core.service;

import java.util.List;

import com.safe.core.beans.Item;

public interface ItemService {

	List<Item> selectAll(Item item);

	Item selectByPrimaryKey(Integer id);

	Boolean deleteByPrimaryKey(Integer id);

	Item update(Item item);

	Item insert(Item item);

	List<Item> selectNoOrg(Item item);

}

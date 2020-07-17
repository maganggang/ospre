package com.safe.core.mapper;

import java.util.List;

import com.safe.core.beans.Item;

public interface ItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

	int insertSelectiveReturnKey(Item item);

	List<Item> findAll(Item item);

	List<Item> selectNoOrg(Item item);
}
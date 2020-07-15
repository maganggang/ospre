package com.safe.core.mapper;

import java.util.List;

import com.safe.core.beans.Dict;

public interface DictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dict record);

    int insertSelective(Dict record);

    Dict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);

	List<Dict> findAll(Dict dict);

	int insertSelectiveReturnKey(Dict dict);
}
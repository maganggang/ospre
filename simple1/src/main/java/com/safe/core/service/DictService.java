package com.safe.core.service;

import java.util.List;

import com.safe.core.beans.Dict;

public interface DictService {

	List<Dict> selectAll(Dict dict);

	Dict selectByPrimaryKey(Integer id);

	Dict update(Dict dict);

	Dict insert(Dict dict);

	Boolean deleteByPrimaryKey(Integer id);

}

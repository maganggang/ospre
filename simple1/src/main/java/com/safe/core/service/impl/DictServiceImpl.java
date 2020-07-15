package com.safe.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safe.core.beans.Dict;
import com.safe.core.mapper.DictMapper;
import com.safe.core.service.DictService;
@Service
public class DictServiceImpl implements DictService{
	@Autowired
private DictMapper dictMapper;
	public List<Dict> selectAll(Dict dict) {
		return dictMapper.findAll(dict);
	}

	public Dict selectByPrimaryKey(Integer id) {
		return dictMapper.selectByPrimaryKey(id);
	}

	public Dict update(Dict dict) {
		int i=dictMapper.updateByPrimaryKey(dict);
		if(i>0){
			return dict;
		}
		return null;
	}

	public Dict insert(Dict dict) {
		int i=dictMapper.insertSelectiveReturnKey(dict);
		if(i>0){
			return dict;
		}
		return null;
	}

	public Boolean deleteByPrimaryKey(Integer id) {
		int i=dictMapper.deleteByPrimaryKey(id);
		if(i>0){
			return true;
		}
		return false;
	}

}

package com.safe.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safe.core.base.bean.ListMapVo;
import com.safe.core.beans.Organization;
import com.safe.core.mapper.OrganizationMapper;
import com.safe.core.service.OrganizationService;
import com.safe.core.utils.TreeUtils;
@Service
public class OrganizationServiceImpl implements OrganizationService{
	@Autowired
private OrganizationMapper organizationMapper;
	
	public List<Organization> selectAll(Organization organization) {
		return organizationMapper.findAll(organization);
	}

	public Organization selectByPrimaryKey(Integer id) {
		return organizationMapper.selectByPrimaryKey(id);
	}

	public Boolean deleteByPrimaryKey(Integer id) {
		int i=organizationMapper.deleteByPrimaryKey(id);
		if(i>0){
			return true;
		}
		return false;
	}

	public Organization update(Organization org) {
		int i=organizationMapper.updateByPrimaryKeySelective(org);
		if(i>0){
			return org;
		}
		return null;
	}

	public Organization insert(Organization org) {
		int i=organizationMapper.insertSelectiveReturnKey(org);
		if(i>0){
			return org;
		}
		return null;
	}

	@Override
	public List<ListMapVo> selectTreeAll(Integer parentId) {
		List<ListMapVo>  list=organizationMapper.findTreeAll(parentId);
		TreeUtils.toTree(list, "id", "parentId");
		for (ListMapVo l:list) {
			if( l.get("children")!=null&&((List<ListMapVo>) l.get("children")).size()>0){
				l.put("open", true);
			}
		}
		return list;
	}

}

package com.safe.core.service;

import java.util.List;

import com.safe.core.base.bean.ListMapVo;
import com.safe.core.beans.Organization;

public interface OrganizationService {

	List<Organization> selectAll(Organization organization);

	Organization selectByPrimaryKey(Integer id);

	Boolean deleteByPrimaryKey(Integer id);

	Organization update(Organization org);

	Organization insert(Organization org);

	List<ListMapVo> selectTreeAll(Integer parentId);

	Boolean deleteList(List<Integer> ids);

}

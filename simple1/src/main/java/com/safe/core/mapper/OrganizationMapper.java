package com.safe.core.mapper;

import java.util.List;

import com.safe.core.base.bean.ListMapVo;
import com.safe.core.beans.Organization;

public interface OrganizationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);

	int insertSelectiveReturnKey(Organization org);

	List<Organization> findAll(Organization organization);

	List<ListMapVo> findTreeAll(Integer parentId);
}
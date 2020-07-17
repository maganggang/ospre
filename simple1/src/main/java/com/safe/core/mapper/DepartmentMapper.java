package com.safe.core.mapper;

import java.util.List;

import com.safe.core.beans.Department;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

	int insertSelectiveReturnKey(Department depart);

	List<Department> findAll(Department department);

	List<Department> selectNoOrg(Department department);
}
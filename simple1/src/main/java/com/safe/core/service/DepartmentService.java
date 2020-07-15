package com.safe.core.service;

import java.util.List;

import com.safe.core.beans.Department;

public interface DepartmentService {

	List<Department> selectAll();

	Department selectByPrimaryKey(Integer id);

	Boolean deleteByPrimaryKey(Integer id);

	Department update(Department dept);

	Department insert(Department dept);

	List<Department> selectNoOrg(Department department);

}

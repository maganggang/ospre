package com.safe.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safe.core.beans.Company;
import com.safe.core.beans.Department;
import com.safe.core.mapper.DepartmentMapper;
import com.safe.core.service.DepartmentService;
@Service
public class DepartmentServiceImpl implements  DepartmentService{
	@Autowired
private DepartmentMapper departmentMapper;
	public List<Department> selectAll() {
		return departmentMapper.findAll();
	}

	public Department selectByPrimaryKey(Integer id) {
		return departmentMapper.selectByPrimaryKey(id);
	}

	public Boolean deleteByPrimaryKey(Integer id) {
		int i=departmentMapper.deleteByPrimaryKey(id);
		if(i>0){
			return true;
		}
		return false;
	}

	public Department update(Department depart) {
		int i=departmentMapper.updateByPrimaryKeySelective(depart);
		if(i>0){
			return depart;
		}
		return null;
	}

	public Department insert(Department depart) {
		int i=departmentMapper.insertSelectiveReturnKey(depart);
		if(i>0){
			return depart;
		}
		return null;
	}
	/**
	 * 查询创建的没被挂关系的部门
	 */
		@Override
		public List<Department> selectNoOrg(Department department) {
			return departmentMapper.selectNoOrg(department);
		}
}

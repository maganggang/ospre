package com.safe.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safe.core.beans.Company;
import com.safe.core.mapper.CompanyMapper;
import com.safe.core.service.CompanyService;
@Service
public class CompanyServiceImpl implements CompanyService{
	@Autowired
private CompanyMapper companyMapper;
	public List<Company> selectAll() {
		return companyMapper.findAll();
	}

	public Company selectByPrimaryKey(Integer id) {
		return companyMapper.selectByPrimaryKey(id);
	}

	public Boolean deleteByPrimaryKey(Integer id) {
		int i=companyMapper.deleteByPrimaryKey(id);
		if(i>0){
			return true;
		}
		return false;
	}

	public Company update(Company company) {
	int i=companyMapper.updateByPrimaryKeySelective(company);
		if(i>0){
			return company;
		}
		return null;
	}

	public Company insert(Company company) {
		int i=companyMapper.insertSelectiveReturnKey(company);
		if(i>0){
			return company;
		}
		return null;
	}
/**
 * 查询创建的没被挂关系的公司
 */
	@Override
	public List<Company> selectNoOrg(Company company) {
		return companyMapper.selectNoOrg(company);
	}

}

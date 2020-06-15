package com.safe.core.service;

import java.util.List;

import com.safe.core.beans.Company;

public interface CompanyService {

	List<Company> selectAll();

	Company selectByPrimaryKey(Integer id);

	Boolean deleteByPrimaryKey(Integer id);

	Company update(Company company);

	Company insert(Company company);

	List<Company> selectNoOrg(Company company);

}

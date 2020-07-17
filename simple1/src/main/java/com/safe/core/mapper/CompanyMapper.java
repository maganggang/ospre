package com.safe.core.mapper;

import java.util.List;

import com.safe.core.beans.Company;

public interface CompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

	List<Company> findAll(Company company);

	int insertSelectiveReturnKey(Company company);

	List<Company> selectNoOrg(Company company);
}
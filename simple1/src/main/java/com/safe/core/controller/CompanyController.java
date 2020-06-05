package com.safe.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.safe.core.base.bean.ResultBean;
import com.safe.core.beans.Account;
import com.safe.core.beans.Company;
import com.safe.core.service.CompanyService;

@Controller
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	@RequestMapping("/all")
	@ResponseBody
	public ResultBean<Company> allCompany(Page<Company> page, Account account){
		ResultBean<Company> b = new ResultBean<Company>();
		page = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		List<Company> result = companyService.selectAll();
		b.setData(result);
		b.setCount(page.getTotal());
		return b;
	}
	@RequestMapping("/company/{id}")
	@ResponseBody
	public Company findOne(@PathVariable Integer id){
		return companyService.selectByPrimaryKey(id);
	}
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Boolean deleteOne(@PathVariable Integer id){
		return companyService.deleteByPrimaryKey(id);
	}
	@RequestMapping("/update")
	@ResponseBody
	public Company updateOne(Company company){
		return companyService.update(company);
	}
	@RequestMapping("/insert")
	@ResponseBody
	public Company createOne(Company company){
		return companyService.insert(company);
	}
}

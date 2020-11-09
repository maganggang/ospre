package com.safe.core.controller;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.safe.core.base.bean.ResultBean;
import com.safe.core.beans.Account;
import com.safe.core.beans.Company;
import com.safe.core.filter.SessionListener;
import com.safe.core.service.CompanyService;
import com.safe.core.utils.BaseUserInfo;

@Controller
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	@RequestMapping("/all")
	@ResponseBody
	public ResultBean<Company> allCompany(Page<Company> page, Company company){
		ResultBean<Company> b = new ResultBean<Company>();
		page = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		List<Company> result = companyService.selectAll(company);
		b.setData(result);
		b.setCount(page.getTotal());
		return b;
	}
	/**
	 * 查询我创建的没被挂关系的公司
	* @Title: selectCompany 
	* @param company
	* @return
	* @return: List<Company> 
	* @author mgg
	* @date 2020年6月15日
	 */
	@RequestMapping("/creator")
	@ResponseBody
	public List<Company> selectNoOrg(HttpServletRequest req, Company company){
		HttpSession session = req.getSession();
		if(company.getCreatorId()==null){
			BaseUserInfo userInfo =(BaseUserInfo) session.getAttribute("userInfo");
			// 清除在线用户后，更新map,替换map sessionid
			SessionListener.sessionContext.getSessionMap().remove(userInfo.getName());
			company.setCreatorId(userInfo.getId());
		}
		List<Company> result = companyService.selectNoOrg(company);
		return result;
	}
	@RequestMapping("/view/{id}")
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

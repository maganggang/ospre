package com.safe.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.safe.core.beans.Department;
import com.safe.core.filter.SessionListener;
import com.safe.core.service.DepartmentService;
import com.safe.core.utils.BaseUserInfo;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private DepartmentService departService;
	@RequestMapping("/all")
	@ResponseBody
	public ResultBean<Department> allDepartment(Page<Department> page, Department department){
		ResultBean<Department> b = new ResultBean<Department>();
		page = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		List<Department> result = departService.selectAll();
		b.setData(result);
		b.setCount(page.getTotal());
		return b;
	}
	/**
	 * 查询
	* @Title: selectCompany 
	* @param req
	* @param department
	* @return
	* @return: List<Department> 
	* @author mgg
	* @date 2020年6月18日
	 */
	@RequestMapping("/creator")
	@ResponseBody
	public List<Department> selectNoOrg(HttpServletRequest req, Department department){
		HttpSession session = req.getSession();
		if(department.getCreatorId()==null){
			BaseUserInfo userInfo =(BaseUserInfo) session.getAttribute("userInfo");
			// 清除在线用户后，更新map,替换map sessionid
			SessionListener.sessionContext.getSessionMap().remove(userInfo.getName());
			department.setCreatorId(userInfo.getId());
		}
		List<Department> result = departService.selectNoOrg(department);
		return result;
	}
	@RequestMapping("/dept/{id}")
	@ResponseBody
	public Department findOne(@PathVariable Integer id){
		return departService.selectByPrimaryKey(id);
	}
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Boolean deleteOne(@PathVariable Integer id){
		return departService.deleteByPrimaryKey(id);
	}
	@RequestMapping("/update")
	@ResponseBody
	public Department updateOne(Department dept){
		return departService.update(dept);
	}
	@RequestMapping("/insert")
	@ResponseBody
	public Department createOne(Department dept){
		return departService.insert(dept);
	}
}

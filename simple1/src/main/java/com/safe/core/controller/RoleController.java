package com.safe.core.controller;

import java.util.ArrayList;
import java.util.Collections;
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
import com.safe.core.beans.Permission;
import com.safe.core.beans.Role;
import com.safe.core.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@RequestMapping("/all")
	@ResponseBody
	public ResultBean<Role> allRole(Page<Role> page,Role role){
		page = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		ResultBean<Role> b=new ResultBean<Role>();
		List<Role> result= roleService.selectAll();
		b.setData(result);
		b.setCount(page.getTotal());
		return b;
	}
	@RequestMapping("/role/{id}")
	@ResponseBody
	public Role findOne(@PathVariable Integer id){
		return roleService.selectByPrimaryKey(id);
	}
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Boolean deleteOne(@PathVariable Integer id){
		return roleService.deleteByPrimaryKey(id);
	}
	@RequestMapping("/update")
	@ResponseBody
	public Role updateOne(Role role){
		return roleService.update(role);
	}
	@RequestMapping("/add")
	@ResponseBody
	public Role addList(Role role){
		return roleService.addRole(role);
	}
	@RequestMapping("/insert")
	@ResponseBody
	public Role createOne(Role role){
		return roleService.insert(role);
	}
	
}

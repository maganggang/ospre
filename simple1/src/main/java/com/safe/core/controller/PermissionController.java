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
import com.safe.core.beans.Permission;
import com.safe.core.beans.Post;
import com.safe.core.beans.Role;
import com.safe.core.service.PermissionService;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	@Autowired
	private PermissionService permissionService;
	@RequestMapping("/all")
	@ResponseBody
	public ResultBean<Permission> allPermission(Page<Permission> page,Permission permission){
		ResultBean<Permission> b=new ResultBean<Permission>();
		page=PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		List<Permission> sList= permissionService.selectAll();
		b.setData(sList);
		b.setCount(page.getTotal());
		return b;
	}
	@RequestMapping("/permission/{id}")
	@ResponseBody
	public Permission findOne(@PathVariable Integer id){
		return permissionService.selectByPrimaryKey(id);
	}
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Boolean deleteOne(@PathVariable Integer id){
		return permissionService.deleteByPrimaryKey(id);
	}
	@RequestMapping("/update")
	@ResponseBody
	public Permission updateOne(Permission permission){
		return permissionService.update(permission);
	}
	@RequestMapping("/insert")
	@ResponseBody
	public Permission createOne(Permission permission){
		return permissionService.insert(permission);
	}
}

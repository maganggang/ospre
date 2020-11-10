package com.safe.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.safe.core.base.bean.ResultBean;
import com.safe.core.beans.Account;
import com.safe.core.beans.Authority;
import com.safe.core.beans.Menu;
import com.safe.core.service.AuthorityService;
import com.safe.core.utils.BaseUserInfo;

@Controller
@RequestMapping("/authority")
public class AuthorityController {
	@Autowired
	private AuthorityService authorityService;
	@RequestMapping("/all")
	@ResponseBody
	public ResultBean<Menu> allAuthority(){
		ResultBean<Menu> b=new ResultBean<Menu>();
		List<Menu>  result=authorityService.selectAll();
		b.setData(result);
		return b;
	}
	@RequestMapping("/authority/{id}")
	@ResponseBody
	public Authority findOne(@PathVariable Integer id){
		return authorityService.selectByPrimaryKey(id);
	}
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Boolean deleteOne(@PathVariable Integer id){
		return authorityService.deleteByPrimaryKey(id);
	}
	@RequestMapping("/update")
	@ResponseBody
	public Authority updateOne(Authority authority){
		return authorityService.update(authority);
	}
	@RequestMapping("/insert")
	@ResponseBody
	public Authority createOne(Authority authority){
		return authorityService.insert(authority);
	}
	@RequestMapping("/add")
	@ResponseBody
	public Authority createList(@RequestBody Authority authos){
		//批量的菜单按钮Id进行插入 默认会删除不在里面的
		return authorityService.insertList(authos);
	}
	@RequestMapping("/selectMyAuth")
	@ResponseBody
	public List<Menu> SelectOne(HttpServletRequest req){
		if(req.getSession().getAttribute("userInfo")!=null){
			BaseUserInfo userInfo =(BaseUserInfo) req.getSession().getAttribute("userInfo");
			Integer i=userInfo.getId();
			List<Menu> result=authorityService.selectAuthority(i);
			return result;
		}
		return null;
	}
	@RequestMapping("/selectMyAuthById")
	@ResponseBody
	public List<Menu> selectMyFirstAuth(HttpServletRequest req,Integer parentId){
		if(req.getSession().getAttribute("accountId")!=null){
			Integer i=Integer.parseInt(req.getSession().getAttribute("accountId").toString());
			List<Menu> result=authorityService.selectAuthorityById(i, parentId);
			return result;
		}
		return null;
	}
	@RequestMapping("/selectByRoleId")
	@ResponseBody
	public List<Menu> selectAuthByRoleId(Integer roleId){
		List<Menu> result=authorityService.selectAuthorityByRoleId(roleId);
		return result;
	}
	@RequestMapping("/selectPermissIds")
	@ResponseBody
	public List<Integer> selectPermissIds(Integer roleId){
		List<Integer> result=authorityService.selectPermissIds(roleId);
		return result;
	}
}

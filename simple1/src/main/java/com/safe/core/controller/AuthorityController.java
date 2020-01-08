package com.safe.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.safe.core.beans.Account;
import com.safe.core.beans.Authority;
import com.safe.core.beans.ResultBean;
import com.safe.core.service.AuthorityService;

@Controller
@RequestMapping("/authority")
public class AuthorityController {
	@Autowired
	private AuthorityService authorityService;
	@RequestMapping("/all")
	@ResponseBody
	public ResultBean<Authority> allAuthority(){
		ResultBean<Authority> b=new ResultBean<Authority>();
		List<Authority>  result=authorityService.selectAll();
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
	@RequestMapping("/selectMyAuth")
	@ResponseBody
	public Account SelectOne(HttpServletRequest req){
		if(req.getSession().getAttribute("accountId")!=null){
			Integer i=Integer.parseInt(req.getSession().getAttribute("accountId").toString());
			Account result=authorityService.selectAuthority(i);
			return result;
		}
		return null;
	}
	
}

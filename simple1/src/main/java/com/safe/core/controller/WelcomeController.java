package com.safe.core.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.safe.core.base.bean.ResultBean;
import com.safe.core.beans.Account;
import com.safe.core.beans.Welcome;
import com.safe.core.service.WelcomeService;
import com.safe.core.utils.BaseUserInfo;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {
	@Autowired
	private WelcomeService service;
	
	@RequestMapping("/all")
	@ResponseBody
	public ResultBean<Welcome> allCompany(Page<Welcome> page, Welcome welcome){
		ResultBean<Welcome> b = new ResultBean<Welcome>();
		//预约状态刷新 为用户查看便更新
		service.reflashStatus(30);
		page = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		List<Welcome> result = service.selectAll(welcome);
		b.setData(result);
		b.setCount(page.getTotal());
		return b;
	}
	@RequestMapping("/view/{id}")
	@ResponseBody
	public Welcome findOne(@PathVariable Integer id){
		return service.selectByPrimaryKey(id);
	};
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Boolean deleteOne(@PathVariable Integer id){
		if(service.deleteByPrimaryKey(id)>0) {
			return true;
		}
		return false;
	}
	@RequestMapping("/update")
	@ResponseBody
	public Welcome updateOne(@RequestBody Welcome welcome){
		//修改个人信息
		Welcome welcome2=service.selectByPrimaryKey(welcome.getId());
		//超时不允许更改
		if(welcome2.getStatus()!=null&&welcome2.getStatus().equals(3)) {
			return null;
		}
		if(welcome.getCreateTime()==null||welcome.getGetTime().before(new Date())) {
			return null;
		}
		welcome.setStatus(0);
		if(service.update(welcome)>0) {
			return service.selectOne(welcome);
		}
		return null;
	}
	@RequestMapping("/insert")
	@ResponseBody
	public Welcome createOneNoAspect(HttpServletRequest req,@RequestBody Welcome welcome){
		HttpSession session = req.getSession();
		BaseUserInfo userInfo =(BaseUserInfo) session.getAttribute("userInfo");
		welcome.setCreateTime(new Date());
		welcome.setStatus(0);
		if (userInfo != null) {
			Integer accountId = userInfo.getId();
			welcome.setCreatorId(accountId);
		} 
		if(welcome.getCreateTime()==null||welcome.getGetTime().before(new Date())) {
			return null;
		}
		//查找相同手机号的预约时间相隔1小时的预约记录
		Welcome welcome2=service.selectOldWelcome(welcome);
		if(welcome2!=null&&welcome2.getId()!=null) {
			return welcome2;
		}
		//小时的手机号，只能一个预约
		if(service.insert(welcome)>0) {
			return welcome;
		}
		return null;
	}
}

package com.safe.core.controller;

import java.util.Date;
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
import com.safe.core.beans.WelcomeItem;
import com.safe.core.service.WelcomeItemService;
import com.safe.core.utils.BaseUserInfo;

@Controller
@RequestMapping("/welcomeItem")
public class WelcomeItemController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	protected WelcomeItemService service;
	@RequestMapping("/all")
	@ResponseBody
	public ResultBean<WelcomeItem> allCompany(Page<WelcomeItem> page, WelcomeItem entity){
		ResultBean<WelcomeItem> b = new ResultBean<WelcomeItem>();
		page = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		List<WelcomeItem> result = service.selectAll(entity);
		b.setData(result);
		b.setCount(page.getTotal());
		return b;
	}
	@RequestMapping("/view/{id}")
	@ResponseBody
	public WelcomeItem findOne(@PathVariable Integer id){
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
	public WelcomeItem updateOne( WelcomeItem entity){
		HttpSession session = request.getSession();
		BaseUserInfo userInfo =(BaseUserInfo) session.getAttribute("userInfo");
		WelcomeItem welcomeItem=service.selectByPrimaryKey(entity.getId());
		if (userInfo != null) {
			entity.setCreatorId(userInfo.getId());
			Integer companyId = userInfo.getOrgId();
			if(companyId==null||!companyId.equals(welcomeItem.getCompanyId())) {
				//没有组织
				return null;
			}
			entity.setCompanyId(companyId);
			
			
		} else {
			return null;
		}
		if(service.update(entity)>0) {
			return service.selectOne(entity);
		}
		return null;
	}
	@RequestMapping("/insert")
	@ResponseBody
	public WelcomeItem createOne(WelcomeItem entity){
		HttpSession session = request.getSession();
		BaseUserInfo userInfo =(BaseUserInfo) session.getAttribute("userInfo");
		entity.setStatus(1);
		entity.setCreateTime(new Date());
		if (userInfo != null) {
			entity.setCreatorId(userInfo.getId());
			Integer companyId = userInfo.getOrgId();
			if(companyId==null) {
				//没有组织
				return null;
			}
			entity.setCompanyId(companyId);
		} else {
			return null;
		}
		if(service.insert(entity)>0) {
			return entity;
		}
		return null;
	}
}

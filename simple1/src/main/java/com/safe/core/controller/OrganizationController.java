package com.safe.core.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.safe.core.base.bean.ListMapVo;
import com.safe.core.base.bean.ResultBean;
import com.safe.core.beans.Organization;
import com.safe.core.beans.Permission;
import com.safe.core.beans.Post;
import com.safe.core.service.OrganizationService;
import com.safe.core.service.PostService;
import com.safe.core.utils.BaseUserInfo;

@Controller
@RequestMapping("/organization")
public class OrganizationController {
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private PostService postService;
	@RequestMapping("/all")
	@ResponseBody
	public ResultBean<Organization> allOrganization(Page<Organization> page,Organization organization){
		ResultBean<Organization> b=new ResultBean<Organization>();
		page=PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		List<Organization>  result=organizationService.selectAll(organization);
		b.setCount(page.getTotal());
		b.setData(result);
		return b;
	}
	@RequestMapping("/view/{id}")
	@ResponseBody
	public Organization findOne(@PathVariable Integer id){
		return organizationService.selectByPrimaryKey(id);
	}
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Boolean deleteOne(@PathVariable Integer id){
		return organizationService.deleteByPrimaryKey(id);
	}
	@RequestMapping("/update")
	@ResponseBody
	public Organization updateOne(Organization org){
		return organizationService.update(org);
	}
	@RequestMapping("/insert")
	@ResponseBody
	public Organization createOne(Organization org){
		return organizationService.insert(org);
	}
	@RequestMapping("/tree/org")
	@ResponseBody
	public List<ListMapVo> orgTree(HttpSession httpSession){
		if(httpSession.getAttribute("userInfo")!=null){
			BaseUserInfo userInfo =(BaseUserInfo) httpSession.getAttribute("userInfo");
			if(userInfo!=null){
				if(userInfo.getPostId()!=null){
					Post post=postService.selectByPrimaryKey(userInfo.getPostId());
					return organizationService.selectTreeAll(post.getOrgId());
				}else if(userInfo.getRoleIds().contains(1)){
					return organizationService.selectTreeAll(null);
				}
			}
		}
			return new ArrayList<>();
	}
}

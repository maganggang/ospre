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
import com.safe.core.beans.Position;
import com.safe.core.beans.Post;
import com.safe.core.beans.User;
import com.safe.core.filter.SessionListener;
import com.safe.core.service.PositionService;
import com.safe.core.service.PostService;
import com.safe.core.service.UserService;
import com.safe.core.utils.BaseUserInfo;

@Controller
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostService postService;
	
	@RequestMapping("/all")
	@ResponseBody
	public ResultBean<Post> allPost(Page<Post> page,Post post){
		ResultBean<Post> b=new ResultBean<Post>();
		page=PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		List<Post> result= postService.selectAll();
		b.setData(result);
		b.setCount(page.getTotal());
		return b;
	}
	/**
	 * 查询我的岗位树
	* @Title: treeCompanyPost 
	* @return
	* @return: List<ListMapVo> 
	* @author mgg
	* @date 2020年3月3日
	 */
	@RequestMapping("/tree/org")
	@ResponseBody
	public List<ListMapVo> treeCompanyPost(HttpSession httpSession){
		if(httpSession.getAttribute("userInfo")!=null){
			BaseUserInfo userInfo =(BaseUserInfo) httpSession.getAttribute("userInfo");
			if(userInfo!=null){
				if(userInfo.getPostId()!=null){
					Post post=postService.selectByPrimaryKey(userInfo.getPostId());
					return postService.selectTreeAll(post.getOrgId());
				}else if(userInfo.getRoleIds().contains(1)){
					return postService.selectTreeAll(null);
				}
			}
		}
			return new ArrayList<>();
	}
	@RequestMapping("/org/all")
	@ResponseBody
	public ResultBean<User> allOrgUser(HttpSession httpSession,Page<User> page,User user){
		ResultBean<User> b=new ResultBean<User>();
		if(httpSession.getAttribute("orgId")!=null){
			String orgId=httpSession.getAttribute("orgId").toString();
			page=PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
			//List<User>  result=postService.findAllByOrg(Integer.parseInt(orgId),user);
			//b.setData(result);
			b.setCount(page.getTotal());
		}else{
			b.setMsg("非组织人员或登录过期！");
		}
		return b;
	}
	@RequestMapping("/post/{id}")
	@ResponseBody
	public Post findOne(@PathVariable Integer id){
		return postService.selectByPrimaryKey(id);
	}
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Boolean deleteOne(@PathVariable Integer id){
		return postService.deleteByPrimaryKey(id);
	}
	@RequestMapping("/update")
	@ResponseBody
	public Post updateOne(Post post){
		return postService.update(post);
	}
	@RequestMapping("/insert")
	@ResponseBody
	public Post createOne(Post post){
		return postService.insert(post);
	}
}

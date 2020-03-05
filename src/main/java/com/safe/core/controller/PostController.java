package com.safe.core.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.safe.core.beans.ListMapVo;
import com.safe.core.beans.Position;
import com.safe.core.beans.Post;
import com.safe.core.beans.ResultBean;
import com.safe.core.beans.User;
import com.safe.core.service.PositionService;
import com.safe.core.service.PostService;
import com.safe.core.service.UserService;

@Controller
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostService postService;
	
	@RequestMapping("/all")
	@ResponseBody
	public List<Post> allPost(){
		return postService.selectAll();
	}
	@RequestMapping("/tree/org")
	@ResponseBody
	public List<ListMapVo> treeCompanyPost(){
		return postService.selectTreeAll();
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
			b.setMsg("����֯��Ա���¼���ڣ�");
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

package com.safe.core.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.safe.core.base.bean.ResultBean;
import com.safe.core.beans.User;
import com.safe.core.service.UserService;
import com.safe.core.utils.BaseUserInfo;

@Controller
@RequestMapping("/user")
public class UserController extends BaseUserInfo{
	@Autowired
	private UserService userService;
	@RequestMapping("/all")
	@ResponseBody
	public ResultBean<User> allUser(Page<User> page,User user){
		ResultBean<User> b=new ResultBean<User>();
		 //设置分页条件，Parameters:pageNum 页码pageSize 每页显示数量count 是否进行count查询
		page=PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
	    List<User> users = userService.selectAll(user);
	    //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        //PageInfo<User> pageInfo = new PageInfo<>(users,5);
	    b.setData(users);
	    b.setCount(page.getTotal());
		return b;
	}
	@RequestMapping("/org/all")
	@ResponseBody
	public ResultBean<User> allOrgUser(HttpSession httpSession,Page<User> page,User user){
		ResultBean<User> b=new ResultBean<User>();
		if(httpSession.getAttribute("orgId")!=null){
			String orgId=httpSession.getAttribute("orgId").toString();
			page=PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
			List<User>  result=userService.findAllByOrg(Integer.parseInt(orgId),user);
			b.setData(result);
			b.setCount(page.getTotal());
		}else{
			b.setMsg("非组织人员或登录过期！");
		}
		return b;
	}
	@RequestMapping("/user/{id}")
	@ResponseBody
	public User findOne(@PathVariable Integer id){
		return userService.select(id);
	}
	/**
	 * 登录人个人详细
	* @Title: findMyInfo 
	* @param session
	* @return
	* @return: User 
	* @author mgg
	* @date 2020年1月8日
	 */
	@RequestMapping("/myInfo")
	@ResponseBody
	public User findMyInfo(){
		Integer userId=this.getUserId();
		if(userId!=null){
			return userService.select(userId); 
		}else{
			return null;
		}
	}
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Boolean deleteOne(@PathVariable Integer id){
		return userService.delete(id);
	}
	@RequestMapping("/deleteList")
	@ResponseBody
	public Boolean deleteList(@RequestBody List<String>  ids){
		System.out.println(ids);
		return userService.deleteList(ids);
	}
	@RequestMapping("/update")
	@ResponseBody
	public User updateOne(User user){
		return userService.update(user);
	}
	@RequestMapping("/insert")
	@ResponseBody
	public User createOne(User user){
		return userService.insert(user);
	}
}

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
import com.safe.core.beans.ResultBean;
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
		 //���÷�ҳ������Parameters:pageNum ҳ��pageSize ÿҳ��ʾ����count �Ƿ����count��ѯ
		page=PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
	    List<User> users = userService.selectAll(user);
	    //ʹ��PageInfo��װ��ѯ�����ֻ��Ҫ��pageInfo����ҳ��Ϳ���
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
			b.setMsg("����֯��Ա���¼���ڣ�");
		}
		return b;
	}
	@RequestMapping("/user/{id}")
	@ResponseBody
	public User findOne(@PathVariable Integer id){
		return userService.selectByPrimaryKey(id);
	}
	/**
	 * ��¼�˸�����ϸ
	* @Title: findMyInfo 
	* @param session
	* @return
	* @return: User 
	* @author mgg
	* @date 2020��1��8��
	 */
	@RequestMapping("/myInfo")
	@ResponseBody
	public User findMyInfo(){
		Integer userId=this.getUserId();
		if(userId!=null){
			return userService.selectByPrimaryKey(userId); 
		}else{
			return null;
		}
	}
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Boolean deleteOne(@PathVariable Integer id){
		return userService.deleteByPrimaryKey(id);
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

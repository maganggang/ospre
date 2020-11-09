package com.safe.core.beans;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.safe.core.base.bean.CreatorBean;
import com.safe.core.base.bean.ListMapVo;

public class Account extends CreatorBean {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String nickname;
	private String mobile;
	private String email;
	private Date loginTime;
	private Date lastLoginTime;
	private Integer count;
	private Integer userId;
	private Integer postId;
	private Integer orgId;
	private List<Role> roleList;
	private List<Menu> moduleList;
	private List<ListMapVo> moduleMapList;
	private User user;
	
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<ListMapVo> getModuleMapList() {
		return moduleMapList;
	}

	public void setModuleMapList(List<ListMapVo> moduleMapList) {
		this.moduleMapList = moduleMapList;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public List<Menu> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<Menu> moduleList) {
		this.moduleList = moduleList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
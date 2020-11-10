package com.safe.core.beans;

import java.util.Date;
import java.util.List;

import com.safe.core.base.bean.CreatorBean;

public class Role extends CreatorBean{

    private String name;

    private String description;


    private Date operatetime;

    private Integer operatorId;
    private List<Role> accountRoles;
    private List<Role> postRoles;
    private List<Integer> accountRoleIds;
    private List<Integer> postRoleIds;
	public Date getOperatetime() {
		return operatetime;
	}

	public void setOperatetime(Date operatetime) {
		this.operatetime = operatetime;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	private List<String> moduleIds;
	private List<String> authorityIds;
    private List<Authority> authorityList; 
    private static final long serialVersionUID = 1L;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	public List<Authority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}

	public List<String> getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(List<String> moduleIds) {
		this.moduleIds = moduleIds;
	}

	public List<String> getAuthorityIds() {
		return authorityIds;
	}

	public void setAuthorityIds(List<String> authorityIds) {
		this.authorityIds = authorityIds;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Role> getAccountRoles() {
		return accountRoles;
	}

	public void setAccountRoles(List<Role> accountRoles) {
		this.accountRoles = accountRoles;
	}

	public List<Role> getPostRoles() {
		return postRoles;
	}

	public void setPostRoles(List<Role> postRoles) {
		this.postRoles = postRoles;
	}

	public List<Integer> getAccountRoleIds() {
		return accountRoleIds;
	}

	public void setAccountRoleIds(List<Integer> accountRoleIds) {
		this.accountRoleIds = accountRoleIds;
	}

	public List<Integer> getPostRoleIds() {
		return postRoleIds;
	}

	public void setPostRoleIds(List<Integer> postRoleIds) {
		this.postRoleIds = postRoleIds;
	}
}
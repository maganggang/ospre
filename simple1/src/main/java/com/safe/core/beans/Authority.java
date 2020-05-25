package com.safe.core.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.safe.core.base.bean.CreatorBean;

public class Authority extends CreatorBean {
	    private Integer roleId;

	    private Integer permissionId;
	private String permissionName;
    private static final long serialVersionUID = 1L;


    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }


	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
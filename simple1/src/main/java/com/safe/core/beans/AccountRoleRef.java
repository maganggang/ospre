package com.safe.core.beans;


import com.safe.core.base.bean.CreatorBean;

public class AccountRoleRef extends CreatorBean{
	private static final long serialVersionUID = 1L;

		private Integer accountId;

	    private Integer roleId;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
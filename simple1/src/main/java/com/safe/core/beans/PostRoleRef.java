package com.safe.core.beans;


import com.safe.core.base.bean.CreatorBean;

public class PostRoleRef extends CreatorBean {

	    private Integer roleId;

	    private Integer postId;


	private static final long serialVersionUID = 1L;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}
package com.safe.core.beans;

import java.sql.Date;

import com.safe.core.base.bean.CreatorBean;
import com.safe.core.base.bean.TimeBean;

public class User   extends CreatorBean{

    private String name;

    private Boolean sex;

    private String idCard;

    private String phone;

    private String email;

    private Integer orgId;


    private Date opreatetime;

    private Integer opreatorId;
    private Integer postId;
    public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


	public Date getOpreatetime() {
		return opreatetime;
	}

	public void setOpreatetime(Date opreatetime) {
		this.opreatetime = opreatetime;
	}

	public Integer getOpreatorId() {
		return opreatorId;
	}

	public void setOpreatorId(Integer opreatorId) {
		this.opreatorId = opreatorId;
	}

	private Post post;
    private static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
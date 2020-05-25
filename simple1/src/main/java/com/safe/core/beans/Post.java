package com.safe.core.beans;


import com.safe.core.base.bean.CreatorBean;

public class Post extends CreatorBean {

	    private Integer orgId;

	    private String name;

	    private String description;


	    private String postCard;
	    

		public String getPostCard() {
			return postCard;
		}

		public void setPostCard(String postCard) {
			this.postCard = postCard;
		}

	private Organization organization;
    private static final long serialVersionUID = 1L;


    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }



	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
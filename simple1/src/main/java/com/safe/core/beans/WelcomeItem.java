package com.safe.core.beans;

import java.util.Date;

public class WelcomeItem {
    private Integer id;

    private String title;

    private Date createTime;

    private Integer creatorId;

    private Integer companyId;

    private String content;
    private Integer status;
    public WelcomeItem(Integer id, String title, Date createTime, Integer creatorId, Integer companyId,Integer status) {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
        this.creatorId = creatorId;
        this.companyId = companyId;
        this.setStatus(status);
    }

    public WelcomeItem(Integer id, String title, Date createTime, Integer creatorId, Integer companyId, String content,Integer status) {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
        this.creatorId = creatorId;
        this.companyId = companyId;
        this.content = content;
        this.setStatus(status);
    }

    public WelcomeItem() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
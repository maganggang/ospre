package com.safe.core.beans;

import java.util.Date;

public class Welcome {
    private Integer id;

    private Integer creatorId;

    private String persionName;

    private String phone;

    private Integer projectId;

    private Date getTime;

    private Integer count;

    private Integer status;

    private Date createTime;

    public Welcome(Integer id, Integer creatorId, String persionName, String phone, Integer projectId, Date getTime, Integer count, Integer status, Date createTime) {
        this.id = id;
        this.creatorId = creatorId;
        this.persionName = persionName;
        this.phone = phone;
        this.projectId = projectId;
        this.getTime = getTime;
        this.count = count;
        this.status = status;
        this.createTime = createTime;
    }

    public Welcome() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getPersionName() {
        return persionName;
    }

    public void setPersionName(String persionName) {
        this.persionName = persionName == null ? null : persionName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status ;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
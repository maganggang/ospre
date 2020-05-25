package com.safe.core.beans;


import com.safe.core.base.bean.CreatorBean;

public class Organization extends CreatorBean {

    private Byte type;

    private Integer value;

    private String name;

    private Integer parentId;


    public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	private static final long serialVersionUID = 1L;


    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}
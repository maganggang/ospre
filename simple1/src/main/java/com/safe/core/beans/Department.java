package com.safe.core.beans;

import java.util.Date;

import com.safe.core.base.bean.CreatorBean;

public class Department extends CreatorBean {

    private String name;

    private String desc;


    private static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

}
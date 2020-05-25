package com.safe.core.beans;


import com.safe.core.base.bean.CreatorBean;

public class Item extends CreatorBean {

	    private String name;

	    private String desc;

	    private Integer areaPositionId;


		public Integer getAreaPositionId() {
			return areaPositionId;
		}

		public void setAreaPositionId(Integer areaPositionId) {
			this.areaPositionId = areaPositionId;
		}

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
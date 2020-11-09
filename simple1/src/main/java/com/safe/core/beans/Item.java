package com.safe.core.beans;


import com.safe.core.base.bean.CreatorBean;

public class Item extends CreatorBean {

	    private String name;

	    private String description;

	    private Integer areaId;


		public Integer getareaId() {
			return areaId;
		}

		public void setareaId(Integer areaId) {
			this.areaId = areaId;
		}

	private static final long serialVersionUID = 1L;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
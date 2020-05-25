package com.safe.core.beans;


import com.safe.core.base.bean.CreatorBean;

public class Permission extends CreatorBean {

	    private Integer menuId;

	    private String pathUrl;

	    private String name;

	    private String orderNum;
	    private String description;
	    
    public Integer getMenuId() {
			return menuId;
		}

		public void setMenuId(Integer menuId) {
			this.menuId = menuId;
		}

		public String getOrderNum() {
			return orderNum;
		}

		public void setOrderNum(String orderNum) {
			this.orderNum = orderNum;
		}

	private static final long serialVersionUID = 1L;

    public String getPathUrl() {
		return pathUrl;
	}

	public void setPathUrl(String pathUrl) {
		this.pathUrl = pathUrl;
	}



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
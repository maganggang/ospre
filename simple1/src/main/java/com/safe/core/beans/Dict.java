package com.safe.core.beans;


import com.safe.core.base.bean.CreatorBean;

public class Dict extends CreatorBean {

	    private String dictType;

	    private String dictValue;

	    private Integer parentId;

	    private String description;


    public Integer getParentId() {
			return parentId;
		}

		public void setParentId(Integer parentId) {
			this.parentId = parentId;
		}

	private static final long serialVersionUID = 1L;


    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType == null ? null : dictType.trim();
    }

    public String getDictValue() {
        return dictValue;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
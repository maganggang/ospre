package com.safe.core.beans;


import com.safe.core.base.bean.CreatorBean;

public class Dict extends CreatorBean {

	    private String dictType;

	    private String dictValue;

	    private Integer parentId;

	    private String desc;


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

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue == null ? null : dictValue.trim();
    }
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}
package com.safe.core.base.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListMapVo extends HashMap<String,Object> {
	private List<Map<String,Object>> permissionMapList;

	public List<Map<String,Object>> getPermissionMapList() {
		return permissionMapList;
	}

	public void setPermissionMapList(List<Map<String,Object>> permissionMapList) {
		this.permissionMapList = permissionMapList;
	}
}

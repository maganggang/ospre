package com.safe.core.base.bean;

import java.util.List;

public class  ResultBean<T> {
private String msg;
private long code;
private Long count;
private List<T> data;
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public long getCode() {
	return code;
}
public void setCode(long code) {
	this.code = code;
}

public List<T> getData() {
	return data;
}
public void setData(List<T> data) {
	this.data = data;
	if(data!=null){
		this.code=0;
		this.msg="ok";
	}
}
public Long getCount() {
	return count;
}
public void setCount(Long count) {
	this.count = count;
} 

}

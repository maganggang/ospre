package com.safe.core.utils;

import java.io.Serializable;

public class Head implements Serializable{
	/**
	    * 文件相对路径
	    */
	   private String relativePath;
	   /**
	    * 文件开始位置
	    */
	   private long   start;
	   /**
	    * 文件大小
	    */
	   private long   size;
	public String getRelativePath() {
		return relativePath;
	}
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	   
}

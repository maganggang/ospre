package com.safe.core.utils;

import java.io.Serializable;

public class Head implements Serializable{
	/**
	    * �ļ����·��
	    */
	   private String relativePath;
	   /**
	    * �ļ���ʼλ��
	    */
	   private long   start;
	   /**
	    * �ļ���С
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

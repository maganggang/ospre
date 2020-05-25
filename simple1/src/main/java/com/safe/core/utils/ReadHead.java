package com.safe.core.utils;

import java.io.RandomAccessFile;
import java.util.List;
/**
 * 支持api可以同时读取多个资源包的内容
 * @author Administrator
 *
 */
public class ReadHead {
	 /**
	   * 文件头集合
	   */
	   private List<Head> heads;
	   /**
	   *  读取文件数据类
	   */
	   private RandomAccessFile randomAccessFile;
	    /**
	   *  文件头长度
	   */
	   private long headLenth;

	   public ReadHead(){

	   }

	   public List<Head> getHeads() {
	       return heads;
	   }

	   public void setHeads(List<Head> heads) {
	       this.heads = heads;
	   }

	   public RandomAccessFile getRandomAccessFile() {
	       return randomAccessFile;
	   }

	   public void setRandomAccessFile(RandomAccessFile randomAccessFile) {
	       this.randomAccessFile = randomAccessFile;
	   }

	   public long getHeadLenth() {
	       return headLenth;
	   }

	   public void setHeadLenth(long headLenth) {
	       this.headLenth = headLenth;
	   }
}

package com.safe.core.utils;

import java.io.RandomAccessFile;
import java.util.List;
/**
 * ֧��api����ͬʱ��ȡ�����Դ��������
 * @author Administrator
 *
 */
public class ReadHead {
	 /**
	   * �ļ�ͷ����
	   */
	   private List<Head> heads;
	   /**
	   *  ��ȡ�ļ�������
	   */
	   private RandomAccessFile randomAccessFile;
	    /**
	   *  �ļ�ͷ����
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

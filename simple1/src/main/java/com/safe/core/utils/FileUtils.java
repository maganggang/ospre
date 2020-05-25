package com.safe.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtils {
	/**
	 * 获取文件目录下文件
	* @Title: getFilesPath 
	* @param path
	* @return
	* @return: List<String> 
	* @author mgg
	* @date 2020年3月4日
	 */
	public static List<String> getFilesPath(String path) {
		List<String> files = new ArrayList<String>();
	    File file = new File(path);
	    File[] tempList = file.listFiles();
	    for (int i = 0; i < tempList.length; i++) {
	        if (tempList[i].isFile()) {
	            files.add(tempList[i].toString());
	        }
	    }
	    return files;
	}
	/**
	 * 获取文件和文件夹路径
	* @Title: getALLFilesPath 
	* @param path
	* @return
	* @return: Map<String,List<String>> 
	* @author mgg
	* @date 2020年3月4日
	 */
	public static Map<String,List<String>> getALLFilesPath(String path) {
		Map<String,List<String>> files=new HashMap<>();
		List<String> filesList = new ArrayList<>();
		List<String> directoryList = new ArrayList<>();
	    File file = new File(path);
	    File[] tempList = file.listFiles();
	    for (int i = 0; i < tempList.length; i++) {
	        if (tempList[i].isFile()) {
	              System.out.println("文     件：" + tempList[i]);
	              filesList.add(tempList[i].toString());
	        }else if (tempList[i].isDirectory()) {
	              System.out.println("文件夹：" + tempList[i]);
	            directoryList.add(tempList[i].toString());
	        }
	    }
	    return files;
	}
	/**
	 * 获取文件夹下的文件
	* @Title: getFiles 
	* @param path
	* @return
	* @return: List<File> 
	* @author mgg
	* @date 2020年3月4日
	 */
	public static List<File> getFiles(String path) {
		List<File> files = new ArrayList<File>();
	    File file = new File(path);
	    File[] tempList = file.listFiles();
	    for (int i = 0; i < tempList.length; i++) {
	        if (tempList[i].isFile()) {
	            files.add(tempList[i]);
	        }
	    }
	    return files;
	}
	/**
     * 得到源文件路径下的所有文件
     * @param dirFile 源文件路径
     * */
    public static List<File> getAllFile(File dirFile){
        List<File> fileList=new ArrayList<>();
        File[] files= dirFile.listFiles();
        for(File file:files){//文件
            if(file.isFile()){
                fileList.add(file);
                //System.out.println("add file:"+file.getPath());
            }else {//目录
                if(file.listFiles().length!=0){//非空目录
                    fileList.addAll(getAllFile(file));//把递归文件加到fileList中
                }else {//空目录
                    fileList.add(file);
                    //System.out.println("add empty dir:"+file.getPath());
                }
            }
        }
        return fileList;
    }
	
	/**
	    * 将Object数组转化为二进制数据
	    * @param heads
	    * @return
	    */
	   public static byte[] getEntryByte(List<Head> heads){
	       try {
	           ByteArrayOutputStream byt=new ByteArrayOutputStream();
	           ObjectOutputStream obj=new ObjectOutputStream(byt);
	           obj.writeObject(heads);
	           byte[] bytes=byt.toByteArray();
	           return bytes;
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
	       return null;
	   }
	/**
	    * 将文件流转化成二进制数组
	    * @param input
	    * @return
	    * @throws IOException
	    */
	   public static byte[] toByteArray(InputStream input) throws IOException {
	       ByteArrayOutputStream output = new ByteArrayOutputStream();
	       byte[] buffer = new byte[4096];
	       int n = 0;
	       while (-1 != (n = input.read(buffer))) {
	           output.write(buffer, 0, n);
	       }
	       return output.toByteArray();
	   }

	   /**
	    * 将文件流转化成二进制数组
	    * @param rf
	    * @return
	    * @throws IOException
	    */
	   public static byte[] toByteArrayByRandomAccess(RandomAccessFile rf,long length) throws IOException {
	       byte[] data = new byte[(int)length];
	       Lock lock = new ReentrantLock();
	       lock.lock();
	       try {
	           //rf.seek(0);
	           rf.read(data);
	           return data;
	       } catch (IOException e) {
	           e.printStackTrace();
	       }finally {
	           lock.unlock();
	       }
	       return data;
	   }


	   /**
	    * 把字节数组保存为一个文件
	    *
	    * @param b
	    * @param outputFile
	    * @return
	    */
	   public static File getFileFromBytes(byte[] b, String outputFile) {
		   if(b!=null){
			   File ret = null;
		       BufferedOutputStream stream = null;
		       try {
		           ret = new File(outputFile);
		           FileOutputStream fstream = new FileOutputStream(ret);
		           stream = new BufferedOutputStream(fstream);
		           stream.write(b);
		       } catch (Exception e) {
		           e.printStackTrace();
		       } finally {
		           if (stream != null) {
		               try {
		                   stream.close();
		               } catch (IOException e) {
		                   e.printStackTrace();
		               }
		           }
		       }
		       return ret;
		   }else{
			   System.out.println("文件不对");
			   return null;
		   }
	      
	   }

	   /**
	    * 将二进制数据转化存储的Object数组
	    * @param datas
	    * @return
	    */
	   public static List<Head> getHeadList(byte[] datas){
	       try {
	           ByteArrayInputStream byteInt=new ByteArrayInputStream(datas);
	           ObjectInputStream objInt=new ObjectInputStream(byteInt);
	           List<Head> heads = (List<Head>)objInt.readObject();
	           return heads;
	       } catch (IOException e) {
	           e.printStackTrace();
	       } catch (ClassNotFoundException e) {
	           e.printStackTrace();
	       }
	       return null;
	   }


	   /**
	    * long 类型转化bytes数组
	    * @param l
	    * @return
	    */
	   public static byte[] getByteByLong(long l){
	       byte[] bytes = new byte[8];
	       for (int i = 0; i < bytes.length; i++)
	           bytes[i] = (byte)(long)(((l) >> i * 8) & 0xff); // 移位和清零
	       return bytes;

	   }


	   /**
	    * byte数组转化为long
	    * @param bytes
	    * @return
	    */
	   public static long getLongByBytes(byte[] bytes){
	       long longa = 0;
	       for (int i = 0; i < bytes.length; i++)
	           longa += (long)((bytes[i] & 0xff) << i * 8); // 移位和清零
	       return longa;
	   }


	/**
	    * 读取指定位置，指定长度的字节
	    * @param rf
	    * @param start
	    * @param size
	    * @return
	    */
	   public static byte[] getBytesFromRf(RandomAccessFile rf,long start,long size){
	       byte[] data = new byte[(int)size];
	  try {
	           rf.seek(start);
	           rf.read(data);
	           return data;
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
	       return data;
	   }

	  /* public static void main(String[] args){
	       byte[] bytes= getByteByLong(344);System.out.println(bytes);
	       System.out.println(bytes);
	       long l = getLongByBytes(bytes);
	       System.out.println(l);
	   }*/


	public static void main(String[] args) {
		getFilesPath("D:\\8189");
	}
}

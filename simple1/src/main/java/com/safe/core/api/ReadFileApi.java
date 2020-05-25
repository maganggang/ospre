package com.safe.core.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.safe.core.utils.FileUtils;
import com.safe.core.utils.Head;
import com.safe.core.utils.ReadHead;
/**
 * .
 * �о����������ļ����
 * @author Administrator
 *
 */
public class ReadFileApi {


	   private static Map<String, ReadHead> dataMap = new HashMap<>();


	   /**
	    * �����ļ����·�������ļ�ͷ����Ϣ
	    * @param heads
	    * @param relativePath
	    * @return
	    */
	   private static  Head  getStaHeadByName(List<Head> heads,String relativePath){
	       for (Head h:heads){
	           if (h.getRelativePath().equals(relativePath)){
	               return h;
	           }
	       }
	       return null;
	   }
	   /**
	    * ����ͷ����Ϣ��ȡ�ļ��ֽ�
	    * @param head
	    * @return
	    */
	   private static  byte[] getFileData(RandomAccessFile rf,Head head,long headLength){
	       long start = 8+head.getStart()+headLength;
	       long size =head.getSize();
	       return FileUtils.getBytesFromRf(rf,start,size);
	   }


	   /**
	    * ������Դ����ȡ�ļ��б�
	    * @param filePath
	    * @return
	    */
	   private static  List<String> getFileName(String filePath){

	       ReadHead heads = dataMap.get(filePath);
	      if (null!=heads){
	           List<Head> hs = heads.getHeads();
	          List<String> res = new ArrayList<>();
	          for (Head h:hs){
	              res.add(h.getRelativePath());
	          }
	          return res;
	      }else {
	          return null;
	      }

	   }


	   /**
	    * �ж��ļ��治����
	    * @param fileRelationName
	    * @return
	    */
	   public static boolean isHave(String fileRelationName){
	       ReadHead readHead = getReadHead(fileRelationName);
	       if (null==readHead){
	           return false;
	       }else {
	           List<Head> heads = readHead.getHeads();
	           Head head = getStaHeadByName(heads,fileRelationName);
	           if (null==head){
	               return false;
	           }else {
	               return true;
	           }
	       }
	   }

	   /**
	    * �����ṩ��api���÷���
	    * @param fileRelationName �ļ����·��
	    * @return
	    */
	   public static byte[] getFile(String fileRelationName){
	       //�����ļ����ҵ���Դ��
	       ReadHead readHead = getReadHead(fileRelationName);
	       if (null==readHead){
	           return null;
	       }else {
	           List<Head> heads = readHead.getHeads();
	           Head head = getStaHeadByName(heads,fileRelationName);
	           if (null!=head){
	               return getFileData(readHead.getRandomAccessFile(),head,readHead.getHeadLenth());
	           }else {
	               System.out.println("�ļ������ڣ�");
	           }
	           return null;
	       }

	   }

	   /**
	    *
	    * @param fileRelationName
	    * @return
	    */
	   private static ReadHead getReadHead(String fileRelationName){
	       for(ReadHead h:dataMap.values()){
	           List<Head> heads = h.getHeads();
	           for (Head head:heads){
	               if (head.getRelativePath().equals(fileRelationName)){
	                   return h;
	               }
	           }
	       }
	       return null;
	   }

	   /**
	    * �����ṩ������Դ�����ص�����
	    * @param file
	    */
	   public static void reloadFile(String file){
	       try {

 	           if (null==dataMap ){
	               dataMap=new HashMap<String, ReadHead>();
	           }
	           if (null == dataMap.get(file)) {
	               ReadHead rh = new ReadHead();
	               RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
	               byte[] headLengthBytes = new byte[8];
	               randomAccessFile.read(headLengthBytes);
	               long headLength = FileUtils.getLongByBytes(headLengthBytes);
	               byte[] headDataBytes = FileUtils.getBytesFromRf(randomAccessFile, 8, headLength);
	               List<Head> heads = FileUtils.getHeadList(headDataBytes);
	               rh.setRandomAccessFile(randomAccessFile);
	               rh.setHeadLenth(headLength);
	               rh.setHeads(heads);
	               dataMap.put(file,rh);
	           }
	       }catch (FileNotFoundException e) {
	           e.printStackTrace();
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
	   }




	   public static void main(String[] args){
	       String filePath = "C:/Users/Administrator/Desktop/test/packFile.entry";
	       reloadFile(filePath);
	       List<String> list=ReadFileApi.getFileName(filePath);
	      System.out.println(list);
	      System.out.println(isHave("c:\\Users\\Administrator\\Desktop\\test\\a.sql"));
	       byte[] datas = getFile("c:\\Users\\Administrator\\Desktop\\test\\a.sql");
	       FileUtils.getFileFromBytes(datas,"c:/Users/Administrator/Desktop/test/position1_user_record.sql");

	   }
}

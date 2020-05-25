package com.safe.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.web.accept.HeaderContentNegotiationStrategy;

public class PackFile {
	/**
	 * ����ļ�
	* @Title: packFile 
	* @param filePath
	* @param targetFile
	* @return: void 
	* @author mgg
	* @date 2020��3��6��
	 */
	public static void packFile(String filePath,String targetFile){
		try{
				File target=new File(targetFile);
				String targetPath=target.getParent();
				File targetFolder=new File(targetPath);
				if ( ! targetFolder.exists() || !targetFolder.isDirectory()){
		              if (!targetFolder.mkdir()){
		                  System.out.println("��������ļ���·�����벻�Ϸ�");
		                  return;
		              }
		           }
				List<File> files=FileUtils.getAllFile(new File(filePath));
				byte[] head=getHeadBytes(files,filePath);
				long headLength=head.length;
				byte[] headLengthByte=new byte[8];
				headLengthByte=FileUtils.getByteByLong(headLength);
				//�洢�ļ�����λ��
				File res =new File(targetFile);
				BufferedOutputStream stream=null;
				//д���ļ�ͷ
				FileOutputStream fStream=new FileOutputStream(res);
				stream = new BufferedOutputStream(fStream);
				stream.write(headLengthByte);
				stream.write(head);
				//д���ļ�
				InputStream fis=null;
				for(File f :files){
					fis =new FileInputStream(f);
					byte[]buffer=new byte[4096];
					int n=0;
					while (-1 != (n = fis.read(buffer))) {
		                stream.write(buffer, 0, n);
		            }
		            System.out.println("д���ļ�:"+f.getPath());
				}
				stream.flush();
				stream.close();
		} catch (Exception e) {
	           e.printStackTrace();
	       }
	}
/**
 * �����ļ�ͷ����Ϣ
* @Title: getHeadBytes 
* @param files
* @param filePath
* @return
* @return: byte[] 
* @author mgg
* @date 2020��3��6��
 */
	private static byte[] getHeadBytes(List<File> files, String filePath) {
		List<Head> heads=new LinkedList<>();
		long cursor=0;
		for(File f:files){
			Head head=new Head();
			String relativePath=f.getPath().replace(filePath, "");
			head.setRelativePath(relativePath);
		    long fileLength=f.length();
		    head.setSize(fileLength);
		    head.setStart(cursor);
		    heads.add(head);
		    cursor=cursor+fileLength;
		}
		return FileUtils.getEntryByte(heads);
	}
	/**
	    * ����·���ж��Ƿ�Ϊ���·������������·����ת�������·��
	    * @param filePath
	    * @return
	    */
	   public static String getFilePath(String filePath){
	       File f = new File(filePath);
	       if (f.isAbsolute()){
	           return filePath;
	       }else{
	           String rootPath  = System.getProperty("user.dir");
	           if (filePath.startsWith("../")){
	               //�ϲ�Ŀ¼
	               File f1 = new File(rootPath);
	              return f1.getParent()+File.separator+filePath.replace("../","");

	           } else if (filePath.startsWith("./")){
	               //��ǰĿ¼
	               return rootPath+File.separator+filePath.replace("./","");
	           }else{
	               // ������ ./  ../��ͷ�ģ�������ǰĿ¼
	               return rootPath+File.separator+filePath.replace("./","");
	           }
	       }
	   }
	   /**
		 * ����������ļ�����������ļ����д��
		 * 
		 * @param File
		 * @param org.apache.tools.zip.ZipOutputStream
		 */
		private static  void zipFile(File inputFile, ZipOutputStream ouputStream) {
			try {
				if (inputFile.exists()) {
					if (inputFile.isFile()) {
						FileInputStream IN = new FileInputStream(inputFile);
						BufferedInputStream bins = new BufferedInputStream(IN, 512);
						ZipEntry entry = new ZipEntry(inputFile.getName());
						ouputStream.putNextEntry(entry);
						// ��ѹ���ļ����������
						int nNumber;
						byte[] buffer = new byte[512];
						while ((nNumber = bins.read(buffer)) != -1) {
							ouputStream.write(buffer, 0, nNumber);
						}
						// �رմ�����������
						bins.close();
						IN.close();
					} else {
						try {
							File[] files = inputFile.listFiles();
							for (int i = 0; i < files.length; i++) {
								zipFile(files[i], ouputStream);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/**
		 * �ѽ��ܵ�ȫ���ļ����ѹ����
		 * 
		 * @param List<File>;
		 * @param org.apache.tools.zip.ZipOutputStream
		 */
		public static void zipFile(List<File> files, ZipOutputStream outputStream) {
			int size = files.size();
			for (int i = 0; i < size; i++) {
				File file = files.get(i);
				zipFile(file, outputStream);
			}
		}
	   /**
	    * ������
	    * @param args
	    */
	   public static void main(String[] args){

	       //getFilePath("."); java -jar PackFile.jar /Users/hjd/Desktop/test1 /Users/hjd/Desktop/test/result3/result.entry
	       //System.out.println(getNewName("../out?222&"));
	       //System.out.println(getFilePath("../out"));
	       String filePath="/Users/Administrator/Desktop/test1";
	       String targetfile="/Users/Administrator/Desktop/test/result3/mgg.entry";
	       //System.out.println(new File("/Users/hjd/Desktop/test/result3/ssss.entry").getParent());
	       if (args.length<1){
	           System.out.println("�������벻��ȷ��������������Ҫ������ļ���·��");
	           return;
	       }else if (args.length==1){
	           System.out.println("δ���������ļ�·��������Ĭ������ �ļ� packFile.entry �ڴ��Ŀ¼��");
	           filePath=args[0];
	           targetfile =filePath+File.separator+"packFile.entry";
	       }else{
	           filePath=args[0];
	           targetfile=args[1];
	       }
	       filePath = getFilePath(filePath);
	       targetfile = getFilePath(targetfile);
	       packFile(filePath,targetfile);
	   }
}

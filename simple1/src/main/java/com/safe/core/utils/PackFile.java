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
	 * 打包文件
	* @Title: packFile 
	* @param filePath
	* @param targetFile
	* @return: void 
	* @author mgg
	* @date 2020年3月6日
	 */
	public static void packFile(String filePath,String targetFile){
		try{
				File target=new File(targetFile);
				String targetPath=target.getParent();
				File targetFolder=new File(targetPath);
				if ( ! targetFolder.exists() || !targetFolder.isDirectory()){
		              if (!targetFolder.mkdir()){
		                  System.out.println("打包后存放文件的路径输入不合法");
		                  return;
		              }
		           }
				List<File> files=FileUtils.getAllFile(new File(filePath));
				byte[] head=getHeadBytes(files,filePath);
				long headLength=head.length;
				byte[] headLengthByte=new byte[8];
				headLengthByte=FileUtils.getByteByLong(headLength);
				//存储文件流的位置
				File res =new File(targetFile);
				BufferedOutputStream stream=null;
				//写入文件头
				FileOutputStream fStream=new FileOutputStream(res);
				stream = new BufferedOutputStream(fStream);
				stream.write(headLengthByte);
				stream.write(head);
				//写入文件
				InputStream fis=null;
				for(File f :files){
					fis =new FileInputStream(f);
					byte[]buffer=new byte[4096];
					int n=0;
					while (-1 != (n = fis.read(buffer))) {
		                stream.write(buffer, 0, n);
		            }
		            System.out.println("写入文件:"+f.getPath());
				}
				stream.flush();
				stream.close();
		} catch (Exception e) {
	           e.printStackTrace();
	       }
	}
/**
 * 生成文件头部信息
* @Title: getHeadBytes 
* @param files
* @param filePath
* @return
* @return: byte[] 
* @author mgg
* @date 2020年3月6日
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
	    * 根据路径判断是否为相对路径，如果是相对路径则转成其绝对路径
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
	               //上层目录
	               File f1 = new File(rootPath);
	              return f1.getParent()+File.separator+filePath.replace("../","");

	           } else if (filePath.startsWith("./")){
	               //当前目录
	               return rootPath+File.separator+filePath.replace("./","");
	           }else{
	               // 不是已 ./  ../开头的，当作当前目录
	               return rootPath+File.separator+filePath.replace("./","");
	           }
	       }
	   }
	   /**
		 * 根据输入的文件与输出流对文件进行打包
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
						// 向压缩文件中输出数据
						int nNumber;
						byte[] buffer = new byte[512];
						while ((nNumber = bins.read(buffer)) != -1) {
							ouputStream.write(buffer, 0, nNumber);
						}
						// 关闭创建的流对象
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
		 * 把接受的全部文件打成压缩包
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
	    * 主函数
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
	           System.out.println("参数输入不正确，请至少输入需要打包的文件夹路径");
	           return;
	       }else if (args.length==1){
	           System.out.println("未输入打包后文件路径，这里默认生成 文件 packFile.entry 在打包目录下");
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

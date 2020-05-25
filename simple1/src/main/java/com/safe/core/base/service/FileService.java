package com.safe.core.base.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.safe.core.utils.FileUtils;
import com.safe.core.utils.PackFile;

@Service
public class FileService {
	/**
	 * ���ļ���ȡ����
	* @Title: downLoadSingleFile 
	* @param response
	* @param filename
	* @param filePath
	* @return: void 
	* @author mgg
	* @date 2020��3��4��
	 */
public void downLoadSingleFile(HttpServletResponse response,String filename,String filePath){
	FileInputStream fis = null; //�ļ�������
	BufferedInputStream bis = null;
	OutputStream os = null; //�����
	try{
	    File file = new File(filePath);
	    if(file.exists()){ //�ж��ļ���Ŀ¼�Ƿ����
			filename = java.net.URLEncoder.encode(filename, "UTF-8");
			filename = new String(filename.getBytes(), "iso-8859-1");
	        response.setContentType("application/force-download");
	      //����Headers
           //response.setHeader("Content-Type","application/octet-stream");
	        response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
	        fis = new FileInputStream(file); 
	        bis = new BufferedInputStream(fis);
	        os = response.getOutputStream();
	        byte[] buffer = new byte[1024];
	        int len= bis.read(buffer);
	            while(len!= -1){
	                os.write(buffer);
	                len= bis.read(buffer);
	            }
	            bis.close();
	            fis.close();
	
	    	}else{
	    		System.out.println("�ļ�������");
	    		String error = "File not exists";
                response.sendRedirect("/imgUpload/imgList?error="+error);
	    	}
    }catch (Exception e) {
		e.printStackTrace();
	}finally{
		try{
            if(fis != null){
                fis.close();
            }
            if( bis != null ){
                bis.close();
            }
            if( os != null){
                os.flush();
                os.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

	}
}
/**
 * ͨ���ļ�·�����д���ļ�
* @Title: downLoadFiles 
* @param files
* @param response
* @return
* @return: HttpServletResponse 
* @author mgg
* @date 2020��3��4��
 */
public void  downLoadFiles(List<File> files, HttpServletResponse response) {
	try {
		//String zipFilename = "D:/tempFile.zip";
		 String directory ="D:\\Temp";
		 File directoryFile=new File(directory);
		  if(!directoryFile.isDirectory() && !directoryFile.exists()){
		        directoryFile.mkdirs();
		 }
		SimpleDateFormat formatter  = new SimpleDateFormat("yyyyMMddHHmmss");
	    String zipFileName = formatter.format(new Date())+".zip";
	    String strZipPath = directory+"\\"+zipFileName;
		File file = new File(strZipPath);
		file.createNewFile();
		if (!file.exists()) {
			file.createNewFile();
		}
		response.reset();
		// response.getWriter()
		// �����ļ������
		FileOutputStream fous = new FileOutputStream(file);
		ZipOutputStream zipOut = new ZipOutputStream(fous);
		PackFile.zipFile(files, zipOut);
		zipOut.close();
		fous.close();
		downloadZip(file, response);
	} catch (Exception e) {
		e.printStackTrace();
	}
}

private void downloadZip(File file, HttpServletResponse response) throws IOException {
	if (file.exists() == false) {
		System.out.println("��ѹ�����ļ�Ŀ¼��" + file + "������.");
		System.out.println("�ļ�������");
		String error = "File not exists";
        response.sendRedirect("/imgUpload/imgList?error="+error);
	} else {
		try {
			// ��������ʽ�����ļ���
			InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// ���response
			response.reset();
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			// �������������������ļ����ڴ˴���Ҫ��URLEncoder.encode�������д���
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(file.getName().getBytes("GB2312"), "ISO8859-1"));
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				File f = new File(file.getPath());
				f.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

}

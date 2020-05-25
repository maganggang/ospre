package com.safe.core.base.controller;

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

import org.apache.ibatis.javassist.tools.framedump;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.safe.core.base.service.FileService;
@Controller
public class FileDownLoadController {
	@Autowired
	private FileService fileSerivce;
/**
 * ���ļ�����
* @Title: down 
* @param response
* @param filePaths
* @return: void 
* @author mgg
* @date 2020��3��5��
 */
@RequestMapping(value="/downZip",method=RequestMethod.GET)
public void down(HttpServletResponse response,String filePaths){
	String[] paths=filePaths.split(",");
	List<File> files=new ArrayList<>();
	File file1=null;
	for(int i=0,j=paths.length;i<j;i++){
		file1=new File(paths[i]);
		files.add(file1);
	}
/*	
	File file2=new File("D:\\1582875880096sign.jpg");
	files.add(file2);*/
	fileSerivce.downLoadFiles(files,response);
}

/**
 * ����  --ѡ���ļ�����
 * @param response
 */
//@RequestMapping(value = "/imgdownload", method = RequestMethod.GET)
public void imgDownload(HttpServletResponse response,String [] names,String [] paths) {
    //���--��������zip�ļ���Ŀ¼
    String directory = "D:\\repository";
    File directoryFile=new File(directory);
    if(!directoryFile.isDirectory() && !directoryFile.exists()){
        directoryFile.mkdirs();
    }
    //�����������zip�ļ���Ŀ¼+�ļ���
    SimpleDateFormat formatter  = new SimpleDateFormat("yyyyMMddHHmmss");
    String zipFileName = formatter.format(new Date())+".zip";
    String strZipPath = directory+"\\"+zipFileName;

    ZipOutputStream zipStream = null;
    FileInputStream zipSource = null;
    BufferedInputStream bufferStream = null;
    File zipFile = new File(strZipPath);
    try{
        //��������ѹ�����������
        zipStream = new ZipOutputStream(new FileOutputStream(zipFile));
        for (int i = 0; i<paths.length ;i++){
            //�����ȡ��ʵ·�����ļ���
            String realFileName = java.net.URLDecoder.decode(names[i],"UTF-8");
            String realFilePath = java.net.URLDecoder.decode(paths[i],"UTF-8");
            File file = new File(realFilePath);
            //TODO:δ���ļ�������ʱ���в����������Ż���
            if(file.exists()) {
                zipSource = new FileInputStream(file);//����Ҫѹ�����ļ���ʽ��Ϊ������
                /**
                 * ѹ����Ŀ���Ǿ���������ļ�������ѹ�����ļ��б��е��б����Ϊ��Ŀ����������һ�������name�����ļ���,
                 * �ļ�����֮ǰ���ظ��ͻᵼ���ļ�������
                 */
                ZipEntry zipEntry = new ZipEntry(realFileName);//��ѹ��Ŀ¼���ļ�������
                zipStream.putNextEntry(zipEntry);//��λ��ѹ����Ŀλ�ã���ʼд���ļ���ѹ������
                bufferStream = new BufferedInputStream(zipSource, 1024 * 10);
                int read = 0;
                byte[] buf = new byte[1024 * 10];
                while((read = bufferStream.read(buf, 0, 1024 * 10)) != -1) {
                    zipStream.write(buf, 0, read);
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        //�ر���
        try {
            if(null != bufferStream) bufferStream.close();
            if(null != zipStream){
                zipStream.flush();
                zipStream.close();
            }
            if(null != zipSource) zipSource.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //�ж�ϵͳѹ���ļ��Ƿ���ڣ�true-�Ѹ�ѹ���ļ�ͨ����������ͻ��˺�ɾ����ѹ���ļ�  false-δ����
    if(zipFile.exists()){
    	downLoad(response,zipFileName,strZipPath);
        zipFile.delete();
    }
}




/**
 * ���ļ�����
* @Title: downLoad 
* @param response
* @param filename
* @param filePath
* @return: void 
* @author mgg
* @date 2020��3��4��
* http://192.168.0.152:8080/simple1/down?filePath=D:\1582876082164sign.jpg&fileName=qw.jpg
 */
@RequestMapping("/down")
public void downLoad(HttpServletResponse response,@RequestParam("fileName")String filename,@RequestParam("filePath")String filePath){
	fileSerivce.downLoadSingleFile(response, filename, filePath);
	}
}

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
 * 多文件下载
* @Title: down 
* @param response
* @param filePaths
* @return: void 
* @author mgg
* @date 2020年3月5日
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
 * 下载  --选择文件下载
 * @param response
 */
//@RequestMapping(value = "/imgdownload", method = RequestMethod.GET)
public void imgDownload(HttpServletResponse response,String [] names,String [] paths) {
    //存放--服务器上zip文件的目录
    String directory = "D:\\repository";
    File directoryFile=new File(directory);
    if(!directoryFile.isDirectory() && !directoryFile.exists()){
        directoryFile.mkdirs();
    }
    //设置最终输出zip文件的目录+文件名
    SimpleDateFormat formatter  = new SimpleDateFormat("yyyyMMddHHmmss");
    String zipFileName = formatter.format(new Date())+".zip";
    String strZipPath = directory+"\\"+zipFileName;

    ZipOutputStream zipStream = null;
    FileInputStream zipSource = null;
    BufferedInputStream bufferStream = null;
    File zipFile = new File(strZipPath);
    try{
        //构造最终压缩包的输出流
        zipStream = new ZipOutputStream(new FileOutputStream(zipFile));
        for (int i = 0; i<paths.length ;i++){
            //解码获取真实路径与文件名
            String realFileName = java.net.URLDecoder.decode(names[i],"UTF-8");
            String realFilePath = java.net.URLDecoder.decode(paths[i],"UTF-8");
            File file = new File(realFilePath);
            //TODO:未对文件不存在时进行操作，后期优化。
            if(file.exists()) {
                zipSource = new FileInputStream(file);//将需要压缩的文件格式化为输入流
                /**
                 * 压缩条目不是具体独立的文件，而是压缩包文件列表中的列表项，称为条目，就像索引一样这里的name就是文件名,
                 * 文件名和之前的重复就会导致文件被覆盖
                 */
                ZipEntry zipEntry = new ZipEntry(realFileName);//在压缩目录中文件的名字
                zipStream.putNextEntry(zipEntry);//定位该压缩条目位置，开始写入文件到压缩包中
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
        //关闭流
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
    //判断系统压缩文件是否存在：true-把该压缩文件通过流输出给客户端后删除该压缩文件  false-未处理
    if(zipFile.exists()){
    	downLoad(response,zipFileName,strZipPath);
        zipFile.delete();
    }
}




/**
 * 单文件下载
* @Title: downLoad 
* @param response
* @param filename
* @param filePath
* @return: void 
* @author mgg
* @date 2020年3月4日
* http://192.168.0.152:8080/simple1/down?filePath=D:\1582876082164sign.jpg&fileName=qw.jpg
 */
@RequestMapping("/down")
public void downLoad(HttpServletResponse response,@RequestParam("fileName")String filename,@RequestParam("filePath")String filePath){
	fileSerivce.downLoadSingleFile(response, filename, filePath);
	}
}

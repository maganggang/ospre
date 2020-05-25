package com.safe.core.base.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
@Controller
public class FileUpLoadController {
	/*
     * 采用file.Transto 来保存上传的文件
     */
    @RequestMapping("upload")
    @ResponseBody
    public String  fileUpload2(@RequestParam("file") CommonsMultipartFile file,String fileName) throws IOException {
         long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+file.getOriginalFilename());
        String path="D:/"+new Date().getTime()+fileName;
         
        File newFile=new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        long  endTime=System.currentTimeMillis();
        System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return "success"; 
    }
}

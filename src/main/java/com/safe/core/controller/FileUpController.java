package com.safe.core.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
@Controller
public class FileUpController {
	/*
     * ����file.Transto �������ϴ����ļ�
     */
    @RequestMapping("upload")
    @ResponseBody
    public String  fileUpload2(@RequestParam("file") CommonsMultipartFile file,String fileName) throws IOException {
         long  startTime=System.currentTimeMillis();
        System.out.println("fileName��"+file.getOriginalFilename());
        String path="D:/"+new Date().getTime()+fileName;
         
        File newFile=new File(path);
        //ͨ��CommonsMultipartFile�ķ���ֱ��д�ļ���ע�����ʱ��
        file.transferTo(newFile);
        long  endTime=System.currentTimeMillis();
        System.out.println("������������ʱ�䣺"+String.valueOf(endTime-startTime)+"ms");
        return "success"; 
    }
}

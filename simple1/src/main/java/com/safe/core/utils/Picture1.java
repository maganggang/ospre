package com.safe.core.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

/**
 * ͼƬ�ָ���ϲ�
 * @author Administrator
 *
 */
public class Picture1 {
	public static void main(String[] args) throws IOException {
		splitImage();
		mergeImage();
	}
	private static void splitImage() throws IOException {
		 
        //String originalImg = "C:\\img\\split\\a380_1280x1024.jpg";
        String originalImg = "D:\\aa.jpg";
        // �����ͼ
        File file = new File(originalImg);
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis);
 
        // �ָ��4*4(16)��Сͼ
        int rows = 3;
        int cols = 3;
        int chunks = rows * cols;
 
        // ����ÿ��Сͼ�Ŀ�Ⱥ͸߶�
        int chunkWidth = image.getWidth() / cols;
        int chunkHeight = image.getHeight() / rows;
 
        int count = 0;
        BufferedImage imgs[] = new BufferedImage[chunks];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                //����Сͼ�Ĵ�С������
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());
 
                //д��ͼ������
                Graphics2D gr = imgs[count++].createGraphics();
                gr.drawImage(image, 0, 0,
                        chunkWidth, chunkHeight,
                        chunkWidth* y, chunkHeight * x,
                        chunkWidth * y + chunkWidth,
                        chunkHeight * x + chunkHeight, null);
                gr.dispose();
            }
        }
 
        // ���Сͼ
        for (int i = 0; i < imgs.length; i++) {
            //ImageIO.write(imgs[i], "jpg", new File("C:\\img\\split\\img" + i + ".jpg"));
            ImageIO.write(imgs[i], "jpg", new File("d:\\mgg_" + i + ".jpg"));
        }
 
        System.out.println("��ɷָ");
 }
	private static void mergeImage() throws IOException {
		 
        int rows = 3;
        int cols = 3;
        int chunks = rows * cols;
 
        int chunkWidth, chunkHeight;
        int type;
 
        //����Сͼ
        File[] imgFiles = new File[chunks];
        for (int i = 0; i < chunks; i++) {
            imgFiles[i] = new File("d:\\mgg_" + i + ".jpg");
        }
 
        //����BufferedImage
        BufferedImage[] buffImages = new BufferedImage[chunks];
        for (int i = 0; i < chunks; i++) {
            buffImages[i] = ImageIO.read(imgFiles[i]);
        }
        type = buffImages[0].getType();
        chunkWidth = buffImages[0].getWidth();
        chunkHeight = buffImages[0].getHeight();
 
        //����ƴ�Ӻ�ͼ�Ĵ�С������
        BufferedImage finalImg = new BufferedImage(chunkWidth * cols, chunkHeight * rows, type);
 
        //д��ͼ������
        int num = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                finalImg.createGraphics().drawImage(buffImages[num], chunkWidth * j, chunkHeight * i, null);
                num++;
            }
        }
 
        //���ƴ�Ӻ��ͼ��
        ImageIO.write(finalImg, "jpeg", new File("d:\\mggcat.jpg"));
        InputStream is=(InputStream) ImageIO.createImageInputStream(finalImg);
 
        System.out.println("���ƴ�ӣ�");
    }
	 public static InputStream mergeImage_1(String path,String name,String prefix) throws IOException {
		 
	        int rows = 3;
	        int cols = 3;
	        int chunks = rows * cols;
	 
	        int chunkWidth, chunkHeight;
	        int type;
	 
	        //����Сͼ
	        File[] imgFiles = new File[chunks];
	        for (int i = 0; i < chunks; i++) {
	            imgFiles[i] = new File(path+name +"_"+ i+"."+prefix);
	        }
	 
	        //����BufferedImage
	        BufferedImage[] buffImages = new BufferedImage[chunks];
	        for (int i = 0; i < chunks; i++) {
	            buffImages[i] = ImageIO.read(imgFiles[i]);
	        }
	        type = buffImages[0].getType();
	        chunkWidth = buffImages[0].getWidth();
	        chunkHeight = buffImages[0].getHeight();
	 
	        //����ƴ�Ӻ�ͼ�Ĵ�С������
	        BufferedImage finalImg = new BufferedImage(chunkWidth * cols, chunkHeight * rows, type);
	 
	        //д��ͼ������
	        int num = 0;
	        for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < cols; j++) {
	                finalImg.createGraphics().drawImage(buffImages[num], chunkWidth * j, chunkHeight * i, null);
	                num++;
	            }
	        }
	 
	        //���ƴ�Ӻ��ͼ��
	        //ImageIO.write(finalImg, "jpeg", new File("d:\\img2\\finalImg.jpg"));
	        //InputStream is=(InputStream) ImageIO.createImageInputStream(finalImg);
	        finalImg.flush();
	        ByteArrayOutputStream bs = new ByteArrayOutputStream();
	        InputStream is=null;
	        ImageOutputStream imOut;
	        imOut = ImageIO.createImageOutputStream(bs);
	        ImageIO.write(finalImg, prefix,imOut);
	        is= new ByteArrayInputStream(bs.toByteArray());
	        System.out.println("���ƴ�ӣ�");
	        return is;
	    }
	/*��Ȩ����������ΪCSDN��������С������ԭ�����£���ѭCC 4.0 BY-SA��ȨЭ�飬ת���븽��ԭ�ĳ������Ӽ���������
	ԭ�����ӣ�https://blog.csdn.net/zhanglu1236789/java/article/details/80852615
*/}

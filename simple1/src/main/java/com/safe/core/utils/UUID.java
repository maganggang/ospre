package com.safe.core.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class UUID {
	private static Date date = new Date();
    private static StringBuilder buf = new StringBuilder();
    private static int seq = 0;
    private static final int ROTATION = 99999;
 
    public static synchronized long next() {
        if (seq > ROTATION)
            seq = 0;
        buf.delete(0, buf.length());
        date.setTime(System.currentTimeMillis());
        String str = String.format("%1$tY%1$tm%1$td%1$tk%1$tM%1$tS%2$05d", date, seq++);
        return Long.parseLong(str);
    }
    
    private UUID(){
        
    }
    public static void main(String args[]) {
        try {
            FileReader read = new FileReader("D:\\Users\\dell\\AppData\\Roaming\\google\\WIN-202010\\safe(2).sql");
            BufferedReader br = new BufferedReader(read);
            String row;

            int rownum = 1;

            int fileNo = 1;
            FileWriter fw = new FileWriter("D:/text"+fileNo +".sql");
            while ((row = br.readLine()) != null) {
                rownum ++;
                fw.append(row + "\r\n");

                if((rownum / 464183) > (fileNo - 1)){
                    fw.close();
                    fileNo ++ ;
                    fw = new FileWriter("D:/text"+fileNo +".sql");
                }
            }
            fw.close();
            System.out.println("rownum="+rownum+";fileNo="+fileNo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

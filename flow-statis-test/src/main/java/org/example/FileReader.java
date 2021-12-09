package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

@Component
public class FileReader {

    Logger logger = LoggerFactory.getLogger(FileReader.class);
    public static String path = "";

    @Scheduled(cron = "0/5 * * * * *")
    public void cron(){
        if(!"".equals(path)){
            logger.info("===path===:"+path);
            System.out.println("测试定时任务!");
            //String path = "C:\\Users\\86188\\Desktop\\bak\\fileReader.txt";
            File file = new File(path);
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = bis.read(bytes)) != -1) {
                    System.out.println(new String(bytes));
                }
            }catch (Exception e){
                logger.error("read file fail ！"+e.getMessage());
            }finally {
                try {
                    bis.close();
                    bis.close();
                }catch (Exception e){
                    logger.error("read file fail ！"+e.getMessage());
                }
            }
        }
    }
}

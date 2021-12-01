package com.sunny.export;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class fileReaderSche {

    @Scheduled(cron = "0/5 * * * * ?")
    public void readFileInfo() {
        System.out.println("当前时间：" + new Date());
    }
}

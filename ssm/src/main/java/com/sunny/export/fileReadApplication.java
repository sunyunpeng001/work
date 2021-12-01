package com.sunny.export;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class fileReadApplication {

    public static void main(String[] args) {
        SpringApplication.run(fileReadApplication.class,args);
    }
}

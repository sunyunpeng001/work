package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableScheduling
public class App 
{
    public static void main( String[] args )
    {
        if(args != null && args.length>0){
            SpringApplication.run(App.class, args);
            System.out.println( "Hello World!" );
            FileReader.path = args[0];
        }else{
            System.out.println("缺少参数!");
        }
    }
}

package com.sunny.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentContoller {

    @RequestMapping("/test")
    public String test(){
        System.out.println("hello ssm!1111111111");
        return "forward:/index.jsp";
    }
}

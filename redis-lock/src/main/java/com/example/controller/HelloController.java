package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/hello")
    public String hello() {
        String s = stringRedisTemplate.opsForValue().get("goodsNum");
        System.out.println("s :"+s);
        int i = 0;
        System.out.println(" i ="+(i++));
        return "hello,this is a springboot demo";
    }
}

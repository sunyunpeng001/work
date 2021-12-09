package com.sunny.test;

import com.sunny.Controller.UserController;
import com.sunny.beans.User;
import com.sunny.service.UserService;
import com.sunny.service.impl.UserServiceimpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {

    private ClassPathXmlApplicationContext ioc;

    @Before
    public void before(){
        ioc = new ClassPathXmlApplicationContext("spring_aop.xml");
    }

    /**
     * 测试连通性
     */
    @Test
    public void test01(){
        Object userService = ioc.getBean(UserService.class);
        System.out.println(userService);
    }

    /**
     * 测试切面方法调用
     */
    @Test
    public void testGetUser(){
        UserService userService = ioc.getBean(UserService.class);
        System.out.println(userService.getUser("1"));
    }

    /**
     * 测试切面后置异常通知
     */
    @Test
    public void testGetUserEx(){
        UserService userService = ioc.getBean(UserService.class);
        System.out.println(userService.getUser(null));
    }

    /**
     * 测试 根据注解找到方法并进行前置通知
     */
    @Test
    public void testQuery(){
        UserController bean = ioc.getBean(UserController.class);
        bean.query(new User(),1,20);
    }

    
}

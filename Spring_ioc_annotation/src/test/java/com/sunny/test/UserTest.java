package com.sunny.test;

import com.sunny.beans.User;
import com.sunny.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {

    ClassPathXmlApplicationContext ioc;

    @Before
    public void Before(){
        ioc = new ClassPathXmlApplicationContext("spring-ioc-annotation.xml");
    }

    /**
     * 测试通过注解是否已经注入 bean
     */
    @Test
    public void test01(){
        UserController bean = ioc.getBean(UserController.class);
        System.out.println(bean);
    }

    /**
     * 1 :通过注解 @Value设置 属性的值
     * 2: @Value 中通过 ${} 获取外部文件中的值
     * #{}获取其他 bean的属性值  spel 方式
     * 3：@Value 中可以设置硬编码
     *
     */
    @Test
    public void test02(){
        User bean = ioc.getBean(User.class);
        System.out.println(bean.getUserId()+","+bean.getUserName()+","+bean.getMysqlName()+","+bean.getRoleName());
    }

    /**
     * @Autowried 自动注入
     * */
    @Test
    public void test03(){
        UserController bean = ioc.getBean(UserController.class);
        bean.getuser();
    }

    /**
     * @DependsOn("role") //Role 先于 User加载
     * */
    @Test
    public void test04(){

    }

    /**
     * @DependsOn("role") //Role 先于 User加载
     * */
    @Test
    public void test05(){
        User bean = ioc.getBean(User.class);
        System.out.println(bean);
    }

}

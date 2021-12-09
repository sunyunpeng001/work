package com.sunny.test;

import com.sunny.beans.Person;
import com.sunny.beans.Role;
import com.sunny.beans.Wife;
import com.sunny.register.registerResourse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class javaConfigTest {

    private AnnotationConfigApplicationContext ioc;
    @Before
    public void before(){
        ioc = new AnnotationConfigApplicationContext(registerResourse.class);
    }


    @Test
    public void dataSourceTest(){
        Object dataSource = ioc.getBean("dataSource");
        System.out.println(dataSource);
    }

    @Test
    public void dataSourceTest2(){
        Object dataSource = ioc.getBean("dataSource2");
        System.out.println(dataSource);
    }

    @Test
    public void dataSourceTest3(){
        Object dataSource = ioc.getBean("dataSource33");
        System.out.println(dataSource);
    }

    @Test
    public void test01(){
        Object bean = ioc.getBean("user");
        System.out.println(bean);
    }

    @Test
    public void testUser2(){
        Object bean = ioc.getBean("user2");
        System.out.println(bean);
    }

    @Test
    public void TestImportSecector(){
        Person bean = ioc.getBean(Person.class);
        Wife bean1 = ioc.getBean(Wife.class);
        System.out.println(bean.getName()+","+bean1.getName());
    }

    @Test
    public void TestImportBeanDefinitionRegistrar(){
        Role role = (Role) ioc.getBean("role");
        System.out.println(role.getName());

    }

}

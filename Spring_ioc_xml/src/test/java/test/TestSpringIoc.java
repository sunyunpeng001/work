package test;

import com.sunny.beans.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringIoc {

    ClassPathXmlApplicationContext ioc;

    @Before
    public void before(){


        ioc = new ClassPathXmlApplicationContext("spring-ioc.xml");
    }

    /**
     * 通过类获取 bean
     */
    @Test
    public void Test01(){
        User bean = ioc.getBean(User.class);
        System.out.println(bean);
    }

    /**
     * 通过别名获取 bean
     */
    @Test
    public void test02(){
        User user2 = (User) ioc.getBean("user2");
        System.out.println(user2);

    }

    /**
     * 通过set注入 bean 的属性值
     */
    @Test
    public void test03(){
        Object user4 = ioc.getBean("user4");
        System.out.println(user4);
    }

    /**
     * 通过set注入 bean 的属性值
     */
    @Test
    public void test04(){
        Object role = ioc.getBean("role");
        System.out.println(role);
    }

    /**
     * 复杂数据注入
     */
    @Test
    public void test05(){
        User bean = (User) ioc.getBean("user5");
        System.out.println(bean);
    }

    /**
     * 使用P命名空间注入
     */
    @Test
    public void test06(){
        User bean = (User) ioc.getBean("user6");
        System.out.println(bean);
    }

    /**
     * 使用 import 导入其他配置文件
     */
    @Test
    public void test07(){
        Object order = ioc.getBean("order");
        System.out.println(order);
    }
    @Test
    public void test08(){
        User bean = (User) ioc.getBean("user7");
        System.out.println(bean);
    }

}

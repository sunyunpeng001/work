package test;

import com.sunny.beans.Order;
import com.sunny.beans.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringIoc2 {

    ClassPathXmlApplicationContext ioc;

    @Before
    public void before(){
        ioc = new ClassPathXmlApplicationContext("spring-ioc2.xml");
    }

    @Test
    public void Test01(){
        System.out.println("Spring加载");
    }
    @Test
    public void Test02(){
        User bean = ioc.getBean(User.class);
        System.out.println(bean);
    }
    @Test
    public void Test03(){
        Order bean = ioc.getBean(Order.class);
        Order bean2 = ioc.getBean(Order.class);
        System.out.println(bean2);

    }

    @Test
    public void Test04(){
        Order bean = ioc.getBean(Order.class);
        Order bean2 = ioc.getBean(Order.class);
        System.out.println(bean);

    }
    @Test
    public void Test05(){
        User bean = ioc.getBean(User.class);
        System.out.println(bean);
    }
    @Test
    public void Test06(){
        Object dataSource = ioc.getBean("dataSource");
        System.out.println(dataSource);
    }

}

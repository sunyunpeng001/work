package com.sunny.register;


import com.sunny.beans.MyImportSelector;
import com.sunny.beans.MyImportBeanDefinitionRegistrar;
import com.sunny.beans.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan
@Import({MyImportSelector.class,MyImportBeanDefinitionRegistrar.class}) //导入实现类，类中注册多个 bean
public class secondResoure {

    @Bean
    public User user2(){
        return  new User();
    }


}

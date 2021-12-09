package com.sunny.register;

import com.alibaba.druid.pool.DruidDataSource;
import com.sunny.beans.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.sunny") //扫描包
@PropertySource("classpath:db.properties") //@PropertySource 可以引入多个外部资源文件
@Import(secondResoure.class) // 引入其他的配置类
public class registerResourse {

    @Value("${mysql.name}")
    private String name;
    @Value("${mysql.password}")
    private String poassword;
    @Value("${mysql.url}")
    private String url;
    @Value("${mysql.driverClassName}")
    private String driverClassName;

    /**
     * 通过注解注册第三方 Bean
     * @return
     *
     * @Scope("") 可以设置作用域:单例,多例等等
     * singleton单例模式(默认):全局有且仅有一个实例
     * prototype原型模式:每次获取Bean的时候会有一个新的实例
     */
    @Bean
    @Scope("singleton")
    public DruidDataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setName(name);
        dataSource.setPassword(poassword);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    /**
     *  依赖外部bean ,可以直接拿来使用，例如 user
     * @param user
     * @return
     */
    @Bean
    @Scope("singleton")
    public DruidDataSource dataSource2(User user){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setName(name);
        dataSource.setPassword(poassword);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        System.out.println("dataSource2:"+user.getUserName());
        return dataSource;
    }

    @Bean
    @Scope("singleton")
    public User user3(){
        return  new User();
    }

    /**
     * 依赖内部bean ,可以直接拿来使用，例如 user3
     * @return
     */
    @Bean(value = "dataSource33")
    public DruidDataSource dataSource3(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setName(name);
        dataSource.setPassword(poassword);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        System.out.println("dataSource3:"+user3().getUserName());
        return dataSource;
    }
}

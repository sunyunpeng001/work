package com.sunny.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
//@DependsOn("role") //Role 先于 User加载
public class User {

    @Value("1001")
    private int userId;
    @Value("孙云鹏")
    private String userName;
    @Value("${mysql.name}")
    private String mysqlName;
    @Value("#{role.roleName}")
    private String roleName;

    public User() {
        System.out.println("user 加载");
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMysqlName() {
        return mysqlName;
    }

    public void setMysqlName(String mysqlName) {
        this.mysqlName = mysqlName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

package com.sunny.dao.impl;

import com.sunny.beans.User;
import com.sunny.dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public User getUser(String id) {
        if(id == null){
            throw new NullPointerException("id 不能为空");
        }
        System.out.println("查询 user");
        return new User();
    }

    @Override
    public void addUser(User user) {
        System.out.println("添加 User");
    }
}

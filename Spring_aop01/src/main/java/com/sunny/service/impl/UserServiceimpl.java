package com.sunny.service.impl;

import com.sunny.beans.User;
import com.sunny.dao.UserDao;
import com.sunny.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public User getUser(String id) {
        return userDao.getUser(id);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }
}

package com.sunny.service.Impl;

import com.sunny.dao.UserDao;
import com.sunny.service.UserSercive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserSercive {

    @Autowired
    UserDao userDao;

    @Override
    public void getUser() {
        userDao.getUser();
    }
}

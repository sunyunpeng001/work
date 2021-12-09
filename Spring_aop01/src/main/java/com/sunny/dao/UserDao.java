package com.sunny.dao;

import com.sunny.beans.User;

public interface UserDao {

    public User getUser(String id);

    public void addUser(User user);
}

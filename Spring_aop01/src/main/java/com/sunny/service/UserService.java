package com.sunny.service;

import com.sunny.beans.User;

public interface UserService {

    public User getUser(String id);

    public void addUser(User user);
}

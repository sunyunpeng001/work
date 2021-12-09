package com.sunny.controller;

import com.sunny.service.UserSercive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    UserSercive  userSercive;

    public void getuser(){
        userSercive.getUser();
    }

}

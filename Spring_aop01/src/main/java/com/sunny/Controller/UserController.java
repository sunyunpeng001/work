package com.sunny.Controller;

import com.sunny.beans.User;
import com.sunny.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *
     */
    @RequestMapping("/query")
    public void query(User user,int pageSize,int pageNum){
        System.out.println("测试注解切点aop");
    }

}

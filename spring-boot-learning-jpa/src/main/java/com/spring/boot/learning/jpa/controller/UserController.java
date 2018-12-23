package com.spring.boot.learning.jpa.controller;

import com.spring.boot.learning.jpa.entity.User;
import com.spring.boot.learning.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lianpeng
 * @date 2018/12/23 23:23
 * @since
 **/
@RestController
@RequestMapping("user")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping("add")
    public String addUser( @RequestBody User user ){

         userService.addUser( user );
        return "success";
    }


    @RequestMapping("get/{id}")
    public User getUser( @PathVariable Long id ){

        return userService.getUser( id );
    }

}

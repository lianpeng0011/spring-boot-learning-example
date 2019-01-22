package com.spring.boot.springbootlearningsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lianp
 * @date 2019/1/22 10:29
 * @since
 **/
@Controller
public class HomeController {


    @GetMapping( "/home" )
    public String  home(){
        return "home";
    }

    @GetMapping( value = {"/login","index"} )
    public String loginPage(){

        return "login";
    }

    @PostMapping( "/user/login" )
    public String login( @RequestBody HttpServletRequest request ){
        System.out.println(request.getParameter( "userName" ));
        System.out.println(request.getParameter( "password" ));
        return "home";
    }
}

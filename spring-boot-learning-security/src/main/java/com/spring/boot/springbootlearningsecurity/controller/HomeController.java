package com.spring.boot.springbootlearningsecurity.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author lianp
 * @date 2019/1/22 10:29
 * @since
 **/
@Controller
public class HomeController {


    @GetMapping( value = { "/home", "" } )
    public String home() {
        return "home";
    }

    @PostMapping( "/success" )
    public String success(){

        System.out.print("登陆成功");
        return "home";
    }

    @GetMapping( value = { "/login", "/index" } )
    public String loginPage() {

        return "login";
    }

    @ResponseBody
    @RequestMapping("/personal_center")
    public void login(HttpServletRequest request) {
        System.out.println("登录成功");
    }

    @GetMapping( "/error" )
    public String error(){

        return "error";
    }

    @GetMapping("/user/get")
    @ResponseBody
    @Secured( "ROLE_USER" )
    public String getUser(){

        return "user";
    }

    @GetMapping("/admin/get")
    @ResponseBody
    @Secured( "ROLE_ADMIN" )
    public String getAdmin(){

        return "admin";
    }
}

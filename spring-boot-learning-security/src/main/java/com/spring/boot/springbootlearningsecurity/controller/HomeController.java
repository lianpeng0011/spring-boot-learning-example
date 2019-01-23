package com.spring.boot.springbootlearningsecurity.controller;

import org.springframework.http.HttpStatus;
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

    @GetMapping( value = { "/login", "/index" } )
    public String loginPage() {

        return "login";
    }

    @PostMapping( "/user/login" )
    @ResponseStatus( value= HttpStatus.OK)
    public String login( HttpServletRequest request,@RequestBody Map<String,Object> map ) {
        System.out.println( request.getParameter( "userName" ) );
        System.out.println( request.getParameter( "password" ) );
        return "home";
    }
    @GetMapping( "/error" )
    public String error(){

        return "error";
    }
}

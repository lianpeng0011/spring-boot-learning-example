package com.spring.boot.learning.jpa.controller;

import com.spring.boot.learning.jpa.entity.Book;
import com.spring.boot.learning.jpa.entity.User;
import com.spring.boot.learning.jpa.respository.BookRespository;
import com.spring.boot.learning.jpa.respository.UserRespository;
import com.spring.boot.learning.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lianpeng
 * @date 2018/12/23 23:23
 * @since
 **/
@RestController
@RequestMapping( "user" )
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private BookRespository bookRespository;

    @Autowired
    private UserRespository userRespository;

    @RequestMapping( "add" )
    public String addUser( @RequestBody User user ) {

//        userService.addUser( user );
        userRespository.save( user );
        return "success";
    }


    @RequestMapping( "get/{id}" )
    public User getUser( @PathVariable Long id ) {

        return userService.getUser( id );
    }

    @RequestMapping( "getAll" )
    public List<Book> getAll() {

        return bookRespository.findAll();
    }

    @RequestMapping( "getUserAll" )
    @ResponseBody
    public Object getUserAll() {

        Sort sort = new Sort( Sort.Direction.DESC, "id", "age" );
        Pageable pageable = new PageRequest( 0, 1 );
        return userRespository.findAll( pageable );
    }

    @RequestMapping( "getUserByName/{name}" )
    public User getUserByName( @PathVariable String name ) {

        return userRespository.getFirstByNameIgnoreCase( name );
    }


}

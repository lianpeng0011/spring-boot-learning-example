package com.spring.boot.controller;

import com.spring.boot.domian.Person;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author lianp
 * @date 2019/1/3 17:04
 * @since
 **/
@RestController
@RequestMapping("/person/")
public class PersonController {


    @PostMapping("save")
    public Person save(@Valid @RequestBody Person person ){

        return person;
    }
}

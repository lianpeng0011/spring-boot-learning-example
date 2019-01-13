package com.spring.boot.webservices.repository;

import com.spring.boot.webservices.domain.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lianpeng
 * @date 2019/1/13 18:45
 * @since
 **/
@Repository
public class UserRepository {

    private Map<Long, User> userMap = new HashMap<>();

    @PostConstruct
    public void init(){
        userMap.put( 1L, createUser( 1, "lianpeng", 29 ) );
        userMap.put( 2L, createUser( 2, "xiaomage", 32 ) );
        userMap.put( 3L, createUser( 3, "刘德华", null ) );
    }

    private User createUser(long id,String name,Integer age){
        User user = new User();
        user.setId( id );
        user.setName( name );
        user.setAge( age );
        return user;
    }

    public User getUserById(Long id){
        return userMap.get( id );
    }
}

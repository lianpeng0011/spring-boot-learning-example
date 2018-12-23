package com.spring.boot.learning.jpa.service;

import com.spring.boot.learning.jpa.entity.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * @author lianpeng
 * @date 2018/12/23 23:22
 * @since
 **/
@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void  addUser( User user ){
        entityManager.persist( user );
    }


    public User getUser(Long id){

        return entityManager.find( User.class, id );
    }
}

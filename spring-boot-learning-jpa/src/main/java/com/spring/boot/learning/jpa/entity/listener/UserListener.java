package com.spring.boot.learning.jpa.entity.listener;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

/**
 * @author lianpeng
 * @date 2018/12/25 21:36
 * @since
 **/
public class UserListener  {

    @PrePersist
    public void prePersist(Object source){
        System.out.println("PrePersist :" + source);
    }

    @PostPersist
    public void postPersist(Object source){
        System.out.println("postPersist :" + source);
    }
}

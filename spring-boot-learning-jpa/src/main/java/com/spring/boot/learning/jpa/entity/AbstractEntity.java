package com.spring.boot.learning.jpa.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author lianpeng
 * @date 2018/12/25 21:14
 * @since
 **/
@MappedSuperclass
@Access( AccessType.FIELD )
public class AbstractEntity {

    @CreatedDate
    @Column(name = "CREATE_TIME", columnDefinition = "datetime ")
    private Date createTime;


}

package com.spring.boot.learning.jpa.entity;

import javax.persistence.*;

/**
 * @author lianpeng
 * @date 2018/12/25 21:40
 * @since
 **/
@Entity
@Table(name="books")
public class Book extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}

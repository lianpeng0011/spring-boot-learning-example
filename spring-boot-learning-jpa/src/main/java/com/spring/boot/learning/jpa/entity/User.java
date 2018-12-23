package com.spring.boot.learning.jpa.entity;

import javax.persistence.*;

/**
 * @author lianpeng
 * @date 2018/12/23 20:51
 * @since
 **/
@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    @Column(name = "user_name", length = 50)
    private String name;

    @Column( length = 3)
    private int age;

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

    public int getAge() {
        return age;
    }

    public void setAge( int age ) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", age=" + age +
               '}';
    }
}

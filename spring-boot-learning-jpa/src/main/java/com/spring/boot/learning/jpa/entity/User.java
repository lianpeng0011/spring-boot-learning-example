package com.spring.boot.learning.jpa.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author lianpeng
 * @date 2018/12/23 20:51
 * @since
 **/
@Entity
@Table
@EntityListeners( AuditingEntityListener.class )
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    @Column(name = "user_name", length = 50)
    private String name;

    @Column( length = 3)
    private int age;

    @JoinColumn(name = "CARD_ID")
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private CreditCard creditCard;

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

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard( CreditCard creditCard ) {
        this.creditCard = creditCard;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", age=" + age +
               ", creditCard" + creditCard +
               '}';
    }
}

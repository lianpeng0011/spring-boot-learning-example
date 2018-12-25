package com.spring.boot.learning.jpa.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lianpeng
 * @date 2018/12/25 21:12
 * @since
 **/
@Entity(name = "CreditCard")
@Table(name="T_CreditCard")
public class CreditCard extends AbstractEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 126)
    private String number;

    @Column(name="REG_DATE")
    private Date regDate;

    @OneToOne
    private User user;


    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber( String number ) {
        this.number = number;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate( Date regDate ) {
        this.regDate = regDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
               "id=" + id +
               ", number='" + number + '\'' +
               ", regDate=" + regDate +
               ", user=" + user +
               '}';
    }
}

package com.spring.boot.learning.jpa.respository;

import com.spring.boot.learning.jpa.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * @author lianpeng
 * @date 2018/12/25 21:44
 * @since
 **/
@Repository
@Transactional
public class BookRespository extends SimpleJpaRepository<Book,Long> {

    @Autowired
    public BookRespository(  EntityManager em ) {
        super( Book.class, em );
    }
}

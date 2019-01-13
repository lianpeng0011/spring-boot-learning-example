package com.spring.boot.learning.jpa.respository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author lianp
 * @date 2018/12/27 15:42
 * @since
 **/
@NoRepositoryBean
public interface MyBaseRepository<T ,ID extends Serializable> extends Repository<T, ID> {

    List<T> findAll( Sort sort );

    T  getByName(String userName);
    Slice<T> findAll( Pageable pageable );

    <S extends T> S save(S var1);


}

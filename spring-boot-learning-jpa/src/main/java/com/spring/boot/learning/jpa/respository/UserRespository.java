package com.spring.boot.learning.jpa.respository;

import com.spring.boot.learning.jpa.entity.User;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lianp
 * @date 2018/12/27 14:45
 * @since
 **/
@Repository
public interface UserRespository extends MyBaseRepository<User,Long> {


    @NonNull List<User> readByNameIsNull();

    @NonNull User getFirstByNameIgnoreCase( String name );

}

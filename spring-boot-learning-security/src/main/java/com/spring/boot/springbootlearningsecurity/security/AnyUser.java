package com.spring.boot.springbootlearningsecurity.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 继承{@link User} 用于实现用户本地化属性
 * @author lianp
 * @date 2019/1/22 17:38
 * @since
 **/
public class AnyUser extends User {

    private String loginName;

    private String nickName;


    AnyUser( String username, String password, Collection<? extends GrantedAuthority> authorities ) {
        super( username, password, authorities );
    }


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName( String loginName ) {
        this.loginName = loginName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName( String nickName ) {
        this.nickName = nickName;
    }
}

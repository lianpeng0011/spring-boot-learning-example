package com.spring.boot.springbootlearningsecurity.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lianp
 * @date 2019/1/24 11:04
 * @since
 **/
public class AnyPassword implements PasswordEncoder {

    @Override
    public String encode( CharSequence rawPassword ) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches( CharSequence rawPassword, String encodedPassword ) {
        return encodedPassword.equals( rawPassword.toString() );
    }
}

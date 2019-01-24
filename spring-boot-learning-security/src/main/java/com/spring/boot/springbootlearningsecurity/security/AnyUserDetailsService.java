package com.spring.boot.springbootlearningsecurity.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * 实现{@link UserDetailsService} 实现本地化的用户登陆验证
 *
 * @author lianp
 * @date 2019/1/22 17:43
 * @since
 **/
@Service
public class AnyUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        if ( !"system".equals( username ) && !"admin".equals( username ) ) {
            throw  new UsernameNotFoundException("用户名不存在");
        }
        Set<SimpleGrantedAuthority> auths = new HashSet<>();
        //给用户添加权限
        auths.add( new SimpleGrantedAuthority("ROLE_USER") );
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        AnyUser user = new AnyUser( username, encoder.encode( "kingsmart" ), auths );
        user.setNickName( "管理员" );
        user.setLoginName( username );
        return user;
    }
}

package com.spring.boot.springbootlearningsecurity.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * @author lianp
 * @date 2019/1/22 16:44
 * @since
 **/
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure( HttpSecurity http ) throws Exception {

        http.authorizeRequests().anyRequest().fullyAuthenticated()
                .and()
                .formLogin()
                .usernameParameter( "username" ).passwordParameter( "pwd" )
                .loginPage( "/login" )
                .loginProcessingUrl("/user/login")
                .failureForwardUrl( "/error" )
                .successForwardUrl( "/home" )
                .permitAll();


        //CSRF
//        http.csrf().csrfTokenRepository( new CookieCsrfTokenRepository() ).requireCsrfProtectionMatcher(
//                ( request ) -> request.getMethod().equals( "POST" ));
    }
}

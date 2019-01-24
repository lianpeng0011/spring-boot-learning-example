package com.spring.boot.springbootlearningsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * @author lianp
 * @date 2019/1/22 16:44
 * @since
 **/
@EnableWebSecurity
@EnableGlobalMethodSecurity( prePostEnabled = true, securedEnabled = true )
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService getUserDetailsService() {

        return new AnyUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService( getUserDetailsService() ).passwordEncoder( passwordEncoder() );
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {

        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter( "username" ).passwordParameter( "pwd" )
                .loginPage( "/login" )
                .loginProcessingUrl( "/login" )
                .failureForwardUrl( "/error" )
                .successForwardUrl( "/success" ) //登陆成功执行的url 该url必须为post
                .permitAll()
                .and()
                .logout()
                .logoutUrl( "/logout" )
                .logoutSuccessUrl( "/login" )
                .permitAll();


        //CSRF
        http.csrf().csrfTokenRepository( new CookieCsrfTokenRepository() ).requireCsrfProtectionMatcher(
                ( request ) -> request.getMethod().equals( "POST" ) );

        //CSP
        http.headers().contentSecurityPolicy( "script- src'self'https：//trustedscripts.example.com ; object-src https://trustedplugins.example.com; report-uri / csp-report-endpoint /" )
                .reportOnly();//设置仅标签投头

        //X-Frame-Options header
        http.headers().frameOptions().sameOrigin();

        //XSS
        http.headers().xssProtection().block( false );
    }
}

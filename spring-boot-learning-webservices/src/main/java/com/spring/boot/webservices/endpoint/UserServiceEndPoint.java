package com.spring.boot.webservices.endpoint;

import com.spring.boot.webservices.domain.User;
import com.spring.boot.webservices.domain.UserIdRequest;
import com.spring.boot.webservices.domain.UserResponse;
import com.spring.boot.webservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author lianpeng
 * @date 2019/1/13 18:38
 * @since
 **/
@Endpoint
public class UserServiceEndPoint {

    @Autowired
    private UserRepository userRepository;

    @PayloadRoot( namespace="https://github.com/lianpeng0011", localPart = "UserIdRequest")
    @ResponsePayload
    public UserResponse getUser( @RequestPayload UserIdRequest request ){
        UserResponse userResponse = new UserResponse();
        User user = userRepository.getUserById( request.getUserId() );
        userResponse.setUser( user );
        Instant instant = Instant.now().ofEpochMilli( request.getTimestamp() );
        ZonedDateTime zonedDateTime = instant.atZone( ZoneId.systemDefault() );
        System.out.println("Web Services 用户ID:"+ request.getUserId() +",请求时间："+ zonedDateTime);
        userResponse.setTimestamp( Instant.now().toEpochMilli() );
        return userResponse;
    }
}

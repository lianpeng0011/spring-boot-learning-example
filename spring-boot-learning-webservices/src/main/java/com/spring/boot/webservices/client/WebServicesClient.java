package com.spring.boot.webservices.client;

import com.spring.boot.webservices.domain.User;
import com.spring.boot.webservices.domain.UserIdRequest;
import com.spring.boot.webservices.domain.UserResponse;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.time.Instant;

/**
 * @author lianpeng
 * @date 2019/1/13 23:49
 * @since
 **/
public class WebServicesClient {

    public static void main( String[] args ) {
        WebServiceTemplate wsTemplate = new WebServiceTemplate();

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound( UserIdRequest.class, UserResponse.class, User.class );

        wsTemplate.setMarshaller( marshaller );
        wsTemplate.setUnmarshaller( marshaller );

        UserIdRequest userIdRequest = new UserIdRequest();
        userIdRequest.setUserId( 1 );
        userIdRequest.setTimestamp( Instant.now().toEpochMilli() );

        UserResponse response = (UserResponse) wsTemplate.marshalSendAndReceive( "http://localhost:8080/services/web-services/user", userIdRequest );

        System.out.print(response);
    }
}

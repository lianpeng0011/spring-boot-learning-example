package com.spring.boot.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication( scanBasePackages = "com.spring.boot.websocket" )
@EnableWebSocket
public class SpringBootLearningWebsocketApplication {

    public static void main( String[] args ) {
        SpringApplication.run( SpringBootLearningWebsocketApplication.class, args );
    }


    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}


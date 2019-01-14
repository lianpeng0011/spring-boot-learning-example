package com.spring.boot.webservices.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ws.config.annotation.EnableWs;

@SpringBootApplication(scanBasePackages = "com.spring.boot.webservices")
public class SpringBootLearningWebservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLearningWebservicesApplication.class, args);
	}

}


package com.spring.boot.learning.jpa.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.spring.boot.learning.jpa"})
@EntityScan("com.spring.boot.learning.jpa")
@EnableJpaRepositories(basePackages = "com.spring.boot.learning.jpa")
@EnableTransactionManagement
public class SpringBootLearningJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLearningJpaApplication.class, args);
	}

}


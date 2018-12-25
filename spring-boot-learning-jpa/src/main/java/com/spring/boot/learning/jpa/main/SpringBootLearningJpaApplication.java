package com.spring.boot.learning.jpa.main;

import com.spring.boot.learning.jpa.respository.BookRespository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//配置启动时contoller扫描
@SpringBootApplication(scanBasePackages = {"com.spring.boot.learning.jpa"})
//配置entity 类路径扫描
@EntityScan("com.spring.boot.learning.jpa")
@EnableJpaRepositories(repositoryBaseClass = BookRespository.class)
//开启事物管理器
@EnableTransactionManagement
public class SpringBootLearningJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLearningJpaApplication.class, args);
	}

}


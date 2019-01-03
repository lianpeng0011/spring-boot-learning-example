package com.spring.boot.mian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Spring boot 默认扫描SpringBootLearningValidationApplication目录下的包
@SpringBootApplication( scanBasePackages = "com.spring.boot" )
public class SpringBootLearningValidationApplication {

    public static void main( String[] args ) {
        SpringApplication.run( SpringBootLearningValidationApplication.class, args );
    }

}


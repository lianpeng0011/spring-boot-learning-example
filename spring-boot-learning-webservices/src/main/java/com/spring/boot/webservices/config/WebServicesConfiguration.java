package com.spring.boot.webservices.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * @author lianpeng
 * @date 2019/1/13 18:37
 * @see WsConfigurerAdapter
 * @since
 **/
@Configuration
public class WebServicesConfiguration extends WsConfigurerAdapter {

    @Bean("user")
    @Autowired
    public Wsdl11Definition userWsdl11Definition(XsdSchema userXsdSchema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName( "UserServicePort" );
        wsdl11Definition.setLocationUri( "/web-services" );
        wsdl11Definition.setTargetNamespace( "https://github.com/lianpeng0011" );
        wsdl11Definition.setSchema( userXsdSchema );

        return wsdl11Definition;
    }


    @Bean
    public XsdSchema userXsdSchema(){
        return new SimpleXsdSchema( new ClassPathResource( "META-INF/schemas/User.xsd" ) );
    }

}

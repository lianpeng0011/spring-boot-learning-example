package com.spring.boot.dubbo;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author lianpeng
 * @date 2018/12/16 22:53
 * @since
 **/
@Service(version = "1.0.0")
public class HelloDubboServiceImpl implements HelloDubboService {
    @Override
    public String sayHello( String name ) {
        return "Hello " + name ;
    }
}

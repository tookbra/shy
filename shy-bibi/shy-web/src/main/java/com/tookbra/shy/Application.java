package com.tookbra.shy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * spring boot 启动类
 * Created by tookbra on 2016/6/15.
 */
@Configuration
@EnableAutoConfiguration()
@ComponentScan(value = "com.tookbra.shy")
//@EnableCaching
public class Application  {

    public static void main(String [] args) {
        SpringApplication.run(Application.class,args);
    }
}

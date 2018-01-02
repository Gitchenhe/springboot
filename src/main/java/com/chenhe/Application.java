package com.chenhe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenhe on 2017/12/26.
 */
@RestController
@SpringBootApplication
@ImportResource({"classpath:spring/spring-context.xml"})
public class Application  extends SpringBootServletInitializer{
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }


}

package com.chenhe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenhe on 2017/12/26.
 */
@RestController
@SpringBootApplication//核心注解,允许自动配置注解
@EnableScheduling//允许定时任务执行
@ImportResource({"classpath:spring/spring-context.xml"})
public class Application  extends SpringBootServletInitializer{
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}

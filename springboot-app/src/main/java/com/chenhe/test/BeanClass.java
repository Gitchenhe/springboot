package com.chenhe.test;

import ch.qos.logback.classic.pattern.MessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chenhe on 2017/12/28.
 */
@Configuration
public class BeanClass {

    private Logger logger = LoggerFactory.getLogger(BeanClass.class);

    @Bean(name="initTestEntity")
    public TestEntity testEntityInit(){
        logger.info("----- Bean 实例化 ----");
        TestEntity testEntity = new TestEntity();
        testEntity.setId(System.currentTimeMillis()+"");
        return testEntity;
    }
    @Bean(name="initTestEntity")
    public TestEntity test2EntityInit(){
        logger.info("----- Bean 实例化 ----testEntityInit");
        TestEntity testEntity = new TestEntity();
        testEntity.setId(System.currentTimeMillis()+"");
        return testEntity;
    }
}

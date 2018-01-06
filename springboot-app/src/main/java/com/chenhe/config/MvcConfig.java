package com.chenhe.config;

import com.chenhe.converter.MyMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;

import java.io.IOException;
import java.util.List;

/**
 * Created by chenhe on 2017/12/26.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{

    private Logger logger = LoggerFactory.getLogger(MvcConfig.class);

    @Bean
    public InternalResourceViewResolver jspViewResolver(){
        logger.info("----- JSP 视图解析器 -----");
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    @Bean
    public XmlViewResolver xmlViewResolver() throws IOException {
        logger.info("----- XML 视图解析器 -----");
        XmlViewResolver xmlViewResolver = new XmlViewResolver();

        Resource resource = new ClassPathResource("/spring/spring-viewresolver.xml");
        xmlViewResolver.setLocation(resource);
        return xmlViewResolver;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        logger.info("扩展现有的 MessageConvert");
        MyMessageConverter myMessageConverter = new MyMessageConverter();
        converters.add(myMessageConverter);
        super.configureMessageConverters(converters);

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        logger.info("清空转换器列表,添加自己的转换器");
        super.extendMessageConverters(converters);
    }
}

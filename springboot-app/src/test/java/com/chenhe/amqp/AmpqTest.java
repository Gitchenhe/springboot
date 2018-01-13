package com.chenhe.amqp;

import com.chenhe.service.amqp.entity.UserEntity;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by chenhe on 2018/1/6.
 */
public class AmpqTest {

    AmqpTemplate amqpTemplate;
    @Test
    public void test() throws IOException {
        UserEntity userEntity = new UserEntity();
        userEntity.setAge("24");
        userEntity.setName("陈贺1");
        System.out.println("发送消息,"+userEntity);
        Gson gson = new Gson();
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        messageProperties.setContentEncoding("utf-8");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
        oos.writeObject(userEntity);
        oos.close();
        byteArrayOutputStream.close();
        Message message = new Message(byteArrayOutputStream.toByteArray(),messageProperties);
        amqpTemplate.send("spring-boot",message);
    }

    @Before
    public void before(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("106.15.199.29");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("chenhe");
        connectionFactory.setPassword("chenhe");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        amqpTemplate = new RabbitTemplate(connectionFactory);
    }
}

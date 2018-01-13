package com.chenhe.service.amqp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by chenhe on 2018/1/6.
 */
@Component
public class RabbitMQDemo {

    private Logger logger = LoggerFactory.getLogger(RabbitMQDemo.class);

    @Value("${com.chenhe.activemq.queue.name}")
    private String queueName;

    @Value("${com.chenhe.activemq.exchange.name}")
    private String exchangeName;

    @Value("${com.chenhe.activemq.host}")
    private String host;
    @Value("${com.chenhe.activemq.port}")
    private String port;
    @Value("${com.chenhe.activemq.user}")
    private String name;
    @Value("${com.chenhe.activemq.password}")
    private String passwd;
    @Value("${com.chenhe.activemq.virtual-host}")
    private String vHost;
    @Value("com.chenhe.activemq.publisherconfirms")
    private String publisherconfirms;

    @Bean(name="RabbitmqConnectFactory")
    ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(Integer.parseInt(port));
        connectionFactory.setUsername(name);
        connectionFactory.setPassword(passwd);
        connectionFactory.setVirtualHost(vHost);
        connectionFactory.setPublisherConfirms(Boolean.parseBoolean(publisherconfirms));
        return  connectionFactory;
    }

    @Bean
    Queue queue(){
        logger.info("[queue]创建队列,名称:{}",queueName);
        return new Queue(queueName,false);
    }

    @Bean
    TopicExchange exchange(){
        logger.info("[exchange]创建交换器,名字:{}",exchangeName);
        return new TopicExchange(exchangeName);
    }

    @Bean
    Binding binding(Queue queue,TopicExchange exchange){
        logger.info("[binding]队列绑定交换器");
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }

    @Bean
    SimpleMessageListenerContainer container(@Qualifier("RabbitmqConnectFactory") ConnectionFactory factory, MessageListenerAdapter  adapter){
        logger.info("[container]创建监听器容器");
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setQueueNames(queueName);
        adapter.setMessageConverter(new MessageConvert());
        container.setMessageListener(adapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(@Qualifier("RabbitReceiver") Receiver receiver){
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter();

        return new MessageListenerAdapter(receiver,"receiveMessage");
    }

}

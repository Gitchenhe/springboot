package com.chenhe.service.amqp.service;

import com.chenhe.service.amqp.entity.MQBaseEntity;
import com.chenhe.service.amqp.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;

/**
 * Created by chenhe on 2018/1/6.
 */
@Component("RabbitReceiver")
public class Receiver {

    private Logger logger = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(MQBaseEntity message){
        UserEntity userEntity = (UserEntity)message;
        logger.info("[rabbit mq]receive message:{}", userEntity);
        latch.countDown();
    }

    public CountDownLatch getLatch(){
        return latch;
    }
}

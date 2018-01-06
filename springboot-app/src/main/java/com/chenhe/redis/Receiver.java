package com.chenhe.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;


/**
 * Created by chenhe on 2018/1/4.
 */
public class Receiver {
    private Logger logger = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch;

    @Autowired
    public Receiver(CountDownLatch latch){
        this.latch=latch;
    }

    public void receiveMessage(String message){
        logger.info("[>>>>接收到消息:{}<<<<]",message);
        latch.countDown();
    }
}

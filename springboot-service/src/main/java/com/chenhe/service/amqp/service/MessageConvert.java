package com.chenhe.service.amqp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by chenhe on 2018/1/9.
 */
public class MessageConvert implements MessageConverter {

    Logger logger = LoggerFactory.getLogger(MessageConvert.class);

    public MessageConvert(){

    }

    @Override
    public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
        logger.info("[toMessage]object={},messageProperties{}", object,messageProperties);
        if (object != null){
            logger.info("[toMessage]className = {}",object.getClass().getName());
        }
        return null;
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        ByteArrayInputStream byteArrayOutputStream = new ByteArrayInputStream(message.getBody());
        try {
            ObjectInputStream oos = new ObjectInputStream(byteArrayOutputStream);
            Object obj = oos.readObject();
            logger.info("[fromMessage]message={}",obj);
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

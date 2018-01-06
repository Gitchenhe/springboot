package com.chenhe.converter;

import com.chenhe.test.TestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

/**
 * Created by chenhe on 2017/12/28.
 */
public class MyMessageConverter extends AbstractHttpMessageConverter {
    private Logger logger = LoggerFactory.getLogger(MyMessageConverter.class);
    @Override
    protected boolean supports(Class aClass) {
        logger.info("converter是否支持={}",aClass.toString());
        if (aClass.equals(TestEntity.class)){
            return true;
        }
        return false;
    }

    @Override
    protected Object readInternal(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

    }
}

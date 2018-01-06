package com.chenhe.redis;

import com.chenhe.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by chenhe on 2018/1/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisDemoTest {

    @Autowired
    StringRedisTemplate template;

    @Test
    public void testPushlishMessage() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("发送消息...");
        template.convertAndSend("chat","time now: "+ simpleDateFormat.format(new Date()));
    }
}
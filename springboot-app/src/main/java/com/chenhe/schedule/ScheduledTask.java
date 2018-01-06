package com.chenhe.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenhe on 2018/1/2.
 */
@Component
public class ScheduledTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTask.class);

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT =new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedDelay = 2000)
    public void reportCurrentTime(){
        LOGGER.info("当前时间:{}",SIMPLE_DATE_FORMAT.format(new Date()));
    }
}

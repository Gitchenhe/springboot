package com.chenhe.jpawithh2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by chenhe on 2018/1/3.
 */
//@Component
public class JdbcDemo {

    private Logger logger = LoggerFactory.getLogger(JdbcDemo.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public StockInfo test(){
        logger.info("准备执行sql查询...");
        List<StockInfo> stockInfoList = jdbcTemplate.queryForList("select * from pub_tstockinfo limit 100",StockInfo.class);

        logger.info("sql查询结果,记录数{}",(stockInfoList==null?null:stockInfoList.size()));

        stockInfoList.forEach(stockInfo -> logger.info(stockInfo.toString()));

        logger.info("sql查询执行完毕...");




        return null;
    }

}

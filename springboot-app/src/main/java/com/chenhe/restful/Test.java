package com.chenhe.restful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by chenhe on 2018/1/2.
 */
public class Test {
    private static final Logger log = LoggerFactory.getLogger(Test.class);
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        log.info(quote.toString());
    }
}

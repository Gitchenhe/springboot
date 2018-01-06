package com.chenhe;

import com.chenhe.h2jdbc.Customer;
import com.chenhe.jpawithh2.StockInfo;
import com.chenhe.jpawithh2.StockInfoRepository;
import com.chenhe.restful.Quote;
import com.chenhe.uploadfile.StorageProperties;
import com.chenhe.uploadfile.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chenhe on 2017/12/26.
 */
@SpringBootApplication//核心注解,允许自动配置注解
//@EnableScheduling//允许定时任务执行
@ImportResource({"classpath:spring/spring-context.xml"})
@EnableConfigurationProperties(StorageProperties.class)
public class Application  extends SpringBootServletInitializer{

    private Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate, StockInfoRepository repository, StorageService storageService){
        //testH2Jdbc();//使用mysql作为数据源的时候,注释掉此处,否则报错.因为这里使用的是H2作为数据库,后面多数据源的时候进行修改

        //springDataJPATest(repository);

        storageService.deleteAll();
        storageService.init();

        return args -> {
            Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random",Quote.class);
            logger.info("演示spring boot消费Restful web service{}",quote.toString());
        };
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Customer testH2Jdbc() {
        logger.info("创建表...");
        jdbcTemplate.execute("DROP TABLE CUSTOMER IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE CUSTOMER(ID SERIAL,FIRST_NAME VARCHAR(255), LAST_NAME VARCHAR(255))");

        List<Object[]> splitNames = Arrays.asList("陈贺", "张三", "陈四").stream().map(name -> name.split("")).collect(Collectors.toList());

        splitNames.forEach(name -> logger.info(String.format("插入数据{%s,%s}", name[0], name[1])));

        jdbcTemplate.batchUpdate("INSERT INTO CUSTOMER(FIRST_NAME,LAST_NAME) VALUES (?,?)", splitNames);

        logger.info("查询姓名为陈贺的记录");
        jdbcTemplate.query("SELECT ID,FIRST_NAME,LAST_NAME FROM CUSTOMER WHERE FIRST_NAME=?", new Object[]{"陈"},
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))).forEach(customer -> logger.info(customer.toString()));

        return new Customer();
    }

    /**
     * 首先他会去Spring Context 中寻找StockInfoRepository, 接着
     * @param repository
     */
    public void springDataJPATest(StockInfoRepository repository){
        repository.save(new StockInfo("100000","中国平安","中国平安","100000"));
        repository.save(new StockInfo("600570001","恒生电子","恒生电子","600570"));

        logger.info("证券信息表,查询所有");
        repository.findAll().forEach(stockInfo -> logger.info(stockInfo.toString()));
        logger.info("----------------------");

        logger.info("证券信息表,查询一条");
        StockInfo stockInfo = repository.findOne("100000");
        logger.info(stockInfo.toString());
        logger.info("----------------------");

        logger.info("证券信息表,根据证券名称查询");
        repository.findByStockName("恒生电子").forEach(stockInfo1 -> logger.info(stockInfo1.toString()));

        logger.info("----------------------");
    }
}

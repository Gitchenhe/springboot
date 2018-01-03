package com.chenhe.jpawithh2;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by chenhe on 2018/1/3.
 */

/**
 * StockInfoRepository 继承自接口 CrudRepository.CrudRepository指定了实体和主键的类型分别是StockInfo和String.
 * 通过继承CrudRepository,接口StockInfoRepository有一些用来操作StockInfo的方法.增删改查种种.
 *
 * Spring JPA 允许开发人员通过定义方法名字,声明其它查询方法比如这里的findByInterCode()
 *
 * 典型的Java应用程序中,通常是通过一个类去实现StockInfoRepository接口. 但是这里不同,这就是Spring Data JPA强大的地方,
 * 我们不需要实现StockInfoRepository接口, Spring Data JPA 会在应用程序运行的时候,自动实现接口
 */
public interface StockInfoRepository extends CrudRepository<StockInfo,String> {
    List<StockInfo> findByStockName(String interCode);
}

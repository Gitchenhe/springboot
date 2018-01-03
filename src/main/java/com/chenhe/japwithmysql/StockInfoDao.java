package com.chenhe.japwithmysql;

import com.chenhe.jpawithh2.StockInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by chenhe on 2018/1/3.
 *
 * 此接口,会被Spring默认的实现
 */
public interface StockInfoDao extends PagingAndSortingRepository<StockInfo,String> {

    List<StockInfo> findByStockNameLike(String stockName);

}


package com.chenhe.japwithmysql;

import com.chenhe.jpawithh2.StockInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by chenhe on 2018/1/3.
 */
@Controller
@RequestMapping(path = "stockinfo")
public class StockInfoController {

    @Autowired
    private StockInfoDao stockInfoDao;

    private Logger logger = LoggerFactory.getLogger(StockInfoController.class);

    @GetMapping(path="/add")
    @ResponseBody
    public StockInfo addNewStockInfo(@RequestParam String interCode, @RequestParam String stockName, @RequestParam String reportCode){
        StockInfo stockInfo = new StockInfo(interCode,stockName,stockName,reportCode);
        logger.info("证券信息添加,data={}",stockInfo);
        stockInfo = stockInfoDao.save(stockInfo);
        logger.info("证券信息添加完成,result data={}",stockInfo);
        return stockInfo;
    }

    @GetMapping(path = "findAll")
    @ResponseBody
    public Page<StockInfo> queryAllStockInfo(@RequestParam(required = false,defaultValue ="100") int pageSize, @RequestParam(required = false,defaultValue = "1") int pageNum){

        PageRequest request = new PageRequest(pageNum,pageSize, new Sort("interCode"));
        logger.info("查询所有证券信息,pageRequest:{}",request);
        Page<StockInfo> page = stockInfoDao.findAll(request);
        logger.info("总记录数:{}",page);
        return page;
    }

    @RequestMapping(path = "findByName")
    @ResponseBody
    public List<StockInfo> queryStockInfo(@RequestParam String stockName){
        logger.info("根据证券名称查询证券信息,证券名称:{}",stockName);
        stockName = "%"+stockName+"%";
        List<StockInfo> stockInfos = stockInfoDao.findByStockNameLike(stockName);
        logger.info("查询结果记录数:{}",stockInfos.size());
        return stockInfos;
    }

}

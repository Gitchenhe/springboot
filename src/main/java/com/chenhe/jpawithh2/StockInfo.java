package com.chenhe.jpawithh2;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by chenhe on 2018/1/3.
 */
@Entity(name = "pub_tstockinfo") //@Entity StockInfo是一个JPA实体,由于缺少@Table,这个实体会被映射为table 名字
public class StockInfo {

    @Id //JPA会把@Id修饰的变量识别为主键
    private String interCode;
    private String stockName,stockFullname,reportCode;//成员没有注解,会默认被映射为同名的column的名字(they’ll be mapped to columns that share the same name as the properties themselves.)

    public StockInfo(){}

    public StockInfo(String interCode, String stockName, String stockFullname, String reportCode) {
        this.interCode = interCode;
        this.stockName = stockName;
        this.stockFullname = stockFullname;
        this.reportCode = reportCode;
    }

    public String getInterCode() {
        return interCode;
    }

    public void setInterCode(String interCode) {
        this.interCode = interCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockFullname() {
        return stockFullname;
    }

    public void setStockFullname(String stockFullname) {
        this.stockFullname = stockFullname;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    @Override
    public String toString() {
        return "StockInfo{" +
                "interCode='" + interCode + '\'' +
                ", stockName='" + stockName + '\'' +
                ", stockFullname='" + stockFullname + '\'' +
                ", reportCode='" + reportCode + '\'' +
                '}';
    }
}

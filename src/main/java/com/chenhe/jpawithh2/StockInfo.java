package com.chenhe.jpawithh2;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by chenhe on 2018/1/3.
 */
@Entity //@Entity StockInfo是一个JPA实体,由于缺少@Table,这个实体会被映射为table 名字
public class StockInfo {

    @Id //JPA会把@Id修饰的变量识别为主键
    private String interCode;
    private String stockName,stockFullName,reportName;//成员没有注解,会默认被映射为同名的column的名字(they’ll be mapped to columns that share the same name as the properties themselves.)

    public StockInfo(){}
    public StockInfo(String interCode, String stockName, String stockFullName, String reportName) {
        this.interCode = interCode;
        this.stockName = stockName;
        this.stockFullName = stockFullName;
        this.reportName = reportName;
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

    public String getStockFullName() {
        return stockFullName;
    }

    public void setStockFullName(String stockFullName) {
        this.stockFullName = stockFullName;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public String toString() {
        return "StockInfo{" +
                "interCode='" + interCode + '\'' +
                ", stockName='" + stockName + '\'' +
                ", stockFullName='" + stockFullName + '\'' +
                ", reportName='" + reportName + '\'' +
                '}';
    }
}

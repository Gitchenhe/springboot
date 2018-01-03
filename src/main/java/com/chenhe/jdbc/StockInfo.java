package com.chenhe.jdbc;

/**
 * Created by chenhe on 2018/1/3.
 */
public class StockInfo {

    private String interCode;
    private String stockName,stockFullName,reportName;

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

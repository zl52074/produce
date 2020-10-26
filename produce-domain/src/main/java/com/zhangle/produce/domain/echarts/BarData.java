package com.zhangle.produce.domain.echarts;

/**
 * @description:
 * @author: zl52074
 * @time: 2020/4/15 0:38
 */
public class BarData {
    private String market;
    private Double price;

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BarData(String market, Double price) {
        this.market = market;
        this.price = price;
    }
}

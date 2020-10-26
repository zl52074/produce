package com.zhangle.produce.domain.produce;

import java.io.Serializable;

/**
 * @description: 不同市场的农产品
 * @author: zl52074
 * @time: 2020/3/25 20:10
 */
public class MarketProduce implements Serializable {
    private String id;
    private String market;
    private String produce;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getProduce() {
        return produce;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }

    @Override
    public String toString() {
        return "MarketProduce{" +
                "id='" + id + '\'' +
                ", market='" + market + '\'' +
                ", produce='" + produce + '\'' +
                '}';
    }
}

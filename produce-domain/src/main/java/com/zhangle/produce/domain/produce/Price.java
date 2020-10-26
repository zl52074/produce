package com.zhangle.produce.domain.produce;

/**
 * @description:
 * @author: zl52074
 * @time: 2020/3/26 21:00
 */
public class Price {
    private int id;
    private String market_price_id;
    private Double price;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarket_price_id() {
        return market_price_id;
    }

    public void setMarket_price_id(String market_price_id) {
        this.market_price_id = market_price_id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", market_price_id='" + market_price_id + '\'' +
                ", price=" + price +
                ", date='" + date + '\'' +
                '}';
    }
}

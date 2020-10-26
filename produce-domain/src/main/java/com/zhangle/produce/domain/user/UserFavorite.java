package com.zhangle.produce.domain.user;

/**
 * @description:
 * @author: zl52074
 * @time: 2020/4/22 20:53
 */
public class UserFavorite {
    String id;
    String user;
    String marketProduce;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMarketProduce() {
        return marketProduce;
    }

    public void setMarketProduce(String marketProduce) {
        this.marketProduce = marketProduce;
    }

    @Override
    public String toString() {
        return "UserFavorite{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", marketProduce='" + marketProduce + '\'' +
                '}';
    }
}

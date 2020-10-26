package com.zhangle.produce.domain.user;

/**
 * @description:
 * @author: zl52074
 * @time: 2020/3/30 20:16
 */
public class UserFavoriteTable {
    private String favoriteId;
    private String marketProduceId;
    private String produceId;
    private String produceName;
    private String categoryId;
    private String categoryName;
    private String marketId;
    private String marketName;

    public String getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(String favoriteId) {
        this.favoriteId = favoriteId;
    }

    public String getMarketProduceId() {
        return marketProduceId;
    }

    public void setMarketProduceId(String marketProduceId) {
        this.marketProduceId = marketProduceId;
    }

    public String getProduceId() {
        return produceId;
    }

    public void setProduceId(String produceId) {
        this.produceId = produceId;
    }

    public String getProduceName() {
        return produceName;
    }

    public void setProduceName(String produceName) {
        this.produceName = produceName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    @Override
    public String toString() {
        return "UserFavoriteTable{" +
                "favoriteId='" + favoriteId + '\'' +
                ", marketProduceId='" + marketProduceId + '\'' +
                ", produceId='" + produceId + '\'' +
                ", produceName='" + produceName + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", marketId='" + marketId + '\'' +
                ", marketName='" + marketName + '\'' +
                '}';
    }
}

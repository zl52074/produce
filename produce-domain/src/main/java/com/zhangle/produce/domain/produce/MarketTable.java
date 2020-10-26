package com.zhangle.produce.domain.produce;

/**
 * @description:
 * @author: zl52074
 * @time: 2020/4/4 21:58
 */
public class MarketTable {
    private String marketId;
    private String marketName;
    private String marketInfo;
    private String marketType;
    private String marketAddress;
    private String provinceId;
    private String provinceName;

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

    public String getMarketInfo() {
        return marketInfo;
    }

    public void setMarketInfo(String marketInfo) {
        this.marketInfo = marketInfo;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(int marketType) {
        if(marketType == 0){
            this.marketType="销地";
        }else if(marketType == 1) {
            this.marketType = "产地";
        }else{
            this.marketType = "其它";
        }
    }

    public String getMarketAddress() {
        return marketAddress;
    }

    public void setMarketAddress(String marketAddress) {
        this.marketAddress = marketAddress;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}

package com.zhangle.produce.service.produce;

import com.zhangle.produce.domain.produce.*;
import com.zhangle.produce.domain.user.UserFavoriteTable;
import org.apache.ibatis.annotations.Param;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ProduceService {
    public List<PriceTable> findPriceTableByCondition(PriceTableCondition condition) throws SQLException;
    public int getPriceCount(PriceTableCondition condition) throws SQLException;
    public List<Category> findAllCategory() throws SQLException;
    public List<Market> findAllMarket()throws SQLException;
    public List<Produce> findAllProduce() throws SQLException;
    public List<Province> findAllProvince() throws SQLException;
    public List<MarketTable> findMarketTableByCondition(MarketTableCondition condition) throws SQLException;
    public int getMarketCount(MarketTableCondition condition) throws SQLException;
    public List<Category> findMarketProduceCategory(String marketId) throws SQLException;
    public List<Produce> findMarketProduce(String marketId) throws SQLException;
    public MarketTable findMarketById(String marketId) throws SQLException;
    public PriceTable findPriceTableByMarketProduceIdAndDate(String marketProduceId,String date) throws SQLException;
    public List<Double> getPriceByProduceIdAndDate(String produceId,String date) throws SQLException;
    public List<PriceTable> findPriceTableOrderByPrice(String produceId,String date) throws SQLException;
    public List<PriceTable> findPriceTableByMarketProduceId(String marketProduceId,String startDate) throws SQLException;
    public List<PriceTable> findPriceTableByMarketProduceIdList(List<PriceTable> list,String date) throws SQLException;
    public List<PriceTable> findPriceTableByConditionInList(PriceTableCondition condition,List<PriceTable> list) throws SQLException;
    public List<Produce> findAllProduceInList(List<PriceTable> list) throws SQLException;
    public List<Category> findAllCategoryInList(List<PriceTable> list) throws SQLException;
    public List<Market> findAllMarketInList(List<PriceTable> list) throws SQLException;
    public int getCountByConditionInList(PriceTableCondition condition,List<PriceTable> list) throws SQLException;
    public List<UserFavoriteTable> findFavoriteTableByCondition(PriceTableCondition condition,String userId) throws SQLException;
    public int getFavoriteCount(PriceTableCondition condition,String userId) throws SQLException;
    public List<Category> findAllFavoriteCategory(String userId) throws SQLException;
    public List<Produce> findAllFavoriteProduce(String userId) throws SQLException;
    public List<Market> findAllFavoriteMarket(String userId) throws SQLException;
    public String getLatestDateByMarketProduceId(String marketProduceId ) throws SQLException;
}

package com.zhangle.produce.service.crawler;

import com.zhangle.produce.domain.crawler.*;
import com.zhangle.produce.domain.produce.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CrawlerService {
    public void saveMarket(Market market) throws SQLException;
    public void saveCategory(Category category) throws SQLException;
    public void saveProduce(Produce produce) throws SQLException;
    public List<String> findAllMarketId() throws SQLException;
    public List<String> findAllProduceId() throws SQLException;
    public void saveMarketProduce(MarketProduce marketProduce) throws SQLException;
    public List<MarketProduce> findAllMarketProduce() throws SQLException;
    public void savePrice(Price price) throws SQLException;
    public void saveMsg(ScheduleMsg msg) throws SQLException;
    public List<ScheduleMsg> findMsgByTime(String time) throws SQLException;
    public Integer getPriceCount() throws SQLException;
    public Integer getCategoryCount() throws SQLException;
    public Integer getProduceCount() throws SQLException;
    public Integer getMarketCount() throws SQLException;
    public Integer getMarketProduceCount() throws SQLException;
}

package com.zhangle.produce.service.crawler.impl;

import com.zhangle.produce.dao.crawler.CrawlerDao;
import com.zhangle.produce.domain.crawler.*;
import com.zhangle.produce.domain.produce.*;
import com.zhangle.produce.service.crawler.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @description: 爬虫service
 * @author: zl52074
 * @time: 2020/3/22 23:07
 */
@Service
@Transactional
public class CrawlerServiceImpl implements CrawlerService {
    @Autowired
    CrawlerDao crawlerDao;

    /**
     * @description 保存market
     * @param market
     * @return void
     * @author zl52074
     * @time 2020/3/22 23:21
     */
    @Override
    public void saveMarket(Market market) throws SQLException {
        //查询数据库，不存在再保存
        if(crawlerDao.findMarketById(market.getId())==null){
            crawlerDao.saveMarket(market);
        }
    }

    /**
     * @description 保存category
     * @param category
     * @return void
     * @author zl52074
     * @time 2020/3/25 19:30
     */
    @Override
    public void saveCategory(Category category) throws SQLException {
        //查询数据库，不存在再保存
        if(crawlerDao.findCategoryById(category.getId())==null){
            crawlerDao.saveCategory(category);
        }
    }


    /**
     * @description 保存produce
     * @param produce
     * @return void
     * @author zl52074
     * @time 2020/3/25 19:38
     */
    @Override
    public void saveProduce(Produce produce) throws SQLException {
        //查询数据库，不存在再保存
        if(crawlerDao.findProduceById(produce.getId())==null){
            crawlerDao.saveProduce(produce);
        }
    }

    /**
     * @description 获取全部市场id
     * @param
     * @return java.util.List<java.lang.String>
     * @author zl52074
     * @time 2020/3/25 20:30
     */
    @Override
    public List<String> findAllMarketId() throws SQLException {
        return crawlerDao.findAllMarketId();
    }

    /**
     * @description 获取全部农产品id
     * @param
     * @return java.util.List<java.lang.String>
     * @author zl52074
     * @time 2020/3/25 20:30
     */
    @Override
    public List<String> findAllProduceId() throws SQLException {
        return crawlerDao.findAllProduceId();
    }

    /**
     * @description 保存marketProduce
     * @param marketProduce
     * @return void
     * @author zl52074
     * @time 2020/3/25 20:30
     */
    @Override
    public void saveMarketProduce(MarketProduce marketProduce) throws SQLException {
        //查询数据库，不存在再保存
        if(crawlerDao.findMarketProduceById(marketProduce.getId())==null){
            crawlerDao.saveMarketProduce(marketProduce);
        }
    }

    /**
     * @description 获取全部marketProduce
     * @param
     * @return java.util.List<com.zhangle.produce.domain.produce.MarketProduce>
     * @author zl52074
     * @time 2020/3/26 20:53
     */
    @Override
    public List<MarketProduce> findAllMarketProduce() throws SQLException {
        return crawlerDao.findAllMarketProduce();
    }

    /**
     * @description 保存农产品价格
     * @param price
     * @return void
     * @author zl52074
     * @time 2020/3/26 21:04
     */
    @Override
    public void savePrice(Price price) throws SQLException {
        //查询数据库，不存在再保存
        if(crawlerDao.findPriceByIdAndDate(price)==null){
            crawlerDao.savePrice(price);
        }
    }

    /**
     * @description 保存定时任务消息
     * @param msg
     * @return void
     * @author zl52074
     * @time 2020/3/27 13:02
     */
    @Override
    public void saveMsg(ScheduleMsg msg) throws SQLException {
        crawlerDao.saveMsg(msg);
    }

    /**
     * @description 根据时间获取日志
     * @param time
     * @return java.util.List<com.zhangle.produce.domain.crawler.ScheduleMsg>
     * @author zl52074
     * @time 2020/3/29 16:41
     */
    @Override
    public List<ScheduleMsg> findMsgByTime(String time) throws SQLException {
        return crawlerDao.findMsgByTime(time);
    }

    /**
     * @description 一系列查询数量的方法
     * @param
     * @return java.lang.Integer
     * @author zl52074
     * @time 2020/3/30 13:35
     */
    @Override
    public Integer getPriceCount() throws SQLException {
        return crawlerDao.getPriceCount();
    }
    @Override
    public Integer getCategoryCount() throws SQLException {
        return crawlerDao.getCategoryCount();
    }
    @Override
    public Integer getProduceCount() throws SQLException {
        return crawlerDao.getProduceCount();
    }
    @Override
    public Integer getMarketCount() throws SQLException {
        return crawlerDao.getMarketCount();
    }
    @Override
    public Integer getMarketProduceCount() throws SQLException {
        return crawlerDao.getMarketProduceCount();
    }
}

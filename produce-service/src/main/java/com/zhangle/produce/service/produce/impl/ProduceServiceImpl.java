package com.zhangle.produce.service.produce.impl;

import com.zhangle.produce.dao.produce.ProduceDao;
import com.zhangle.produce.domain.produce.*;
import com.zhangle.produce.domain.user.UserFavoriteTable;
import com.zhangle.produce.service.produce.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @description:
 * @author: zl52074
 * @time: 2020/3/31 17:23
 */
@Service
@Transactional
public class ProduceServiceImpl implements ProduceService {
    @Autowired
    private ProduceDao produceDao;

    /**
     * @description 根据条件刷动态查询priceTable
     * @param condition
     * @return java.util.List<com.zhangle.produce.domain.produce.PriceTable>
     * @author zl52074
     * @time 2020/3/30 22:01
     */
    @Override
    public List<PriceTable> findPriceTableByCondition(PriceTableCondition condition) throws SQLException {
        return produceDao.findPriceTableByCondition(condition);
    }

    /**
     * @description 根据条件获取总数据量
     * @param condition
     * @return int
     * @author zl52074
     * @time 2020/3/30 22:01
     */
    @Override
    public int getPriceCount(PriceTableCondition condition) throws SQLException {
        return produceDao.getPriceCount(condition);
    }

    /**
     * @description 查询全部农产品类别
     * @param
     * @return com.zhangle.produce.domain.produce.Category
     * @author zl52074
     * @time 2020/4/4 12:11
     */
    @Override
    public List<Category> findAllCategory() throws SQLException {
        return produceDao.findAllCategory();
    }

    /**
     * @description 查询全部农产品市场
     * @param
     * @return com.zhangle.produce.domain.produce.Market
     * @author zl52074
     * @time 2020/4/4 12:12
     */
    @Override
    public List<Market> findAllMarket() throws SQLException {
        return produceDao.findAllMarket();
    }

    /**
     * @description 获取全部农产品
     * @param
     * @return java.util.List<com.zhangle.produce.domain.produce.Produce>
     * @author zl52074
     * @time 2020/4/4 13:02
     */
    @Override
    public List<Produce> findAllProduce() throws SQLException {
        return produceDao.findAllProduce();
    }

    /**
     * @description 获取全部市场省份
     * @param
     * @return java.util.List<com.zhangle.produce.domain.produce.Province>
     * @author zl52074
     * @time 2020/4/4 22:17
     */
    @Override
    public List<Province> findAllProvince() throws SQLException {
        return produceDao.findAllProvince();
    }

    /**
     * @description 根据条件刷动态查询marketTable
     * @param condition
     * @return java.util.List<com.zhangle.produce.domain.produce.PriceTable>
     * @author zl52074
     * @time 2020/4/5 14:40
     */
    @Override
    public List<MarketTable> findMarketTableByCondition(MarketTableCondition condition) throws SQLException {
        return produceDao.findMarketTableByCondition(condition);
    }

    /***
     * @description 根据条件刷查询数据量
     * @param condition
     * @return int
     * @author zl52074
     * @time 2020/4/5 14:40
     */
    @Override
    public int getMarketCount(MarketTableCondition condition) throws SQLException {
        return produceDao.getMarketCount(condition);
    }

    /**
     * @description 根据市场id获取市场下的产品分类
     * @param marketId
     * @return java.util.List<com.zhangle.produce.domain.produce.Category>
     * @author zl52074
     * @time 2020/4/5 19:22
     */
    @Override
    public List<Category> findMarketProduceCategory(String marketId) throws SQLException {
        return produceDao.findMarketProduceCategory(marketId);
    }

    /**
     * @description 根据市场id获取市场下的产品
     * @param marketId
     * @return java.util.List<com.zhangle.produce.domain.produce.Produce>
     * @author zl52074
     * @time 2020/4/5 19:22
     */
    @Override
    public List<Produce> findMarketProduce(String marketId) throws SQLException {
        return produceDao.findMarketProduce(marketId);
    }

    /**
     * @description 根据id获取市场详情
     * @param marketId
     * @return com.zhangle.produce.domain.produce.Market
     * @author zl52074
     * @time 2020/4/5 19:50
     */
    @Override
    public MarketTable findMarketById(String marketId) throws SQLException {
        return produceDao.findMarketById(marketId);
    }

    /**
     * @description 根据marketProduceId和date查找priceTable
     * @param marketProduceId
     * @param date
     * @return com.zhangle.produce.domain.produce.PriceTable
     * @author zl52074
     * @time 2020/4/13 23:16
     */
    @Override
    public PriceTable findPriceTableByMarketProduceIdAndDate(String marketProduceId, String date) throws SQLException {
        return produceDao.findPriceTableByMarketProduceIdAndDate(marketProduceId, date);
    }

    /**
     * @description 获取当前农产品当前日期的全部市场价格
     * @param produceId
     * @param date
     * @return java.util.List<java.lang.Integer>
     * @author zl52074
     * @time 2020/4/14 22:01
     */
    @Override
    public List<Double> getPriceByProduceIdAndDate(String produceId, String date) throws SQLException {
        return produceDao.getPriceByProduceIdAndDate(produceId,date);
    }

    /**
     * @description 获取当日价格最低的五条数据
     * @param produceId
     * @param date
     * @return java.util.List<com.zhangle.produce.domain.produce.PriceTable>
     * @author zl52074
     * @time 2020/4/14 23:19
     */
    @Override
    public List<PriceTable> findPriceTableOrderByPrice(String produceId, String date) throws SQLException {
        return produceDao.findPriceTableOrderByPrice(produceId,date);
    }

    /**
     * @description 根据marketProduceId获取价格历史
     * @param marketProduceId
     * @param startDate
     * @return java.util.List<com.zhangle.produce.domain.produce.PriceTable>
     * @author zl52074
     * @time 2020/4/16 17:42
     */
    @Override
    public List<PriceTable> findPriceTableByMarketProduceId(String marketProduceId, String startDate) throws SQLException {
        return produceDao.findPriceTableByMarketProduceId(marketProduceId, startDate);
    }

    /**
     * @description 根据session中的list查询
     * @param list
     * @param date
     * @return java.util.List<com.zhangle.produce.domain.produce.PriceTable>
     * @author zl52074
     * @time 2020/4/19 21:12
     */
    @Override
    public List<PriceTable> findPriceTableByMarketProduceIdList(List<PriceTable> list, String date) throws SQLException {
        return produceDao.findPriceTableByMarketProduceIdList(list, date);
    }

    /**
     * @description 根据session中的list条件查询
     * @param condition
     * @param list
     * @return java.util.List<com.zhangle.produce.domain.produce.PriceTable>
     * @author zl52074
     * @time 2020/4/20 20:49
     */
    @Override
    public List<PriceTable> findPriceTableByConditionInList(PriceTableCondition condition, List<PriceTable> list) throws SQLException {
        return produceDao.findPriceTableByConditionInList(condition, list);
    }

    /**
     * @description 根据session中的list查询全部农产品种类
     * @param
     * @return java.util.List<com.zhangle.produce.domain.produce.Produce>
     * @author zl52074
     * @time 2020/4/20 22:04
     */
    @Override
    public List<Produce> findAllProduceInList(List<PriceTable> list) throws SQLException {
        return produceDao.findAllProduceInList(list);
    }

    /**
     * @description 根据session中的list查询全部农产品类别
     * @param list
     * @return java.util.List<com.zhangle.produce.domain.produce.Category>
     * @author zl52074
     * @time 2020/4/20 22:20
     */
    @Override
    public List<Category> findAllCategoryInList(List<PriceTable> list) throws SQLException {
        return produceDao.findAllCategoryInList(list);
    }

    /**
     * @description 根据session中的list查询全部农产品市场
     * @param list
     * @return java.util.List<com.zhangle.produce.domain.produce.Market>
     * @author zl52074
     * @time 2020/4/20 22:27
     */
    @Override
    public List<Market> findAllMarketInList(List<PriceTable> list) throws SQLException {
        return produceDao.findAllMarketInList(list);
    }

    @Override
    public int getCountByConditionInList(PriceTableCondition condition, List<PriceTable> list) throws SQLException {
        return produceDao.getCountByConditionInList(condition, list);
    }

    @Override
    public List<UserFavoriteTable> findFavoriteTableByCondition(PriceTableCondition condition,String userId) throws SQLException {
        return produceDao.findFavoriteTableByCondition(condition,userId);
    }

    @Override
    public int getFavoriteCount(PriceTableCondition condition,String userId) throws SQLException {
        return produceDao.getFavoriteCount(condition,userId);
    }

    @Override
    public List<Category> findAllFavoriteCategory(String userId) throws SQLException {
        return produceDao.findAllFavoriteCategory(userId);
    }

    @Override
    public List<Produce> findAllFavoriteProduce(String userId) throws SQLException {
        return produceDao.findAllFavoriteProduce(userId);
    }

    @Override
    public List<Market> findAllFavoriteMarket(String userId) throws SQLException {
        return produceDao.findAllFavoriteMarket(userId);
    }

    /**
     * @description 获取产品最近的更新日期
     * @param marketProduceId
     * @return java.lang.String
     * @author zl52074
     * @time 2020/4/23 20:43
     */
    @Override
    public String getLatestDateByMarketProduceId(String marketProduceId) throws SQLException {
        return produceDao.getLatestDateByMarketProduceId(marketProduceId);
    }


}

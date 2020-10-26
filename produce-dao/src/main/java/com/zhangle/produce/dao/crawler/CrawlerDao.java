package com.zhangle.produce.dao.crawler;

import com.zhangle.produce.domain.crawler.*;
import com.zhangle.produce.domain.produce.*;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
/**
 * @description: 爬虫dao
 * @author: zl52074
 * @time: 2020/3/22 23:07
 */
@Repository
public interface CrawlerDao {
    /**
     * @description 保存market到数据库
     * @param market
     * @return void
     * @author zl52074
     * @time 2020/3/22 23:15
     */
    @Update("insert into produce_market values (#{id},#{name},#{info},#{type},#{address},#{province})")
    public void saveMarket(Market market) throws SQLException;

    /**
     * @description 根据id查询market
     * @param id
     * @return com.zhangle.produce.domain.produce.Market
     * @author zl52074
     * @time 2020/3/22 23:18
     */
    @Select("select * from produce_market where id = #{id}")
    public Market findMarketById(String id) throws SQLException;


    /**
     * @description 保存category到数据库
     * @param category
     * @return void
     * @author zl52074
     * @time 2020/3/25 19:27
     */
    @Update("insert into produce_category values (#{id},#{name})")
    public void saveCategory(Category category) throws SQLException;

    /**
     * @description 根据id查询category
     * @param id
     * @return com.zhangle.produce.domain.produce.Market
     * @author zl52074
     * @time 2020/3/25 19:29
     */
    @Select("select * from produce_category where id = #{id}")
    public Market findCategoryById(String id) throws SQLException;

    /**
     * @description 保存produce到数据库
     * @param produce
     * @return void
     * @author zl52074
     * @time 2020/3/25 19:36
     */
    @Update("insert into produce values (#{id},#{name},#{category})")
    public void saveProduce(Produce produce) throws SQLException;

    /**
     * @description 根据id查询produce
     * @param id
     * @return com.zhangle.produce.domain.produce.Market
     * @author zl52074
     * @time 2020/3/25 19:36
     */
    @Select("select * from produce where id = #{id}")
    public Market findProduceById(String id) throws SQLException;

    /**
     * @description 获取全部市场id
     * @param
     * @return java.util.List<java.lang.String>
     * @author zl52074
     * @time 2020/3/25 20:24
     */
    @Select("select id from produce_market")
    public List<String> findAllMarketId() throws SQLException;


    /**
     * @description 获取全部农产品id
     * @param
     * @return java.util.List<java.lang.String>
     * @author zl52074
     * @time 2020/3/25 20:24
     */
    @Select("select id from produce")
    public List<String> findAllProduceId() throws SQLException;

    /**
     * @description 保存marketProduce至数据库
     * @param marketProduce
     * @return void
     * @author zl52074
     * @time 2020/3/25 20:26
     */
    @Update("insert into market_produce values (#{id},#{market},#{produce})")
    public void saveMarketProduce(MarketProduce marketProduce) throws SQLException;

    /**
     * @description 根据id查询marketProduce
     * @param id
     * @return com.zhangle.produce.domain.produce.Market
     * @author zl52074
     * @time 2020/3/25 20:27
     */
    @Select("select * from market_produce where id = #{id}")
    public Market findMarketProduceById(String id) throws SQLException;

    /**
     * @description 查询全部marketProduce
     * @param
     * @return java.util.List<com.zhangle.produce.domain.produce.MarketProduce>
     * @author zl52074
     * @time 2020/3/26 20:50
     */
    @Select("select * from market_produce")
    public List<MarketProduce> findAllMarketProduce() throws SQLException;

    /**
     * @description 保存农产品价格
     * @param price
     * @return void
     * @author zl52074
     * @time 2020/3/26 21:04
     */
    @Update("insert into produce_price values (#{id},#{market_price_id},#{price},#{date})")
    public void savePrice(Price price) throws SQLException;

    /**
     * @description 根据market_price_id和date查询
     * @param
     * @return com.zhangle.produce.domain.produce.Price
     * @author zl52074
     * @time 2020/3/26 22:40
     */
    @Select("select * from produce_price where market_price_id = #{market_price_id} and date = #{date}")
    public Price findPriceByIdAndDate(Price price) throws SQLException;

    /**
     * @description 保存定时任务完成消息
     * @param msg
     * @return void
     * @author zl52074
     * @time 2020/3/27 12:52
     */
    @Update("insert into schedule_msg values (#{id},#{msg},#{time},#{change})")
    public void saveMsg(ScheduleMsg msg) throws SQLException;

    /**
     * @description 根据时间获取日志
     * @param time
     * @return java.util.List<com.zhangle.produce.domain.crawler.ScheduleMsg>
     * @author zl52074
     * @time 2020/3/29 16:40
     */
    @Select("select * from schedule_msg where time >  #{date}")
    public List<ScheduleMsg> findMsgByTime(String time) throws SQLException;

    /**
     * @description 获取价格表总记录数
     * @param
     * @return java.lang.Integer
     * @author zl52074
     * @time 2020/3/30 13:27
     */
    @Select("select count(*) from produce_price")
    public Integer getPriceCount() throws SQLException;

    /**
     * @description 获取总分类数
     * @param
     * @return java.lang.Integer
     * @author zl52074
     * @time 2020/3/30 13:30
     */
    @Select("select count(*) from produce_category")
    public Integer getCategoryCount() throws SQLException;

    /**
     * @description 获取农产品总数
     * @param
     * @return java.lang.Integer
     * @author zl52074
     * @time 2020/3/30 13:30
     */
    @Select("select count(*) from produce")
    public Integer getProduceCount() throws SQLException;

    /**
     * @description 获取市场总数
     * @param
     * @return java.lang.Integer
     * @author zl52074
     * @time 2020/3/30 13:30
     */
    @Select("select count(*) from produce_market")
    public Integer getMarketCount() throws SQLException;

    /**
     * @description 获取市场产品总数
     * @param
     * @return java.lang.Integer
     * @author zl52074
     * @time 2020/3/30 13:30
     */
    @Select("select count(*) from market_produce")
    public Integer getMarketProduceCount() throws SQLException;




}

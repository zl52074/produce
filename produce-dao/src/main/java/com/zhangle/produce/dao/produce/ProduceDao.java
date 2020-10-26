package com.zhangle.produce.dao.produce;

import com.zhangle.produce.domain.produce.*;
import com.zhangle.produce.domain.user.UserFavoriteTable;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Repository
public interface ProduceDao {

    /**
     * @description 根据条件刷动态查询priceTable
     * @param condition
     * @return java.util.List<com.zhangle.produce.domain.produce.PriceTable>
     * @author zl52074
     * @time 2020/3/30 22:01
     */
    @SelectProvider(type = PriceTableProvider.class,method = "findByCondition")
    public List<PriceTable> findPriceTableByCondition(PriceTableCondition condition) throws SQLException;
    /**
     * @description 根据条件获取总数据量
     * @param condition
     * @return int
     * @author zl52074
     * @time 2020/3/30 22:01
     */
    @SelectProvider(type = PriceTableProvider.class,method = "getCount")
    public int getPriceCount(PriceTableCondition condition) throws SQLException;

    /**
     * @description 查询全部农产品类别
     * @param
     * @return com.zhangle.produce.domain.produce.Category
     * @author zl52074
     * @time 2020/4/4 12:11
     */
    @Select("select * from produce_category")
    public List<Category> findAllCategory() throws SQLException;

    /**
     * @description 查询全部农产品市场
     * @param
     * @return com.zhangle.produce.domain.produce.Market
     * @author zl52074
     * @time 2020/4/4 12:12
     */
    @Select("select pm.* from produce_market pm inner join market_produce mp on pm.id = mp.market group by id")
    public List<Market> findAllMarket()throws SQLException;

    /**
     * @description 获取全部农产品
     * @param
     * @return java.util.List<com.zhangle.produce.domain.produce.Produce>
     * @author zl52074
     * @time 2020/4/4 13:01
     */
    @Select("select p.* from produce p inner join market_produce mp on p.id=mp.produce group by id ")
    public List<Produce> findAllProduce() throws SQLException;

    /**
     * @description 查询全部市场省份
     * @param
     * @return java.util.List<com.zhangle.produce.domain.produce.Province>
     * @author zl52074
     * @time 2020/4/4 22:16
     */
    @Select("select * from province")
    public List<Province> findAllProvince() throws SQLException;


    /**
     * @description 根据条件动态查询marketTable
     * @param condition
     * @return java.util.List<com.zhangle.produce.domain.produce.PriceTable>
     * @author zl52074
     * @time 2020/4/5 14:34
     */
    @SelectProvider(type = MarketTableProvider.class,method = "findByCondition")
    public List<MarketTable> findMarketTableByCondition(MarketTableCondition condition) throws SQLException;

    /**
     * @description 根据条件获取总数据量
     * @param condition
     * @return int
     * @author zl52074
     * @time 2020/4/5 14:34
     */
    @SelectProvider(type = MarketTableProvider.class,method = "getCount")
    public int getMarketCount(MarketTableCondition condition) throws SQLException;

    /**
     * @description 根据市场id获取市场下的产品分类
     * @param marketId
     * @return java.util.List<com.zhangle.produce.domain.produce.Category>
     * @author zl52074
     * @time 2020/4/5 19:13
     */
    @Select("select pc.* from market_produce mp inner join produce p on mp.produce = p.id " +
            "inner join produce_category pc on p.category = pc.id " +
            "where mp.market = #{marketId} group by pc.name order by pc.id")
    public List<Category> findMarketProduceCategory(String marketId) throws SQLException;

    /**
     * @description 根据市场id获取市场下的产品
     * @param marketId
     * @return java.util.List<com.zhangle.produce.domain.produce.Produce>
     * @author zl52074
     * @time 2020/4/5 19:20
     */
    @Select("select p.* from market_produce mp inner join produce p on mp.produce = p.id " +
            "inner join produce_category pc on p.category = pc.id where mp.market = #{marketId}  order by p.id")
    public List<Produce> findMarketProduce(String marketId) throws SQLException;

    /**
     * @description 根据id查询市场详情
     * @param marketId
     * @return com.zhangle.produce.domain.produce.Market
     * @author zl52074
     * @time 2020/4/5 19:49
     */
    @Select("select * from market_table where marketId = #{marketId}")
    public MarketTable findMarketById(String marketId) throws SQLException;


    /**
     * @description 根据marketProduceId和date查找priceTable
     * @param marketProduceId
     * @param date
     * @return com.zhangle.produce.domain.produce.PriceTable
     * @author zl52074
     * @time 2020/4/13 23:04
     */
    @Select("select * from price_table where marketProduceId = #{marketProduceId} and date = #{date}")
    public PriceTable findPriceTableByMarketProduceIdAndDate(@Param("marketProduceId")String marketProduceId, @Param("date")String date) throws SQLException;

    /**
     * @description 获取当前农产品当前日期的全部市场价格
     * @param produceId
     * @param date
     * @return java.util.List<java.lang.Integer>
     * @author zl52074
     * @time 2020/4/14 22:01
     */
    @Select("select price from price_table where produceId = #{produceId} and date = #{date}")
    public List<Double> getPriceByProduceIdAndDate(@Param("produceId")String produceId,@Param("date")String date) throws SQLException;

    /**
     * @description 获取当日价格最低的五条数据
     * @param produceId
     * @param date
     * @return java.util.List<com.zhangle.produce.domain.produce.PriceTable>
     * @author zl52074
     * @time 2020/4/14 23:19
     */
    @Select("select * from price_table where produceId = #{produceId} and date = #{date} order by price")
    public List<PriceTable> findPriceTableOrderByPrice(@Param("produceId")String produceId,@Param("date")String date) throws SQLException;

    /**
     * @description 根据marketProduceId获取价格历史
     * @param marketProduceId
     * @param startDate
     * @return java.util.List<com.zhangle.produce.domain.produce.PriceTable>
     * @author zl52074
     * @time 2020/4/16 17:33
     */
    @Select("select * from price_table where marketProduceId = #{marketProduceId} and date >= #{startDate} order by date")
    public List<PriceTable> findPriceTableByMarketProduceId(@Param("marketProduceId")String marketProduceId,@Param("startDate")String startDate) throws SQLException;

    /**
     * @description 根据session中的list查询
     * @param list
     * @param date
     * @return java.util.List<com.zhangle.produce.domain.produce.PriceTable>
     * @author zl52074
     * @time 2020/4/19 21:12
     */
    @SelectProvider(type = PriceTableProvider.class,method = "findByMarketProduceIdList")
    public List<PriceTable> findPriceTableByMarketProduceIdList(List<PriceTable> list,String date) throws SQLException;

    /**
     * @description 根据session中的list条件查询
     * @param condition
     * @param list
     * @return java.util.List<com.zhangle.produce.domain.produce.PriceTable>
     * @author zl52074
     * @time 2020/4/20 20:49
     */
    @SelectProvider(type = PriceTableProvider.class,method = "findByConditionInList")
    public List<PriceTable> findPriceTableByConditionInList(PriceTableCondition condition,List<PriceTable> list) throws SQLException;

    /**
     * @description 根据session中的list条件查询数量
     * @param condition
     * @param list
     * @return java.util.List<com.zhangle.produce.domain.produce.PriceTable>
     * @author zl52074
     * @time 2020/4/20 20:49
     */
    @SelectProvider(type = PriceTableProvider.class,method = "getCountByConditionInList")
    public int getCountByConditionInList(PriceTableCondition condition,List<PriceTable> list) throws SQLException;


    /**
     * @description 根据session中的list查询全部农产品
     * @param
     * @return java.util.List<com.zhangle.produce.domain.produce.Produce>
     * @author zl52074
     * @time 2020/4/20 22:02
     */
    @SelectProvider(type = PriceTableProvider.class,method = "findAllProduceInList")
    @Results(id = "1",value = {
            @Result(property = "id",column = "produceId"),
            @Result(property = "name",column = "produceName"),
            @Result(property = "category",column = "categoryId")
    })
    public List<Produce> findAllProduceInList(@Param("arg0")List list) throws SQLException;

    /**
     * @description 根据session中的list查询全部农产品类别
     * @param
     * @return java.util.List<com.zhangle.produce.domain.produce.Produce>
     * @author zl52074
     * @time 2020/4/20 22:02
     */
    @SelectProvider(type = PriceTableProvider.class,method = "findAllCategoryInList")
    @Results(id = "2",value = {
            @Result(property = "id",column = "categoryId"),
            @Result(property = "name",column = "categoryName")
    })
    public List<Category> findAllCategoryInList(@Param("arg0") List list) throws SQLException;

    /**
     * @description 根据session中的list查询全部农产品市场
     * @param list
     * @return java.util.List<com.zhangle.produce.domain.produce.Market>
     * @author zl52074
     * @time 2020/4/20 22:27
     */
    @SelectProvider(type = PriceTableProvider.class,method = "findAllMarketInList")
    @Results(id = "3",value = {
            @Result(property = "id",column = "marketId"),
            @Result(property = "name",column = "marketName")
    })
    public List<Market> findAllMarketInList(@Param("arg0")List list) throws SQLException;




    @SelectProvider(type = PriceTableProvider.class,method = "findFavoriteTableByCondition")
    public List<UserFavoriteTable> findFavoriteTableByCondition(PriceTableCondition condition,String userId) throws SQLException;

    @SelectProvider(type = PriceTableProvider.class,method = "getFavoriteCount")
    public int getFavoriteCount(PriceTableCondition condition,String userId) throws SQLException;


    @Select("select * from user_favorite_table where userId = #{userId} group by produceId")
    @ResultMap("1")
    public List<Produce> findAllFavoriteProduce(String userId) throws SQLException;

    @Select("select * from user_favorite_table where userId = #{userId} group by categoryId")
    @ResultMap("2")
    public List<Category> findAllFavoriteCategory(String userId) throws SQLException;

    @Select("select * from user_favorite_table where userId = #{userId} group by marketId")
    @ResultMap("3")
    public List<Market> findAllFavoriteMarket(String userId) throws SQLException;

    /**
     * @description 获取产品最近的更新日期
     * @param marketProduceId
     * @return java.lang.String
     * @author zl52074
     * @time 2020/4/23 20:43
     */
    @Select("select date from price_table where marketProduceId = #{marketProduceId} order by date desc limit 0,1")
    public String getLatestDateByMarketProduceId(String marketProduceId ) throws SQLException;
}



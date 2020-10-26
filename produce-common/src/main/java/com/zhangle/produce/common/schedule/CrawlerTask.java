package com.zhangle.produce.common.schedule;

import com.zhangle.produce.common.util.HttpUtils;
import com.zhangle.produce.domain.crawler.*;
import com.zhangle.produce.domain.produce.*;
import com.zhangle.produce.service.crawler.CrawlerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: 定时爬虫
 * @author: zl52074
 * @time: 2020/3/22 1:40
 */
@Component
public class CrawlerTask {
    @Autowired
    private HttpUtils httpUtils;
    @Autowired
    private CrawlerService crawlerService;

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    //主机地址
    private String baseUrl = "http://nc.mofcom.gov.cn";

    /**
     * @description 定时任务，启动爬虫
     * @param
     * @return void
     * @author zl52074
     * @time 2020/3/22 23:26
     */
    @Scheduled(cron="0 0 0,16 * * ?")//每天的0点16点各执行一次
//    @Scheduled(fixedDelay = 1000*3600*10)
    public void doTaskA() {
        try {
            int prePriceCount = crawlerService.getPriceCount();
            savePrice();
            int afterPriceCount = crawlerService.getPriceCount();
            ScheduleMsg msg = new ScheduleMsg();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日-HH:mm:ss");
            String time = formatter.format(System.currentTimeMillis());
            msg.setMsg(time + "-价格数据更新完毕");
            msg.setTime(new Date());
            msg.setChange("price:" + prePriceCount + "->" + afterPriceCount + " | price:" + (afterPriceCount - prePriceCount));
            crawlerService.saveMsg(msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron="0 0 2 ? * 7")//每周星期天凌晨2点执行一次
//    @Scheduled(fixedDelay = 1000*3600*10)
    public void doTaskB() {
        try {
            int preMarketCount = crawlerService.getMarketCount();
            int preCategoryCount = crawlerService.getCategoryCount();
            int preProduceCount = crawlerService.getProduceCount();
            int preMarketProduceCount = crawlerService.getMarketProduceCount();
            saveMarket();
            saveCategoryAndProduce();
            saveMarketProduce();
            int afterMarketCount = crawlerService.getMarketCount();
            int afterCategoryCount = crawlerService.getCategoryCount();
            int afterProduceCount = crawlerService.getProduceCount();
            int afterMarketProduceCount = crawlerService.getMarketProduceCount();
            ScheduleMsg msg = new ScheduleMsg();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日-HH:mm:ss");
            String time = formatter.format(System.currentTimeMillis());
            msg.setMsg("" + time + "-基础数据更新完毕");
            msg.setTime(new Date());
            msg.setChange("M:" + preMarketCount + "->" + afterMarketCount
                    + ";C:" + preCategoryCount + "->" + afterCategoryCount
                    + ";P:" + preProduceCount + "->" + afterProduceCount
                    + ";MP:" + preMarketProduceCount + "->" + afterMarketProduceCount
                    + " | M:" + (afterMarketCount - preMarketCount)
                    + ";C:" + (afterCategoryCount - preCategoryCount)
                    + ";P:" + (afterProduceCount - preProduceCount)
                    + ";MP:" + (afterMarketProduceCount - preMarketProduceCount));
            crawlerService.saveMsg(msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    /**
     * @description 爬虫获取总页数
     * @param url
     * @return int
     * @author zl52074
     * @time 2020/3/22 23:22
     */
    public int getPageCount(String url){
        int count = 0;
        String html = httpUtils.doGetHtml(url);
        // 正则表达式
        String regex = "v_PageCount\\s*=\\s*(?<count>\\d+)";
        // 创建 Pattern 对象
        Pattern pattern = Pattern.compile(regex);
        // 创建 matcher 对象
        Matcher match = pattern.matcher(html);
        if(match.find()){
            count = Integer.parseInt(match.group("count"));
        }else
            System.out.println("no match");
      return count;
    }


    /**
     * @description 抓取market全部信息并保存
     * @param
     * @return void
     * @author zl52074
     * @time 2020/3/22 23:22
     */
    public void saveMarket(){
        logger.info("开始抓取并更新market");
        int pageCount = 0;
        String marketUrl = "http://nc.mofcom.gov.cn/channel/jghq2017/market_list.shtml?page=";
        logger.info("抓取market列表页："+marketUrl);
        pageCount = getPageCount(marketUrl+1);
        for (int i = 1; i < pageCount+1; i++) {
            String html = httpUtils.doGetHtml(marketUrl+i);
            Document doc = Jsoup.parse(html);
            //获取marketlist下的li元素
            Elements marketEles = doc.select("ul.marketList > li");
            for(Element marketEle:marketEles){
                String regex = null;
                Pattern pattern = null;
                Matcher match = null;
                Market market = new Market();
                //获取market详情连接
                String url = marketEle.select("div > a").attr("href");
                //正则匹配id，获取并封装id
                regex = "id=(?<id>\\d+)";
                pattern = Pattern.compile(regex);
                match = pattern.matcher(url);
                if(match.find()){
                    market.setId(match.group("id"));
                }else
                    System.out.println("no match");
                //获取并封装name
                String name = marketEle.select("h1").text();
                market.setName(name);

                //获取产销类型和地址
                String typeAndAddress = marketEle.select("div.mt20").text();
                //正则匹配产销类型和地址，获取并封装address
                regex = "产销类型.\\s*(?<type>..)\\s*地址.\\s*(?<address>.*)";
                pattern = Pattern.compile(regex);
                match = pattern.matcher(typeAndAddress);
                if(match.find()){
                    market.setType(match.group("type"));
                    market.setAddress(match.group("address"));
                }else
                    System.out.println("no match");

                //获取并封装info
                market.setInfo(getMarketInfo(baseUrl+url));
                //持久化market
                try {
                    crawlerService.saveMarket(market);
                } catch (SQLException e) {
                    logger.debug("market保存失败",e.getMessage());
                    e.printStackTrace();

                }
            }
            logger.info("全部market信息保存完毕");
        }
    }

    /**
     * @description 抓取market详情页中的info
     * @param url
     * @return java.lang.String
     * @author zl52074
     * @time 2020/3/22 23:23
     */
    public String getMarketInfo(String url){
        String html = httpUtils.doGetHtml(url);
        Document doc = Jsoup.parse(html);
        Element infoEle = doc.select("div.bref-box").first();
        String info = infoEle.text();
        return info;
    }

    /**
     * @description 抓取农产品类别和农产品
     * @param
     * @return void
     * @author zl52074
     * @time 2020/3/25 20:15
     */
    public void saveCategoryAndProduce() {
        String baseProduceUrl = "http://nc.mofcom.gov.cn/nc/js/data/get_base_mark_list.jsp";
        logger.info("抓取分类和农产品：" + baseProduceUrl);
        String html = httpUtils.doGetHtml(baseProduceUrl);
        String regex = "(?<category>\\d+),'(?<name>.*?)',(?<id>\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(html);
        while (match.find()) {
            if (Integer.parseInt(match.group("category")) == 0) {
                Category category = new Category();
                category.setId(match.group("id"));
                category.setName(match.group("name"));
                try {
                    crawlerService.saveCategory(category);
                } catch (SQLException e) {
                    logger.debug("category保存失败", e.getMessage());
                    e.printStackTrace();
                }
            } else {
                Produce produce = new Produce();
                produce.setId(match.group("id"));
                produce.setName(match.group("name"));
                produce.setCategory(match.group("category"));
                try {
                    crawlerService.saveProduce(produce);
                } catch (SQLException e) {
                    logger.debug("produce保存失败", e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        logger.info("全部农产品和分类信息保存完毕");
    }

    /**
     * @description 保存农产品市场和农产品的对应关系
     * @param
     * @return void
     * @author zl52074
     * @time 2020/3/25 20:17
     */
    public void saveMarketProduce(){
        logger.info("开始抓取marketProduce");
        try {
            List<String> marketIds = crawlerService.findAllMarketId();
            List<String> produceIds = crawlerService.findAllProduceId();
            int i=0;
            for(String marketId:marketIds){
                System.out.println("开始抓取"+marketId);
                for(String produceId:produceIds){
                    String marketInfoUrl = "http://nc.mofcom.gov.cn/channel/jghq2017/market_detail.shtml?id="+marketId+"&craft_index="+produceId;
                    if(getPageCount(marketInfoUrl)!=0){
                        MarketProduce marketProduce = new MarketProduce();
                        marketProduce.setId("m"+marketId+"p"+produceId);
                        marketProduce.setMarket(marketId);
                        marketProduce.setProduce(produceId);
                        crawlerService.saveMarketProduce(marketProduce);
                    }
                }
                i=i+1;
                System.out.println(marketId+"已完成"+i+"/"+marketIds.size());
            }
            logger.info("抓取并保存marketProduce完成");
        } catch (SQLException e) {
            logger.debug("获取marketId,produceIds失败", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @description 抓取并保存农产品价格
     * @param
     * @return void
     * @author zl52074
     * @time 2020/3/26 21:00
     */
    public void savePrice(){
        try {
            List<MarketProduce> marketProduces = crawlerService.findAllMarketProduce();
            int i = 0;
            logger.info("开始抓取并更新价格");
            for(MarketProduce marketProduce:marketProduces){
                String marketId = marketProduce.getMarket();
                String produceId = marketProduce.getProduce();
                //获取当前时间-7天
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(System.currentTimeMillis()-1000*3600*24*7);
                String startDate = formatter.format(date);
                String marketInfoUrl = "http://nc.mofcom.gov.cn/channel/jghq2017/market_detail.shtml?id="+marketId+"&craft_index="+produceId+"&startTime="+startDate;
                String html = httpUtils.doGetHtml(marketInfoUrl);
                Matcher match = Pattern.compile("v_PageCount\\s*=\\s*(?<count>\\d+)").matcher(html);
                if(match.find()){
                    int count = Integer.parseInt(match.group("count"));
                    if(count!=0){
                        Document dom = Jsoup.parse(html);
                        Elements trEles = dom.select("table.table-01 > tbody > tr");
                        for(Element trEle:trEles){
                            if(!trEle.toString().contains("td")){
                                continue;
                            }else{
                                Elements tdEles = trEle.select("td");
                                Price price = new Price();
                                price.setMarket_price_id(marketProduce.getId());
                                price.setDate(tdEles.get(0).text());
                                price.setPrice(Double.parseDouble(tdEles.get(2).select("span").text()));
                                crawlerService.savePrice(price);
                            }
                        }
                    }
                }else{
                    System.out.println("no match");
                }
                i = i+1;
                System.out.println(marketProduce.getId()+"已完成"+i+"/"+marketProduces.size());
            }
            logger.info("抓取并更新价格已完成");
        } catch (SQLException e) {
            logger.debug("获取marketProduces失败", e.getMessage());
            e.printStackTrace();
        }
    }
}

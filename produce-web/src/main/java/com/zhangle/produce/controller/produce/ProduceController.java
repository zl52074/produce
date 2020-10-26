package com.zhangle.produce.controller.produce;

import com.zhangle.produce.common.util.UUIDUtils;
import com.zhangle.produce.domain.datatables.DataTables;
import com.zhangle.produce.domain.echarts.BarData;
import com.zhangle.produce.domain.echarts.LineData;
import com.zhangle.produce.domain.produce.*;
import com.zhangle.produce.domain.user.User;
import com.zhangle.produce.domain.user.UserFavorite;
import com.zhangle.produce.domain.user.UserFavoriteTable;
import com.zhangle.produce.service.produce.ProduceService;
import com.zhangle.produce.service.user.UserService;
import javafx.scene.chart.LineChart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: zl52074
 * @time: 2020/3/31 17:02
 */
@Controller
public class ProduceController {
    @Autowired
    private ProduceService produceService;
    @Autowired
    private UserService userService;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @description 提供datatables json数据
     * @param condition
     * @param request
     * @param response
     * @return com.zhangle.produce.domain.datatables.DataTables<com.zhangle.produce.domain.produce.PriceTable>
     * @author zl52074
     * @time 2020/4/4 12:16
     */
    @ResponseBody
    @RequestMapping("/getPriceData.do")
    public DataTables<PriceTable> findAll(PriceTableCondition condition, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        DataTables<PriceTable> table = new DataTables<PriceTable>();
        int count = produceService.getPriceCount(condition);
        List<PriceTable> PriceTable = produceService.findPriceTableByCondition(condition);
        table.setRecordsFiltered(count);
        table.setRecordsTotal(count);
        table.setData(PriceTable);
        return table;
    }

    /**
     * @description 返回priceTable页面
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @author zl52074
     * @time 2020/4/4 12:17
     */
    @RequestMapping("priceTable.do")
    public ModelAndView toPriceTablePage() throws SQLException {
        ModelAndView mv = new ModelAndView();
        mv.addObject("categories", produceService.findAllCategory());
        mv.addObject("markets", produceService.findAllMarket());
        mv.setViewName( "produce/priceTable");
        return mv;
    }

    /**
     * @description 获取全部农产品
     * @param
     * @return java.util.List<com.zhangle.produce.domain.produce.Produce>
     * @author zl52074
     * @time 2020/4/5 14:35
     */
    @RequestMapping("getAllProduce.do")
    @ResponseBody
    public List<Produce> getAllProduce(String marketId,boolean inList,boolean inFavorite, HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        if (marketId != null) {
            return produceService.findMarketProduce(marketId);
        }
        if(inList){
            if(session.getAttribute("showList")==null){
                return new ArrayList<Produce>();
            }else{
                List<PriceTable> showList = (List<PriceTable>) session.getAttribute("showList");
                return produceService.findAllProduceInList(showList);
            }

        }
        if(inFavorite){
            if(session.getAttribute("user")==null){
                return new ArrayList<Produce>();
            }else{
                User user = (User) session.getAttribute("user");
                return produceService.findAllFavoriteProduce(user.getId());
            }

        }
        return produceService.findAllProduce();
    }


    /**
     * @description 返回marketTable页面
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @author zl52074
     * @time 2020/4/5 14:36
     */
    @RequestMapping("marketTable.do")
    public ModelAndView toMarketTablePage() throws SQLException {
        ModelAndView mv = new ModelAndView();
        mv.addObject("provinces", produceService.findAllProvince());
        mv.setViewName( "produce/marketTable");
        return mv;
    }

    /**
     * @description marketTable数据
     * @param condition
     * @param request
     * @param response
     * @return com.zhangle.produce.domain.datatables.DataTables<com.zhangle.produce.domain.produce.MarketTable>
     * @author zl52074
     * @time 2020/4/5 16:18
     */
    @ResponseBody
    @RequestMapping("/getMarketData.do")
    public DataTables<MarketTable> getMarketData(MarketTableCondition condition, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        DataTables<MarketTable> table = new DataTables<MarketTable>();
        int count = produceService.getMarketCount(condition);
        List<MarketTable> MarketTable = produceService.findMarketTableByCondition(condition);
        table.setRecordsFiltered(count);
        table.setRecordsTotal(count);
        table.setData(MarketTable);
        return table;
    }

    /**
     * @description 返回市场详情页
     * @param marketId
     * @return org.springframework.web.servlet.ModelAndView
     * @author zl52074
     * @time 2020/4/5 19:51
     */
    @RequestMapping("marketProduceTable.do")
    public ModelAndView toMarketProduceTablePage(String from,String marketId) throws SQLException {
        ModelAndView mv = new ModelAndView();
        mv.addObject("from", from);
        mv.addObject("market", produceService.findMarketById(marketId));
        mv.addObject("marketId",marketId);
        mv.addObject("categories", produceService.findMarketProduceCategory(marketId));
        mv.setViewName( "produce/marketProduceTable");
        return mv;
    }

    /**
     * @description 返回产品详情页
     * @param from
     * @param p
     * @return org.springframework.web.servlet.ModelAndView
     * @author zl52074
     * @time 2020/4/13 22:33
     */
    @RequestMapping("produceInfo.do")
    public ModelAndView toProduceInfoPage(String from,PriceTable p,HttpServletRequest request) throws SQLException {
        if(p.getDate()==null){
            String latestDate = produceService.getLatestDateByMarketProduceId(p.getMarketProduceId());
            p.setDate(latestDate);
        }
        ModelAndView mv = new ModelAndView();
        PriceTable priceTable = produceService.findPriceTableByMarketProduceIdAndDate(p.getMarketProduceId(),p.getDate());
        List<Double> prices = produceService.getPriceByProduceIdAndDate(p.getProduceId(), p.getDate());
        double avg = 0;
        if(prices.size()!=0){
            double sum = 0;
            for(Double price:prices){
                sum = sum+price;
            }
            avg = sum/prices.size();
        }
        List<PriceTable> tables = produceService.findPriceTableOrderByPrice(p.getProduceId(),p.getDate());
        int index = 0;
        for(PriceTable table:tables){
            index = index +1;
            if(table.getMarketProduceId().equals(p.getMarketProduceId())){
                break;
            }
        }
        List<PriceTable> orders = new ArrayList<PriceTable>();
        if(tables.size()>=6){
            if(index<=6){
                orders = tables.subList(0, 6);
            }else{
                orders = tables.subList(0, 5);
            }
        }else{
            orders = tables;
        }

        boolean isShow = false;
        HttpSession session = request.getSession();
        List<PriceTable> list = new ArrayList<PriceTable>();
        List<PriceTable> shows = new ArrayList<PriceTable>();
        if(session.getAttribute("showList")!=null){
            List<PriceTable> showList = (List<PriceTable>) session.getAttribute("showList");
            for(PriceTable showTable:showList){
                if(showTable.getMarketProduceId().equals(p.getMarketProduceId())){
                    isShow = true;
                }
                if(showTable.getProduceId().equals(p.getProduceId())){
                    list.add(showTable);
                }

            }
            if(isShow){
                shows = produceService.findPriceTableByMarketProduceIdList(list, p.getDate());
            }else{
                list.add(p);
                shows = produceService.findPriceTableByMarketProduceIdList(list, p.getDate());
            }
        }
        if(session.getAttribute("showList")==null){
            shows.add(produceService.findPriceTableByMarketProduceIdAndDate(p.getMarketProduceId(),p.getDate()));
        }
        int showIndex = 0;
        for(PriceTable showTable:shows){
            showIndex = showIndex+1;
            if(showTable.getMarketProduceId().equals(p.getMarketProduceId())){
                break;
            }
        }
        boolean isFavorite = false;
        if(session.getAttribute("user")!=null){
            User user = (User) session.getAttribute("user");
            List<UserFavorite> userFavorites = userService.findUserFavoriteByUserId(user.getId());
            for(UserFavorite userFavorite :userFavorites){
                if(userFavorite.getMarketProduce().equals(p.getMarketProduceId())){
                    mv.addObject("favoriteId",userFavorite.getId());
                    isFavorite = true;
                }
            }
        }

        mv.addObject("targetProduce",priceTable);
        mv.addObject("from", from);
        mv.addObject("avg", avg);
        mv.addObject("index", index);
        mv.addObject("showIndex", showIndex);
        mv.addObject("orders", orders);
        mv.addObject("p", p);
        mv.addObject("isShow",isShow);
        mv.addObject("shows",shows);
        mv.addObject("isFavorite",isFavorite);
        mv.setViewName( "produce/produceInfo");
        return mv;
    }

    @ResponseBody
    @RequestMapping("getBarChartData.do")
    public List<BarData> getBarChartData(PriceTable p,String mode,HttpServletRequest request) throws SQLException {
        List<BarData> datas = new ArrayList<BarData>();
        List<PriceTable> dataTables = new ArrayList<PriceTable>();
        if(mode.equals("rank")){
            PriceTable priceTable = produceService.findPriceTableByMarketProduceIdAndDate(p.getMarketProduceId(),p.getDate());
            List<PriceTable> tables = produceService.findPriceTableOrderByPrice(p.getProduceId(),p.getDate());
            int index = 0;
            for(PriceTable table:tables){
                index = index +1;
                if(table.getMarketProduceId().equals(p.getMarketProduceId())){
                    break;
                }
            }
            if(tables.size()>=6){
                if(index<=6){
                    dataTables = tables.subList(0, 6);
                }else{
                    dataTables = tables.subList(0, 5);
                    dataTables.add(priceTable);
                }
            }else{
                dataTables = tables;
            }
        }else if(mode.equals("show")){
            boolean isShow = false;
            HttpSession session = request.getSession();
            List<PriceTable> list = new ArrayList<PriceTable>();
            if(session.getAttribute("showList")!=null){
                List<PriceTable> showList = (List<PriceTable>) session.getAttribute("showList");
                for(PriceTable showTable:showList){
                    if(showTable.getMarketProduceId().equals(p.getMarketProduceId())){
                        isShow = true;
                    }
                    if(showTable.getProduceId().equals(p.getProduceId())){
                        list.add(showTable);
                    }

                }
                if(isShow){
                    dataTables = produceService.findPriceTableByMarketProduceIdList(list, p.getDate());
                }else{
                    list.add(p);
                    dataTables = produceService.findPriceTableByMarketProduceIdList(list, p.getDate());
                }
            }
            if(session.getAttribute("showList")==null){
                dataTables.add(produceService.findPriceTableByMarketProduceIdAndDate(p.getMarketProduceId(),p.getDate()));
            }
        }

        for(PriceTable table:dataTables){
            datas.add(new BarData(table.getMarketName(),table.getPrice()));
        }
        return datas;
    }

    @ResponseBody
    @RequestMapping("getLineChartData.do")
    public List<LineData> getLineChartData(PriceTable p,String mode,HttpServletRequest request) throws SQLException {
        List<String> marketProduceIds = new ArrayList<>();
        if(mode.equals("rank")){
            PriceTable priceTable = produceService.findPriceTableByMarketProduceIdAndDate(p.getMarketProduceId(),p.getDate());
            List<PriceTable> tables = produceService.findPriceTableOrderByPrice(p.getProduceId(),p.getDate());
            int index = 0;
            for(PriceTable table:tables){
                index = index +1;
                if(table.getMarketProduceId().equals(p.getMarketProduceId())){
                    break;
                }
            }
            List<PriceTable> orders = new ArrayList<PriceTable>();
            if(tables.size()>=6){
                if(index<=6){
                    orders = tables.subList(0, 6);
                }else{
                    orders = tables.subList(0, 5);
                    orders.add(priceTable);
                }
            }else{
                orders = tables;
            }
            for(PriceTable order:orders){
                marketProduceIds.add(order.getMarketProduceId());
            }
        }else if(mode.equals("show")){
            boolean isShow = false;
            HttpSession session = request.getSession();
            List<PriceTable> list = new ArrayList<PriceTable>();
            List<PriceTable> shows = new ArrayList<PriceTable>();
            if(session.getAttribute("showList")!=null){
                List<PriceTable> showList = (List<PriceTable>) session.getAttribute("showList");
                for(PriceTable showTable:showList){
                    if(showTable.getMarketProduceId().equals(p.getMarketProduceId())){
                        isShow = true;
                    }
                    if(showTable.getProduceId().equals(p.getProduceId())){
                        list.add(showTable);
                    }

                }
                if(isShow){
                    shows = produceService.findPriceTableByMarketProduceIdList(list, p.getDate());
                }else{
                    list.add(p);
                    shows = produceService.findPriceTableByMarketProduceIdList(list, p.getDate());
                }
            }
            if(session.getAttribute("showList")==null){
                shows.add(produceService.findPriceTableByMarketProduceIdAndDate(p.getMarketProduceId(),p.getDate()));
            }
            for(PriceTable table:shows){
                marketProduceIds.add(table.getMarketProduceId());
            }
        }

        //以下开始处理lineChart数据
        List<LineData> datas = new ArrayList<LineData>();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        String startDate = formatter.format(System.currentTimeMillis()-1000L*3600*24*30);
        String[] color = {"#428bca","#530b65","#2f853d","#1a158f","#45454a","#085115"};
        int colorIndex = 0;
        for(String marketProduceId :marketProduceIds){
            List<PriceTable> priceTables = produceService.findPriceTableByMarketProduceId(marketProduceId,startDate);
            Map<String,String> priceMap = new LinkedHashMap<String,String>();
            for(PriceTable table:priceTables){
                priceMap.put(table.getDate(), table.getPrice().toString());
            }
            String[] priceArray = new String[30];
            for(int i=29;i>=0;i--){
                if(priceMap.get(formatter.format(System.currentTimeMillis()-1000L*3600*24*i))!=null){
                    priceArray[29-i] = priceMap.get(formatter.format(System.currentTimeMillis()-1000L*3600*24*i));
                }
            }
            if(marketProduceId.equals(p.getMarketProduceId())){
                LineData lineData = new LineData();
                lineData.setName(priceTables.get(0).getMarketName());
                lineData.setData(priceArray);
                lineData.setColor("#c23531");
                datas.add(lineData);
            }else{
                LineData lineData = new LineData();
                lineData.setName(priceTables.get(0).getMarketName());
                lineData.setData(priceArray);
                lineData.setColor(color[colorIndex]);
                datas.add(lineData);
            }
            colorIndex = colorIndex+1;
        }
        return datas;
    }

    /**
     * @description 添加showlist到session
     * @param opt
     * @param p
     * @param request
     * @return java.lang.String
     * @author zl52074
     * @time 2020/4/19 19:33
     */
    @ResponseBody
    @RequestMapping("showOpt.do")
    public String showOpt(String opt,PriceTable p,HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("showList")==null){
            List<PriceTable> showList = new ArrayList<PriceTable>();
            session.setAttribute("showList",showList);
        }
        if(opt.equals("add")){
            List<PriceTable> showList = (List<PriceTable>) session.getAttribute("showList");
            boolean exist = false;
            int count = 0;
            for(PriceTable showTable:showList){
                if(showTable.getProduceId().equals(p.getProduceId())){
                    count++;
                }
                if(showTable.getMarketProduceId().equals(p.getMarketProduceId())){
                    exist = true;
                }
            }
            if(exist){
                return "exist";
            }else{
                if(count>=5){
                    return "overflow";
                }else{
                    showList.add(p);
                    session.setAttribute("showList",showList);
                    return "success";
                }
            }

        }else if(opt.equals("delete")){
            List<PriceTable> showList = (List<PriceTable>) session.getAttribute("showList");
            for(PriceTable showTable:showList){
                if(showTable.getMarketProduceId().equals(p.getMarketProduceId())){
                    showList.remove(showTable);
                }
            }
            return "success";
        }
        else {
            return "";
        }
    }

    @RequestMapping("showListTable.do")
    public ModelAndView toShowListTablePage(HttpServletRequest request) throws SQLException {
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        if(session.getAttribute("showList")==null){
            mv.addObject("categories",new ArrayList<Category>());
            mv.addObject("markets",new ArrayList<Market>());
        }else{
            List<PriceTable> showList = (List<PriceTable>) session.getAttribute("showList");
            mv.addObject("categories",produceService.findAllCategoryInList(showList));
            mv.addObject("markets",produceService.findAllMarketInList(showList));

        }
        mv.setViewName( "produce/showListTable");
        return mv;
    }


    @ResponseBody
    @RequestMapping("getShowListData.do")
    public DataTables<PriceTable> getShowListData(PriceTableCondition condition, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        DataTables<PriceTable> table = new DataTables<PriceTable>();
        int count = 0;
        if(session.getAttribute("showList")==null){
            table.setRecordsFiltered(count);
            table.setRecordsTotal(count);
            table.setData(new ArrayList<PriceTable>());
            return table;
        }else{
            List<PriceTable> showList = (List<PriceTable>) session.getAttribute("showList");
            count = produceService.getCountByConditionInList(condition, showList);
            List<PriceTable> PriceTable = produceService.findPriceTableByConditionInList(condition, showList);
            table.setRecordsFiltered(count);
            table.setRecordsTotal(count);
            table.setData(PriceTable);
            return table;
        }
    }


    @ResponseBody
    @RequestMapping("favoriteOpt.do")
    public String favoriteOpt(String opt,PriceTable p,String favoriteId,HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user")==null){
            return "none";
        }else{
            if(opt.equals("add")){

                User user = (User) session.getAttribute("user");
                UserFavorite userFavorite = new UserFavorite();
                userFavorite.setId(UUIDUtils.getUUID());
                userFavorite.setUser(user.getId());
                userFavorite.setMarketProduce(p.getMarketProduceId());
                if(userService.findUserFavoriteByUserIdAndMarketProduce(userFavorite)==null){
                    userService.saveFavorite(userFavorite);
                    return "success";
                }else{
                    return "exist";
                }

            }else if(opt.equals("delete")){
                userService.deleteUserFavorite(favoriteId);
                return "success";
            }else{
                return "";
            }

        }
    }


    @RequestMapping("favoriteListTable.do")
    public ModelAndView toFavoriteListTablePage(HttpServletRequest request) throws SQLException {
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        if(session.getAttribute("user")==null){
            mv.addObject("categories",new ArrayList<Category>());
            mv.addObject("markets",new ArrayList<Market>());
        }else{
            User user = (User) session.getAttribute("user");
            mv.addObject("categories",produceService.findAllFavoriteCategory(user.getId()));
            mv.addObject("markets",produceService.findAllFavoriteMarket(user.getId()));

        }
        mv.setViewName( "produce/favoriteListTable");
        return mv;
    }


    @ResponseBody
    @RequestMapping("getFavoriteListData.do")
    public DataTables<UserFavoriteTable> getFavoriteListData(PriceTableCondition condition, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        DataTables<UserFavoriteTable> table = new DataTables<UserFavoriteTable>();
        int count = 0;
        if(session.getAttribute("user")==null){
            table.setRecordsFiltered(count);
            table.setRecordsTotal(count);
            table.setData(new ArrayList<UserFavoriteTable>());
            return table;
        }else{
            User user = (User) session.getAttribute("user");
            String userId = user.getId();
            List<UserFavoriteTable> favoriteTables = produceService.findFavoriteTableByCondition(condition,userId);
            count =produceService.getFavoriteCount(condition,userId);
            table.setRecordsFiltered(count);
            table.setRecordsTotal(count);
            table.setData(favoriteTables);
            return table;
        }
    }


}

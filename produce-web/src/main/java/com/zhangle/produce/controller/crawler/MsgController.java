package com.zhangle.produce.controller.crawler;

import com.zhangle.produce.domain.crawler.ScheduleMsg;
import com.zhangle.produce.service.crawler.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: zl52074
 * @time: 2020/3/29 16:42
 */
@Controller
public class MsgController {
    @Autowired
    private CrawlerService crawlerService;

    @RequestMapping("msg.do")
    public ModelAndView showMsg(HttpServletRequest request) throws SQLException {
        ModelAndView mv = new ModelAndView();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        List<ScheduleMsg> msgs = crawlerService.findMsgByTime(formatter.format(System.currentTimeMillis()));
        List<ScheduleMsg> allMsgs = crawlerService.findMsgByTime("1970-01-01");
        int priceCount = crawlerService.getPriceCount();
        int marketCount = crawlerService.getMarketCount();
        int categoryCount = crawlerService.getCategoryCount();
        int produceCount = crawlerService.getProduceCount();
        int marketProduceCount = crawlerService.getMarketProduceCount();

        mv.addObject("date",formatter.format(System.currentTimeMillis()));
        mv.addObject("msgs", msgs);
        mv.addObject("allMsgs", allMsgs);
        mv.addObject("priceCount", priceCount);
        mv.addObject("marketCount", marketCount);
        mv.addObject("categoryCount", categoryCount);
        mv.addObject("produceCount", produceCount);
        mv.addObject("marketProduceCount", marketProduceCount);
        mv.setViewName("crawler/msg");
        return mv;
    }

    @RequestMapping("/user/{username}")
    @ResponseBody
    public Map<String, String> hello(@PathVariable("username") String username){
        HashMap<String,String> result = new HashMap<>();
        result.put("result", "hello "+username);
        return result;
    }
}

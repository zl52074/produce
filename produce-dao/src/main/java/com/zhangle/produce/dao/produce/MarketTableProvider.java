package com.zhangle.produce.dao.produce;

import com.zhangle.produce.domain.produce.MarketTableCondition;
import com.zhangle.produce.domain.produce.PriceTableCondition;

/**
 * @description:
 * @author: zl52074
 * @time: 2020/4/5 14:25
 */
public class MarketTableProvider {
    public String findByCondition(MarketTableCondition condition){
        StringBuffer sql=new StringBuffer("SELECT * FROM market_table WHERE 1=1 ");
        if (condition.getProvinceId()!=null){
            sql.append("AND provinceId='"+condition.getProvinceId()+"' ");
        }
        if (condition.getMarketType()!=null){
            sql.append(" AND marketType='"+condition.getMarketType()+"' ");
        }
        if(condition.getKeyWord()!=null&&!condition.getKeyWord().equals("")){
            sql.append(" AND marketName LIKE'%"+condition.getKeyWord()+"%'");
        }

        sql.append("LIMIT "+condition.getStart()+","+condition.getLength());

        return sql.toString();
    }

    public String getCount(MarketTableCondition condition){
        StringBuffer sql=new StringBuffer("SELECT COUNT(*) FROM market_table WHERE 1=1 ");
        if (condition.getProvinceId()!=null){
            sql.append("AND provinceId='"+condition.getProvinceId()+"' ");
        }
        if (condition.getMarketType()!=null){
            sql.append(" AND marketType='"+condition.getMarketType()+"' ");
        }
        if(condition.getKeyWord()!=null&&!condition.getKeyWord().equals("")){
            sql.append(" AND marketName LIKE'%"+condition.getKeyWord()+"%'");
        }

        return sql.toString();
    }
}

package com.zhangle.produce.dao.produce;

import com.zhangle.produce.domain.produce.PriceTable;
import com.zhangle.produce.domain.produce.PriceTableCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: zl52074
 * @time: 2020/3/30 22:01
 */
public class PriceTableProvider {

    /**
     * @description 动态拼写条件sql
     * @param condition
     * @return java.lang.String
     * @author zl52074
     * @time 2020/3/30 22:16
     */
    public String findByCondition(PriceTableCondition condition){
        StringBuffer sql=new StringBuffer("SELECT * FROM price_table WHERE 1=1 ");
        if (condition.getCategoryId()!=null){
            sql.append("AND categoryId='"+condition.getCategoryId()+"' ");
        }
        if (condition.getProduceId()!=null){
            sql.append(" AND produceId='"+condition.getProduceId()+"' ");
        }

        if (condition.getMarketId()!=null){
            sql.append(" AND marketId='"+condition.getMarketId()+"' ");
        }

        if (condition.getStartDate()!=null){
            sql.append(" AND date >='"+condition.getStartDate()+"' ");
        }

        if (condition.getEndDate()!=null){
            sql.append(" AND date <='"+condition.getEndDate()+"' ");
        }

        if(condition.getKeyWord()!=null&&!condition.getKeyWord().equals("")){
            sql.append(" AND produceName LIKE'%"+condition.getKeyWord()+"%' ");
            sql.append("OR marketName LIKE '%"+condition.getKeyWord()+"%' ");
            if (condition.getCategoryId()!=null){
                sql.append("AND categoryId='"+condition.getCategoryId()+"' ");
            }
            if (condition.getProduceId()!=null){
                sql.append(" AND produceId='"+condition.getProduceId()+"' ");
            }

            if (condition.getMarketId()!=null){
                sql.append(" AND marketId='"+condition.getMarketId()+"' ");
            }

            if (condition.getStartDate()!=null){
                sql.append(" AND date >='"+condition.getStartDate()+"' ");
            }

            if (condition.getEndDate()!=null){
                sql.append(" AND date <='"+condition.getEndDate()+"' ");
            }
        }

        if(condition.getProduceName()!=null&&!condition.getProduceName().equals("")){
            sql.append(" AND produceName LIKE'%"+condition.getProduceName()+"%'");
        }

        sql.append("ORDER BY date DESC,produceId LIMIT "+condition.getStart()+","+condition.getLength());

        return sql.toString();
    }

    public String getCount(PriceTableCondition condition){
        StringBuffer sql=new StringBuffer("SELECT COUNT(*) FROM price_table WHERE 1=1 ");
        if (condition.getCategoryId()!=null){
            sql.append("AND categoryId='"+condition.getCategoryId()+"' ");
        }
        if (condition.getProduceId()!=null){
            sql.append(" AND produceId='"+condition.getProduceId()+"' ");
        }

        if (condition.getMarketId()!=null){
            sql.append(" AND marketId='"+condition.getMarketId()+"' ");
        }

        if (condition.getStartDate()!=null){
            sql.append(" AND date >='"+condition.getStartDate()+"' ");
        }

        if (condition.getEndDate()!=null){
            sql.append(" AND date <='"+condition.getEndDate()+"' ");
        }

        if(condition.getKeyWord()!=null&&!condition.getKeyWord().equals("")){
            sql.append(" AND produceName LIKE'%"+condition.getKeyWord()+"%' ");
            sql.append("OR marketName LIKE '%"+condition.getKeyWord()+"%' ");
            if (condition.getCategoryId()!=null){
                sql.append("AND categoryId='"+condition.getCategoryId()+"' ");
            }
            if (condition.getProduceId()!=null){
                sql.append(" AND produceId='"+condition.getProduceId()+"' ");
            }

            if (condition.getMarketId()!=null){
                sql.append(" AND marketId='"+condition.getMarketId()+"' ");
            }

            if (condition.getStartDate()!=null){
                sql.append(" AND date >='"+condition.getStartDate()+"' ");
            }

            if (condition.getEndDate()!=null){
                sql.append(" AND date <='"+condition.getEndDate()+"' ");
            }
        }

        if(condition.getProduceName()!=null&&!condition.getProduceName().equals("")){
            sql.append(" AND produceName LIKE'%"+condition.getProduceName()+"%'");
        }

        return sql.toString();
    }


    public String findByMarketProduceIdList(List<PriceTable> list,String date){
        StringBuffer sql=new StringBuffer("SELECT * FROM price_table WHERE marketProduceId in ( ");
        for(PriceTable table :list){
            sql.append(" '"+table.getMarketProduceId()+"', ");
        }
        sql.append(" '0') AND date = '"+date+"' ORDER BY price");
        return sql.toString();
    }

    public String findByConditionInList(PriceTableCondition condition,List<PriceTable> list){
        StringBuffer sql=new StringBuffer("SELECT * FROM market_produce_table WHERE marketProduceId in ( ");
        for(PriceTable table :list){
            sql.append(" '"+table.getMarketProduceId()+"', ");
        }
        sql.append(" '0') ");
        if (condition.getCategoryId()!=null){
            sql.append("AND categoryId='"+condition.getCategoryId()+"' ");
        }
        if (condition.getProduceId()!=null){
            sql.append(" AND produceId='"+condition.getProduceId()+"' ");
        }

        if (condition.getMarketId()!=null){
            sql.append(" AND marketId='"+condition.getMarketId()+"' ");
        }

        if(condition.getKeyWord()!=null&&!condition.getKeyWord().equals("")){
            sql.append(" AND produceName LIKE'%"+condition.getKeyWord()+"%' ");
            sql.append("OR marketName LIKE '%"+condition.getKeyWord()+"%' ");
            if (condition.getCategoryId()!=null){
                sql.append("AND categoryId='"+condition.getCategoryId()+"' ");
            }
            if (condition.getProduceId()!=null){
                sql.append(" AND produceId='"+condition.getProduceId()+"' ");
            }

            if (condition.getMarketId()!=null){
                sql.append(" AND marketId='"+condition.getMarketId()+"' ");
            }
            sql.append(" AND marketProduceId in ( ");
            for(PriceTable table :list){
                sql.append(" '"+table.getMarketProduceId()+"', ");
            }
            sql.append(" '0') ");

        }
        sql.append(" LIMIT "+condition.getStart()+","+condition.getLength());

        return sql.toString();
    }

    public String getCountByConditionInList(PriceTableCondition condition,List<PriceTable> list){
        StringBuffer sql=new StringBuffer("SELECT COUNT(*) from market_produce_table WHERE marketProduceId in ( ");
        for(PriceTable table :list){
            sql.append(" '"+table.getMarketProduceId()+"', ");
        }
        sql.append(" '0') ");
        if (condition.getCategoryId()!=null){
            sql.append(" AND categoryId='"+condition.getCategoryId()+"' ");
        }
        if (condition.getProduceId()!=null){
            sql.append(" AND produceId='"+condition.getProduceId()+"' ");
        }

        if (condition.getMarketId()!=null){
            sql.append(" AND marketId='"+condition.getMarketId()+"' ");
        }

        if(condition.getKeyWord()!=null&&!condition.getKeyWord().equals("")){
            sql.append(" AND produceName LIKE'%"+condition.getKeyWord()+"%' ");
            sql.append("OR marketName LIKE '%"+condition.getKeyWord()+"%' ");
            if (condition.getCategoryId()!=null){
                sql.append("AND categoryId='"+condition.getCategoryId()+"' ");
            }
            if (condition.getProduceId()!=null){
                sql.append(" AND produceId='"+condition.getProduceId()+"' ");
            }

            if (condition.getMarketId()!=null){
                sql.append(" AND marketId='"+condition.getMarketId()+"' ");
            }
            sql.append(" AND marketProduceId in ( ");
            for(PriceTable table :list){
                sql.append(" '"+table.getMarketProduceId()+"', ");
            }
            sql.append(" '0') ");
        }

        return sql.toString();
    }

    public String findAllProduceInList(List<PriceTable> list){
        StringBuffer sql=new StringBuffer("SELECT * FROM market_produce_table WHERE marketProduceId in ( ");
        for(PriceTable table :list){
            sql.append(" '"+table.getMarketProduceId()+"', ");
        }
        sql.append(" '0') GROUP BY produceId");
        return sql.toString();
    }

    public String findAllCategoryInList(List<PriceTable> list){
        StringBuffer sql=new StringBuffer("SELECT * FROM market_produce_table WHERE marketProduceId in ( ");
        for(PriceTable table :list){
            sql.append(" '"+table.getMarketProduceId()+"', ");
        }
        sql.append(" '0') GROUP BY categoryId");
        return sql.toString();
    }

    public String findAllMarketInList(List<PriceTable> list){
        StringBuffer sql=new StringBuffer("SELECT * FROM market_produce_table WHERE marketProduceId in ( ");
        for(PriceTable table :list){
            sql.append(" '"+table.getMarketProduceId()+"', ");
        }
        sql.append(" '0') GROUP BY marketId");
        return sql.toString();
    }


    public String findFavoriteTableByCondition(PriceTableCondition condition,String userId){
        StringBuffer sql=new StringBuffer("SELECT * FROM user_favorite_table WHERE userId= '"+userId+"' ");
        if (condition.getCategoryId()!=null){
            sql.append("AND categoryId='"+condition.getCategoryId()+"' ");
        }
        if (condition.getProduceId()!=null){
            sql.append(" AND produceId='"+condition.getProduceId()+"' ");
        }

        if (condition.getMarketId()!=null){
            sql.append(" AND marketId='"+condition.getMarketId()+"' ");
        }

        if(condition.getKeyWord()!=null&&!condition.getKeyWord().equals("")){
            sql.append(" AND produceName LIKE'%"+condition.getKeyWord()+"%' ");
            sql.append("OR marketName LIKE '%"+condition.getKeyWord()+"%' ");
            if (condition.getCategoryId()!=null){
                sql.append("AND categoryId='"+condition.getCategoryId()+"' ");
            }
            if (condition.getProduceId()!=null){
                sql.append(" AND produceId='"+condition.getProduceId()+"' ");
            }

            if (condition.getMarketId()!=null){
                sql.append(" AND marketId='"+condition.getMarketId()+"' ");
            }

        }

        if(condition.getProduceName()!=null&&!condition.getProduceName().equals("")){
            sql.append(" AND produceName LIKE'%"+condition.getProduceName()+"%'");
        }

        sql.append("LIMIT "+condition.getStart()+","+condition.getLength());

        return sql.toString();
    }
    public String getFavoriteCount(PriceTableCondition condition,String userId){
        StringBuffer sql=new StringBuffer("SELECT COUNT(*) FROM user_favorite_table WHERE userId='"+userId+"' ");
        if (condition.getCategoryId()!=null){
            sql.append("AND categoryId='"+condition.getCategoryId()+"' ");
        }
        if (condition.getProduceId()!=null){
            sql.append(" AND produceId='"+condition.getProduceId()+"' ");
        }

        if (condition.getMarketId()!=null){
            sql.append(" AND marketId='"+condition.getMarketId()+"' ");
        }

        if(condition.getKeyWord()!=null&&!condition.getKeyWord().equals("")){
            sql.append(" AND produceName LIKE'%"+condition.getKeyWord()+"%' ");
            sql.append("OR marketName LIKE '%"+condition.getKeyWord()+"%' ");
            if (condition.getCategoryId()!=null){
                sql.append("AND categoryId='"+condition.getCategoryId()+"' ");
            }
            if (condition.getProduceId()!=null){
                sql.append(" AND produceId='"+condition.getProduceId()+"' ");
            }

            if (condition.getMarketId()!=null){
                sql.append(" AND marketId='"+condition.getMarketId()+"' ");
            }

        }

        if(condition.getProduceName()!=null&&!condition.getProduceName().equals("")){
            sql.append(" AND produceName LIKE'%"+condition.getProduceName()+"%'");
        }

        return sql.toString();
    }


}

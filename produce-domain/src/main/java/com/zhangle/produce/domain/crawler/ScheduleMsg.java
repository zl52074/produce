package com.zhangle.produce.domain.crawler;

import java.util.Date;

/**
 * @description:
 * @author: zl52074
 * @time: 2020/3/27 12:33
 */
public class ScheduleMsg {
    private Integer id;
    private String msg;
    private Date time;
    private String change;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }
}
